/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

/**
 *Clase absracta que tiene que heredar los algoritmos de la familia de algoritmos colaborativo
 * @author bogdan
 * @version 1.0
 */
public abstract class ColaborativeFiltering implements RecommenderAlgorithms{
    /**
     *número de calificaciones que debe tener un elemento
     */
    public final int minRatings=30;
    
    /**
     * numero de vecinos similares a un elemento
     */
    public final int neighborhoodSize_N=30;
}
