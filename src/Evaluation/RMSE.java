/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Dao.Pair;
import Ratings.ModelDataSet;
import java.util.ArrayList;

/**
 *
 * @author bogdan
 */
public class RMSE extends BasicAcurrancyErrorMetric implements EvaluationType {

    public RMSE(ModelDataSet mds) {
        super(mds);
    }

    @Override
    public double calculate(ArrayList<PairEvaluation> predictionRating) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
