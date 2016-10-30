/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion.DAO;




/**
 *
 * @author bogdan
 */
public abstract class AccesoDatos {
    
    
    
    //atributos 
    private String host;
    private String basededatos;
    private int puerto;
    private String user;
    private String password;
    
    //constructor
    public AccesoDatos(String ho,String baseded,int puer,String use,String pass)
    {
        this.host=ho;
        this.basededatos=baseded;
        this.puerto=puer;
        this.user=use;
        this.password=pass;
    }
    
    //Metodos GET
    public String getHost(){return host;}
    public String getBaseDatos(){return basededatos;}
    public int getPuerto(){return puerto;}
    public String getUser(){return user;}
    public String getPassword(){return password;}
    
    //Metodos SET
    public void setHost(String nuevoHost){this.host=nuevoHost;}
    public void setBaseDatos(String nuevaBaseDatos){this.basededatos=nuevaBaseDatos;}
    public void setPuerto(int nuevoPuerto){this.puerto=nuevoPuerto;}
    public void setUser(String nuevoUser){this.user=nuevoUser;}
    public void setPassword(String nuevoPassword){this.password=nuevoPassword;}
    
    
   
    
    abstract public void cargarDatos();
    
    abstract public void configurarConexionBD(String newhost,String newbasededatos,int newpuerto,String newuser,String newpassword);
    
    
}
