/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Ratings.ModelDataSet;

/**
 *
 * @author bogdan
 */
public abstract class BasicAcurrancyErrorMetric {
     ModelDataSet mds;
     
     public BasicAcurrancyErrorMetric(ModelDataSet mds){
         this.mds=mds;
     }
     
    public ModelDataSet getModel(){
       return mds;
    }
    
    public void setModel(ModelDataSet mds){
        this.mds=mds;
    }
     
}
