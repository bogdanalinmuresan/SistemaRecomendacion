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
