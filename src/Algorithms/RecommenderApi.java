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
    
    
    public RecommenderApi(){
        this.rc=new RecommenderContext();
        this.modelapi=new ModelAPI();
    }
    
    public RecommenderApi(ModelAPI mds){
        rc=new RecommenderContext();
        this.modelapi=mds;
    }
    
    
   
    
    public void addAlgorithm(RecommenderAlgorithms ra){
        rc.setAlgorithmType(ra);
    }
    
   
    
    public double prediction(User u, Item i){
        return rc.prediction(u, i);
    }
    
    public ArrayList<Item> topNRecomendation(User i,int N){
        return rc.top10Recommendation(i,N);
    }
    
    
    
}
