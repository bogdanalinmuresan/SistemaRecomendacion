/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosClasicos;

/**
 *
 * @author bogdan
 */
public class Pair {
    int item1=0;
    double similitud=0.0;

    Pair(int i1 ,double similitud){
        this.item1=i1;
        this.similitud=similitud;
    }
    public int getItem1(){return item1;}
    public Double getSimilitud(){return similitud;}
}
