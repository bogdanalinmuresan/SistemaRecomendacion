/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ratings;

import Dao.Item;
import Dao.Pair;
import Dao.User;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author bogdan
 */
public interface InterfaceModel {
    /**
     * most similar items in similarMatrix model,this is applied for each item to be scored
     */
    public final int minRatings=1;
    
    /**
     * Similar items
     */
    public final int neighborhoodSize_N=30;
    
    public void buildModel();
    
    public HashMap<Item, ArrayList<Pair> > getItemsUniverse();
    
    public ArrayList<Pair> getSimilarItems(Item ite);
    
    public double getRatingOfSimilarItemUserVoted(Item  i,User u);
    
}
