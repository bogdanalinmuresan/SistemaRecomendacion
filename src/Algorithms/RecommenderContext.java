/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;
import Dao.Item;
import Dao.User;
import Ratings.ModelAPI;

/**
 *
 * @author bogdan
 */
public class RecommenderContext {
    //Interface reference
    private RecommenderAlgorithms algorithmType;
    ModelAPI modelapi;
    
    
    public RecommenderContext(){
    }

    public RecommenderAlgorithms getAlgorithType(){
        return algorithmType;
    }
    
    public void setAlgorithmType(RecommenderAlgorithms rc){
        this.algorithmType=rc;
    }
    
    
    public void setModel(ModelAPI modelapi){
        this.modelapi=modelapi;
    }
    
    // Métodos de servicio (invocan los métodos implementados por las estrategias)
    public ArrayList<Item> top10Recommendation(User u){
       return algorithmType.top10Recomendation(u);
   }
   
   public double prediction(User u,Item i,ModelAPI modelapi){
       return algorithmType.predict(u, i,modelapi);
   }
    
}
