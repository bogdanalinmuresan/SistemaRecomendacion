/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Dao.Item;
import Dao.User;

/**
 *Interfaz que representa la medida de calificación de un eleemnto
 * @author bogdan
 * @version 1.0
 */
public abstract class ItemScorer  {
    
    /**
     * constructor de la clase
     */
    public ItemScorer(){
        
    }
    /**
     * metodo abstracto que tienen que implementar las clases que heredan de esta interfaz
     * @param u usuario
     * @param i elemento
     * @return  la calificación
     */
    public abstract double itemscore(User u,Item i);
    
}
