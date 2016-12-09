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
 *Esta clase es la encargada de establecer la conexion entre las estrategias y la interfaz
 * @author bogdan
 * @version 1.0
 */
public class ScoreContext {
    ScoreMeasure scoremeasure;
    User userContext;
    Item itemContext;
    
    /**
     * Constructor de la clase
     */
    public ScoreContext(){
        
    }
    
    /**
     * Metodo get
     * @return la medida de calificaión
     * @see ScoreMeasure
     */
    public ScoreMeasure getScoreMeasure(){
        return scoremeasure;
    }
    
    /**
     * Método modificador de la metidad de calificacón
     * @param score nueva medida
     */
    public void setScoreMeasure(ScoreMeasure score){
        this.scoremeasure=score;
       
    }
    
    /**
     * Método que devuelve la calificación
     * @param u usuario
     * @param i elemento
     * @return la calificación
     */
    public double score(User u,Item i){
       return scoremeasure.score(u, i);
   }
}
