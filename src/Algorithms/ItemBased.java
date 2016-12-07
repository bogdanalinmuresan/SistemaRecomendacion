/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Dao.Events;
import java.util.ArrayList;
import Dao.Item;
import Dao.Pair;
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
        mapi=new ModelAPI();
        measureapi=new ScoreAPI(mapi);
    }
    
    public ItemBased(ScoreAPI measureapi,ModelAPI modelapi){
        this.measureapi=measureapi;
        this.mapi=modelapi;
    }
    
  

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
     * 
     * @param u user 
     * @param ite item
     * @return -1 if user has rated item ite  ,-2 if item isnt store in model then it must change knn interface variable    
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
