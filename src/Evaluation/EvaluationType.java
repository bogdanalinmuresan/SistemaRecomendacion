/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Dao.Pair;
import java.util.ArrayList;

/**
 *
 * @author bogdan
 */
public interface EvaluationType {
    
    public abstract double calculate(ArrayList<PairEvaluation> predictionRating);
    
}