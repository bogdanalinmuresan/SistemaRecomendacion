/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosClasicos;

import static java.lang.Math.sqrt;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sistemarecomendacion.DAO.Events;
import static sistemarecomendacion.DAO.DAO.getItemEventDAO;
import algoritmosClasicos.Pair;
import java.util.ArrayList;
/**
 *
 * @author bogdan
 */
public class itemItemCF {
    
    public  HashMap<Integer, ArrayList<Pair>> buildModel()
    {/*
        HashMap<Integer, HashMap<Integer, Double>> similarityMatrixModel=new HashMap<>();
        
        //for each item get ratings 
        for (Map.Entry<Integer, List<Events>> entry : getItemEventDAO().entrySet()) {
        Integer item1=entry.getKey();
        List<Events> ratingsA=entry.getValue();
            
            //for each item get ratings 
            HashMap itemSimilarity=new HashMap<Integer,Double>();
            for (Map.Entry<Integer, List<Events>> entryB : getItemEventDAO().entrySet()) {
                Integer item2=entryB.getKey();
                List<Events> ratingsB=entryB.getValue();
            
                //calculate the similarity between items 
                double similitud=determineSimilarity(ratingsA, ratingsB);
                //System.out.println("simiitud ="+similitud);
                itemSimilarity.put(item2, similitud);
            }
            
            similarityMatrixModel.put(item1, (HashMap<Integer, Double>) itemSimilarity);
        }
        System.out.println("tam  = "+similarityMatrixModel.size());
        return similarityMatrixModel;
        */
        
        HashMap<Integer, ArrayList<Pair> > similarityMatrixModel=new HashMap<>();
        
        //for each item get ratings 
        for (Map.Entry<Integer, List<Events>> entry : getItemEventDAO().entrySet()) {
        Integer item1=entry.getKey();
        List<Events> ratingsA=entry.getValue();
            
            //for each item get ratings 
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
        System.out.println("tam  = "+similarityMatrixModel.size());
        return similarityMatrixModel;
    }
    /**
     * Item scorer
     */
    private double weightedSum(int userId,int itemId)
    {
        double SumTop=0;
        double SumBottom=0;
        //get the similar movies to itemId 
        
        return 0;
        
    }
    
    
    
    /**
     * Computa la similaridad entre los vectores de ratings de dos elementoss
     * @param eventos1
     * @param eventos2 
     * @return  devuelve la similitud entre dos items
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