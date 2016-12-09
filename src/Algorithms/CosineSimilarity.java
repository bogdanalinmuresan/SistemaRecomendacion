/* 
 * Copyright (C) 2016 Bogdan Alin Muresan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Algorithms;

import static java.lang.Math.sqrt;
import java.util.ArrayList;
import Dao.Events;

/**
 *Clase que calcula la similitud basada en coseno.
 * @author bogdan
 */
public class CosineSimilarity extends ItemSimilarity {
    
    /**
     * Constructor por defecto
     */
    public CosineSimilarity(){
        
    }

    /**
     * Método que calcula la similitud entre dos listas de calificaciones
     * @param ratingsA lista de calificaciones A
     * @param ratingsB lista de calificaciones B
     * @return la similitud,0 si alguna lista esta vacía
     */
    @Override
    public double determineSimilarity(ArrayList<Events> ratingsA, ArrayList<Events> ratingsB) 
    {
        double res=0;
        float dotProduct=0;
        float magnitudeA=0;
        float magnitudeB=0;
        //float resultado=0;
        double ratingA;
        double ratingB;
        if(!ratingsB.isEmpty() && !ratingsA.isEmpty()){
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
            res=(dotProduct/(sqrt(magnitudeA)*sqrt(magnitudeB)));
        } else {
        }
      
      //System.out.println("similitud entre ratings"+res);
      return res;
    }
    /**
     * Método que calcula la similitud
     * @param ratingsA lista de calificaciones A
     * @param ratingsB lista de calificaciones B
     * @return la similitud 
     * @see CosineSimilarity#determineSimilarity(java.util.ArrayList, java.util.ArrayList) 
     */
    @Override
    public double compare(ArrayList<Events> ratingsA, ArrayList<Events> ratingsB) {
        return determineSimilarity(ratingsA, ratingsB);
    } 
    
   /**
    * 
    * @param ratingsA
    * @param ratingsB
    * @return 
    * @see CosineSimilarity#compare(java.util.ArrayList, java.util.ArrayList) 
    */
    public double cosine(ArrayList<Events> ratingsA, ArrayList<Events> ratingsB){
        return determineSimilarity(ratingsA, ratingsB);
    }
}


