/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;
import Dao.Item;
import Dao.User;

/**
 *
 * @author bogdan
 */
public interface  RecommenderAlgorithms {
    
    

    public  abstract ArrayList<Item> topNRecomendation(User u,int N);
    
    public abstract double predict(User u,Item i);
}
