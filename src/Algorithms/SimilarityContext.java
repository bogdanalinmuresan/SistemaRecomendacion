/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Dao.Events;
import java.util.ArrayList;

/**
 *Clase que establece la relacion entre la api y las clase que implementa las medidas de similitud
 * @author bogdan
 * @version 1.0
 * @see SimilarityApi
 * @see SimilarityCalculate
 */
public class SimilarityContext {
    SimilarityCalculate interfaceSimilarityCalculate;
    
    /**
     * Constructor por defecto
     */
    public SimilarityContext(){
        
    }
    
    /**
     * Método selector
     * @return la interfaz 
     * @see SimilarityCalculate
     */
    public SimilarityCalculate getSimilarity(){
        return interfaceSimilarityCalculate;
    }
    /**
     * Establecer nueva medida de cálculo de similitud
     * @param simcal 
     */
    public void setSimilarity(SimilarityCalculate simcal){
        this.interfaceSimilarityCalculate=simcal;
    }
    /**
     * Método que cálcula la similitud 
     * @param ratingsA lista de calificaciones A
     * @param ratingsB lista de calificaciones B
     * @return  la similitud entre las dos listas de calificaciones
     */
    public double compare(ArrayList<Events> ratingsA,ArrayList<Events> ratingsB){
        return interfaceSimilarityCalculate.compare(ratingsA, ratingsB);
    }
    
    
}

