/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ratings;


import Dao.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import Dao.Events;
import Algorithms.SimilarityApi;
import Dao.AccessDataAPI;
import Dao.Item;   
import Dao.User;
import java.util.Set;

/**
 *
 * @author bogdan
 */
public class KnnModel implements InterfaceModel{
    AccessDataAPI adapi;
    HashMap<Item, ArrayList<Pair> >similarityMatrixModel;
    SimilarityApi similarityapi;
    
    /**
     *
     * @param ad
     * @param simapi
     */
    public KnnModel(AccessDataAPI ad,SimilarityApi simapi){
        similarityMatrixModel=new HashMap<> ();
        this.adapi=ad; 
        this.similarityapi=simapi;
        buildModel();
    }
    
    /**
     *
     * @return
     */
    public AccessDataAPI getAccessDataApi(){
        return adapi;
    }
    
     /**
     * get all the items of model
     * @return 
     */
    @Override
    public HashMap<Item, ArrayList<Pair> > getItemsUniverse(){
        return similarityMatrixModel;
    }
    
    
    /**
     * get all the items and its similarity 
     * @param ite
     * @return null if item not found  in the Similarity Matrix InterfaceModel
     */
    @Override
    public ArrayList<Pair> getSimilarItems(Item ite)
    { 
        ArrayList<Pair> similarItem=getItemsUniverse().get(ite);
        //System.out.println("tam similarItem en getSimilarItem()"+similarItem.size());
        //System.out.println("el elemento es"+ite.getId());
        if(similarItem!=null){
            Collections.sort(similarItem , new Comparator<Pair>() {
                    @Override
                    public int compare(Pair p1, Pair p2)
                    {
                        //return p1.getSimilitud().compareTo(p2.getSimilitud());
                        if(p1.getSimilitud()>p2.getSimilitud())
                            return -1;
                        else if(p1.getSimilitud()<p2.getSimilitud())
                            return 1;
                        else return 0;
                    }
            });
          return similarItem;
        }
        return null;
    }
     /**
     * data model constructor ,build the SimilarityMatrixModel
     */
    @Override
    public  void buildModel()
    {
        //CosineSimilarity c=new CosineSimilarity();
        ArrayList<Pair> itemSimilarity;
        //System.out.println("itemSimilarity lo primero en builmodels"+itemSimilarity.size());
        double similitud=0.0;
        Pair p;
        
        //for each item get ratings 
        for (Map.Entry<Item, ArrayList<Events>> entryA : adapi.getItemEvent().entrySet()) {
            Item item1=entryA.getKey();
            ArrayList<Events> ratingsA=entryA.getValue();
           //System.out.println("itemId "+item1.getId());
            //System.out.println("tam rating "+ratingsA.size());
            
            itemSimilarity=new ArrayList<>();
            //select ItemVectorRating greater than threshold 
            if(ratingsA.size() >= InterfaceModel.minRatings){
                //System.out.println("itemAId = "+item1.getId());
                //System.out.println("tam ratingsA = "+ratingsA.size());
                //for each item, get ratings 
                for (Map.Entry<Item, ArrayList<Events>> entryB : adapi.getItemEvent().entrySet()) {
                    
                    Item item2=entryB.getKey();
                    ArrayList<Events> ratingsB=entryB.getValue();
                    if(item1.getId()!=item2.getId()){
                        //not calculate the similarity for the same item
                        if(ratingsB.size() >= InterfaceModel.minRatings){
                            //calculate the similarity between items 
                            similitud=similarityapi.compare(ratingsA,ratingsB);
                            //System.out.println("simiitud ="+similitud);
                            p=new Pair(item2, similitud);
                            itemSimilarity.add(p);
                            //System.out.println("item "+item1.getId()+" itemSimilaritySize() "+itemSimilarity.size());
                        }
                    }
                }
                
                getItemsUniverse().put(item1, itemSimilarity);
            }   
        }
        //System.out.println("tam getItemUniverse().size()"+getItemsUniverse().get(new Item(2)).size());        
    }

    /**
     * find the ratings of a item that user has rate previously
     * @param i item 
     * @param u user
     * @return  rating, -99 if don vote the item
     */
    @Override
    public double getRatingOfSimilarItemUserVoted(Item  i,User u){
        double rating=-99;
        boolean esta =false;
        for(Events e:adapi.getEvents())
        {
           if(i.getId()==e.getItem().getId() && u.getUserId()==e.getUser().getUserId()){
                rating=e.getRating();
                esta=true;
                //System.out.println("entra en getRatingOfSimilarItemUserVoted(Item  i,User u) ");
           }
        }
        if(esta)
            return rating;
        else
            return -99;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Events> getEvents() {
        return adapi.getEvents();
    }

    /**
     *
     * @return
     */
    @Override
    public Set<User> getUser() {
        return adapi.getUser();
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Item> getItems() {
        return adapi.getItems();
    }

    /**
     *
     * @return
     */
    @Override
    public HashMap<User, ArrayList<Events>> getUserEventDAO() {
        return adapi.getUserEvent();
    }

    /**
     *
     * @return
     */
    @Override
    public HashMap<Item, ArrayList<Events>> getItemEventDAO() {
        return adapi.getItemEvent();
    }
    
    /**
     *
     * @param u
     * @return
     */
    public ArrayList<Item> getItemsUserRated(User u){
        ArrayList<Item> itemsUserRated=new ArrayList<>();
        ArrayList<Events> userEvents;
        userEvents=adapi.getUserEvent().get(u);
        if(userEvents==null)
            System.out.println("es null user events");
        
        for(int i=0;i<userEvents.size();i++){
            //get elements user has rated
            Item item=new Item(userEvents.get(i).getItem());
            itemsUserRated.add(item);
        }
        return itemsUserRated;
    }
    

  
}

