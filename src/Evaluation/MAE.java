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
 *Clase que calcula la media del error absoluto
 * @author bogdan
 * @version 1.0
 */
public class MAE extends BasicAcurrancyErrorMetric implements EvaluationType{

    /**
     * Constructor con parámetros
     * @param mds acceso al modelo de evaluacion
     */
    public MAE(EvaluationModel mds){
        super(mds);
    }
    
    /**
     * Constructor por defecto
     */
    public MAE(){
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
            num+=abs((p.getFirst()-p.getSecond()));
        }
        
    return num/tam;
    }
    
}
