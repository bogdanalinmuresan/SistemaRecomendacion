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

import Dao.Item;
import Dao.User;

/**
 *Interfaz que representa la medida de calificación de un eleemnto
 * @author bogdan
 * @version 1.0
 */
public abstract class ItemScorer  {
    
    /**
     * constructor de la clase
     */
    public ItemScorer(){
        
    }
    /**
     * metodo abstracto que tienen que implementar las clases que heredan de esta interfaz
     * @param u usuario
     * @param i elemento
     * @return  la calificación
     */
    public abstract double itemscore(User u,Item i);
    
}
