/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ratings;

import Dao.AccessDataAPI;
import Dao.Item;
import Dao.Pair;
import Dao.User;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author bogdan
 */
public class ModelAPI {
    
    ModelContext mc;
    AccessDataAPI accessDataApi;
    
    public ModelAPI(){
        mc=new ModelContext();
        accessDataApi=new AccessDataAPI();
    }
    
    public ModelAPI(AccessDataAPI accessDataApi){
        mc=new ModelContext();
        this.accessDataApi=accessDataApi;
    }
    
    public AccessDataAPI getAccessDataApi(){
        return accessDataApi;
    }
    
    public void setAccessDataApi(){
        
    }
    
    public void knnModel(){
        KnnModel knnM=new KnnModel(accessDataApi);
        mc.setModel(knnM);
    }
    
    public HashMap<Item, ArrayList<Pair> > getItemsUniverse(){
        return mc.getItemsUniverse();
    }
    
    public double getRatingOfSimilarItemUserVoted(Item  i,User u){
        return mc.getRatingOfSimilarItemUserVoted(i, u);
    }
    
     public ArrayList<Pair> getSimilarItems(Item ite){
         return mc.getSimilarItems(ite);
     }
}
