/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import java.util.ArrayList;

/**
 *CLase que establece la relación entre las métricas y la interfaz del módulo 
 * @author bogdan
 * @version 1.0
 */
public class MetricsContext {
    EvaluationType evalType;
    
    /**
     * Establece un nueva métrica
     * @param et 
     */
    public void setEvaluationType(EvaluationType et){
        this.evalType=et;
    }
    /**
     * Devuelve el tipo de métrica establecida
     * @return la métrica
     */
    public EvaluationType getEvaluationType(){
        return evalType;
    }
    
    /**
     * Calcula el valor del error cometido
     * @param predicRat lista de predicciones y valoraciones
     * @return el error cometido
     *@see EvaluationType#calculate(java.util.ArrayList) 
     */
    public double calculate(ArrayList<PairEvaluation> predicRat){
        return evalType.calculate( predicRat);
    }
    
    
}
