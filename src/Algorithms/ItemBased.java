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
import java.util.ArrayList;
import Dao.Item;
import Dao.Pair;
import Dao.User;
import Ratings.*;

/**
 *Clase que implementa el algoritmo item-based
 * @author bogdan
 * @version 1.0
 */
public class ItemBased extends ColaborativeFiltering{
    ModelAPI mapi;
    ScoreAPI measureapi;

    /**
     * Constructor por defecto
     */
    public ItemBased() {
        mapi=new ModelAPI();
        measureapi=new ScoreAPI(mapi);
    }
    
    /**
     * Constructor con parámetros
     * @param measureapi medida de calificación
     * @see ScoreAPI
     * @param modelapi acceso a los datos del modelo
     * @see ModelAPI
     */
    public ItemBased(ScoreAPI measureapi,ModelAPI modelapi){
        this.measureapi=measureapi;
        this.mapi=modelapi;
    }
    
  
    /**
     * Método que devuelve las n recoemndaciones de un usuario
     * @param u usuario
     * @param n número de recomendaciones
     * @return  lista de las recomendaciones
     */
    @Override
    public ArrayList<Item> topNRecomendation(User u,int n) {
        ArrayList<Item> res=new ArrayList<>();
        //10 items ,user rate ,and the the 10 most similar items 
        //first get the items user rate
        ArrayList<Events> userEvents;
        userEvents=mapi.getUserEvent().get(u);
        if(userEvents==null)
            System.out.println("es null user events");
            
        
        //mapi.getSimilarItems(eventos.getItem()).get(1);
        //get the most simiar items that user has rated
        for(int i=0;i<userEvents.size();i++){
            //get elements user has rated
            Item item=new Item(userEvents.get(i).getItem());
            //System.out.println("item id"+item.getId());
            if(mapi.getSimilarItems(item)!=null ){
                //System.out.println("es null get Similar Item");
                //get the most similar items only the most 
                if(mapi.getSimilarItems(item).size()>0 ){
                    Pair mostSimilarItem=new Pair(mapi.getSimilarItems(item).get(0));
                    Item itemrecomed=new Item(mostSimilarItem.getItem1());
                    res.add(itemrecomed);
                }
            }
            
        }//for
        if(res.size()>n){
            ArrayList<Item> top10=new ArrayList<>();
            for(int i=0;i<n;i++){
                Item top=new Item(res.get(i));
                top10.add(top);
            }
            
            return  top10;
        }else{
            
            return res;
        }
        
        
    }

    /**
     * Método que devuelve la predicción entre un usuario y un elemento
     * @param u user 
     * @param ite item
     * @return la predicción ,0 si el usuario ha votado el elemento ite
     */
    @Override
    public double predict(User u, Item ite) {
        double res=0;
        //scoremeasure=new WeightSum(u, ite, mapi);
        res=measureapi.score(u, ite);
        //scoremeasure.score(u, ite);
        
    return res;
    }

}
