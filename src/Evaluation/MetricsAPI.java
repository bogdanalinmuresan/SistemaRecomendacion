/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import java.util.ArrayList;

/**
 *
 * @author bogdan
 */
public class MetricsAPI {
    private MetricsContext mt;
    
    
    public MetricsAPI(){
        
        mt=new MetricsContext();
    }
    
    public void mae(){
        MAE mae=new MAE();
        mt.setEvaluationType(mae);
        //return mt.calculate( predRat);
    }
    public void setMetric(EvaluationType evaltype){
        mt.setEvaluationType(evaltype);
    }
    
    public double calculate(ArrayList<PairEvaluation> predRat){
        return mt.calculate(predRat);
    }
    
}
