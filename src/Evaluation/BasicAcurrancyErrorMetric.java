/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Ratings.EvaluationModel;

/**
 *
 * @author bogdan
 */
public abstract class BasicAcurrancyErrorMetric {
     EvaluationModel mds;
     
     public BasicAcurrancyErrorMetric(EvaluationModel mds){
         this.mds=mds;
     }
     
     public BasicAcurrancyErrorMetric(){
         
     }
     
    public EvaluationModel getModel(){
       return mds;
    }
    
    public void setModel(EvaluationModel mds){
        this.mds=mds;
    }
     
}
