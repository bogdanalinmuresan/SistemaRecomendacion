/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;
import Dao.Events;

/**
 *clase interfaz que va a hereder la clases que implemente un medida de similitud
 * @author bogdan
 * @version 1.0
 * 
 */
public interface SimilarityCalculate {
    
    /**
     * Método que mide la similitud entre dos listas de calificaciones
     * @param ratingsA lista de calificaciones A
     * @param ratingsB lista de calificaciones B
     * @return la similitud
     */
    public double compare(ArrayList<Events> ratingsA,ArrayList<Events> ratingsB);
    
}
