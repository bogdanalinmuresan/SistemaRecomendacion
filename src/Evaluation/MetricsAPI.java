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
