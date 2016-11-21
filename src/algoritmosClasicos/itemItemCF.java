/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosClasicos;

import sistemarecomendacion.DAO.Events;
import java.util.ArrayList;
import java.util.List;
import sistemarecomendacion.DAO.DAO;
import static sistemarecomendacion.DAO.DAO.getEventsDAO;
/**
 *
 * @author bogdan
 */
public class itemItemCF implements knn{
    ItemItemModel modelo=new ItemItemModel();
    
    /**
     * constructor
     * @param model
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
        double sumBottom=1;
        
        int tem=DAO.getItemEventDAO().get(itemId).size();
        List<Events>eventos=DAO.getItemEventDAO().get(itemId);
        boolean esta=false;
        for(int i=0;i<tem && !esta;i++){
            if(userId==eventos.get(i).getUserID())
                esta=true;
        }
        if(esta){
            System.out.println("esta peli ya la ha votado el usuario ");
        }else{
      
            //get the similar items to itemId 
            ArrayList<Pair> similarItem;
            similarItem=modelo.getSimilarItems(itemId);
            if(similarItem==null)
                System.err.println("es null");
            //get the ratings for all the similar items(similar items.size()< minNeighbor) the user has seen
            double temp;
            for(int i=0; i< similarItem.size();i++){
                //get rating for userId and similarItem[i]
                temp=getRatingOfItemUserVoted(getEventsDAO(), userId);
                sumTop+=temp*similarItem.get(i).getSimilitud();
                sumBottom+=Math.abs(similarItem.get(i).getSimilitud());
            }
        //calculate the weighted sums
        
        //System.out.println("tam similar items"+similarItem.size());
        } 
            
        return sumTop/sumBottom;
        
    }
    
    /*find the ratings of a item that user has rate previously
     * 
     */
    public double getRatingOfItemUserVoted(ArrayList<Events> v,int userId)
    {
        for(Events e:v)
        {
            if(userId==e.getMovieID())
                return e.getRating();
        }
        return -1; 
    }

}