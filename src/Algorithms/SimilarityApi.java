/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Dao.Events;
import java.util.ArrayList;

/**
 *
 * @author bogdan
 */
public class SimilarityApi {
    SimilarityContext similarityContext;
    
    public SimilarityApi(){
        similarityContext=new SimilarityContext();
    }
    
    public void addSimilarity(SimilarityCalculate similCalculate){
        similarityContext.setSimilarity(similCalculate);
    }
    
    public double compare(ArrayList<Events> ratingsA,ArrayList<Events> ratingsB){
        return similarityContext.compare(ratingsA, ratingsB);
    }
}
