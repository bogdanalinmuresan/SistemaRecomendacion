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
public abstract class ItemScorer  {
    
    public ItemScorer(){
        
    }
    
    public abstract double itemscore(User u,Item i);
    
}
