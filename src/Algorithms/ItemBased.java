/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;
import static sistemarecomendacion.DAO.DAO.getEventsDAO;
import sistemarecomendacion.DAO.Events;
import sistemarecomendacion.DAO.Item;
import sistemarecomendacion.DAO.User;
import Ratings.*;

/**
 *
 * @author bogdan
 */
public class ItemBased extends ColaborativeFiltering{
    private WeightSum ws;
    private CosineSimilarity c;
    ModelDataSet mds;

    public ItemBased() {
        
    }
    
    public ItemBased(ModelDataSet mds){
        this.mds=mds;
    }

   

    @Override
    public ArrayList<Item> top10Recomendation(User u) {
        ArrayList<Item> res=new ArrayList<>();
        
        return res;
    }

    @Override
    public double predict(User u, Item ite) {
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
            similarItem=mds.getSimilarItems(ite);
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


}
