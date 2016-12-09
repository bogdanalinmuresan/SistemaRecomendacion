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

import java.util.ArrayList;
import Dao.Events;

/**
 *Clase que representa la interfaz para la similitud entre elementos
 * @author bogdan
 * @version 1.0
 */
public abstract class ItemSimilarity implements SimilarityCalculate{
    /**
     * Constructor por defecto
     */
    public ItemSimilarity(){}
    
    /**
     * Método para calcular la similitud entre dos listas de calificaciones pertenecientes a dos elementos
     * @param ratingsA lista calificaciones A
     * @param ratingsB lista calificaciones B
     * @return  la similitud
     */
    public abstract double determineSimilarity(ArrayList<Events> ratingsA,ArrayList<Events> ratingsB);
}
