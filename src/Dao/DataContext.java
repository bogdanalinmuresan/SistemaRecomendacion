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
