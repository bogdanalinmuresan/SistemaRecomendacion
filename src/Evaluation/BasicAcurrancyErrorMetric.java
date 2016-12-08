/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Ratings.EvaluationModel;

/**
 *Clase abstracta que hereda de EvaluationType y que engloba las metricas para medir el error
 * @author bogdan
 * @version 1.0
 * @see EvaluationType 
 */
public abstract class BasicAcurrancyErrorMetric {
     EvaluationModel mds;
     
     /**
      * Constructor por parámetros
      * @param mds modelo para evaluacion
      */
     public BasicAcurrancyErrorMetric(EvaluationModel mds){
         this.mds=mds;
     }
     
     /**
      * Constructor por defecto
      */
     public BasicAcurrancyErrorMetric(){
         mds=new EvaluationModel();
     }
     
    /**
     * Método selector de modelo
     * @return el modelo
     */
    public EvaluationModel getModel(){
       return mds;
    }
    
    /**
     * Método modificador de modelo
     * @param mds el modelo de evaluacion
     */
    public void setModel(EvaluationModel mds){
        this.mds=mds;
    }
     
}
