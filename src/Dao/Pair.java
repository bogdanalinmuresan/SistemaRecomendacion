/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

/**
 *
 * @author bogdan
 */
public class Pair {
    Item item1;
    double similitud=0.0;
    
    public Pair(){
        item1=new Movie();
    }

    public Pair(Item i1 ,double similitud){
        this.item1=i1;
        this.similitud=similitud;
    }
    public Item getItem1(){return item1;}
    public Double getSimilitud(){return similitud;}
    
}
