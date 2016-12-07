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
 *
 * @author bogdan
 */
public class ScoreAPI {
    ScoreContext measurecontext;
    private final ModelAPI modelapi;
    ScoreMeasure sc;
    
    public ScoreAPI(ModelAPI mapi){
        this.measurecontext=new ScoreContext();
        this.modelapi=mapi;
    }
   
    public double score(User u,Item i){
        return measurecontext.score(u, i);
    }
    
    public ScoreAPI(ModelAPI mapi,ScoreMeasure sc){
        
        this.modelapi=mapi;
        this.measurecontext=new ScoreContext();
        measurecontext.setScoreMeasure(sc);
    }
    
    public void setScoreMeasure(ScoreMeasure sc){
        this.sc=sc;
        measurecontext.setScoreMeasure(sc);
    }
    
    
}
