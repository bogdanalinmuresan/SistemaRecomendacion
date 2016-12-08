/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

 
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.json.*;
/**
 *Clase para acceder a una base de datos no SQL en concreto MongoDb
 * @author bogdan
 */
public class AccessDataNOSQL extends InterfazCliente {
    
    /**
     * Constructor con parametros
     * @param cadena
     * @param use
     * @param pass 
     */
    public AccessDataNOSQL( String use, String pass,String cadena) {
        super( use, pass,cadena);
        cargarDatosDAO();
    }
    
 
  
    
   /*--------------------------------------------------------------------------*/
    
    /**
     * Metodo que se conecta a una coleccion de una base de datos no sql 
     * @param baseDatos la base de datos a la que se conecta
     * @param coleccion coleccion de la base de datos 
     * @return  devuelve la coleccion de la base de datos a la que se conecta 
     */
    public MongoCollection<Document> consultaBD(String baseDatos ,String coleccion)
    {
        String direccion="mongodb://"+getUser()+":"+getPassword()+"@"+getCadenaConexion();
        //mongodb://bogdan:ar03pbo@ds033337.mongolab.com:33337/nosql
        MongoClientURI connectionString = new MongoClientURI(direccion);
        MongoClient mongoClient = new MongoClient(connectionString);

        MongoDatabase database = mongoClient.getDatabase(baseDatos);//nosql

        MongoCollection<Document> collec = database.getCollection(coleccion);
        
        return collec;
    }
   
    /**
     * Método para cargar en memoria los datos de la base de datos 
     */
    @Override
    public void cargarDatosDAO() {
        cargarUserDAO();
        cargarItemsDAO();
        cargarEventosDAO();
        cargarUserEventDAO();
        cargarItemEventDAO();
        
    }

    /**
     * Método para cargar en memoria las calificaciones
     */
    @Override
    public void cargarEventosDAO() {
        MongoCollection<Document> collec=consultaBD("nosql","ratings");
        MongoCursor<Document> cursor =collec.find().iterator();
        
        while(cursor.hasNext())
        {
            try {
                String stringjson=cursor.next().toJson();
                JSONObject obj1=new JSONObject(stringjson);
                
                String userId=obj1.getString("userId");
                String movieID=obj1.getString("movieId");
                String rating=obj1.getString("rating");
                String timestamp=obj1.getString("timestamp");
                
                User u=new User(Integer.parseInt(userId));
                Item i= new Movie(Integer.parseInt(movieID));
                
                double rat=Double.parseDouble(rating);
                int tim=Integer.parseInt(timestamp);
                
                Events evento=new Events(u, (Movie) i,rat,tim);
                getEventsDAO().add(evento);
                
            } catch (JSONException ex) {
                Logger.getLogger(AccessDataNOSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cursor.close();    
    }

    /**
     * Metodo para cargar en memoria los elementos
     *
     */
    @Override
    public void cargarItemsDAO()  
    {
        MongoCollection<Document> collec=consultaBD("nosql","movies");
         
        MongoCursor<Document> cursor = collec.find().iterator();
        while (cursor.hasNext()) 
        {
            try {
                String stringjson=cursor.next().toJson();
                //JsonReader jr=new JsonReader(stringjson);
                JSONObject obj1=new JSONObject(stringjson);
                String movieId=obj1.getString("movieId");
                String title=obj1.getString("title");
                String genres=obj1.getString("genres");
                
                Movie peli=new Movie();
                peli.setId(Integer.parseInt(movieId));
                peli.setTitle(title);
                peli.setGenre(genres);
                
                getItemsDAO().add(peli);
            } catch (JSONException ex) {
                Logger.getLogger(AccessDataNOSQL.class.getName()).log(Level.SEVERE, null, ex);
                System.err.print("error en cargarItemsDAO en AccesoNoSQL");
            }
        }
    cursor.close();
    }

    /**
     * Método para cargar en memoria los usuarios
     */
    @Override
    public void cargarUserDAO() {
        MongoCollection<Document> collec=consultaBD("nosql","ratings");
        MongoCursor<Document> cursor =collec.find().iterator();
        
        while(cursor.hasNext())
        {
            try {
                String stringjson=cursor.next().toJson();
                JSONObject obj1=new JSONObject(stringjson);
                
                String userId=obj1.getString("userId");
                User u=new User();
                //Integer.parseInt(userId);
                u.setUserId(Integer.parseInt(userId));
                getUserDAO().add(u);
                
            } catch (JSONException ex) {
                Logger.getLogger(AccessDataNOSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cursor.close();
    }

    /**
     * Carga en memoria los calificacioens generadas por cada usuario
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
                        resEvent.add(new Events(e));
                    } else {
                    }
		} 
                getUserEventDAO().put(u,resEvent);
                
            }
        }catch(Exception e)
        {
           System.out.println("no carga los datos en el metodo ");
        }
    }

    
    /**
     * Método para cargar en memoria las calificaciones de cada pelicula
     */
    @Override
    public void cargarItemEventDAO() {
        try
        {
            for(Item m:getItemsDAO())
            {
                Item elemento=new Item(m);
                //recorrer los eventos
                ArrayList<Events> resEvent=new ArrayList<>();
                
                for(Events e:getEventsDAO()) {
                    if(e.getItem().getId()==elemento.getId())
                    {    
                        resEvent.add(new Events(e));
                    }
		}
                //insertamos
               // System.out.println("elemento"+elemento.getId());
                getItemEventDAO().put(elemento, resEvent);   
            }
        }
        catch(Exception e)
        {
            
        }
    }
}
