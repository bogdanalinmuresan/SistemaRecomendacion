/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import java.util.ArrayList;

/**
 *Clase interfaz que representa un metrica 
 * @author bogdan
 * @version 1.0
 */
public interface EvaluationType {
    
    /**
     * Método que calcula el error cometido
     * @param predictionRating una lista de predicciones y valoraciones
     * @return el error cometido
     */
    public abstract double calculate(ArrayList<PairEvaluation> predictionRating);
    
}
