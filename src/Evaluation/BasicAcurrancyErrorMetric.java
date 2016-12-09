/* 
 * Copyright (C) 2016 Bogdan Alin Muresan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
