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

/**
 *Clase que representa una predicción y valoración
 * @author bogdan
 * @version 1.0
 */
public class PairEvaluation {
    double first;
    double second;
    
    /**
     * Constructor con parámetros
     * @param i
     * @param j 
     */
    public PairEvaluation(int i,int j){
        first=i;
        second=j;
    }
    /**
     * Constructor por defecto
     */
    public PairEvaluation(){
        
    }
    
    /**
     * Método para devolver la predicción
     * @return la predicción
     */
    public double getFirst(){
        return first;
    }
    
    /**
     * Método modificador
     * @param f nueva predicción
     */
    public void setFirst(double f){
        this.first=f;
    }
    
    /**
     * Método que devuelve la valoración
     * @return la valoración
     */
    public double getSecond(){
        return second;
    }
    
    /**
     * Método modificador 
     * @param s la nueva caloración
     */
    public void setSecond(double s){
        this.second=s;
    }
}
