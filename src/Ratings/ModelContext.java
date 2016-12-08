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
 *Clase intermedia entre los modelo y la interfaz del modúlo
 * @author bogdan
 */
public class ModelContext {
    InterfaceModel im;

    /**
     *Método modificador 
     * @param im nuevo modelo de datos
     */
    public void setModel(InterfaceModel im){
        this.im=im;
    }
    
    /**
     *Método selector
     * @return el modelo de datos
     */
    public InterfaceModel getModel(){
        return im;
    }
    
    /**
     *Devuelve el universo del modelo
     * @return el universo del modelo
     * @see InterfaceModel#getItemsUniverse() 
     */
    public HashMap<Item, ArrayList<Pair> > getItemsUniverse(){
        return im.getItemsUniverse();
    }
    
    /**
     *Devuelve los elementos similares a un elementos
     * @param ite elemento dado
     * @return lista de elementos similares
     */
    public ArrayList<Pair> getSimilarItems(Item ite){
        return im.getSimilarItems(ite);
    }
    
    /**
     *Método para devolver la valoracion de un usuario a un elementos
     * @param i elemento
     * @param u usuario
     * @return la valoración
     */
    public double getRatingOfSimilarItemUserVoted(Item  i,User u){
         return im.getRatingOfSimilarItemUserVoted(i, u);
     }
    
    /**
     *Método que devuelve las calificaciones del sistema
     * @return lista de calificaciones
     */
    public ArrayList<Events> getEvents(){
         return im.getEvents();
     }
    
    /**
     *Metodo para acceder a los usuarios del sistema
     * @return lista de usuarios
     */
    public Set<User> getUser(){
        return im.getUser();
    }
    
    /**
     *Método para devolver los elementos del sistema
     * @return lista de elementos
     */
    public ArrayList<Item> getItems(){
        return im.getItems();
    }
    
    /**
     *Método que devuelve las valoraciones porducidas por todos los usuarios
     * @return lista con todas las valoraciones de todos losusuarios
     */
    public HashMap<User,ArrayList<Events> > getUserEventDAO(){
        return im.getUserEventDAO();
    }
    
    /**
     *Método que devuelve las valoraciones obtenidas por todos los elementos
     * @return lista de todas las valoraciones para cada elementos del sistema
     */
    public HashMap<Item,ArrayList<Events> > getItemEventDAO(){
        return im.getItemEventDAO();
    }
}
