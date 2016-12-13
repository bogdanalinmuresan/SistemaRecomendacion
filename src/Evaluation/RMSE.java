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
 *Clase que calcula root mean squared error
 * @author bogdan
 */
public class RMSE extends BasicAcurrancyErrorMetric implements EvaluationType {

    /**
     * Constructor con párametros
     * @param mds 
     */
    public RMSE(EvaluationModel mds) {
        super(mds);
    }
    
    /**
     * Constructor por defecto
     */
    public RMSE(){
        super();
    }

    /**
     * Calcula root mean squared error
     * @param predictionRating
     * @return el error cometido 
     * @see EvaluationType#calculate(java.util.ArrayList) 
     */
    @Override
    public double calculate(ArrayList<PairEvaluation> predictionRating) {
    double tam=predictionRating.size();
    double num=0;
        for(PairEvaluation p: predictionRating){
            num+=Math.pow(p.getFirst()-p.getSecond(), 2);
        }
    double res=num/tam;
    System.out.println("rmse antes de la raiz= "+res); 
    System.out.println("rmse despues de la raiz= "+Math.sqrt(res));   
    return Math.sqrt(res);
    }
    
}
