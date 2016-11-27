/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;
import Dao.Events;

/**
 *
 * @author bogdan
 */
public interface SimilarityCalculate {
    
    public double compare(ArrayList<Events> ratingsA,ArrayList<Events> ratingsB);
    
}
