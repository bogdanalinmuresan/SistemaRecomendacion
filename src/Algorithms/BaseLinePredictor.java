/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Dao.DAO.*;
import static Dao.DAO.*;
import Dao.Events;
import Dao.Item;
import Dao.User;
import Ratings.ModelDataSet;
import java.util.ArrayList;

/**
 *
 * @author bogdan
 */
public class BaseLinePredictor implements RecommenderAlgorithms{
    
    
    ModelDataSet mds;
    public BaseLinePredictor(ModelDataSet mds){
        this.mds=mds;
    }
    
    @Override
    public ArrayList<Item> top10Recomendation(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double predict(User u, Item i) {
        double res=-99; 
        res=mds.getRatingOfSimilarItemUserVoted(i, u);
        if(res==-99)
            res=globalMeanRating()+getStandardDeviationForUser(u)+getStandardDeviationForItem(i);
        
        return res;
        
    }
    
    
    public double globalMeanRating(){
        double res=0;
        
        for(Events e:getEventsDAO()){
            res+=e.getRating();
        }
        //System.out.println("global mean rating= "+res/getEventsDAO().size());
        return res/getEventsDAO().size();
    }
    

    public double getAverageRatingForUser(User u){
        double avg=0;
        double res=0;
        double itemUserVoted=0;
        //items user rated
        for(Events e:getEventsDAO()){
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
        for(Events e:getEventsDAO()){
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
        double sd=0;
        int tam=0;
        //movies seen by user 
        for(Events e:getEventsDAO()){
            if(u.getUserId()==e.getUser().getUserId()){
               sd+=Math.pow((e.getRating()-avg), 2);
                tam++;
            }
        }
        if(tam==1)
            return Math.sqrt(sd);
        else
            return Math.sqrt(sd/tam-1);
    }
    
    public double getStandardDeviationForItem(Item i){
        double avg=getAverageRatingForItem(i);
        double tam=0;
        double sd=0;
        
        for(Events e:getEventsDAO()){
            if(i.getId()==e.getItem().getId()){
                sd+=Math.pow((e.getRating()-avg), 2);
                tam++;
            }
        }
        if(tam==1)
            return Math.sqrt(sd);
        else
            return Math.sqrt(sd/(tam-1));
    }
    
    
}//end class
