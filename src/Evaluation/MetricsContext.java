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
public class MetricsContext {
    EvaluationType evalType;
    
    public void setEvaluationType(EvaluationType et){
        this.evalType=et;
    }
    
    public EvaluationType getEvaluationType(){
        return evalType;
    }
    
    public double calculate(ArrayList<PairEvaluation> predicRat){
        return evalType.calculate( predicRat);
    }
    
    
}
