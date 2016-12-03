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
    DataContext datacontext;
    
    public AccessDataAPI(){
        this.datacontext=new DataContext();
        
    }
    
    public void addNewConnection(InterfazCliente ic){
        datacontext.setAccessType(ic);
    }
    
    public void accessSQL(String user ,String pass,String cadena){
        AccessDataJDBC ajdbc=new AccessDataJDBC(user, pass, cadena);
        datacontext.setAccessType(ajdbc);
    }
    
    public void accessNOSQL(String use, String pass,String cadena){
        AccessDataNOSQL nosql=new AccessDataNOSQL(use,pass,cadena);
        datacontext.setAccessType(nosql);
    }
    
    public ArrayList<Events> getEvents(){
        return datacontext.getEvents();
    }
    
    public HashMap<Item,ArrayList<Events>> getItemEvent(){
        return datacontext.getItemEvent();
    }
    
     public Set<User> getUser(){
         return datacontext.getUser();
     }
     
    public ArrayList<Item> getItems(){
        return datacontext.getItems();
    }
    
    public HashMap<User,ArrayList<Events>> getUserEvent(){
        return datacontext.getUserEvent();
    }
    
    
      
    
    
}
