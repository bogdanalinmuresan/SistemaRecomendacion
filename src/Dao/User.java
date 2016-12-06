/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.Objects;

/**
 *
 * @author bogdan
 */
public class User {
    private int userId;
    
    public User(){
    
    }
    
    public User(int i){
        this.userId=i;
    }
    
    public User(User u){
        this.userId=u.getUserId();
    }
  
    public int getUserId() { return userId; }
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
