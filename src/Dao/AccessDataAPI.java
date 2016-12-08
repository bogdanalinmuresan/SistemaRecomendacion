/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *Clase que establace la interfaz del módulo para acceso a fuentes de datos
 * @author bogdan
 */
public class AccessDataAPI {
    DataContext datacontext;
    
    /**
     * Constructor por defecto
     */
    public AccessDataAPI(){
        this.datacontext=new DataContext();
        
    }
    /**
     * Añadir nueva conexión 
     * @param ic nueva conexión
     */
    public void addNewConnection(InterfazCliente ic){
        datacontext.setAccessType(ic);
    }
   
    /**
     * Método que devuelve todas la calificaciones
     * @return  todas la calificaciones
     */
    public ArrayList<Events> getEvents(){
        return datacontext.getEvents();
    }
    
    /**
     * Método que devuelve todas las calificaciones de todos los elementos
     * @return las calificaciones de todos los elementos
     */
    public HashMap<Item,ArrayList<Events>> getItemEvent(){
        return datacontext.getItemEvent();
    }
    
    /**
     * Método que devuelve todos los usuarios
     * @return todos los usuarios
     */
     public Set<User> getUser(){
         return datacontext.getUser();
     }
     
     /**
      * Método que duevuelve todo los elementos 
      * @return todo los elementos 
      */
    public ArrayList<Item> getItems(){
        return datacontext.getItems();
    }
    /**
     * Devuelve las calificaciones de todos los usuarios
     * @return de todos los usuarios
     */
    public HashMap<User,ArrayList<Events>> getUserEvent(){
        return datacontext.getUserEvent();
    }
}
