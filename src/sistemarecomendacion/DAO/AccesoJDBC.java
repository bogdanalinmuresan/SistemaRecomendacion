/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author bogdan
 */
public class AccesoJDBC extends AccesoDatos {
   
   //**************** Atributos de la clase **********************************// 
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
    
    //Metodos selectores
    public ArrayList<Integer> getUserDAO(){return userDAO;}
    public ArrayList<Movie> getItemsDAO(){return itemsDAO;}
    public HashMap<Integer,List<Events> > getItemEventDAO(){return itemEventDAO;}
    public HashMap<Integer,List<Events> > getUserEventDAO(){return userEventDAO;}
    public ArrayList<Events> getEventsDAO(){return eventsDAO;}
    //constructor
    public AccesoJDBC(String ho,String baseded,int puer,String use,String pass)
    {
        super(ho,baseded,puer,use,pass);
    }
    
    /**
     * 
     * @param consulta
     * @return 
     */
    public ResultSet consultaBD(String consulta){
        ResultSet rset=null;
        try{
             // Step 1: Allocate a database "Connection" object
            //Connection conn = DriverManager.getConnection(
             //      "jdbc:mysql://localhost:3306/sistemasderecomendaciontfg", "root", ""); // MySQL
             String consu=getHost()+":"+getPuerto()+"/"+getBaseDatos();
             //System.out.println(consu);
             Connection conn=DriverManager.getConnection(consu,getUser(),"");
             // Step 2: Allocate a "Statement" object in the Connection
             Statement stmt = conn.createStatement();
             rset = stmt.executeQuery(consulta);
             
        }catch(SQLException ex) {
            System.out.println("error en conectar con la base de datos" );
        }
        return rset;
        
    }
    /**
     * Carga en memoria los usuarios de la base de datos
     * 
     */
    public void cargarUsuariosDao()
    {
        try
        {
             ResultSet rset=consultaBD("SELECT DISTINCT userId FROM `ratings` ORDER BY `userId` ASC");
            
            while(rset.next()) {   // Move the cursor to the next row
                getUserDAO().add(Integer.parseInt(rset.getString("userId")));
            }//fin while
             
        }catch(Exception e)
        {
            
        }
    }
    /**
     * @return Carga las peliculas en memoria 
     */
    public void cargarMoviesDao(){
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
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return;
	}
               
    }
    
    public void cargarEventosDao()
    {
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
        }catch(Exception e)
        {
            System.out.println("error en cargar eventos dao");
        }
    }

    /**
     * @Carga en memoria los eventos generados por cada usuario usuario
     */
    public void cargarUserEventDao()
    { 
        try{
            Iterator<Integer> itUser = getUserDAO().iterator();
            while (itUser.hasNext())
            {
                //para cada usuario obtener sus eventos
                Integer elemento=itUser.next();
                //System.out.println("userId="+elemento);
                //recorrer los eventos
                List<Events> resEvent=new ArrayList<>();
                Iterator<Events> itEvent = getEventsDAO().iterator();
                /*
                for(int i=0;i<eventsDAO.size();i++){
                    Events evento=(Events)eventsDAO.get(i);
                    //System.out.println(evento.getUserID());
                    if(elemento==evento.getUserID())
                    {
                        resEvent.add(itEvent.next()); 
                    }
                    
                }
                
                */
                
		while (itEvent.hasNext()) {
                   //System.out.println("contador ="+itEvent.next().getUserID());
                    if ((itEvent.next().getUserID())==elemento)
                    {
                    //System.out.println(" events:="+itEvent.next().getUserID()+" "+itEvent.next().getMovieID());
                        //System.out.println("userId + event userId "+elemento+ " " +itEvent.next().getUserID());
                        resEvent.add(itEvent.next());
                     //   System.out.println("tamanio de eventos "+resEvent.size());
                    }
		}
                
                //System.out.println("->"+resEvent.size());
                //System.out.println("tamanio events ="+resEvent.size());
                //insertamos 
                getUserEventDAO().put(elemento,resEvent);
                //System.out.println("ususario = "+elemento);
                
            }
        }catch(Exception e ){
            
        }
        
        
    }//fin metodo

    /**
     * Carga en memoria los eventos generados por cada pelicula
     */
    public void cargarItemEventDao()
    {
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
    }//fin metodo
 
    @Override
    public void cargarDatos() {
        try{
            cargarEventosDao();
            cargarMoviesDao();
            cargarUsuariosDao();
            cargarUserEventDao();
            cargarItemEventDao();
        }catch(Exception e )
        {
            e.printStackTrace();
        }
        
    }

    @Override
    public void configurarConexionBD(String newhost, String newbasededatos, int newpuerto, String newuser, String newpassword) {
        setHost(newhost);
        setBaseDatos(newbasededatos);
        setPuerto(newpuerto);
        setUser(newuser);
        setPassword(newpassword);
    }

  
    
   
}
