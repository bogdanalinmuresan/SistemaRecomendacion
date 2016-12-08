/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
