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
public class SimilarityContext {
    SimilarityCalculate interfaceSimilarityCalculate;
    
    public SimilarityContext(){
        
    }
    
    public SimilarityCalculate getSimilarity(){
        return interfaceSimilarityCalculate;
    }
    
    public void setSimilarity(SimilarityCalculate simcal){
        this.interfaceSimilarityCalculate=simcal;
    }
    
    public double compare(ArrayList<Events> ratingsA,ArrayList<Events> ratingsB){
        return interfaceSimilarityCalculate.compare(ratingsA, ratingsB);
    }
    
    
}

