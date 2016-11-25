/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosClasicos;

import sistemarecomendacion.DAO.Events;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import static sistemarecomendacion.DAO.DAO.getEventsDAO;
import static sistemarecomendacion.DAO.DAO.getItemEventDAO;
import static sistemarecomendacion.DAO.DAO.getItemsDAO;
import sistemarecomendacion.DAO.Item;
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
        
        double res;
        
        //Movie m=new Movie(itemId);
        //System.out.println("tamañio geteventitem"+DAO.getItemEventDAO().get(ite).size());
        //int tem=DAO.getItemEventDAO().get(ite).size();
        //System.out.println("tam getItemEventDao() = "+tem);
        ArrayList<Events>eventos=getEventsDAO();
        if(eventos==null) System.out.println("devuelve null weightSum ");
        int tam=eventos.size();
        boolean esta=false;
       
        
        
            ArrayList<Pair> similarItem;
            similarItem=modelo.getSimilarItems(ite);
            System.out.println("tam similar item "+similarItem.size());
            System.out.println(" item "+ite.getId());
            //for(Pair p:similarItem){
                
            //    System.out.println("similar item"+p.getItem1().getId()+" similitud : "+p.getSimilitud());
            //}
            if(similarItem!=null){
                //System.out.println("no hay elemento similares");
            
                   //System.out.println("similarItem.size() en weightSum"+similarItem.size());
                    //get the ratings for all the similar items(similar items.size()< minNeighbor) the user has seen
                double sumTop=0;
                double sumBottom=1;    
                double temp;
                //k most similar items that user u has also rated for some neightborhood
                
                for(int i=0; i< similarItem.size() ;i++){
                    //get rating for userId and similarItem[i]
                    temp=getRatingOfSimilarItemUserVoted(similarItem.get(i).getItem1(), u );
                    if(temp!=-99){
                        //System.out.println("el rating es "+temp);
                        sumTop+=temp*similarItem.get(i).getSimilitud();
                        sumBottom+=Math.abs(similarItem.get(i).getSimilitud());
                    }else{
                        
                    }
                }
                System.out.println("sum top "+sumTop);
                System.out.println("sum bottonm "+sumBottom);
                res=sumTop/sumBottom;
                
            }else{
                //no esta la pelicla en la similarityMatrixModel
                return res=-2;
            }
        //calculate the weighted sums
        
        //System.out.println("tam similar items"+similarItem.size());
         
        //System.out.println("res weightSum"+sumTop/sumBottom);
        return res;
        
    }
    
    /*find the ratings of a item that user has rate previously
     * 
     */
    public double getRatingOfSimilarItemUserVoted(Item  i,User u)
    {
        double rating=-99;
        for(Events e:getEventsDAO())
        {
           if(i.getId()==e.getItem().getId() && u.getUserId()==e.getUser().getUserId())
                rating=e.getRating();
        }
        return rating;
    }
    
          
    public ArrayList<Pair> top10Recomendation(User u){
        ArrayList<Item> res=new ArrayList<>();
        ArrayList<Pair> resultado=new ArrayList<>();
        double score;
        for(int i =0;i<10;i++)
        {
            score=weightedSum(u, (Movie) getItemsDAO().get(i));
            resultado.add(new Pair((Movie) getItemsDAO().get(i) ,score));
        }
        
        Collections.sort(resultado , new Comparator<Pair>() {
                @Override
                public int compare(Pair p1, Pair p2)
                {
                    //return p1.getSimilitud().compareTo(p2.getSimilitud());
                    if(p1.getSimilitud()>p2.getSimilitud())
                        return -1;
                    else if(p1.getSimilitud()<p2.getSimilitud())
                        return 1;
                    else return 0;
                }
        });
        
        return resultado;
    }
}
