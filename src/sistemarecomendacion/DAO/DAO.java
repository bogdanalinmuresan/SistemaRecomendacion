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
    Set<Integer> userDAO=new HashSet();
    //items
    ArrayList<Movie> itemsDAO=new ArrayList<Movie>();
    //provides access to events by item ID.
    HashMap itemEventDAO =new HashMap<Integer,List<Events> >();
    //provides access to events by user ID.
    HashMap userEventDAO =new HashMap<Integer,List<Events> >();
    //provides access to the database of events.(ratings)
    ArrayList eventsDAO=new ArrayList<Events>();
    
     /**
     * Método selector 
     * @return Devuelve los usuarios 
     */
    public Set<Integer> getUserDAO(){return userDAO;}
    /**
     * Método selector 
     * @return devuelve las peliculas
     */
    public ArrayList<Movie> getItemsDAO(){return itemsDAO;}
    /**
     * Método selector
     * @return los eventos de cada pelicula(ratings)
     */
    public HashMap<Integer,List<Events> > getItemEventDAO(){return itemEventDAO;}
    /**
     * Método selector
     * @return devuelve los eventos de cada usuario
     */
    public HashMap<Integer,List<Events> > getUserEventDAO(){return userEventDAO;}
    /**
     * Método selector 
     * @return devuelve los eventos (ratings)
     */
    public ArrayList<Events> getEventsDAO(){return eventsDAO;}
    
    public abstract void cargarDatosDAO();
    public abstract void cargarEventosDAO();
    public abstract void cargarItemsDAO();
    public abstract void cargarUserDAO();
    public abstract void cargarUserEventDAO();
    public abstract void cargarItemEventDAO();
    
}
