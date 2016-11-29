/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author bogdan
 */
public abstract class DAO {
     //users 
    private  Set userDAO=new HashSet<User>();
    //items
    private ArrayList itemsDAO=new ArrayList<Item>();
    //provides access to events by item ID.
    private HashMap itemEventDAO =new HashMap<Item,ArrayList<Events>>();
    //provides access to events by user ID.
    private HashMap userEventDAO =new HashMap<User,ArrayList<Events>>();
    //provides access to the database of events.(ratings)
    private ArrayList eventsDAO=new ArrayList<Events>();
    
     /**
     * Método selector 
     * @return Devuelve los usuarios 
     */
    public   Set<User> getUserDAO(){return userDAO;}
    /**
     * Método selector 
     * @return devuelve las peliculas
     */
    public  ArrayList<Item> getItemsDAO(){return itemsDAO;}
    /**
     * Método selector
     * @return los eventos de cada pelicula(ratings)
     */
    public HashMap<Item,ArrayList<Events> > getItemEventDAO(){return itemEventDAO;}
    /**
     * Método selector
     * @return devuelve los eventos de cada usuario
     */
    public HashMap<User,ArrayList<Events> > getUserEventDAO(){return userEventDAO;}
    /**
     * Método selector 
     * @return devuelve los eventos (ratings)
     */
    public  ArrayList<Events> getEventsDAO(){return eventsDAO;}
    
    public abstract void cargarDatosDAO();
    public abstract void cargarEventosDAO();
    public abstract void cargarItemsDAO();
    public abstract void cargarUserDAO();
    public abstract void cargarUserEventDAO();
    public abstract void cargarItemEventDAO();
    
}
