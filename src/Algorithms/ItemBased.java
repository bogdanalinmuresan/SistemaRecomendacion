/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;
import Dao.Item;
import Dao.User;
import Ratings.*;

/**
 *
 * @author bogdan
 */
public class ItemBased extends ColaborativeFiltering{
    
    
    ModelAPI mapi;
    ScoreAPI measureapi;

    public ItemBased() {
        //this.mapi=mdapi;
    }
    
    public ItemBased(ScoreAPI measureapi){
        this.measureapi=measureapi;
        //this.mapi=mdapi;
    }
    
  

    @Override
    public ArrayList<Item> top10Recomendation(User u) {
        ArrayList<Item> res=new ArrayList<>();
        //10 items ,user rate ,and the the 10 most similar items 
        
        
        return res;
    }

    /**
     * 
     * @param u user 
     * @param ite item
     * @param modelapi
     * @return -1 if user has rated item ite  ,-2 if item isnt store in model then it must change knn interface variable    
     */
    @Override
    public double predict(User u, Item ite,ModelAPI modelapi) {
        double res=0;
        //scoremeasure=new WeightSum(u, ite, mapi);
        res=measureapi.score(u, ite);
        //scoremeasure.score(u, ite);
        
    return res;
    }

}
