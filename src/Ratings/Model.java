/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ratings;

/**
 *
 * @author bogdan
 */
public interface Model {
    /**
     * most similar items in similarMatrix model,this is applied for each item to be scored
     */
    public final int minRatings=1;
    
    /**
     * Similar items
     */
    public final int neighborhoodSize_N=30;
}