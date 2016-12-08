/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Dao.Item;
import Dao.User;

/**
 *Clase que debe implementar todos los algoritmos de medidas de calificación
 * @author bogdan
 * @version 1.0
 */
public interface ScoreMeasure {
    
    /**
     * Método que devuelve la calificación de un usuario y un elemento
     * @param u
     * @param i
     * @return  la predicción
     */
    double score(User u,Item i);
    
}
