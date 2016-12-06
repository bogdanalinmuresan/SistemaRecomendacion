/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Ratings.EvaluationModel;
import java.util.ArrayList;

/**
 *
 * @author bogdan
 */
public class RMSE extends BasicAcurrancyErrorMetric implements EvaluationType {

    public RMSE(EvaluationModel mds) {
        super(mds);
    }
    
    public RMSE(){
        super();
    }

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
