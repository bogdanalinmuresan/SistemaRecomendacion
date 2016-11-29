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
    private WeightSum ws;
    private CosineSimilarity c;
    ModelAPI mapi;

    public ItemBased() {
    }
    
    public ItemBased(ModelAPI mdapi){
        this.mapi=mdapi;
    }

    @Override
    public ArrayList<Item> top10Recomendation(User u) {
        ArrayList<Item> res=new ArrayList<>();
        
        return res;
    }

    /**
     * 
     * @param u user 
     * @param ite item
     * @return -1 if user has rated item ite  ,-2 if item isnt store in model then it must change knn interface variable    
     */
    @Override
    public double predict(User u, Item ite) {
        double res=0;
        ws=new WeightSum(u, ite, mapi);
        res=ws.itemscore(u, ite);
        
    return res;
    }

}
