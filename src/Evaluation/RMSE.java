/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Ratings.EvaluationModel;
import java.util.ArrayList;

/**
 *Clase que calcula root mean squared error
 * @author bogdan
 */
public class RMSE extends BasicAcurrancyErrorMetric implements EvaluationType {

    /**
     * Constructor con párametros
     * @param mds 
     */
    public RMSE(EvaluationModel mds) {
        super(mds);
    }
    
    /**
     * Constructor por defecto
     */
    public RMSE(){
        super();
    }

    /**
     * Calcula root mean squared error
     * @param predictionRating
     * @return el error cometido 
     * @see EvaluationType#calculate(java.util.ArrayList) 
     */
    @Override
    public double calculate(ArrayList<PairEvaluation> predictionRating) {
    double tam=predictionRating.size();
    double num=0;
        for(PairEvaluation p: predictionRating){
            num+=Math.pow(p.getFirst()-p.getSecond(), 2);
        }
        
    return Math.sqrt(num/tam);
    }
    
}
