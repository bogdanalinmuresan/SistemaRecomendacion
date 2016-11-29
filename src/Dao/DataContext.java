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
 *
 * @author bogdan
 */
public class DataContext {
    InterfazCliente ic;
    
  
    public DataContext(){
        
    }
    
    public void setAccessType(InterfazCliente ic){
        this.ic=ic;
    }
    
   
    public InterfazCliente getAccessType(){
        return ic;
    }
    
    public void cargarDatos(){
        ic.cargarDatosDAO();
    }
    
    public ArrayList<Events> getEvents(){
       return ic.getEventsDAO();
    }
    
    public HashMap<Item,ArrayList<Events>> getItemEvent(){
        return ic.getItemEventDAO();
    }
    
    public Set<User> getUser(){
        return ic.getUserDAO();
    }
    
    public ArrayList<Item> getItems(){
        return ic.getItemsDAO();
    }
    
    public HashMap<User,ArrayList<Events>> getUserEvent(){
        return ic.getUserEventDAO();
    }
    
 
   
}
