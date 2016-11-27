/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Api;
import Algorithms.BaseLinePredictor;
import Algorithms.ItemBased;
import Algorithms.RecommenderContext;
import Ratings.ModelDataSet;
import java.util.ArrayList;
import Dao.Item;
import Dao.User;
/**
 *
 * @author bogdan
 */
public class RecommenderApi {
    private RecommenderContext rc;
    private ModelDataSet mds;
    
    public RecommenderApi(){
        rc=new RecommenderContext();
        this.mds=new ModelDataSet();
    }
    
    public RecommenderApi(ModelDataSet mds){
        rc=new RecommenderContext();
        this.mds=mds;
    }
    
    public void itemBased(){
        ItemBased itB=new ItemBased(mds);
        rc.setAlgorithmType(itB);
        
    }
    
    public void baselinePrediction(){
        BaseLinePredictor bsp=new BaseLinePredictor(mds);
        rc.setAlgorithmType(bsp);
    }
    
    public void contentBased(){
        
    }
    
    public double prediction(User u, Item i){
        return rc.prediction(u, i);
    }
    
    public ArrayList<Item> top10Recomendation(User i){
        return rc.top10Recommendation(i);
    }
    
    
    
}
