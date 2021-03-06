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
import java.sql.*;
import java.util.ArrayList;
import java.sql.SQLException;
/**
 *Clase que proporciona el acceso a una fuente de datos mysql para cargar en memoria
 *los datos
 * @author bogdan
 * @version 1.0
 */
public class AccessDataJDBC extends InterfazCliente {
  
    /**
     * Constructor con párametros
     * 
     * @param cadena
     * @param use
     * @param pass 
     */
    public AccessDataJDBC(String use,String pass,String cadena)
    {
        super(use,pass,cadena);
        cargarDatosDAO();
    }
    
    /**
     * Método para realizar una consulta en la base de datos
     * @param consulta La consulta en mysql
     * @return El resultado de la consulta
     */
    public ResultSet consultaBD(String consulta){
        ResultSet rset=null;
        try{
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
     * Carga las calificaciones (ratings) de la base de datos en memoria
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
     *  Carga los elementos en memoria 
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
     * Carga en memoria las calificaciones(ratings) generados por cada usuario
     */
    @Override
    public void cargarUserEventDAO() {
        try{
            for(User u:getUserDAO())
            {
                //para cada usuario obtener sus eventos
                
                ArrayList<Events> resEvent=new ArrayList<>();
                
		for(Events e:getEventsDAO()) {
                    if (e.getUser().getUserId()==u.getUserId())
                    {
                        resEvent.add(e);
                    } else {
                    }
		} 
                //System.out.println("user   "+u.getUserId()+"tam events "+resEvent.size());
                getUserEventDAO().put(u,resEvent);
                
            }
            System.out.println("getUserEventDAO().size()  "+getUserEventDAO().size());
        }catch(Exception e ){
            
        }
    }

    /**
     * Carga en memoria los eventos generados por elemento
     */
    @Override
    public void cargarItemEventDAO() {
        try{
           // Iterator<Movie> itMovie = getItemsDAO().iterator();
            for(Item m:getItemsDAO())
            {
                Item elemento=new Item(m);
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