/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ratings;

import Dao.Events;
import Dao.Item;
import Dao.Pair;
import Dao.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author bogdan
 */
public interface InterfaceModel {
    /**
     * most similar items in similarMatrix model,this is applied for each item to be scored
     */
    public final int minRatings=0;
    
    /**
     * Similar items
     */
    public final int neighborhoodSize_N=30;
    
    public void buildModel();
    
    public HashMap<Item, ArrayList<Pair> > getItemsUniverse();
    
    public ArrayList<Pair> getSimilarItems(Item ite);
    
    public double getRatingOfSimilarItemUserVoted(Item  i,User u);
    
    public ArrayList<Events> getEvents();
    
    public Set<User> getUser();
    
    public ArrayList<Item> getItems();
    
    
}
