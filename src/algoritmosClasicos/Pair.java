/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosClasicos;

import java.util.Comparator;

/**
 *
 * @author bogdan
 */
public class Pair   {
    int item1=0;
    double similitud=0.0;

    Pair(int i1 ,double similitud){
        this.item1=i1;
        this.similitud=similitud;
    }
    public int getItem1(){return item1;}
    public Double getSimilitud(){return similitud;}
    
}

    class MyComparator implements Comparator<Pair> {
 
         @Override
         public int compare(Pair o1, Pair o2)
         {
             if(o1.getSimilitud()>o2.getSimilitud())
                 return -1;
             else if(o1.getSimilitud()<o2.getSimilitud())
                 return 1;
             else return 0;
         }
     }

