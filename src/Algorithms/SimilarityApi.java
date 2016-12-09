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
package Algorithms;

import Dao.Events;
import java.util.ArrayList;

/**
 *clase que actua de interfaz para las medidas de similitud
 * @author bogdan
 * @version 1.0
 */
public class SimilarityApi {
    SimilarityContext similarityContext;
    /**
     * Constructor de la clase
     */
    public SimilarityApi(){
        similarityContext=new SimilarityContext();
    }
    /**
     * Añadir nueva medida de similitud
     * @param similCalculate nueva medida
     */
    public void addSimilarity(SimilarityCalculate similCalculate){
        similarityContext.setSimilarity(similCalculate);
    }
    /**
     * Método que devuelve la similitud 
     * @param ratingsA lista de calificaciones A
     * @param ratingsB lista de calificaciones B
     * @return  la similitud
     */
    public double compare(ArrayList<Events> ratingsA,ArrayList<Events> ratingsB){
        return similarityContext.compare(ratingsA, ratingsB);
    }
}
