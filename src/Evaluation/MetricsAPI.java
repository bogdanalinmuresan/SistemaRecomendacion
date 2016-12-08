/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import java.util.ArrayList;

/**
 *Clase api que permite cambiar las métrica 
 * @author bogdan
 * @version 1.0
 */
public class MetricsAPI {
    private MetricsContext mt;
    
    /**
     * Constructor por defecto
     */
    public MetricsAPI(){
        
        mt=new MetricsContext();
    }
    
    /**
    * Establecer nueva metrica
    * @param evaltype 
    */
    public void setMetric(EvaluationType evaltype){
        mt.setEvaluationType(evaltype);
    }
    /**
     * Calcula el error cometido 
     * @param predRat lista de predicciones y valoraciones
     * @return el error cometido
     */
    public double calculate(ArrayList<PairEvaluation> predRat){
        return mt.calculate(predRat);
    }
    
}
