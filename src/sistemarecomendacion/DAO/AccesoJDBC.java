/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.Iterator;

/**
 *Clase que proporciona el acceso a una fuente de datos mysql para cargar en memoria
 *los datos
 * 
 * @author bogdan
 */
public class AccesoJDBC extends InterfazCliente {
   
    
   //**************** Atributos de la clase **********************************// 
   /*
    //users 
    private ArrayList<Integer> userDAO=new ArrayList<Integer>();
    //items
    private ArrayList<Movie> itemsDAO=new ArrayList<Movie>();
    //provides access to events by item ID.
    private HashMap itemEventDAO =new HashMap<Integer,List<Events> >();
    //provides access to events by user ID.
    private HashMap userEventDAO =new HashMap<Integer,List<Events> >();
    //provides access to the database of events.(ratings)
    private ArrayList eventsDAO=new ArrayList<Events>();
    */
    /**
     * Método que devuelve los usuarios 
     * @return los usuarios del sistema
     */
    

    /*-------------------------------------------------------------------------*/
    
    /**
     * Constructor con párametros
     * 
     * @param ho
     * @param baseded
     * @param puer
     * @param use
     * @param pass 
     */
    public AccesoJDBC(String ho,String baseded,int puer,String use,String pass)
    {
        super(ho,baseded,puer,use,pass);
    }
    
    /**
     * Método para realizar una consulta en la base de datos
     * @param consulta La consulta en mysql
     * @return El resultado de la consulta
     */
    public ResultSet consultaBD(String consulta){
        ResultSet rset=null;
        try{
             //      "jdbc:mysql://localhost:3306/sistemasderecomendaciontfg", "root", ""); // MySQL
             String consu=getHost()+":"+getPuerto()+"/"+getBaseDatos();
             Connection conn=DriverManager.getConnection(consu,getUser(),"");
             Statement stmt = conn.createStatement();
             rset = stmt.executeQuery(consulta);
             
        }catch(SQLException ex) {
            System.out.println("error en conectar con la base de datos" );
        }
        return rset;
        
    }
    
 
    /**
     * Método modificador para confgurar la conexión con la base de datos
     * 
     * @param newhost
     * @param newbasededatos
     * @param newpuerto
     * @param newuser
     * @param newpassword 
     */
    @Override
    public void configurarConexionBD(String newhost, String newbasededatos, int newpuerto, String newuser, String newpassword) {
        setHost(newhost);
        setBaseDatos(newbasededatos);
        setPuerto(newpuerto);
        setUser(newuser);
        setPassword(newpassword);
    }

    /**
     * Método que carga en memoria todos los datos de la base de datos
     */
    @Override
    public void cargarDatosDAO() {
        try{
            cargarEventosDAO();
            cargarItemsDAO();
            cargarUserDAO();
            cargarUserEventDAO();
            cargarItemEventDAO();
        }catch(Exception e )
        {
            System.out.print("error en cargar los datos ");
        }
    }

    /**
     * Carga lo eventos(ratings) de la base de datos en memoria
     */
    @Override
    public void cargarEventosDAO() {
        try{
            ResultSet rset=consultaBD("SELECT * FROM `ratings`");
            
            while(rset.next()){
                Events evento=new Events();
                
                evento.setUserID(Integer.parseInt(rset.getString("userId")));
                evento.setMovieID(Integer.parseInt(rset.getString("movieId")));
                evento.setRating(Double.parseDouble(rset.getString("rating")));
                evento.setTimestamp(Integer.parseInt(rset.getString("timestamp")));
                
                getEventsDAO().add(evento);
                
            }
        }catch(SQLException | NumberFormatException e)
        {
            System.out.println("error en cargar eventos dao");
        }
    }

    /**
     *  Carga las peliculas en memoria 
     */
    @Override
    public void cargarItemsDAO() {
        try {
            ResultSet rset=consultaBD("SELECT * FROM `movies`");
            
            while(rset.next()) {   // Move the cursor to the next row
                Movie peli=new Movie();
                
                peli.setId( Integer.parseInt(rset.getString("movieid")));
                peli.setGenre(rset.getString("genres"));
                peli.setTitle(rset.getString("title"));

                getItemsDAO().add(peli);
            }//fin while
                
	} catch (SQLException e) {
		System.out.println("Error en cargar las peliculas en memoria");
	}
    }

    /**
     * Carga en memoria los usuarios de la base de datos
     * 
     */
    @Override
    public void cargarUserDAO() {
        try
        {
             ResultSet rset=consultaBD("SELECT DISTINCT userId FROM `ratings` ORDER BY `userId` ASC");
            
            while(rset.next()) {   // Move the cursor to the next row
                getUserDAO().add(Integer.parseInt(rset.getString("userId")));
            }//fin while
             
        }catch(SQLException | NumberFormatException e)
        {
            System.out.println("error en cargar los usuarios de la base de datos" );
        }
    }

    /**
     * Carga en memoria los eventos(ratings) generados por cada usuario
     */
    @Override
    public void cargarUserEventDAO() {
        try{
            Iterator<Integer> itUser = getUserDAO().iterator();
            while (itUser.hasNext())
            {
                //para cada usuario obtener sus eventos
                Integer elemento=itUser.next();
                List<Events> resEvent=new ArrayList<>();
                Iterator<Events> itEvent = getEventsDAO().iterator();
                
		while (itEvent.hasNext()) {
                    if ((itEvent.next().getUserID())==elemento)
                    {
                        resEvent.add(itEvent.next());
                    }
		} 
                getUserEventDAO().put(elemento,resEvent);
                
            }
        }catch(Exception e ){
            
        }
    }

    /**
     * Carga en memoria los eventos generados por cada pelicula
     */
    @Override
    public void cargarItemEventDAO() {
        try{
             Iterator<Movie> itMovie = getItemsDAO().iterator();
            while (itMovie.hasNext())
            {
                Integer elemento=(Integer)itMovie.next().getId();
                //recorrer los eventos
                List<Events> resEvent=new ArrayList<>();
                Iterator<Events> itEvent = getEventsDAO().iterator();
                while (itEvent.hasNext()) {
                    if((itEvent.next().getMovieID())==elemento)
                    {    
                        resEvent.add(itEvent.next());
                    }
		}
                //insertamos 
                getItemEventDAO().put(elemento,resEvent);   
            }
        }catch(Exception e ){
            
        }
    }

}