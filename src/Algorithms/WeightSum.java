/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import sistemarecomendacion.DAO.Item;
import sistemarecomendacion.DAO.User;

/**
 *
 * @author bogdan
 */
public class WeightSum extends ItemScorer implements ScoreMeasure{
    
    public WeightSum(){ 
    }
    
    public WeightSum(User u,Item i){
        
    }

    @Override
    public double itemscore(User u, Item i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double score(User u, Item i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
