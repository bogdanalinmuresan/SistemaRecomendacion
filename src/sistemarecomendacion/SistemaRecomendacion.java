/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion;

import Api.RecommenderApi;
import Ratings.ModelDataSet;
import org.json.JSONException;
import Dao.AccesoJDBC;
import Dao.Movie;
import Dao.User;

/**
 *
 * @author bogdan
 */
public class SistemaRecomendacion {

   
    public static void main(String[] args) throws JSONException {

   
        /***********************Acceso  SQL****************************/
        /*
        new User(352), new Movie(1229)
        new User(1), new Movie(967)
        new User(1), new Movie(15)
        */
 
        String cadenaConexion="jdbc:mysql://localhost:3306/sistemarecomendaciontfg";
        AccesoJDBC nuevoacceso= new AccesoJDBC("bogdan","123456",cadenaConexion);
       
        nuevoacceso.cargarDatosDAO();
        ModelDataSet mds=new ModelDataSet();
        RecommenderApi rec=new RecommenderApi(mds);
        //rec.itemBased();
        rec.baselinePrediction();
        double res;
        res=rec.prediction(new User(1), new Movie(16));
        System.out.println("prediccion Baseline = "+res);
        
        rec.itemBased();
        double resI;
        resI=rec.prediction(new User(1), new Movie(16));
        System.out.println("prediccion ItemBased = "+resI);
             
        /***********************Acceso No SQL****************************/
        
        /*comando para arrancar mongo local : brew services start/stop/restart mongo */
        
        
      
        //  String host="ds033337.mongolab.com";
        
        /*
        String user="bogdan";
        String pass="ar03pbo";
        AccesoNOSQL nosql=new AccesoNOSQL( user, pass,"ds033337.mongolab.com:33337/nosql");
        nosql.cargarDatosDAO();
        */
     
    }
 
}
