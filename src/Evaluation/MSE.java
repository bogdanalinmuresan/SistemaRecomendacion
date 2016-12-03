/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Dao.Pair;
import Ratings.EvaluationModel;
import Ratings.KnnModel;
import java.util.ArrayList;

/**
 *
 * @author bogdan
 */
public class MSE extends BasicAcurrancyErrorMetric implements EvaluationType {

    public MSE(EvaluationModel mds) {
        super(mds);
    }
    
    public MSE()
    {
        super();
    }

    @Override
    public double calculate(ArrayList<PairEvaluation> predictionRating) {
    double tam=predictionRating.size();
    double num=0;
        for(PairEvaluation p: predictionRating){
            num+=Math.pow(p.getFirst()-p.getSecond(), 2);
        }
        
    return num/tam;
    }
    
}
