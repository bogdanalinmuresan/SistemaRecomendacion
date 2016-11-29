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
    
    public int getId() { return itemId; }
    public void setId(int i) {this.itemId = i;}
    
    public Item(int Id){
        this.itemId=Id;
    }
    public Item(){
        itemId=0;
    }
    
    public Item(Item i){
        this.itemId=i.getId();
    }
    
    @Override
    public int hashCode(){
         return Objects.hash(itemId);
    }

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
