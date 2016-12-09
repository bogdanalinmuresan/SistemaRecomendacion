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
 *Clase que establece la relacion entre la api y las clase que implementa las medidas de similitud
 * @author bogdan
 * @version 1.0
 * @see SimilarityApi
 * @see SimilarityCalculate
 */
public class SimilarityContext {
    SimilarityCalculate interfaceSimilarityCalculate;
    
    /**
     * Constructor por defecto
     */
    public SimilarityContext(){
        
    }
    
    /**
     * Método selector
     * @return la interfaz 
     * @see SimilarityCalculate
     */
    public SimilarityCalculate getSimilarity(){
        return interfaceSimilarityCalculate;
    }
    /**
     * Establecer nueva medida de cálculo de similitud
     * @param simcal 
     */
    public void setSimilarity(SimilarityCalculate simcal){
        this.interfaceSimilarityCalculate=simcal;
    }
    /**
     * Método que cálcula la similitud 
     * @param ratingsA lista de calificaciones A
     * @param ratingsB lista de calificaciones B
     * @return  la similitud entre las dos listas de calificaciones
     */
    public double compare(ArrayList<Events> ratingsA,ArrayList<Events> ratingsB){
        return interfaceSimilarityCalculate.compare(ratingsA, ratingsB);
    }
    
    
}

