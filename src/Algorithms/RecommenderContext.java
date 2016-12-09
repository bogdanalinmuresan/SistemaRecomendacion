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
import Ratings.ModelAPI;

/**
 *Clase que estables la conexion entre la clase api y los algoritmos 
 * @author bogdan
 * @see RecommenderApi
 */
public class RecommenderContext {
    //Interface reference
    private RecommenderAlgorithms algorithmType;
    ModelAPI modelapi;
    
    /**
     * Constructor por defecto
     */
    public RecommenderContext(){
    }

    /**
     * Método selector que devuelve el algoritmo
     * @return al algoritmo
     */
    public RecommenderAlgorithms getAlgorithmType(){
        return algorithmType;
    }
    
    /**
     * Método modificador 
     * @param rc nuevo algoritmo
     * @see RecommenderAlgorithms
     */
    public void setAlgorithmType(RecommenderAlgorithms rc){
        this.algorithmType=rc;
    }
    
    /**
     * Método modificador 
     * @param modelapi nuevo modelo de datos
     * @see ModelAPI
     */
    public void setModel(ModelAPI modelapi){
        this.modelapi=modelapi;
    }
    
    // Métodos de servicio (invocan los métodos implementados por las estrategias)
    /**
     *  Métodos de servicio (invocan los métodos implementados por las estrategias)
     * @param u usuario
     * @param N número de recomendaciones
     * @return lsita con elementos recomendados para el usuario
     */
    public ArrayList<Item> top10Recommendation(User u,int N){
       return algorithmType.topNRecomendation(u,N);
   }
   
    /**
     * Método para predecir una calificación
     * @param u usuario
     * @param i elemento
     * @return la predicción
     */
   public double prediction(User u,Item i){
       return algorithmType.predict(u, i);
   }
    
}
