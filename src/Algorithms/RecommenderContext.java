/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;
import Dao.Item;
import Dao.User;

/**
 *
 * @author bogdan
 */
public class RecommenderContext {
    //Interface reference
    private RecommenderAlgorithms algorithmType;
    
    
    public RecommenderContext(){
        
    }

    public RecommenderAlgorithms getAlgorithType(){
        return algorithmType;
    }
    
    public void setAlgorithmType(RecommenderAlgorithms rc){
        this.algorithmType=rc;
    }
    
    // Métodos de servicio (invocan los métodos implementados por las estrategias)
    public ArrayList<Item> top10Recommendation(User u){
       return algorithmType.top10Recomendation(u);
   }
   
   public double prediction(User u,Item i){
       return algorithmType.predict(u, i);
   }
    
}
