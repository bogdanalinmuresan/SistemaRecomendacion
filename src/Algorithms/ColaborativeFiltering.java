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

/**
 *Clase absracta que tiene que heredar los algoritmos de la familia de algoritmos colaborativo
 * @author bogdan
 * @version 1.0
 */
public abstract class ColaborativeFiltering implements RecommenderAlgorithms{
    /**
     *número de calificaciones que debe tener un elemento
     */
    public final int minRatings=30;
    
    /**
     * numero de vecinos similares a un elemento
     */
    public final int neighborhoodSize_N=30;
}
