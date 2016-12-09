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
