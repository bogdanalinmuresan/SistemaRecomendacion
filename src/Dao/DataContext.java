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
 *Clase que establece la relacion entre las formas de acceder a una fuente de datos y la api de este paquete
 * @author bogdan
 */
public class DataContext {
    InterfazCliente ic;
    
    /**
     * Constructor por defecto
     */
    public DataContext(){
        
    }
    /**
     * Método para establecer nueva conexion a fuentes de datos
     * @param ic 
     */
    public void setAccessType(InterfazCliente ic){
        this.ic=ic;
    }
    
   /**
    * Método selector
    * @return la interfaz 
    * @see InterfazCliente
    */
    public InterfazCliente getAccessType(){
        return ic;
    }
    
    /**
     * Cargar los datos en memoria 
     */
    public void cargarDatos(){
        ic.cargarDatosDAO();
    }
    /**
     * Método que devuelve las calificaciones
     * @return las calificaciones
     */
    public ArrayList<Events> getEvents(){
       return ic.getEventsDAO();
    }
    /**
     * 
     * @return 
     * @see DAO#getItemEventDAO() 
     */
    public HashMap<Item,ArrayList<Events>> getItemEvent(){
        return ic.getItemEventDAO();
    }
    /**
     * 
     * @return 
     * @see DAO#getUserDAO() 
     */
    public Set<User> getUser(){
        return ic.getUserDAO();
    }
    /**
     * 
     * @return
     * @see DAO#getItemsDAO() 
     */
    public ArrayList<Item> getItems(){
        return ic.getItemsDAO();
    }
    /**
     * 
     * @return
     * @see DAO#getUserEventDAO() 
     */
    public HashMap<User,ArrayList<Events>> getUserEvent(){
        return ic.getUserEventDAO();
    }
    
 
   
}
