/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Dao.Pair;
import Dao.Item;
import Dao.User;
import Ratings.ModelAPI;
import java.util.ArrayList;

/**
 *
 * @author bogdan
 */
public class WeightSum extends ItemScorer implements ScoreMeasure{
    private ModelAPI modelapi;    
    
    public WeightSum(){ 
    }
    
    public WeightSum(User u,Item i,ModelAPI modelapi){
        this.modelapi=modelapi;
    }

    @Override
    public double itemscore(User u, Item i) {
       return score(u, i);
    }

    @Override
    public double score(User u, Item ite) {
        
        double res=-2;
        //res ==-99 if user dont voted item i
        res=modelapi.getRatingOfSimilarItemUserVoted(ite, u );
        if(res==-99){
            ArrayList<Pair> similarItem;
            similarItem=modelapi.getSimilarItems(ite);

            if(similarItem!=null){
                double sumTop=0;
                double sumBottom=1;    
                double temp;
                for(int i=0; i< similarItem.size() ;i++){
                    //get rating for userId and similarItem[i]
                    temp=modelapi.getRatingOfSimilarItemUserVoted(similarItem.get(i).getItem1(), u );
                    //items that user voted ,dont mind
                    if(temp!=-99){
                        sumTop+=temp*similarItem.get(i).getSimilitud();
                        sumBottom+=Math.abs(similarItem.get(i).getSimilitud());
                    }
                }
                res=sumTop/sumBottom;
                return res;
            }else{
                //no esta la pelicla en la similarityMatrixModel
                return res=-2;
            }
        }
        return -2;
    }
    
}
