/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
