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
public class ModelContext {
    InterfaceModel im;

    public void setModel(InterfaceModel im){
        this.im=im;
    }
    
    public InterfaceModel getModel(){
        return im;
    }
    
    public HashMap<Item, ArrayList<Pair> > getItemsUniverse(){
        return im.getItemsUniverse();
    }
    
    public ArrayList<Pair> getSimilarItems(Item ite){
        return im.getSimilarItems(ite);
    }
    
     public double getRatingOfSimilarItemUserVoted(Item  i,User u){
         return im.getRatingOfSimilarItemUserVoted(i, u);
     }
    
     public ArrayList<Events> getEvents(){
         return im.getEvents();
     }
    
    public Set<User> getUser(){
        return im.getUser();
    }
    
    
    public ArrayList<Item> getItems(){
        return im.getItems();
    }
    
    public HashMap<User,ArrayList<Events> > getUserEventDAO(){
        return im.getUserEventDAO();
    }
    
    public HashMap<Item,ArrayList<Events> > getItemEventDAO(){
        return im.getItemEventDAO();
    }
}
