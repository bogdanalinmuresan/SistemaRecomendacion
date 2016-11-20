/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion;

import algoritmosClasicos.Pair;
import java.util.ArrayList;
import org.json.JSONException;
import sistemarecomendacion.DAO.AccesoJDBC;
import sistemarecomendacion.DAO.AccesoNOSQL;
import algoritmosClasicos.itemItemCF;
import java.util.HashMap;
import algoritmosClasicos.ItemItemModel;

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
       nuevoacceso.cargarDatosDAO();
        System.out.println("Se han cargado los datos correctamente");
        
        
        System.out.println("tamanio movies sql  "+nuevoacceso.getItemsDAO().size());
        System.out.println("tamanio eventos sql  "+nuevoacceso.getEventsDAO().size());
        System.out.println("tamanio usuarios sql  "+nuevoacceso.getUserDAO().size());
        System.out.println("tamanio itemEventDao sql  "+nuevoacceso.getItemEventDAO().size());
        System.out.println("tamanio userEventDao sql  "+nuevoacceso.getUserEventDAO().size());
        // System.out.println("tamanio userEventDao sql  "+nuevoacceso.userEventDAO.keySet().size());
        ItemItemModel itemItemModel=new ItemItemModel();
        HashMap<Integer, ArrayList<Pair> > res=new HashMap<Integer, ArrayList<Pair> >();
        itemItemModel.buildModel();
        //res=itemItemModel.buildModel();
        itemItemCF itemitem=new itemItemCF(itemItemModel);
        
            //ArrayList<Pair> ratings= new ArrayList<>();
           // ratings=(ArrayList<Pair>) entry.getValue();
            /*for(Pair p:res.entrySet().iterator().next().getValue())
            {
                System.out.println(""+p.getSimilitud());
            }*/
        System.out.println(""+itemitem.weightedSum(1,2727));
       
      
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
        String host="ds033337.mongolab.com";
        String bd="nosql";
        Integer puerto=33337;
        String user="bogdan";
        String pass="ar03pbo";
        AccesoNOSQL nosql=new AccesoNOSQL(host, bd, puerto, user, pass);
       
        
        //nosql.cargarDatosDAO();
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
