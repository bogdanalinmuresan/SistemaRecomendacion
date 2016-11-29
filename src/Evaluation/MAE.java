/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Ratings.KnnModel;
import java.util.ArrayList;

/**
 *
 * @author bogdan
 */
public class MAE extends BasicAcurrancyErrorMetric implements EvaluationType{

    
    public MAE(KnnModel mds){
        super(mds);
    }
    
    
    @Override
    public double calculate(ArrayList<PairEvaluation> predictionRating) {
    double tam=predictionRating.size();
    double num=0;
    
        for(PairEvaluation p: predictionRating){
            num+=Math.pow(p.getFirst()-p.getSecond(), 2);
        }
        
    return (Math.sqrt(num))/tam;
    }
    
}
