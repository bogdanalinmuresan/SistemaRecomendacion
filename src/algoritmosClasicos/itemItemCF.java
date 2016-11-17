/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosClasicos;

import static java.lang.Math.sqrt;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sistemarecomendacion.DAO.Events;
import static sistemarecomendacion.DAO.DAO.getItemEventDAO;

/**
 *
 * @author bogdan
 */
public class itemItemCF {
    
    public  HashMap<Integer, HashMap<Integer, Double>> buildModel()
    {
        HashMap<Integer, HashMap<Integer, Double>> similarityMatrixModel=new HashMap<>();
        
        for (Map.Entry<Integer, List<Events>> entry : getItemEventDAO().entrySet()) {
        Integer item1=entry.getKey();
        List<Events> ratingsA=entry.getValue();
        
            HashMap itemSimilarity=new HashMap<Integer,Double>();
            for (Map.Entry<Integer, List<Events>> entryB : getItemEventDAO().entrySet()) {
                Integer item2=entryB.getKey();
                List<Events> ratingsB=entryB.getValue();
            
                double similitud=determineSimilarity(ratingsA, ratingsB);
                
                itemSimilarity.put(item2, similitud);
            }
            
            similarityMatrixModel.put(item1, (HashMap<Integer, Double>) itemSimilarity);
        }
        System.out.println("tam  = "+similarityMatrixModel.size());
        return similarityMatrixModel;
    }
    
    
    
    
    /**
     * Computa la similaridad entre los vectores de ratings de dos elementoss
     * @param eventos1
     * @param eventos2 
     * @return  devuelve la similitud entre dos items
     */
    public double itemVectorSimilarity(List<Events> eventos1,List<Events> eventos2)
    {
        /*
        double similitud=0;
        Comparator  comparator = new MyComparator();
        PriorityQueue<Pair> res=new PriorityQueue(100, comparator);
        
        //Iterator< Map.Entry<Integer,List<Events> > > it = getItemEventDAO().entrySet().iterator();
        while(it.hasNext())
        {
            
            
            //check if there's a next element
            if(it.hasNext())
            {
                Map.Entry<Integer,List<Events>> pair1=(Map.Entry)it.next();
                it.next();//avance to next item
                Map.Entry<Integer,List<Events>> pair2=(Map.Entry)it.next();
                if(pair1.getValue().size()>0 && pair2.getValue().size()>0  ){
                    //we have two  item ratings vector ,which is larger 
                    if (pair1.getValue().size() >= pair2.getValue().size())
                    {

                        similitud=determineSimilarity(pair1.getValue(),pair2.getValue());
                    }
                    else{
                        similitud=determineSimilarity(pair2.getValue(),pair1.getValue());
                    }
                }
                 Pair p=new Pair(pair1.getKey(),pair2.getKey(),similitud);
                 res.add(p);
            }
            
            //insert simility value in a priority que
           
            
          
            //
            Iterator<Pair> itpq = res.iterator();
            while (itpq.hasNext()){
                System.out.println ( "Value: "+ itpq.next().getSimilitud()); 
            }
             
             Pair head = res.peek();
             System.out.println("Value "+head.getSimilitud());
             */
        
            return determineSimilarity(eventos1, eventos2);
        }
       
        
    
    private double determineSimilarity(final List<Events> ratingsA,final List<Events>ratingsB)
    {
      float dotProduct=0;
      float magnitudeA=0;
      float magnitudeB=0;
      //float resultado=0;
      double ratingA=0;
      double ratingB=0;
      
      if(ratingsA.size()>ratingsB.size()){
        for(int i=0;i<ratingsB.size();i++)
        {
            ratingA=ratingsA.get(i).getRating();
            ratingB=ratingsB.get(i).getRating();

            dotProduct+=ratingA*ratingB;
            magnitudeA+=ratingA*ratingA;
            magnitudeB+=ratingB*ratingB;
          
        }
      }else{
          for(int i=0;i<ratingsA.size();i++)
        {
            ratingA=ratingsA.get(i).getRating();
            ratingB=ratingsB.get(i).getRating();

            dotProduct+=ratingA*ratingB;
            magnitudeA+=ratingA*ratingA;
            magnitudeB+=ratingB*ratingB;
          
        }
      }
      
      
      return (dotProduct/(sqrt(magnitudeA)*sqrt(magnitudeB)));
    }
    
    private class Pair{
        int item1=0;
        int item2=0;
        double similitud=0.0;
        
        Pair(int i1 ,int i2,double similitud){
            this.item1=i1;
            this.item2=i2;
            this.similitud=similitud;
        }
        
        private int getItem1(){return item1;}
        private int getItem2(){return item2;}
        private Double getSimilitud(){return similitud;}
        
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
    
}
