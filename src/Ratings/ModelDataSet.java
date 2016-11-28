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
import static Dao.DAO.getItemEventDAO;
import Dao.Events;
import Dao.Movie;
import Algorithms.CosineSimilarity;
import static Dao.DAO.getEventsDAO;
import Dao.Item;
import Dao.User;

/**
 *
 * @author bogdan
 */
public class ModelDataSet implements Model{
    HashMap<Item, ArrayList<Pair> >similarityMatrixModel;
    
     /**
     * constructor
     */
    public ModelDataSet(){
        similarityMatrixModel=new HashMap<> ();
        buildModel();
    }
    
     /**
     * get all the items of model
     * @return 
     */
    public HashMap<Item, ArrayList<Pair> > getItemsUniverse(){return similarityMatrixModel;}
    
    
    /**
     * get all the items and its similarity 
     * @param ite
     * @return null if item not found  in the Similarity Matrix Model
     */
    public ArrayList<Pair> getSimilarItems(Item ite)
    { 
        ArrayList<Pair> similarItem=getItemsUniverse().get(ite);
       
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
     * @return the similarity matrix
     */
    public  HashMap<Item, ArrayList<Pair>> buildModel()
    {
        CosineSimilarity c=new CosineSimilarity();
        ArrayList<Pair> itemSimilarity;
        //System.out.println("itemSimilarity lo primero en builmodels"+itemSimilarity.size());
        double similitud=0.0;
        Pair p;
        
        //for each item get ratings 
        for (Map.Entry<Movie, ArrayList<Events>> entryA : getItemEventDAO().entrySet()) {
            Movie item1=entryA.getKey();
            ArrayList<Events> ratingsA=entryA.getValue();
           //System.out.println("itemId "+item1.getId());
            //System.out.println("tam rating "+ratingsA.size());
            
            itemSimilarity=new ArrayList<Pair>();
            //select ItemVectorRating greater than threshold 
            if(ratingsA.size() >= Model.minRatings){
                //System.out.println("itemAId = "+item1.getId());
                //System.out.println("tam ratingsA = "+ratingsA.size());
                //for each item, get ratings 
                for (Map.Entry<Movie, ArrayList<Events>> entryB : getItemEventDAO().entrySet()) {
                    
                    Movie item2=entryB.getKey();
                    ArrayList<Events> ratingsB=entryB.getValue();
                    if(item1.getId()!=item2.getId()){
                        //not calculate the similarity for the same item
                        if(ratingsB.size() >= Model.minRatings && item1.getId()!=item2.getId()){
                            //calculate the similarity between items 
                            similitud=c.determineSimilarity(ratingsA,ratingsB);
                            //System.out.println("simiitud ="+similitud);
                            p=new Pair(item2, similitud);
                            itemSimilarity.add(p);
                            //System.out.println("item "+item1.getId()+" itemSimilaritySize() "+itemSimilarity.size());
                        }
                    }
                }
            similarityMatrixModel.put(item1, itemSimilarity);
            }   
        }
                return similarityMatrixModel;
    }
    
    /**
     * find the ratings of a item that user has rate previously
     * @param i item 
     * @param u user
     * @return  rating, -99 if don vote the item
     */
    public double getRatingOfSimilarItemUserVoted(Item  i,User u)
    {
        double rating=-99;
        for(Events e:getEventsDAO())
        {
           if(i.getId()==e.getItem().getId() && u.getUserId()==e.getUser().getUserId())
                rating=e.getRating();
        }
        return rating;
    }
}

