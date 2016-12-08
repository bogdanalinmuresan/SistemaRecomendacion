/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Dao.Events;
import java.util.ArrayList;

/**
 *clase que actua de interfaz para las medidas de similitud
 * @author bogdan
 * @version 1.0
 */
public class SimilarityApi {
    SimilarityContext similarityContext;
    /**
     * Constructor de la clase
     */
    public SimilarityApi(){
        similarityContext=new SimilarityContext();
    }
    /**
     * Añadir nueva medida de similitud
     * @param similCalculate nueva medida
     */
    public void addSimilarity(SimilarityCalculate similCalculate){
        similarityContext.setSimilarity(similCalculate);
    }
    /**
     * Método que devuelve la similitud 
     * @param ratingsA lista de calificaciones A
     * @param ratingsB lista de calificaciones B
     * @return  la similitud
     */
    public double compare(ArrayList<Events> ratingsA,ArrayList<Events> ratingsB){
        return similarityContext.compare(ratingsA, ratingsB);
    }
}
