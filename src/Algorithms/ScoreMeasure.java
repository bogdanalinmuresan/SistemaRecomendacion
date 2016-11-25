/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import sistemarecomendacion.DAO.Item;
import sistemarecomendacion.DAO.User;

/**
 *
 * @author bogdan
 */
public interface ScoreMeasure {
    
    double score(User u,Item i);
    
}
