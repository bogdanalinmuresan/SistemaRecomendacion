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
public class RecommenderApi {
    private final RecommenderContext rc;
    private  ModelAPI modelapi;
    
    
    public RecommenderApi(ModelAPI mds,RecommenderAlgorithms ra){
        rc=new RecommenderContext();
        this.modelapi=mds;
    }
    
    public RecommenderApi(ModelAPI mds){
        rc=new RecommenderContext();
        this.modelapi=mds;
    }
    
    
   
    
    public void configureAlgorithm(RecommenderAlgorithms ra,ModelAPI modelapi){
        rc.setAlgorithmType(ra);
        this.modelapi=modelapi;
        rc.setModel(modelapi);
    }
    
   
    
    public double prediction(User u, Item i){
        return rc.prediction(u, i,modelapi);
    }
    
    public ArrayList<Item> top10Recomendation(User i){
        return rc.top10Recommendation(i);
    }
    
    
    
}
