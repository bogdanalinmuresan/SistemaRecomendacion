/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

/**
 *Clase que representa una predicción y valoración
 * @author bogdan
 * @version 1.0
 */
public class PairEvaluation {
    double first;
    double second;
    
    /**
     * Constructor con parámetros
     * @param i
     * @param j 
     */
    public PairEvaluation(int i,int j){
        first=i;
        second=j;
    }
    /**
     * Constructor por defecto
     */
    public PairEvaluation(){
        
    }
    
    /**
     * Método para devolver la predicción
     * @return la predicción
     */
    public double getFirst(){
        return first;
    }
    
    /**
     * Método modificador
     * @param f nueva predicción
     */
    public void setFirst(double f){
        this.first=f;
    }
    
    /**
     * Método que devuelve la valoración
     * @return la valoración
     */
    public double getSecond(){
        return second;
    }
    
    /**
     * Método modificador 
     * @param s la nueva caloración
     */
    public void setSecond(double s){
        this.second=s;
    }
}
