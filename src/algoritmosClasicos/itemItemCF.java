/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosClasicos;

import static java.lang.Math.sqrt;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sistemarecomendacion.DAO.Events;
import sistemarecomendacion.DAO.*;
import static sistemarecomendacion.DAO.DAO.getItemEventDAO;
import algoritmosClasicos.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import static sistemarecomendacion.DAO.DAO.getEventsDAO;
/**
 *
 * @author bogdan
 */
public class itemItemCF implements knn{
    ItemItemModel modelo=new ItemItemModel();
    
    /**
     * constructor
     */
    public itemItemCF(ItemItemModel model){
        this.modelo=model;
        
    }
    
    /**
     * Item scorer
     * @param userId
     * @param itemId
     * @return 
     */
    public double weightedSum(int userId,int itemId)
    {
        double sumTop=0;
        double sumBottom=0;
        
       
        //get the similar items to itemId 
        ArrayList<Pair> similarItem=new ArrayList();
        similarItem=modelo.getSimilarItems(itemId);
        if(similarItem==null)
            System.out.println("es null");
        // Sorting
        Collections.sort(similarItem , new Comparator<Pair>() {
                @Override
                public int compare(Pair p1, Pair p2)
                {

                    return p1.getSimilitud().compareTo(p2.getSimilitud());
                }
            });
        
        //get the ratings for all the similar items the user has seen
        double temp=0;
        for(int i=0; i<knn.minNeighbor && similarItem.size()>i ;i++){
            //get rating for userId and similarItem[i]
            temp=getRating(getEventsDAO(), itemId);
            sumTop+=temp*similarItem.get(i).getSimilitud();
            sumBottom+=Math.abs(similarItem.get(i).getSimilitud());
        }
        //calculate the weighted sums
        
        //System.out.println("tam similar items"+similarItem.size());
        
        return sumTop/sumBottom;
        
    }
    
    /*find the ratings from a list of events*
     * 
     */
    public double getRating(ArrayList<Events> v,int item)
    {
        for(Events e:v)
        {
            if(item==e.getMovieID())
                return e.getRating();
        }
        return -1;
        
        
    }

}