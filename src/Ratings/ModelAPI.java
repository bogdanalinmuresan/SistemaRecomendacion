/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ratings;

import Dao.AccessDataAPI;
import Dao.Events;
import Dao.Item;
import Dao.Pair;
import Dao.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
    
    
    public void setModel(InterfaceModel newModel){
        mc.setModel(newModel);
    }
    
    public void knnModel(){
        KnnModel knnM=new KnnModel(accessDataApi);
        mc.setModel(knnM);
    }
    public void EvaluationModel(int k){
        EvaluationModel evalModel=new EvaluationModel(accessDataApi, k);
        mc.setModel(evalModel);
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
    
    public ArrayList<Events> getEvents(){
       return mc.getEvents();
    }
    
    public Set<User> getUser(){
        return mc.getUser();
    }
    
    public ArrayList<Item> getItems(){
        return mc.getItems();
    }
     
}
