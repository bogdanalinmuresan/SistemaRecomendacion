/* 
 * Copyright (C) 2016 Bogdan Alin Muresan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Algorithms;

import Dao.Events;
import Dao.Item;
import Dao.User;
import Ratings.ModelAPI;
import java.util.ArrayList;

/**
 *Clase para el calculo de la prediccion de una calificación mediante la función baseline
 * @author bogdan
 *@version 1.0 
 */
public class BaseLinePredictor implements RecommenderAlgorithms{
    ModelAPI accessModelApi;
    
    /**
     * Constructor con párametros
     * @param mapi 
     */
    public BaseLinePredictor(ModelAPI mapi){
        this.accessModelApi=mapi;
    }
    
    /**
     * Método selector
     * @param modelapi nuevo modelo de datos
     */
    public void setAccessModelApi(ModelAPI modelapi){
        this.accessModelApi=modelapi;
    }
    
    /**
     * Método selector 
     * @return el modelo de datos
     */
    public ModelAPI getAccessModelApi(){
        return accessModelApi;
    }
    
    
    /**
     * Método que devuelve recomendaciones
     * @param u usuario
     * @param n número de recomendaciones a devolver
     * @return lista con elementos recomendados para el usuario
     */
    @Override
    public ArrayList<Item> topNRecomendation(User u,int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Método que predice la calificación para un usuario y un elemento
     * @param u usuario
     * @param i elemento
     * @return la prediccion ,0 si el usuario ha votado el elemento i
     */
    @Override
    public double predict(User u, Item i) {
        double res=0; 
        res=accessModelApi.getRatingOfSimilarItemUserVoted(i, u);
        //user has not voted item i 
        if(res==-99){
            res=globalMeanRating()+getStandardDeviationForUser(u)+getStandardDeviationForItem(i);
            
            return res;
        }else{
            //item voted
           return res; 
        } 
    }
    
    /**
     * Devuelve la media global de las calificaciones
     * @return la media global
     */
    public double globalMeanRating(){
        double res=0;
        
        double tam=accessModelApi.getEvents().size();
        for(Events e:accessModelApi.getEvents()){
            res+=e.getRating();
        }
       double resultado=0;
       resultado=res/tam;
       
       if(Double.isNaN(resultado))
           return resultado=0;
       else{
           //System.out.println("global mean rating "+resultado);
            return resultado;
       }
    }
    
    /**
     * Método para calcular la calificación media para un usuario
     * @param u usuario
     * @return  la media ,0 si el usuario no ha votado todavía
     */
    public double getAverageRatingForUser(User u){
        double avg=0;
        double res=0;
        double itemUserVoted=0;
        //items user rated
        for(Events e:accessModelApi.getEvents()){
            if(u.getUserId()==e.getUser().getUserId()){
                res+=e.getRating();
                itemUserVoted++;
            }
        }
        avg=res/itemUserVoted;
        if(Double.isNaN(avg))
            return 0.0;
        else
            return avg;
    }
    
    /**
     *
     * @param i
     * @return
     */
    public double getAverageRatingForItem(Item i){
        double avg=0.0;
        double res=0.0;
        int userVotedItem = 0;
        for(Events e:accessModelApi.getEvents()){
            if((i.getId()==e.getItem().getId()) ){
                res=res+e.getRating();
                userVotedItem++;
            }
        } 
        avg=res/userVotedItem;
        if(Double.isNaN(avg))
            return 0.0;
        else
            return avg;
    }
    
    /**
     * Método para el cálculo de la desviación estándard para un usuario
     * @param u usuario
     * @return la desviación estándard,0 si el usuario no tiene calificaciones
     */
    public double getStandardDeviationForUser(User u ){
        double avg=getAverageRatingForUser(u);
        //System.out.println("avg stardad deviation for user"+avg);
        double sd=0;
        int tam=0;
        //movies seen by user 
        for(Events e:accessModelApi.getEvents()){
            if(u.getUserId()==e.getUser().getUserId()){
               sd+=Math.pow((e.getRating()-avg), 2);
                tam++;
            }
        }
        //System.out.println("standard deviation for user "+(sd/tam-1));
        if(tam==1)
            if(Double.isNaN(sd))
                return 0;
            else{
                //System.out.println(" tam == 1 standard deviation for user "+Math.sqrt(sd));
                return Math.sqrt(sd);
            }
        else
            if(Double.isNaN(sd/tam-1))
                return 0;
            else{
                if(Double.isNaN(Math.sqrt(sd/tam-1))){
                    //System.out.println(" tam =! 1 standard deviation for user "+Math.sqrt(sd/tam-1));
                    return 0;
                }
                else{
                    //System.out.println(" tam =! 1 standard deviation for user "+Math.sqrt(sd/tam-1));
                    return Math.sqrt(sd/tam-1);
                }
            }
    }
    
    /**
     * Método para el cálculo de la desviación estandard de un elemento
     * @param i elemento
     * @return la desviación estandard,0 si el elemento no tiene calificaciones
     */
    public double getStandardDeviationForItem(Item i){
        double avg=getAverageRatingForItem(i);
        double tam=0;
        double sd=0;
        
        for(Events e:accessModelApi.getEvents()){
            if(i.getId()==e.getItem().getId()){
                sd+=Math.pow((e.getRating()-avg), 2);
                tam++;
            }
        }
        
        if(tam==1)
            if(Double.isNaN(sd))
                return 0;
            else{
                //System.out.println("tam ==1 standard deviation for item "+Math.sqrt(sd));
                return Math.sqrt(sd);
            }
        else
            if(Double.isNaN(sd/tam-1))
                return 0;
            else
                if(Double.isNaN(Math.sqrt(sd/tam-1))){
                   return 0; 
                }else{
                    //System.out.println("tam != 1 standard deviation for item "+Math.sqrt(sd/tam-1));
                    return Math.sqrt(sd/tam-1);
                   
                }
    
    }

    
    
}//end class
