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

import java.util.Objects;

/**
 *clase generica que representa un item en nuestro sistema
 * @author bogdan
 */
public  class Item {
    int itemId;
    
    /**
     * método selector
     * @return identificador del elemento
     */
    public int getId() { return itemId; }
    /**
     * Método modificador 
     * @param i nuevo identificador de elemento
     */
    public void setId(int i) {this.itemId = i;}
    
    /**
     * Constructor con parámetros
     * @param Id 
     */
    public Item(int Id){
        this.itemId=Id;
    }
    
    /**
     * Constructor por defecto
     */
    public Item(){
        itemId=0;
    }
    
    /**
     * Constructor de copia 
     * @param i 
     */
    public Item(Item i){
        this.itemId=i.getId();
    }
    
    /**
     * Sobre escritura del método hashcode()
     * @return un identificador del elemento
     */
    @Override
    public int hashCode(){
         return Objects.hash(itemId);
    }

    /**
     * Método que devuelve si dos objetos son iguales
     * @param obj
     * @return true si dos objetos son iguales
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        /*
        if (getClass() != obj.getClass()) {
            return false;
        }
                */
        final Item other = (Item) obj;
        return this.itemId == other.itemId;
    }
    
}
