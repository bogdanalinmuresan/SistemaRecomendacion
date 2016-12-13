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

import Dao.Pair;
import Dao.Item;
import Dao.User;
import Ratings.InterfaceModel;
import static Ratings.InterfaceModel.neighborhoodSize_N;
import Ratings.ModelAPI;
import java.util.ArrayList;

/**
 *clase que implementa una medida de calificacion 
 * @author bogdan
 * @version 1.0
 * @see ItemScorer
 * @see ScoreMeasure
 */
public class WeightSum extends ItemScorer implements ScoreMeasure{
    private ModelAPI modelapi;    
    
    /**
     * constructor de la clase
     */
    public WeightSum(){
        modelapi=new ModelAPI();
    }
    
    /**
     * constructor con parámetros
     * @param modelapi acceso al modelo de datos
     */
    public WeightSum(ModelAPI modelapi){
        this.modelapi=modelapi;
        //System.out.println("constructor de WeightSum(ModelAPI modelapi)");
    }
    
    /**
     * Método modificador del modelo de datos
     * @param modelapi  nuevo modelo de datos
     */
    public void setModelApi(ModelAPI modelapi){
        this.modelapi=modelapi;
    }

    /**
     * Método que devuelve la calificacion de un elemento dato un usuario
     * @param u usuario
     * @param i elemento
     * @return la calificación
     */
    @Override
    public double itemscore(User u, Item i) {
       return score(u, i);
    }

    /**
     * Método que devuelve la calificación 
     * @param u usuario
     * @param ite elemento
     * @return  la calificación
     * @see WeightSum#itemscore(Dao.User, Dao.Item)
     */
    @Override
    public double score(User u, Item ite) {
        
        double res=0;
        //res ==-99 if user dont voted item i
        res=modelapi.getRatingOfSimilarItemUserVoted(ite, u );
        //System.out.print("res en weight sum= "+res);
        if(res==-99.0){
            ArrayList<Pair> similarItem;
            similarItem=modelapi.getSimilarItems(ite);
            //System.out.println("similarItem.size()"+similarItem.size());
            if(similarItem!=null){
                double sumTop=0;
                double sumBottom=1;    
                double temp;
                for(int i=0; i< similarItem.size(); i++){
                    temp=modelapi.getRatingOfSimilarItemUserVoted(similarItem.get(i).getItem1(), u );
                    //items that user voted ,dont mind
                    if(temp!=-99){
                        sumTop+=temp*similarItem.get(i).getSimilitud();
                        sumBottom+=Math.abs(similarItem.get(i).getSimilitud());
                    }
                }
                res=sumTop/sumBottom;
                //System.out.println("res dentro de score en wirghted Sum ="+res);
                return res;
            }else{
                //no esta la pelicla en la similarityMatrixModel
               // System.out.println("devuelve 0 porque no esta en la matrix\n");
                return res=0;
            }
        }
        //System.out.println("resultado res en build model "+res);
        return res;
    }
    
}
