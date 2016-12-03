/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion;

import Algorithms.BaseLinePredictor;
import Algorithms.ItemBased;
import Algorithms.ScoreAPI;
import Algorithms.WeightSum;
import Algorithms.RecommenderApi;
import Dao.AccessDataAPI;
import Dao.Events;
import Dao.Item;
import org.json.JSONException;
import Dao.Movie;
import Dao.User;
import Evaluation.EvaluationType;
import Evaluation.MAE;
import Evaluation.MetricsAPI;
import Evaluation.PairEvaluation;
import Ratings.EvaluationModel;
import Ratings.ModelAPI;
import java.util.ArrayList;

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
        
        User usuario =new User (1);
        Item item=new Movie(15);
        //ModelAPI accesoModelo=new ModelAPI(accesoDataApi);
        //accesoModelo.knnModel();
        /***********************************************************************/
        //carga el primer bloque 
        EvaluationModel evalModel=new EvaluationModel(accesoDataApi,0);
        //evalModel.buildModel();
        ModelAPI accesoModelo=new ModelAPI(accesoDataApi);
        accesoModelo.setModel(evalModel);
        
        //EvaluationModel evalModelItem=new EvaluationModel(accesoDataApi,0);
        //evalModel.buildModel();
        //ModelAPI accesoModeloItem=new ModelAPI(accesoDataApi);
        //accesoModelo.setModel(evalModelItem);
        
        /*
        //carga el segundo 
        evalModel.setK(1);
        //carga el segundo
        evalModel.setK(2);
        //carga el terecero
        evalModel.setK(3);
        //carga el tercero
        evalModel.setK(4);
        
        */
        
        /***********************************************************************/
        RecommenderApi recbaseline=new RecommenderApi(accesoModelo);
        RecommenderApi recItem=new RecommenderApi(accesoModelo);
        
        //configure baseline predictor
        BaseLinePredictor baseprediction=new BaseLinePredictor(accesoModelo);
        recbaseline.configureAlgorithm(baseprediction, accesoModelo);
        
        //configure item-based
        WeightSum weightSum=new WeightSum(accesoModelo);
        ScoreAPI measapi=new ScoreAPI(accesoModelo, weightSum);
        ItemBased itemBased=new ItemBased(measapi);
        recItem.configureAlgorithm(itemBased,accesoModelo);
        
        //----
        double predictionBaseline=0;
        double predictionItem=0;
        ArrayList<PairEvaluation> resPredictionBaseline=new ArrayList<>();
        ArrayList<PairEvaluation> resPredictionItem=new ArrayList<>();
        
        ArrayList<Events> bloque0=evalModel.getTestBlock0();
        
        for(int i=0; i<bloque0.size();i++){
            PairEvaluation entradaBaseline=new PairEvaluation();
            PairEvaluation entradaItem=new PairEvaluation();
            Events evento=new Events(bloque0.get(i));
            //---
            predictionBaseline=recbaseline.prediction(evento.getUser() ,evento.getItem());
            predictionItem=recItem.prediction(evento.getUser() ,evento.getItem());
            
            //System.out.println("prediccion Baseline = "+predictionBaseline);
            if(predictionItem!=-2)
                System.out.println("prediccion Item = "+predictionItem);
            //---
            entradaBaseline.setFirst(predictionBaseline);
            entradaBaseline.setSecond(evento.getRating());
            
            entradaItem.setFirst(predictionItem);
            entradaItem.setFirst(evento.getRating());
            
            resPredictionBaseline.add(entradaBaseline);
            resPredictionItem.add(entradaItem);
        }
        
        
        MetricsAPI accessMetrics=new MetricsAPI();
        EvaluationType mae=new MAE();
        accessMetrics.setMetric(mae);
        
        
        double resultadoMae=0;
        resultadoMae=mae.calculate(resPredictionBaseline);
        System.out.println("mae baseline "+resultadoMae);
        
        double resultadoItem=0;
        resultadoItem=mae.calculate(resPredictionItem);
        System.out.println("mae item "+resultadoItem);
  
        /**********************************************************************/
        
        /***********************************************************************/
        /*
        BaseLinePredictor baseprediction=new BaseLinePredictor(accesoModelo);
        rec.configureAlgorithm(baseprediction, accesoModelo);
        double res;
        res=rec.prediction(usuario ,item);
        System.out.println("prediccion Baseline = "+res);
        */
        /***********************************************************************/
        //rec.itemBased();
        /*
        WeightSum weightSum=new WeightSum(accesoModelo);
        ScoreAPI measapi=new ScoreAPI(accesoModelo, weightSum);
        ItemBased itemBased=new ItemBased(measapi);

        //rec.itemBased(measapi);
        rec.configureAlgorithm(itemBased,accesoModelo);
        double resI;
        resI=rec.prediction(usuario,item);
        System.out.println("prediccion ItemBased = "+resI);
        
        */
             
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
