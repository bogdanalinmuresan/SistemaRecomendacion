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
public abstract class AccesoDatos {

    //atributos 
    private String host;
    private String basededatos;
    private int puerto;
    private String user;
    private String password;
    
    /**
     * Constructor con parametros
     * 
     * @param ho
     * @param baseded
     * @param puer
     * @param use
     * @param pass 
     */
    public AccesoDatos(String ho,String baseded,int puer,String use,String pass)
    {
        this.host=ho;
        this.basededatos=baseded;
        this.puerto=puer;
        this.user=use;
        this.password=pass;
    }
    
    /**
     * Metodo selector que devuelve el nombre del host
     * @return el host
     */
    public String getHost(){return host;}
    /**
     * Devuelve el nombre de la base de datos a la que se conecta
     * @return  La base de datos
     */
    public String getBaseDatos(){return basededatos;}
    /**
     * Devuelve el puerto
     * @return El puerto 
     */
    public int getPuerto(){return puerto;}
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
     * Método para configurar el host de la conexión
     * @param nuevoHost 
     */
    public void setHost(String nuevoHost){this.host=nuevoHost;}
    /**
     * Método que configura una base de datos
     * @param nuevaBaseDatos 
     */
    public void setBaseDatos(String nuevaBaseDatos){this.basededatos=nuevaBaseDatos;}
    /**
     * Método para configurar el puerto
     * @param nuevoPuerto 
     */
    public void setPuerto(int nuevoPuerto){this.puerto=nuevoPuerto;}
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
    
    
    /**
     * Método para cargar todos los datos en memoria de la fuente de datos proporcionada
     */
    abstract public void cargarDatos();
    
    
    /**
     * Método para configurar la conexión a la la fuente de datos proporcionancio los 
     * parametros necesarios
     * 
     * @param newhost
     * @param newbasededatos
     * @param newpuerto
     * @param newuser
     * @param newpassword 
     */
    abstract public void configurarConexionBD(String newhost,String newbasededatos,int newpuerto,String newuser,String newpassword);
 
}
