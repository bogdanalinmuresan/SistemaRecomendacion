/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Dao.Item;
import Dao.User;
import Ratings.ModelAPI;

/**
 *Esta clase ofrece una api a los algoritmos de calificacion 
 * @author bogdan
 * @version 1.0
 */
public class ScoreAPI {
    ScoreContext measurecontext;
    private  ModelAPI modelapi;
    ScoreMeasure sc;
    
    /**
     * Constructor de la clase
     * @param mapi objeto para acceso al modelo 
     */
    public ScoreAPI(ModelAPI mapi){
        this.measurecontext=new ScoreContext();
        this.modelapi=mapi;
    }
   
    /**
     * Método que califica un item dado un usuario
     * @param u usuario 
     * @param i elemento
     * @return La calificación
     */
    public double score(User u,Item i){
        return measurecontext.score(u, i);
    }
    
    /**
     * Constructor de la clase
     * @param mapi acceso al modelo 
     * @param sc medida de calificación
     */
    public ScoreAPI(ModelAPI mapi,ScoreMeasure sc){
        
        this.modelapi=mapi;
        this.measurecontext=new ScoreContext();
        measurecontext.setScoreMeasure(sc);
    }
    
    /**
     * Método modificador para establecer nueva medida de calificación
     * @param sc  nueva medida de calificación
     */
    public void setScoreMeasure(ScoreMeasure sc){
        this.sc=sc;
        measurecontext.setScoreMeasure(sc);
    }
    
    
}
