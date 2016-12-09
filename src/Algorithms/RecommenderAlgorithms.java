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
import Dao.Item;
import Dao.User;

/**
 *Clase que establece la conexión entre la familia de algoritmos y la interfaz 
 * @author bogdan
 * @version 1.0
 */
public interface  RecommenderAlgorithms {
    
    /**
     * Método que calcula una lista de recomendaciones para un usuario 
     * @param u usuario
     * @param N número de elementos 
     * @return lista de elementos 
     */
    public  abstract ArrayList<Item> topNRecomendation(User u,int N);
    
    /**
     * Método para calcular un predicción para un usuario y en elemento dado
     * @param u usuario
     * @param i elemeto
     * @return  la predicción
     */
    public abstract double predict(User u,Item i);
}
