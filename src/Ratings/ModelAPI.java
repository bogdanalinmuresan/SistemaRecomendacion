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
package Ratings;

import Dao.Events;
import Dao.Item;
import Dao.Pair;
import Dao.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *CLase para acceder a un modelo de datos
 * @author bogdan
 * @version 1.0
 */
public class ModelAPI {
    
    ModelContext mc;
    //AccessDataAPI accessDataApi;
    
    /**
     *Constructor por defecto
     */
    public ModelAPI(){
        mc=new ModelContext();
       // accessDataApi=new AccessDataAPI();
    }
    

    /**
     *Constructor con parámetros
     * @param interfaceModel
     */
    
    public ModelAPI(InterfaceModel interfaceModel){
        mc=new ModelContext();
        mc.setModel(interfaceModel);
    }
    
    /**
     *Método modificador 
     * @param newModel nuevo modelo de datos
     */
    public void setModel(InterfaceModel newModel){
        mc.setModel(newModel);
    }

    /**
     *Devuelve el universo del modelo
     * @return el universo 
     * @see InterfaceModel#getItemsUniverse() 
     */
    
    public HashMap<Item, ArrayList<Pair> > getItemsUniverse(){
        return mc.getItemsUniverse();
    }
    
    /**
     *Devuelva la valoracion realizada por un usuario a un elemento
     * @param i elemento
     * @param u usuario
     * @return la valoracion, 0 sin no ha votado
     */
    public double getRatingOfSimilarItemUserVoted(Item  i,User u){
        return mc.getRatingOfSimilarItemUserVoted(i, u);
    }
    
    /**
     *Método que devuelve los elementos similares de un elemento dado
     * @param ite elementos
     * @return lista de elements similares
     */
    public ArrayList<Pair> getSimilarItems(Item ite){
        return mc.getSimilarItems(ite);
    }
    
    /**
     *Método que devuelve las calificaciones 
     * @return lista de calificaciones
     */
    public ArrayList<Events> getEvents(){
       return mc.getEvents();
    }
    
    /**
     *Método para obtener los usuarios 
     * @return lista de usuarios
     */
    public Set<User> getUser(){
        return mc.getUser();
    }
    
    /**
     *Método que devuelve los elementos 
     * @return lista los elementos 
     */
    public ArrayList<Item> getItems(){
        return mc.getItems();
    }

    /**
     *Método que devuelve las valoraciones porducidas por todos los usuarios
     * @return lista con todas las valoraciones de todos losusuarios
     */
    public HashMap<User,ArrayList<Events> > getUserEvent(){
        return mc.getUserEventDAO();
    }
    
    /**
     *Método que devuelve las valoraciones obtenidas por todos los elementos
     * @return lista de todas las valoraciones para cada elementos del sistema
     */
    public HashMap<Item,ArrayList<Events> > getItemEvent(){
        return mc.getItemEventDAO();
    } 
}
