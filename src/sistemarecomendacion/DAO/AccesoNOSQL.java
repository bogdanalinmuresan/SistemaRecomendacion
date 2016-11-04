/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion.DAO;

 
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.json.*;
/**
 *Clase para acceder a una base de datos no SQL en concreto MongoDb
 * @author bogdan
 */
public class AccesoNOSQL extends InterfazCliente {
    
    /**
     * Constructor con parametros
     * @param ho
     * @param baseded
     * @param puer
     * @param use
     * @param pass 
     */
    public AccesoNOSQL(String ho, String baseded, int puer, String use, String pass) {
        super(ho, baseded, puer, use, pass);
    }
    
    //**************** Atributos de la clase **********************************// 
    //users 
    private Set<Integer> userDAO=new HashSet();
    //items
    private ArrayList<Movie> itemsDAO=new ArrayList<Movie>();
    //provides access to events by item ID.
    private HashMap itemEventDAO =new HashMap<Integer,List<Events> >();
    //provides access to events by user ID.
    private HashMap userEventDAO =new HashMap<Integer,List<Events> >();
    //provides access to the database of events.(ratings)
    private ArrayList eventsDAO=new ArrayList<Events>();
    
    /**
     * Método selector 
     * @return Devuelve los usuarios 
     */
    public Set<Integer> getUserDAO(){return userDAO;}
    /**
     * Método selector 
     * @return devuelve las peliculas
     */
    public ArrayList<Movie> getItemsDAO(){return itemsDAO;}
    /**
     * Método selector
     * @return los eventos de cada pelicula(ratings)
     */
    public HashMap<Integer,List<Events> > getItemEventDAO(){return itemEventDAO;}
    /**
     * Método selector
     * @return devuelve los eventos de cada usuario
     */
    public HashMap<Integer,List<Events> > getUserEventDAO(){return userEventDAO;}
    /**
     * Método selector 
     * @return devuelve los eventos (ratings)
     */
    public ArrayList<Events> getEventsDAO(){return eventsDAO;}
    
   /*--------------------------------------------------------------------------*/
    
    /**
     * Metodo que se conecta a una coleccion de una base de datos no sql 
     * @param baseDatos la base de datos a la que se conecta
     * @param coleccion coleccion de la base de datos 
     * @return  devuelve la coleccion de la base de datos a la que se conecta 
     */
    public MongoCollection<Document> consultaBD(String baseDatos ,String coleccion)
    {
        String direccion="mongodb://"+getUser()+":"+getPassword()+"@"+getHost()+":"+getPuerto()+"/"+getBaseDatos();
        //mongodb://bogdan:ar03pbo@ds033337.mongolab.com:33337/nosql
        MongoClientURI connectionString = new MongoClientURI(direccion);
        MongoClient mongoClient = new MongoClient(connectionString);

        MongoDatabase database = mongoClient.getDatabase(baseDatos);//nosql

        MongoCollection<Document> collec = database.getCollection(coleccion);
        
        return collec;
    }
   
    
    /**
     * Método para configurar la conexión con la base de datos
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
     * Método para cargar en memoria los eventos 
     */
    @Override
    public void cargarEventosDAO() {
        MongoCollection<Document> collec=consultaBD("nosql","ratings");
        MongoCursor<Document> cursor =collec.find().iterator();
        
        while(cursor.hasNext())
        {
            try {
                Events evento=new Events();
                String stringjson=cursor.next().toJson();
                JSONObject obj1=new JSONObject(stringjson);
                
                String userId=obj1.getString("userId");
                String movieID=obj1.getString("movieId");
                String rating=obj1.getString("rating");
                String timestamp=obj1.getString("timestamp");
                
                evento.setUserID(Integer.parseInt(userId));
                evento.setMovieID(Integer.parseInt(movieID));
                evento.setRating(Double.parseDouble(rating));
                evento.setTimestamp(Integer.parseInt(timestamp));
                
                getEventsDAO().add(evento);
                
            } catch (JSONException ex) {
                Logger.getLogger(AccesoNOSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cursor.close();    
    }

    /**
     * Metodo para cargar en memoria las peliculas
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
                Logger.getLogger(AccesoNOSQL.class.getName()).log(Level.SEVERE, null, ex);
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
                getUserDAO().add(Integer.parseInt(userId));
                
            } catch (JSONException ex) {
                Logger.getLogger(AccesoNOSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cursor.close();
    }

    /**
     * Carga en memoria los eventos generados por cada usuario
     */
    @Override
    public void cargarUserEventDAO() {
        try{
            Iterator<Integer> itUser = getUserDAO().iterator();
            while (itUser.hasNext())
           {
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
        }catch(Exception e)
        {
           System.out.println("no carga los datos en el metodo ");
        }
    }

    
    /**
     * Método para cargar en memoria los eventos de cada pelicula
     */
    @Override
    public void cargarItemEventDAO() {
        try
        {
            Iterator<Movie> itItem = getItemsDAO().iterator();
            while(itItem.hasNext())
            {
                Integer elemento=itItem.next().getId();
                Iterator<Events> itEvent = getEventsDAO().iterator();
                List<Events> resEvent=new ArrayList<>();
                while (itEvent.hasNext()) {
                    if ((elemento==itEvent.next().getMovieID()) )
                    {
                        resEvent.add(itEvent.next());
                    }
                }
                getItemEventDAO().put(elemento,resEvent);
            }
        }
        catch(Exception e)
        {
            
        }
    }

}
