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
 *Clase nterfaz que van a heredar todas las implementaciones de modelos 
 * @author bogdan
 * @version 1.0
 */
public interface InterfaceModel {
    /**
     * most similar items in similarMatrix model,this is applied for each item to be scored
     */
    public final int minRatings=30;
    /**
     * Similar items
     */
    public final int neighborhoodSize_N=30;
    
    /**
     *Método que construyo el modelo de datos en memoria
     */
    public void buildModel();
    
    /**
     *Método que devuelve el universo del modelo 
     * @return una matriz con todas la similitudes entre todos los elementos
     */
    public HashMap<Item, ArrayList<Pair> > getItemsUniverse();
    
    /**
     *Método que devuelve todos los elementos y su similitud con un elemento dado
     * @param ite elemento dado
     * @return una lista de todos los elementos del sistema junto con la similitud que forman con el elemento ite
     */
    public ArrayList<Pair> getSimilarItems(Item ite);
    
    /**
     *Método que devuelve la valoración que ha realizado un usuario a un elemento 
     * @param i elemento
     * @param u usuario
     * @return la valoración
     */
    public double getRatingOfSimilarItemUserVoted(Item  i,User u);
    
    /**
     *Método que devuelve las calificaciones del sistema
     * @return lista con todas la calificaciones realizada por los usuario a los elementos con su correspondienta valoracion
     */
    public ArrayList<Events> getEvents();
    
    /**
     *Método que devuelve los usuarios 
     * @return lista de usuario del sistema
     */
    public Set<User> getUser();
    
    /**
     *Método que devuelve una lista con todo los elementos
     * @return lista de elementos del sistema
     */
    public ArrayList<Item> getItems();
    
    /**
     *Método que devuelve las valoraciones porducidas por todos los usuarios
     * @return lista con todas las valoraciones de todos losusuarios
     */
    public HashMap<User,ArrayList<Events> > getUserEventDAO();
    
    /**
     *Método que devuelve las valoraciones obtenidas por todos los elementos
     * @return lista de todas las valoraciones para cada elementos del sistema
     */
    public HashMap<Item,ArrayList<Events> > getItemEventDAO();
    
}
