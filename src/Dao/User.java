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
