/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosClasicos;

import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static sistemarecomendacion.DAO.DAO.getItemEventDAO;
import sistemarecomendacion.DAO.Events;
import sistemarecomendacion.DAO.Item;
import sistemarecomendacion.DAO.Movie;

/**
 *
 * @author bogdan
 */
public class ItemItemModel {
            HashMap<Movie, ArrayList<Pair> >similarityMatrixModel;
            //numbers of neighbors to retain in the similarity matrix
           

    /**
     * constructor
     */
    public ItemItemModel(){
        similarityMatrixModel=new HashMap<Movie,ArrayList<Pair>> ();
        
    }

    /**
     * get all the items of model
     * @return 
     */
    public HashMap<Movie, ArrayList<Pair> > getItemsUniverse(){return similarityMatrixModel;}
    
    /**
     * get all the items and its similarity 
     * @param ite
     * @return 
     */
    public ArrayList<Pair> getSimilarItems(Movie ite){
        
        //System.out.println("boolena getItemUniverse.get(ite) "+getItemsUniverse().containsKey(ite));
        
        ArrayList<Pair> similarItem=getItemsUniverse().get(ite);
        if(similarItem!=null){
                //System.out.println("tam similarItem = "+similarItem.size());
               // for(Pair p:similarItem){
               //     System.out.println("p.getId = "+p.getItem1().getId());
               // }
                ArrayList<Pair> similarItemsSorted=new ArrayList<>();
                //get items with the similarity 

                // Sorting
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

                //System.out.println("similarItem.size()= "+similarItem.size());

                if(similarItem.size() >= knn.neighborhoodSize_N){
                    for(int i=0; i<knn.neighborhoodSize_N;i++){
                        similarItemsSorted.add((Pair) similarItem.get(i));
                    }

                    return similarItemsSorted;
                }else{
                    return similarItem;
                }
        }else{
            return null;
        }
}
      
    
    
    /**
     * data model constructor ,build the SimilarityMatrixModel
     * @return the similarity matrix
     */
    public  HashMap<Movie, ArrayList<Pair>> buildModel()
    {
        ArrayList<Pair> itemSimilarity;
        //System.out.println("itemSimilarity lo primero en builmodels"+itemSimilarity.size());
        double similitud=0.0;
        Pair p;
        
        //for each item get ratings 
        for (Map.Entry<Movie, ArrayList<Events>> entry : getItemEventDAO().entrySet()) {
            Movie item1=entry.getKey();
            
            ArrayList<Events> ratingsA=entry.getValue();
            
            itemSimilarity=new ArrayList<Pair>();
            //select ItemVectorRating greater than threshold 
            if(ratingsA.size() >= knn.minNeighbor_k){
                //System.out.println("itemAId = "+item1.getId());
                //System.out.println("tam ratingsA = "+ratingsA.size());
                //for each item, get ratings 
                for (Map.Entry<Movie, ArrayList<Events>> entryB : getItemEventDAO().entrySet()) {
                    Movie item2=entryB.getKey();
                    ArrayList<Events> ratingsB=entryB.getValue();
                    
                        //not calculate the similarity for the same item
                        if(ratingsB.size() >= knn.minNeighbor_k && item1.getId()!=item2.getId()){
                            //calculate the similarity between items 
                            similitud=determineSimilarity(ratingsA, ratingsB);
                            //System.out.println("simiitud ="+similitud);
                            p=new Pair(item2, similitud);
                            itemSimilarity.add(p);
                            //System.out.println("item "+item1.getId()+" itemSimilaritySize() "+itemSimilarity.size());
                        }
                        
                }
                similarityMatrixModel.put(item1, itemSimilarity);
                
            }   
           
               
             
        }
                // System.out.println("tam similarityMatrix model en builModel  = "+similarityMatrixModel.size());
                //ArrayList<Pair> get = similarityMatrixModel.get(new Movie(16));
                //System.out.println("tamaño de similarity matrix"+get.size());
                return similarityMatrixModel;
    }
    
    /**
     * determine similarity between two items
     * @param eventos1
     * @param eventos2
     * @return 
     */
    public double itemVectorSimilarity(List<Events> eventos1,List<Events> eventos2)
    {
        return determineSimilarity(eventos1, eventos2);
    }
    
    private double determineSimilarity(final List<Events> ratingsA,final List<Events>ratingsB)
    {
      float dotProduct=0;
      float magnitudeA=0;
      float magnitudeB=0;
      //float resultado=0;
      double ratingA=0;
      double ratingB=0;
      
      if(ratingsA.size()>ratingsB.size()){
        for(int i=0;i<ratingsB.size();i++)
        {
            ratingA=ratingsA.get(i).getRating();
            ratingB=ratingsB.get(i).getRating();

            dotProduct+=ratingA*ratingB;
            magnitudeA+=ratingA*ratingA;
            magnitudeB+=ratingB*ratingB;
          
        }
      }else{
          for(int i=0;i<ratingsA.size();i++)
        {
            ratingA=ratingsA.get(i).getRating();
            ratingB=ratingsB.get(i).getRating();

            dotProduct+=ratingA*ratingB;
            magnitudeA+=ratingA*ratingA;
            magnitudeB+=ratingB*ratingB;
          
        }
      }
      
      
      return (dotProduct/(sqrt(magnitudeA)*sqrt(magnitudeB)));
    }
    
}
