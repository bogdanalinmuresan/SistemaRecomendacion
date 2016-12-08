/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.Objects;

/**
 *Clase que representa un usuario en el sistema
 * @author bogdan
 * @version 1.0
 */
public class User {
    private int userId;
    
    /**
     * Constructor por defecto
     */
    public User(){
    
    }
    
    /**
     * Constructor con parámetros
     * @param i 
     */
    public User(int i){
        this.userId=i;
    }
    
    /**
     * Constructor de copia
     * @param u 
     */
    public User(User u){
        this.userId=u.getUserId();
    }
  
    /**
     * Métodos selector
     * @return  el valor identificativo de un usuario
     */
    public int getUserId() { return userId; }
    
    /**
     * Método modificador 
     * @param i el nuevo valor identificativo del usuario
     */
    public void setUserId(int i) {this.userId = i;}
    
    @Override
    public int hashCode(){
        return Objects.hash(userId);
        
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }
}
