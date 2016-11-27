/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 *Clase que proporciona el acceso a una fuente de datos mysql para cargar en memoria
 *los datos
 * 
 * @author bogdan
 */
public class AccesoJDBC extends InterfazCliente {
  
    /**
     * Constructor con párametros
     * 
     * @param cadena
     * @param use
     * @param pass 
     */
    public AccesoJDBC(String use,String pass,String cadena)
    {
        super(use,pass,cadena);
    }
    
    /**
     * Método para realizar una consulta en la base de datos
     * @param consulta La consulta en mysql
     * @return El resultado de la consulta
     */
    public ResultSet consultaBD(String consulta){
        ResultSet rset=null;
        try{
             //      "jdbc:mysql://localhost:3306/sistemarecomendaciontfg", "root", ""); // MySQL
             //String consu=getCadenaConexion();
            //System.out.println("cadena de conexion:"+getCadenaConexion());
            //System.out.println("user:"+getUser());
            //System.out.println("pass:"+getPassword());
            
            Connection conn=DriverManager.getConnection(getCadenaConexion(),getUser(),getPassword());
            Statement stmt = conn.createStatement();
            rset = stmt.executeQuery(consulta);
             
        }catch(SQLException ex) {
            System.out.println("error en conectar con la base de datos,comprueba que el servidor esta activo" );
            System.out.println("error que coge"+ex);
        }
        return rset;
        
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
               
                User u=new User(Integer.parseInt(rset.getString("userId")));
                Item i=new Movie(Integer.parseInt(rset.getString("movieId")));
                //evento.setUserID(Integer.parseInt(rset.getString("userId")));
                //evento.setMovieID(Integer.parseInt(rset.getString("movieId")));
                double sim=(Double.parseDouble(rset.getString("rating")));
                int times=(Integer.parseInt(rset.getString("timestamp")));
                Events evento=new Events(u, (Movie) i,sim,times);
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
                User u =new User();
                u.setUserId(Integer.parseInt(rset.getString("userId")));
                getUserDAO().add(u);
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
            for(User u:getUserDAO())
            {
                //para cada usuario obtener sus eventos
                
                List<Events> resEvent=new ArrayList<>();
                
		for(Events e:getEventsDAO()) {
                    if (e.getUser().getUserId()==u.getUserId())
                    {
                        resEvent.add(e);
                    } else {
                    }
		} 
                getUserEventDAO().put(u,resEvent);
                
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
           // Iterator<Movie> itMovie = getItemsDAO().iterator();
            for(Movie m:getItemsDAO())
            {
                Movie elemento=new Movie(m);
                ArrayList<Events> resEvent=new ArrayList<>();
                //System.out.println("itemId: "+elemento.getId());
                for(Events e:getEventsDAO()) {
                    if(m.getId()==e.getItem().getId())
                    {
                        Events eve=new Events(e);
                        resEvent.add(eve);
                        //System.out.println(" itemId "+m.getId()+"event ItemId"+e.getItem().getId());
                    }
		}
                //insertamos
               // System.out.println("elemento"+elemento.getId());
                getItemEventDAO().put(elemento, resEvent);   
            }
        }catch(Exception e ){
            
        }
    }

}