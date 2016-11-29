/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Ratings.KnnModel;

/**
 *
 * @author bogdan
 */
public abstract class BasicAcurrancyErrorMetric {
     KnnModel mds;
     
     public BasicAcurrancyErrorMetric(KnnModel mds){
         this.mds=mds;
     }
     
    public KnnModel getModel(){
       return mds;
    }
    
    public void setModel(KnnModel mds){
        this.mds=mds;
    }
     
}
