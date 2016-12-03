/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Dao.Events;
import Dao.Item;
import Dao.User;
import Ratings.ModelAPI;
import java.util.ArrayList;

/**
 *
 * @author bogdan
 */
public class BaseLinePredictor implements RecommenderAlgorithms{
    ModelAPI accessModelApi;
    
    
    public BaseLinePredictor(ModelAPI mapi){
        this.accessModelApi=mapi;
    }
    
    public void setModelApi(ModelAPI modelapi){
        this.accessModelApi=modelapi;
    }
    
    
    
    @Override
    public ArrayList<Item> top10Recomendation(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double predict(User u, Item i,ModelAPI modelapi) {
        double res=-1; 
        res=accessModelApi.getRatingOfSimilarItemUserVoted(i, u);
        
        //System.out.println("res ="+res);
        //user has not voted item i 
        if(res==-99){
            res=globalMeanRating()+getStandardDeviationForUser(u)+getStandardDeviationForItem(i);
            
            return res;
        }else{
            //item voted
           return -2; 
        } 
    }
    
    
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
