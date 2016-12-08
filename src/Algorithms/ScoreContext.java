/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Dao.Item;
import Dao.User;

/**
 *Esta clase es la encargada de establecer la conexion entre las estrategias y la interfaz
 * @author bogdan
 * @version 1.0
 */
public class ScoreContext {
    ScoreMeasure scoremeasure;
    User userContext;
    Item itemContext;
    
    /**
     * Constructor de la clase
     */
    public ScoreContext(){
        
    }
    
    /**
     * Metodo get
     * @return la medida de calificaión
     * @see ScoreMeasure
     */
    public ScoreMeasure getScoreMeasure(){
        return scoremeasure;
    }
    
    /**
     * Método modificador de la metidad de calificacón
     * @param score nueva medida
     */
    public void setScoreMeasure(ScoreMeasure score){
        this.scoremeasure=score;
       
    }
    
    /**
     * Método que devuelve la calificación
     * @param u usuario
     * @param i elemento
     * @return la calificación
     */
    public double score(User u,Item i){
       return scoremeasure.score(u, i);
   }
}
