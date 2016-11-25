/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;
import sistemarecomendacion.DAO.Item;
import sistemarecomendacion.DAO.User;

/**
 *
 * @author bogdan
 */
public interface  RecommenderAlgorithms {
    
    

    public  abstract ArrayList<Item> top10Recomendation(User u);
    
    public abstract double predict(User u,Item i);
}
