/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Api;
import Algorithms.ItemBased;
import Algorithms.RecommenderContext;
import Ratings.ModelDataSet;
import java.util.ArrayList;
import sistemarecomendacion.DAO.Item;
import sistemarecomendacion.DAO.User;
/**
 *
 * @author bogdan
 */
public class RecommenderApi {
    private RecommenderContext rc;
    
    public RecommenderApi(){
        rc=new RecommenderContext();
    }
    
    public void itemBased(ModelDataSet mds){
        ItemBased itB=new ItemBased(mds);
        rc.setAlgorithmType(itB);
        
    }
    
    public void baselinePrediction(ModelDataSet mds){
        
    }
    
    public void contentBased(ModelDataSet mds){
        
    }
    
    public double prediction(User u, Item i){
        return rc.prediction(u, i);
    }
    
    public ArrayList<Item> top10Recomendation(User i){
        return rc.top10Recommendation(i);
    }
    
    
    
}
