/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
