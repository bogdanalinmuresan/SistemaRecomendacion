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

/**
 *Clase abstracta que heredan las clases inferiores para acceder a la fuente de 
 * datos.
 * 
 * @author bogdan
 * @version 1.0
 */
public abstract class InterfazCliente extends DAO {

    //atributos 
    private String cadena;
    private String user;
    private String password;
    
    /**
     * Constructor con parametros
     * 
     * @param use usuario
     * @param pass  contrasenia
     * @param cadenaconexion cadean de conexion
     */
    public InterfazCliente(String use,String pass,String  cadenaconexion)
    {
        this.cadena=cadenaconexion;
        this.user=use;
        this.password=pass;
    }
    
    /**
     * Medodo modificador 
     * @param cadena nueva cadena 
     */
    public void setCadenaConexion(String cadena){
        this.cadena=cadena;
    }
   /**
    * Devuelve la cadena de conexión
    * @return cadena de conexion
    */
    public String getCadenaConexion(){
        return cadena;
    }
   
    /**
     * Devuelve el usuario
     * @return usuario
     */
    public String getUser(){return user;}
    
    /**
     * Devuelve la contraseña 
     * @return La contraseña
     */
    public String getPassword(){return password;}

    /**
     * Método para configurar un usuario para la conexión con la base de datos
     * @param nuevoUser  en nuevo usuario
     */
    public void setUser(String nuevoUser){this.user=nuevoUser;}
    /**
     * Método para configurar la contraseña para la conexión con la base de datos
     * @param nuevoPassword  la nueva contraseña
     */
    public void setPassword(String nuevoPassword){this.password=nuevoPassword;}
    
   
}
