/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Dao.Item;
import Dao.User;

/**
 *
 * @author bogdan
 */
public class ScoreContext {
    ScoreMeasure scoremeasure;
    User userContext;
    Item itemContext;
    
    public ScoreContext(){
        
    }
    
    public ScoreMeasure getScore(){
        return scoremeasure;
    }
    
    public void setScoreMeasure(ScoreMeasure score){
        this.scoremeasure=score;
       
    }
    
    public double score(User u,Item i){
       return scoremeasure.score(u, i);
   }
}
