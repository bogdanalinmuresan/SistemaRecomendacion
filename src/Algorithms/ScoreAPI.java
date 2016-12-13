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

import Dao.Item;
import Dao.User;
import Ratings.ModelAPI;

/**
 *Esta clase ofrece una api a los algoritmos de calificacion 
 * @author bogdan
 * @version 1.0
 */
public class ScoreAPI {
    ScoreContext measurecontext;
    private  ModelAPI modelapi;
    ScoreMeasure sc;
    
    /**
     * Constructor de la clase
     * @param mapi objeto para acceso al modelo 
     */
    public ScoreAPI(ModelAPI mapi){
        this.measurecontext=new ScoreContext();
        this.modelapi=mapi;
        //System.out.println("Constructor de ScoreAPI(ModelAPI mapi)");
    }
    
    /**
     * Constructor de la clase
     * @param mapi acceso al modelo 
     * @param sc medida de calificación
     */
    public ScoreAPI(ModelAPI mapi,ScoreMeasure sc){
        
        this.modelapi=mapi;
        this.measurecontext=new ScoreContext();
        measurecontext.setScoreMeasure(sc);
        this.sc=sc;
    }
   
    /**
     * Método que califica un item dado un usuario
     * @param u usuario 
     * @param i elemento
     * @return La calificación
     */
    public double score(User u,Item i){
        return measurecontext.score(u, i);
    }
    
    /**
     * Método modificador para establecer nueva medida de calificación
     * @param sc  nueva medida de calificación
     */
    public void setScoreMeasure(ScoreMeasure sc){
        this.sc=sc;
        measurecontext.setScoreMeasure(sc);
        //System.out.println("setScoreMeasure(ScoreMeasure sc)");
    }
    
    
}
