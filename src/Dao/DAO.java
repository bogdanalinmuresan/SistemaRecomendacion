/* 
 * Copyright (C) 2016 Bogdan Alin Muresan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *Clase abstracta que representa el acceso a una fuente de datos 
 * @author bogdan
 * @version 1.0
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
    
    /**
     *Método que carga los datos en memoria 
     */
    public abstract void cargarDatosDAO();

    /**
     *Método que carga las calificaciones
     */
    public abstract void cargarEventosDAO();

    /**
     *Método que carga los elementos
     */
    public abstract void cargarItemsDAO();

    /**
     *Método que carga los usuarios 
     */
    public abstract void cargarUserDAO();

    /**
     *Método que cara las calificaciones de los usuarios
     */
    public abstract void cargarUserEventDAO();

    /**
     *Método que carga las calificaciones de los elementos
     */
    public abstract void cargarItemEventDAO();
    
}
