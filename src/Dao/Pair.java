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

/**
 *Clase que representa a un elemento y una similitud con otro elemento
 * @author bogdan
 * @version 1.0
 */
public class Pair {
    Item item1;
    double similitud=0.0;
    
    /**
     * Constructor por defecto
     */
    public Pair(){
        item1=new Item();
    }
    
    /**
     * Constructor de copia
     * @param p 
     */
    public Pair(Pair p){
        this.item1=p.item1;
        this.similitud=p.similitud;
    }

    /**
     * Constructor con parámetros
     * @param i1
     * @param similitud 
     */
    public Pair(Item i1 ,double similitud){
        this.item1=i1;
        this.similitud=similitud;
    }
    /**
     * Método selectro
     * @return el elemento
     */
    public Item getItem1(){return item1;}
    /**
     * Método selector 
     * @return la similitud
     */
    public Double getSimilitud(){return similitud;}
    
}
