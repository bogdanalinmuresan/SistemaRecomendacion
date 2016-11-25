/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion;

import Api.RecommenderApi;
import Ratings.ModelDataSet;
import org.json.JSONException;
import sistemarecomendacion.DAO.AccesoJDBC;
import sistemarecomendacion.DAO.AccesoNOSQL;
import algoritmosClasicos.itemItemCF;
import algoritmosClasicos.ItemItemModel;
import algoritmosClasicos.Pair;
import java.util.ArrayList;
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
        
        
       
        
        String cadenaConexion="jdbc:mysql://localhost:3306/sistemarecomendaciontfg";
        AccesoJDBC nuevoacceso= new AccesoJDBC("bogdan","123456",cadenaConexion);
       
        nuevoacceso.cargarDatosDAO();
        ModelDataSet mds=new ModelDataSet();
        RecommenderApi rec=new RecommenderApi();
        rec.itemBased(mds);
        double res;
        res=rec.prediction(new User(1), new Movie(15));
        System.out.println("prediccion = "+res);
        /*
        
        ItemItemModel itemItemModel=new ItemItemModel();
        itemItemModel.buildModel();
        itemItemCF itemitem=new itemItemCF(itemItemModel);
        
        User u=new User(1);
        Movie mov=new Movie(15);
        
        ArrayList<Pair> top=new ArrayList<>();
        top =itemitem.top10Recomendation(new User(1) );
        
        for (Pair top1 : top) {
            System.out.println("item1 "+top1.getItem1().getId()+ "simility "+top1.getSimilitud() );
        }
        
        double res=+itemitem.weightedSum(u,mov);
         System.out.println("prediccion score ="+res);
       
        if(res==-1 || res==0)
            System.out.println("El usuario ya ha votado esta pelicula");
        else
            if(res==-2)
                System.out.println("No hay suficientes datos para predecir sobre esta pelicula");
            else{
                if(res<-3)
                System.out.println("La prediccion no es valida ");
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
        Movie mov=new Movie(2335);
        double res=+itemitem.weightedSum(u,mov);
        if(res==-1 || res==0)
            System.out.println("El usuario ya ha votado esta pelicula");
        else
            if(res==-2)
                System.out.println("No hay suficientes datos para predecir sobre esta pelicula");
           
                    
        System.out.println("prediccion score nosql ="+res);
        */
        
        //--------------------------------------------------------------------*/
        
        
        
        
           
    }
 
}
