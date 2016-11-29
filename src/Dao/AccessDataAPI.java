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
public class AccessDataAPI {
    DataContext dc;
    
    public AccessDataAPI(){
        this.dc=new DataContext();
        
    }
    
    public void accessSQL(String user ,String pass,String cadena){
        AccessDataJDBC ajdbc=new AccessDataJDBC(user, pass, cadena);
        dc.setAccessType(ajdbc);
    }
    
    public void accessNOSQL(String use, String pass,String cadena){
        AccessDataNOSQL nosql=new AccessDataNOSQL(use,pass,cadena);
        dc.setAccessType(nosql);
    }
    
    public ArrayList<Events> getEvents(){
        return dc.getEvents();
    }
    
    public HashMap<Item,ArrayList<Events>> getItemEvent(){
        return dc.getItemEvent();
    }
    
     public Set<User> getUser(){
         return dc.getUser();
     }
     
    public ArrayList<Item> getItems(){
        return dc.getItems();
    }
    
    public HashMap<User,ArrayList<Events>> getUserEvent(){
        return dc.getUserEvent();
    }
    
    
      
    
    
}
