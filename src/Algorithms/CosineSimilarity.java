/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import static java.lang.Math.sqrt;
import java.util.ArrayList;
import Dao.Events;

/**
 *
 * @author bogdan
 */
public class CosineSimilarity extends ItemSimilarity implements SimilarityCalculate{
    
    public CosineSimilarity(){
        
    }

    @Override
    public double determineSimilarity(ArrayList<Events> ratingsA, ArrayList<Events> ratingsB) 
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
      
      double res=(dotProduct/(sqrt(magnitudeA)*sqrt(magnitudeB)));
      //System.out.println("similitud entre ratings"+res);
      return res;
    }

    @Override
    public double compare(ArrayList<Events> ratingsA, ArrayList<Events> ratingsB) {
        return determineSimilarity(ratingsA, ratingsB);
    }   
}
