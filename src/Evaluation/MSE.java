/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Ratings.EvaluationModel;
import java.util.ArrayList;

/**
 *Clase que calcula la media del error al cuadrado
 * @author bogdan
 */
public class MSE extends BasicAcurrancyErrorMetric implements EvaluationType {

    /**
     * Constructor con parámetros
     * @param mds acceso al modelo de evaluación
     */
    public MSE(EvaluationModel mds) {
        super(mds);
    }
    
    /**
     * Constructor por defecto
     */
    public MSE()
    {
        super();
    }

    /**
     * 
     * @param predictionRating
     * @return 
     * @see EvaluationType#calculate(java.util.ArrayList) 
     */
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
