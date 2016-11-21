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

/**
 *
 * @author bogdan
 */
public class ItemItemModel {
            HashMap<Integer, ArrayList<Pair> >similarityMatrixModel;
            //numbers of neighbors to retain in the similarity matrix
           

    /**
     * constructor
     */
    public ItemItemModel(){
        similarityMatrixModel=new HashMap<>();
         int modelSize=0;
    }

    /**
     * get all the items of model
     * @return 
     */
    public HashMap<Integer, ArrayList<Pair> > getItemsUniverse(){return similarityMatrixModel;}
    
    /**
     * get all the items and its similarity with itemId, the similirity aren`t sorted
     * @param itemId
     * @return 
     */
    public ArrayList<Pair> getSimilarItems(Integer itemId){
        ArrayList<Pair> similarItem;
        ArrayList<Pair>similarItemsSorted =new ArrayList<Pair>();
        //get items with the similarity 
        
        similarItem=similarityMatrixModel.get(itemId);
        if(similarityMatrixModel==null)
            System.err.println("similarityMatrixModels devuelve null ");
        
        
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
        }
        
        //System.out.println("similarItemsSorted.size()= "+similarItemsSorted.size());
        
        //devolver los k similar items
        
        
        return similarItemsSorted;
    }
    
    /**
     * data model constructor ,build the SimilarityMatrixModel
     * @return the similarity matrix
     */
    public  HashMap<Integer, ArrayList<Pair>> buildModel()
    {
        //for each item get ratings 
        for (Map.Entry<Integer, List<Events>> entry : getItemEventDAO().entrySet()) {
            Integer item1=entry.getKey();
            List<Events> ratingsA=entry.getValue();
            ArrayList itemSimilarity=new ArrayList<Pair>();
            //select ItemVectorRating greater than threshold 
            if(ratingsA.size() >= knn.minNeighbor_k){
                //for each item, get ratings 
                for (Map.Entry<Integer, List<Events>> entryB : getItemEventDAO().entrySet()) {
                    Integer item2=entryB.getKey();
                    List<Events> ratingsB=entryB.getValue();
                        //not calculate the similarity for the same item
                        if(ratingsB.size() >= knn.minNeighbor_k && item1!=item2){
                            //calculate the similarity between items 
                            double similitud=determineSimilarity(ratingsA, ratingsB);
                            //System.out.println("simiitud ="+similitud);
                            Pair p=new Pair(item2, similitud);
                            itemSimilarity.add(p);
                        }
                    similarityMatrixModel.put(item1, itemSimilarity);
                }
            }    
            
        }
        //System.out.println("tam  = "+similarityMatrixModel.size());
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
