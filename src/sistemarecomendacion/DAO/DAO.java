/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion.DAO;

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
    static Set userDAO=new HashSet<User>();
    //items
    static ArrayList itemsDAO=new ArrayList<Movie>();
    //provides access to events by item ID.
    static HashMap itemEventDAO =new HashMap<Movie,ArrayList<Events>>();
    //provides access to events by user ID.
    static HashMap userEventDAO =new HashMap<User,ArrayList<Events>>();
    //provides access to the database of events.(ratings)
    static ArrayList eventsDAO=new ArrayList<Events>();
    
     /**
     * Método selector 
     * @return Devuelve los usuarios 
     */
    public static Set<User> getUserDAO(){return userDAO;}
    /**
     * Método selector 
     * @return devuelve las peliculas
     */
    public static ArrayList<Movie> getItemsDAO(){return itemsDAO;}
    /**
     * Método selector
     * @return los eventos de cada pelicula(ratings)
     */
    public static HashMap<Movie,ArrayList<Events> > getItemEventDAO(){return itemEventDAO;}
    /**
     * Método selector
     * @return devuelve los eventos de cada usuario
     */
    public  static HashMap<User,List<Events> > getUserEventDAO(){return userEventDAO;}
    /**
     * Método selector 
     * @return devuelve los eventos (ratings)
     */
    public static ArrayList<Events> getEventsDAO(){return eventsDAO;}
    
    public abstract void cargarDatosDAO();
    public abstract void cargarEventosDAO();
    public abstract void cargarItemsDAO();
    public abstract void cargarUserDAO();
    public abstract void cargarUserEventDAO();
    public abstract void cargarItemEventDAO();
    
}
