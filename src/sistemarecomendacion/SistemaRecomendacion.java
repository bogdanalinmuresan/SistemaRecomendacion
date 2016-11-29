/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion;

import Api.RecommenderApi;
import Dao.AccessDataAPI;
import Ratings.KnnModel;
import org.json.JSONException;
import Dao.AccessDataJDBC;
import Dao.Item;
import Dao.Movie;
import Dao.User;
import Ratings.ModelAPI;

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
        new User(1), new Movie(16) user has rated that item
        */
 
        String cadenaConexion="jdbc:mysql://localhost:3306/sistemarecomendaciontfg";
        //AccessDataJDBC nuevoacceso= new AccessDataJDBC("bogdan","123456",cadenaConexion);
        //nuevoacceso.cargarDatosDAO();
        
        AccessDataAPI accesoDataApi=new AccessDataAPI();
        accesoDataApi.accessSQL("bogdan","123456",cadenaConexion);
        
        
        ModelAPI accesoModelo=new ModelAPI(accesoDataApi);
        accesoModelo.knnModel();
        
        RecommenderApi rec=new RecommenderApi(accesoModelo);
        
        rec.baselinePrediction();
        double res;
        res=rec.prediction(new User(1), new Movie(15));
        System.out.println("prediccion Baseline = "+res);
        
        rec.itemBased();
        double resI;
        resI=rec.prediction(new User(1), new Item(15));
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
