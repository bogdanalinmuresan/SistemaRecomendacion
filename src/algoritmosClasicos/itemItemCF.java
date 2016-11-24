/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosClasicos;

import sistemarecomendacion.DAO.Events;
import java.util.ArrayList;
import static sistemarecomendacion.DAO.DAO.getEventsDAO;
import static sistemarecomendacion.DAO.DAO.getItemEventDAO;
import sistemarecomendacion.DAO.Movie;
import sistemarecomendacion.DAO.User;
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
     * @param u
     * @param ite
     * @return 
     */
    public double weightedSum(User u,Movie ite)
    {
        double sumTop=0;
        double sumBottom=1;
        double res;
        
        //Movie m=new Movie(itemId);
        //System.out.println("tamañio geteventitem"+DAO.getItemEventDAO().get(ite).size());
        //int tem=DAO.getItemEventDAO().get(ite).size();
        //System.out.println("tam getItemEventDao() = "+tem);
        ArrayList<Events>eventos=getItemEventDAO().get(ite);
        if(eventos==null) System.out.println("es null ");
        int tem=eventos.size();
        boolean esta=false;
        
        for(int i=0;i<tem && !esta;i++){
            if(u.getUserId()==eventos.get(i).getUser().getUserId())
                esta=true;
        }
        if(esta){
            res=-1;
            //System.out.println("esta peli ya la ha votado el usuario ");
        }else{
            ArrayList<Pair> similarItem;
            similarItem=modelo.getSimilarItems(ite);
            if(similarItem!=null){
                //System.out.println("no hay elemento similares");
            
                   //System.out.println("similarItem.size() en weightSum"+similarItem.size());
                    //get the ratings for all the similar items(similar items.size()< minNeighbor) the user has seen
                    double temp;
                    for(int i=0; i< similarItem.size();i++){
                        //get rating for userId and similarItem[i]
                        temp=getRatingOfItemUserVoted(getEventsDAO(), u);
                        sumTop+=temp*similarItem.get(i).getSimilitud();
                        sumBottom+=Math.abs(similarItem.get(i).getSimilitud());
                    }
            }else{
                //no esta la pelicla en la similarityMatrixModel
                return res=-2;
            }
        //calculate the weighted sums
        
        //System.out.println("tam similar items"+similarItem.size());
        } 
        //System.out.println("res weightSum"+sumTop/sumBottom);
        return res= sumTop/sumBottom;
        
    }
    
    /*find the ratings of a item that user has rate previously
     * 
     */
    public double getRatingOfItemUserVoted(ArrayList<Events> v,User user)
    {
        for(Events e:v)
        {
            if(user.getUserId()==e.getItem().getId())
                return e.getRating();
        }
        return -1; 
    }

}