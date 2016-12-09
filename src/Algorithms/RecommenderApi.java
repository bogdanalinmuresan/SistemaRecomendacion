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
 *Clase api a los algoritmos de recomendacón 
 * @author bogdan
 * @version 1.0
 */
public class RecommenderApi {
    private final RecommenderContext rc;
    private  ModelAPI modelapi;
    
    /**
     * Constructor por defecto
     */
    public RecommenderApi(){
        this.rc=new RecommenderContext();
        this.modelapi=new ModelAPI();
    }
    
    /**
     * Constructor con párametros
     * @param mds acceso al modelo de datos
     * @see ModelAPI
     */
    public RecommenderApi(ModelAPI mds){
        rc=new RecommenderContext();
        this.modelapi=mds;
    }
    
    /**
     * Añadir nuevo algoritmo
     * @param ra nuevo algoritmo
     */
    public void addAlgorithm(RecommenderAlgorithms ra){
        rc.setAlgorithmType(ra);
    }
    
    /**
     * Método que devuelve la predicción para un usuario y un elemento 
     * @param u usuario
     * @param i elemento
     * @return la prediccion
     */
    public double prediction(User u, Item i){
        return rc.prediction(u, i);
    }
    
    /**
     * Devuelve los N elementos más recomendados para un usuario
     * @param i usuario
     * @param N los elementos a recomendar
     * @return una lista de los N elementos más recomendados
     */
    public ArrayList<Item> topNRecomendation(User i,int N){
        return rc.top10Recommendation(i,N);
    }
    
    
    
}
