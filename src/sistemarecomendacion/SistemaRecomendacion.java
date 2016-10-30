/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import sistemarecomendacion.DAO.AccesoJDBC;
import sistemarecomendacion.DAO.AccesoNOSQL;
import sistemarecomendacion.DAO.Events;

/**
 *
 * @author bogdan
 */
public class SistemaRecomendacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JSONException {
        // TODO code application logic here
        //"jdbc:mysql://localhost:3306/sistemasderecomendaciontfg", "root", ""); //
        
        
        /***********************Acceso  SQL****************************/
        AccesoJDBC nuevoacceso= new AccesoJDBC("jdbc:mysql://localhost","sistemasderecomendaciontfg",3306,"root","");
        /*
        System.out.println("host = " +nuevoacceso.getHost());
        System.out.println("base de datos  = " +nuevoacceso.getBaseDatos());
        System.out.println("puerto = " +nuevoacceso.getPuerto());
        System.out.println("usuario = " +nuevoacceso.getUser());
        System.out.println("pass = " +nuevoacceso.getPassword());
        */
        
        
        //nuevoacceso.cargarMoviesDao();
        //nuevoacceso.cargarEventosDao();
        //nuevoacceso.cargarDatos();
        
        
        /*
        System.out.println("tamanio movies  "+nuevoacceso.itemsDAO.size());
        System.out.println("tamanio eventos  "+nuevoacceso.eventsDAO.size());
        System.out.println("tamanio usuarios  "+nuevoacceso.userDAO.size());
        System.out.println("tamanio itemEventDao  "+nuevoacceso.itemEventDAO.size());
        System.out.println("tamanio userEventDao  "+nuevoacceso.userEventDAO.size());
        //System.out.println("tamanio userEventDao  "+nuevoacceso.userEventDAO.keySet().size());
        
        */
        
        /*Cuenta el nr de eventos que tiene cada item*/
        
        //Integer contador=0;
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
        
        
        //*--------------------------------------------------------------------
        String host="ds033337.mongolab.com";
        String bd="nosql";
        Integer puerto=33337;
        String user="bogdan";
        String pass="ar03pbo";
        AccesoNOSQL nosql=new AccesoNOSQL(host, bd, puerto, user, pass);
       /*comando para arrancar mongo local : brew services start/stop/restart mongo */
        
        nosql.cargarDatos();
        //--------------------------------------------------------------------*/
        
        
        
        
        System.out.println("tamanio peliculas" +nosql.getItemsDAO().size());
        System.out.println("tamanio usuarios" +nosql.getUserDAO().size());
        System.out.println("tamanio evento" +nosql.getEventsDAO().size());
        System.out.println("tamanio userEventDao" +nosql.getUserEventDAO().size());
        System.out.println("tamanio itemEventDao" +nosql.getItemEventDAO().size());
        
        
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
        
        Iterator it = nosql.getItemEventDAO().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println("itemId"+pair.getKey());
            ArrayList<Events> eventos=new ArrayList<>();
            eventos=(ArrayList<Events>) pair.getValue();
            System.out.println("tamanios="+eventos.size());
        }
    }
 
}
