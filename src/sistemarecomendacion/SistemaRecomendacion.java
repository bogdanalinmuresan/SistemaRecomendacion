/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion;

import org.json.JSONException;
import sistemarecomendacion.DAO.AccesoJDBC;
import sistemarecomendacion.DAO.AccesoNOSQL;
import algoritmosClasicos.itemItemCF;
import algoritmosClasicos.ItemItemModel;
import sistemarecomendacion.DAO.Item;
import sistemarecomendacion.DAO.Movie;
import sistemarecomendacion.DAO.User;

/**
 *
 * @author bogdan
 */
public class SistemaRecomendacion {

    /**
     * @param args the command line arguments
     * @throws org.json.JSONException
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws JSONException {
        // TODO code application logic here
        //"jdbc:mysql://localhost:3306/sistemasderecomendaciontfg", "root", ""); //
        
        
        /***********************Acceso  SQL****************************/
        String cadenaConexion="jdbc:mysql://localhost:3306/sistemasderecomendaciontfg";
        AccesoJDBC nuevoacceso= new AccesoJDBC("root","",cadenaConexion);
        /*
        System.out.println("host = " +nuevoacceso.getHost());
        System.out.println("base de datos  = " +nuevoacceso.getBaseDatos());
        System.out.println("puerto = " +nuevoacceso.getPuerto());
        System.out.println("usuario = " +nuevoacceso.getUser());
        System.out.println("pass = " +nuevoacceso.getPassword());
        */
        
        
        //nuevoacceso.cargarMoviesDao();
        //nuevoacceso.cargarEventosDao();
        nuevoacceso.cargarDatosDAO();
        //System.out.println("Se han cargado los datos correctamente");
        
        /*
        System.out.println("tamanio movies sql  "+nuevoacceso.getItemsDAO().size());
        System.out.println("tamanio eventos sql  "+nuevoacceso.getEventsDAO().size());
        System.out.println("tamanio usuarios sql  "+nuevoacceso.getUserDAO().size());
        System.out.println("tamanio itemEventDao sql  "+nuevoacceso.getItemEventDAO().size());
        System.out.println("tamanio userEventDao sql  "+nuevoacceso.getUserEventDAO().size());
        */
        // System.out.println("tamanio userEventDao sql  "+nuevoacceso.userEventDAO.keySet().size());
        ItemItemModel itemItemModel=new ItemItemModel();
        //HashMap<Integer, ArrayList<Pair> > res=new HashMap<Integer, ArrayList<Pair> >();
        itemItemModel.buildModel();
        //res=itemItemModel.buildModel();
        itemItemCF itemitem=new itemItemCF(itemItemModel);
        /*for(int i=0;i<10;i++){
            User user=new User(i);
            Movie mov=new Movie(i);
            System.out.println("prediction user "+i+" ="+itemitem.weightedSum(user,mov));
        }*/
        
        
        User u=new User(3);
        Movie mov=new Movie(233);
        double res=+itemitem.weightedSum(u,mov);
        if(res==-1 || res==0)
            System.out.println("El usuario ya ha votado esta pelicula");
        else
            if(res==-2)
                System.out.println("No hay suficientes datos para predecir sobre esta pelicula");
           
                    
        System.out.println("prediccion score ="+res);
      
        //System.out.println("value "+itemItem.itemVectorSimilarity(it.next().getValue(), it.next().getValue()));
        
        
        /*Cuenta el nr de eventos que tiene cada item*/
        
        //Integer contador=0;
        /*
        Iterator entries = nuevoacceso.getItemEventDAO().entrySet().iterator();
        while (entries.hasNext()) {
          Entry thisEntry = (Entry) entries.next();
          Integer key = (Integer)thisEntry.getKey();
          ArrayList<Events> value = (ArrayList<Events>) thisEntry.getValue();
          System.out.println("item id   =" +key);
          System.out.println("events tamanio =" +value.size());
          //contador=contador+value.size();
          
        }
        //System.out.println("contador =" +contador);
       */
        
        
      /*Cuenta el numero de eventos que tiene cada user
        Iterator entries = nuevoacceso.getUserEventDAO().entrySet().iterator();
        while (entries.hasNext()) {
          Entry thisEntry = (Entry) entries.next();
          Integer key = (Integer)thisEntry.getKey();
          ArrayList<Events> value = (ArrayList<Events>) thisEntry.getValue();
          System.out.println("user id   =" +key);
          System.out.println("events tamanio =" +value.size());
          
        }
          */  
        /*
        for(int i=0;i<=nuevoacceso.eventsDAO.size();i++){
            Events evento=(Events) nuevoacceso.eventsDAO.get(i);
            System.out.println(evento.getUserID());
        }
                */
        
        
        /***********************Acceso No SQL****************************/
        
        /*comando para arrancar mongo local : brew services start/stop/restart mongo */
        
        
        //*--------------------------------------------------------------------
        //  String host="ds033337.mongolab.com";
        
        /*
        String user="bogdan";
        String pass="ar03pbo";
        AccesoNOSQL nosql=new AccesoNOSQL( user, pass,"ds033337.mongolab.com:33337/nosql");
        nosql.cargarDatosDAO();
        
        ItemItemModel itemItemModel=new ItemItemModel();
        //HashMap<Integer, ArrayList<Pair> > res=new HashMap<Integer, ArrayList<Pair> >();
        itemItemModel.buildModel();
        //res=itemItemModel.buildModel();
        itemItemCF itemitem=new itemItemCF(itemItemModel);
       
        User u=new User(3);
        Movie mov=new Movie(233);
        double res=+itemitem.weightedSum(u,mov);
        if(res==-1 || res==0)
            System.out.println("El usuario ya ha votado esta pelicula");
        else
            if(res==-2)
                System.out.println("No hay suficientes datos para predecir sobre esta pelicula");
           
                    
        System.out.println("prediccion score nosql ="+res);
        
        */
        //--------------------------------------------------------------------*/
        
        
        
        
        //System.out.println("tamanio peliculas Nosql" +nosql.getItemsDAO().size());
        //System.out.println("tamanio usuarios Nosqll" +nosql.getUserDAO().size());
        //System.out.println("tamanio evento Nosql" +nosql.getEventsDAO().size());
        // System.out.println("tamanio userEventDao Nosql" +nosql.getUserEventDAO().size());
        //System.out.println("tamanio itemEventDao Nosql" +nosql.getItemEventDAO().size());
        
        
        /*****Muestra los userId y el tamaño de los eventos de cada uno*************/
        
        /*--------------------------------------------------------------------
        Iterator it = nosql.getUserEventDAO().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println("userId"+pair.getKey());
            ArrayList<Events> eventos=new ArrayList<>();
            eventos=(ArrayList<Events>) pair.getValue();
            System.out.println("tamanios="+eventos.size());
        }
        ----------------------------------------------------------------------*/
        /*
        Iterator it = nosql.getItemEventDAO().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println("itemId"+pair.getKey());
            ArrayList<Events> eventos=new ArrayList<>();
            eventos=(ArrayList<Events>) pair.getValue();
            System.out.println("tamanios="+eventos.size());
        }
                */
    }
 
}
