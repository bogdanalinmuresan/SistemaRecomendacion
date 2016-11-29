/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Dao.Pair;
import Ratings.KnnModel;
import java.util.ArrayList;

/**
 *
 * @author bogdan
 */
public class MetricsAPI {
    private MetricsContext mt;
    private KnnModel mds;
    
    public void mae(){
        MAE mae=new MAE(mds);
         mt.setEvaluationType(mae);
        //return mt.calculate( predRat);
    }
    
    public double calculate(ArrayList<PairEvaluation> predRat){
        return mt.calculate(predRat);
    }
    
}
