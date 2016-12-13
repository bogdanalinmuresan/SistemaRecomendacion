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
    System.out.println("mse= "+num/tam);   
    return num/tam;
    }
    
}
