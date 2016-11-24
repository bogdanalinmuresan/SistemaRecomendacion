/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion.DAO;

/**
 *Clase abstracta que heredan las clases inferiores para acceder a la fuente de 
 * datos.
 * 
 * @author bogdan
 * 
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
     * Método consultos
     * @return cadena de conexion
     */
    public String getCadenaConexion(){
        return cadena;
    }
   
    /**
     * Devuelve el usuario
     * @return user
     */
    public String getUser(){return user;}
    /**
     * Devuelve la contraseña 
     * @return La contraseña
     */
    public String getPassword(){return password;}

    /**
     * Método para configurar un usuario para la conexión con la base de datos
     * @param nuevoUser 
     */
    public void setUser(String nuevoUser){this.user=nuevoUser;}
    /**
     * Método para configurar la contraseña para la conexión con la base de datos
     * @param nuevoPassword 
     */
    public void setPassword(String nuevoPassword){this.password=nuevoPassword;}
 
}
