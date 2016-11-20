/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosClasicos;

import static java.lang.Math.sqrt;
import java.util.ArrayList;
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
            HashMap<Integer, ArrayList<Pair> >similarityMatrixModel=new HashMap<>();
            //numbers of neighbors to retain in the similarity matrix
            int modelSize=0;

    public ItemItemModel(){
        
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
        return similarityMatrixModel.get(itemId);
    }
    
    /**
     * data model constructor
     * @return 
     */
    public  HashMap<Integer, ArrayList<Pair>> buildModel()
    {
        //HashMap<Integer, ArrayList<Pair> > similarityMatrixModel=new HashMap<>();
        
        //for each item get ratings 
        for (Map.Entry<Integer, List<Events>> entry : getItemEventDAO().entrySet()) {
        Integer item1=entry.getKey();
        List<Events> ratingsA=entry.getValue();
            
            //for each item, get ratings 
            ArrayList itemSimilarity=new ArrayList<Pair>();
            for (Map.Entry<Integer, List<Events>> entryB : getItemEventDAO().entrySet()) {
                Integer item2=entryB.getKey();
                List<Events> ratingsB=entryB.getValue();
            
                //calculate the similarity between items 
                double similitud=determineSimilarity(ratingsA, ratingsB);
                //System.out.println("simiitud ="+similitud);
                Pair p=new Pair(item2, similitud);
                itemSimilarity.add(p);
            }
            
            similarityMatrixModel.put(item1, itemSimilarity);
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
