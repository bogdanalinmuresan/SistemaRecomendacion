/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;
import Dao.Events;

/**
 *Clase que representa la interfaz para la similitud entre elementos
 * @author bogdan
 * @version 1.0
 */
public abstract class ItemSimilarity implements SimilarityCalculate{
    /**
     * Constructor por defecto
     */
    public ItemSimilarity(){}
    
    /**
     * Método para calcular la similitud entre dos listas de calificaciones pertenecientes a dos elementos
     * @param ratingsA lista calificaciones A
     * @param ratingsB lista calificaciones B
     * @return  la similitud
     */
    public abstract double determineSimilarity(ArrayList<Events> ratingsA,ArrayList<Events> ratingsB);
}
