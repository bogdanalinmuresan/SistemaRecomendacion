/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Ratings.EvaluationModel;
import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 *
 * @author bogdan
 */
public class MAE extends BasicAcurrancyErrorMetric implements EvaluationType{

    
    public MAE(EvaluationModel mds){
        super(mds);
    }
    
    public MAE(){
        super();
    }
    
    
    @Override
    public double calculate(ArrayList<PairEvaluation> predictionRating) {
    double tam=predictionRating.size();
    double num=0;
    
        for(PairEvaluation p: predictionRating){
            num+=abs((p.getFirst()-p.getSecond()));
        }
        
    return num/tam;
    }
    
}
