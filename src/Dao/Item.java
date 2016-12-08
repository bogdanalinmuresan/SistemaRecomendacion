/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
