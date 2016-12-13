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
package sistemarecomendacion;

import Algorithms.BaseLinePredictor;
import Algorithms.CosineSimilarity;
import Algorithms.ItemBased;
import Algorithms.RecommenderApi;
import Algorithms.ScoreAPI;
import Algorithms.SimilarityApi;
import Algorithms.WeightSum;
import Dao.AccessDataAPI;
import Dao.AccessDataJDBC;
import Dao.AccessDataNOSQL;
import Dao.Item;
import Dao.User;
import org.json.JSONException;
import Evaluation.EvaluationType;
import Evaluation.MAE;
import Evaluation.MSE;
import Evaluation.MetricsAPI;
import Evaluation.PairEvaluation;
import Evaluation.RMSE;
import Ratings.EvaluationModel;
import Ratings.KnnModel;
import Ratings.ModelAPI;
import java.util.ArrayList;

/**
 *
 * @author bogdan
 */
public class SistemaRecomendacion {

    /**
     *
     * @param args
     * @throws JSONException
     */
    public static void main(String[] args) throws JSONException {

   
        /***********************Acceso  SQL****************************/
        /*
        new User(352), new Movie(1229)
        new User(1), new Movie(967)
        new User(1), new Movie(15)
        new User(1), new Movie(16) user has rated that item
        */
        
        //acceso conexion base de datos
        String cadenaConexion="jdbc:mysql://localhost:3306/sistemarecomendaciontfg";
        AccessDataAPI accesoDataApi=new AccessDataAPI();
        AccessDataJDBC accesoJDBC=new AccessDataJDBC("bogdan","123456",cadenaConexion);
        accesoDataApi.addNewConnection(accesoJDBC);
       
        /*
        String cadenaConexion="ds033337.mongolab.com:33337/nosql";
	String user="bogdan";
	String pass="ar03pbo";
	AccessDataAPI accesoDataApi=new AccessDataAPI();
	AccessDataNOSQL accesoNosql=new AccessDataNOSQL(user,pass,cadenaConexion);
	accesoDataApi.addNewConnection(accesoNosql);
        */
        /*
        //configuramos la medida de similitud
        CosineSimilarity cosine=new CosineSimilarity();
        SimilarityApi similarityApi=new SimilarityApi();
        similarityApi.addSimilarity(cosine);
        
        7//configuramos el modelo
        EvaluationModel evalModel=new EvaluationModel(accesoDataApi,-1,similarityApi);
        ModelAPI accesoModelo=new ModelAPI(evalModel);
        accesoModelo.setModel(evalModel);
       
        KnnModel knn=new KnnModel(accesoDataApi, similarityApi);
        ModelAPI accesoModelo=new ModelAPI();
        accesoModelo.setModel(knn);
        
        
        
     
        /***********************************************************************/
        
        //cpnfiguramos los algoritmos de recoemndacion
        //RecommenderApi recbaseline=new RecommenderApi(accesoModelo);
        //RecommenderApi recItem=new RecommenderApi(accesoModelo);
        
        //configure baseline predictor
        //BaseLinePredictor baseprediction=new BaseLinePredictor(accesoModelo);
        //recbaseline.addAlgorithm(baseprediction);
        /*
        //configure item-based algorithm
        WeightSum weightSum=new WeightSum(accesoModelo);
        ScoreAPI measapi=new ScoreAPI(accesoModelo);
        measapi.setScoreMeasure(weightSum);
        ItemBased itemBased=new ItemBased(measapi,accesoModelo);
        recItem.addAlgorithm(itemBased);
        double prediction=0;
        prediction=recItem.prediction(new User(1),new Item(15));
        System.out.println("prediction "+prediction);
        User u=new User(1);
        ArrayList<Item> top10=recItem.topNRecomendation(u, 10);
        for(Item item:top10){
            System.out.println(item.getId());
        }
       */
        /*
        //configure metrics
        MetricsAPI accessMetrics=new MetricsAPI();
        
        EvaluationType mae=new MAE();
        EvaluationType mse=new MSE();
        EvaluationType rmse=new RMSE();
        
        
        
        long startTime = System.nanoTime();
        
        double resultadoMetricasMae=0;
        double resultadoMetricasMse=0;
        double resultadoMetricasRmse=0;
         ArrayList<PairEvaluation> vectorPredictionRatings=new ArrayList<>();
        */
        
        /***********************************************************************/
         /*
        evalModel.setK(2);
        vectorPredictionRatings=evalModel.evaluationK(recItem);
        
        accessMetrics.setMetric(mae);
        resultadoMetricasMae=accessMetrics.calculate(vectorPredictionRatings);
        System.out.println("resultado metricas mae block 2  "+resultadoMetricasMae);
        
        accessMetrics.setMetric(mse);
        resultadoMetricasMse=accessMetrics.calculate(vectorPredictionRatings);
        System.out.println("resultado metricas mse block 2  "+resultadoMetricasMse);
       
        accessMetrics.setMetric(rmse);
        resultadoMetricasRmse=accessMetrics.calculate(vectorPredictionRatings);
        System.out.println("resultado metricas rmae block 2  "+resultadoMetricasRmse);
         */
        /***********************************************************************/
         /*
        evalModel.setK(3);
        vectorPredictionRatings=evalModel.evaluationK(recItem);
        
        accessMetrics.setMetric(mae);
        resultadoMetricasMae=accessMetrics.calculate(vectorPredictionRatings);
        System.out.println("resultado metricas mae block 3  "+resultadoMetricasMae);
        
        accessMetrics.setMetric(mse);
        resultadoMetricasMse=accessMetrics.calculate(vectorPredictionRatings);
        System.out.println("resultado metricas mae block 3  "+resultadoMetricasMse);
       
        accessMetrics.setMetric(rmse);
        resultadoMetricasRmse=accessMetrics.calculate(vectorPredictionRatings);
        System.out.println("resultado metricas rmae block 3  "+resultadoMetricasRmse);
         */
        /***********************************************************************/
      /*
      evalModel.setK(4);
        vectorPredictionRatings=evalModel.evaluationK(recItem);
        
        accessMetrics.setMetric(mae);
        resultadoMetricasMae=accessMetrics.calculate(vectorPredictionRatings);
        System.out.println("resultado metricas mae block 4  "+resultadoMetricasMae);
        
        accessMetrics.setMetric(mse);
        resultadoMetricasMse=accessMetrics.calculate(vectorPredictionRatings);
        System.out.println("resultado metricas mae block 4  "+resultadoMetricasMse);
       
        accessMetrics.setMetric(rmse);
        resultadoMetricasRmse=accessMetrics.calculate(vectorPredictionRatings);
        System.out.println("resultado metricas rmae block 4  "+resultadoMetricasRmse);
         */
        /***********************************************************************/
        
      
      
        
        //configure metrics
        
       
        
        /*
        evalModel.setK(2);
        resultadoMetricas=evalModel.evaluationK(recItem,accessMetrics);
        //System.out.println("resultado metricas mae block 2  "+resultadoMetricas);
        evalModel.setK(3);
        resultadoMetricas=evalModel.evaluationK(recItem,accessMetrics);
        //System.out.println("resultado metricas mae block 3  "+resultadoMetricas);
        
        evalModel.setK(4);
        resultadoMetricas= evalModel.evaluationK(recItem,accessMetrics);
        //System.out.println("resultado metricas mae block 4  "+resultadoMetricas);
        
        
        //carga el primer bloque 
        ModelAPI modelapi=new ModelAPI(accesoDataApi);
        KnnModel knn=new KnnModel(accesoDataApi);
        modelapi.setModel(knn);
        modelapi.knnModel();
        RecommenderApi recapi=new RecommenderApi(modelapi);
        
         //configure item-based algorithm
        WeightSum wSum=new WeightSum(modelapi);
        ScoreAPI meapi=new ScoreAPI(modelapi, wSum);
        ItemBased iBased=new ItemBased(meapi,modelapi);
        
        /*
        recapi.configureAlgorithm(iBased);
        User u=new User(1);
        ArrayList<Item> top10=recapi.topNRecomendation(u, 10);
        for(Item item:top10){
            System.out.println(item.getId());
        }
        */
        
        /*
        EvaluationType mse=new MSE();
        accessMetrics.setMetric(mse);
       
        resultadoMetricas=evalModel.evaluationK(recbaseline,accessMetrics);
        System.out.println("resultado metricas mse block 0  "+resultadoMetricas);
        evalModel.setK(1);
        resultadoMetricas=evalModel.evaluationK(recbaseline,accessMetrics);
        
        System.out.println("resultado metricas mse block 1  "+resultadoMetricas);
        */
        /*
        evalModel.setK(2);
        resultadoMetricas=evalModel.evaluationK(recbaseline,accessMetrics);
        System.out.println("resultado metricas mse block 2  "+resultadoMetricas);
        evalModel.setK(3);
        resultadoMetricas=evalModel.evaluationK(recbaseline,accessMetrics);
        System.out.println("resultado metricas mse block 3  "+resultadoMetricas);
        
        evalModel.setK(4);
        resultadoMetricas=evalModel.evaluationK(recbaseline,accessMetrics);
        System.out.println("resultado metricas mse block 4  "+resultadoMetricas);
        */
        /*
        EvaluationType rmse=new MSE();
        accessMetrics.setMetric(rmse);
        
        resultadoMetricas=evalModel.evaluationK(recbaseline,accessMetrics);
        System.out.println("resultado metricas rmse block 0  "+resultadoMetricas);
        evalModel.setK(1);
        
        resultadoMetricas=evalModel.evaluationK(recbaseline,accessMetrics);
        System.out.println("resultado metricas rmse block 1  "+resultadoMetricas);
        */
        /*
        evalModel.setK(2);
        resultadoMetricas=evalModel.evaluationK(recbaseline,accessMetrics);
        System.out.println("resultado metricas rmse block 2  "+resultadoMetricas);
        evalModel.setK(3);
        resultadoMetricas=evalModel.evaluationK(recbaseline,accessMetrics);
        System.out.println("resultado metricas rmse block 3  "+resultadoMetricas);
        evalModel.setK(4);
        resultadoMetricas=evalModel.evaluationK(recbaseline,accessMetrics);
        System.out.println("resultado metricas rmse block 4  "+resultadoMetricas);
        */
        //long endTime = System.nanoTime();
        //long duration = (endTime - startTime);
        //System.out.println("duration exacution "+duration/1000000);
        /**********************************************************************
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
            if(predictionBaseline!=0){
                //System.out.println("prediccion Baseline = "+predictionBaseline);
                entradaBaseline.setFirst(predictionBaseline);
                entradaBaseline.setSecond(evento.getRating());
                resPredictionBaseline.add(entradaBaseline);
            }
            if(predictionItem!=0){
                //System.out.println("prediccion Item = "+predictionItem);
                entradaItem.setFirst(predictionItem);
                entradaItem.setSecond(evento.getRating());
                resPredictionItem.add(entradaItem);
            }
            //---
            
           
        }
       
        
        
        //MetricsAPI accessMetrics=new MetricsAPI();
        //EvaluationType mae=new MAE();
        //accessMetrics.setMetric(mae);
        
        
        
        double resultadoMae=0;
         double resultadoItem=0;
         
        resultadoMae=mae.calculate(resPredictionBaseline);
        System.out.println("mae baseline "+resultadoMae);
        
        resultadoItem=mae.calculate(resPredictionItem);
        System.out.println("mae item "+resultadoItem);
        
        
        EvaluationType mse=new MSE();
        accessMetrics.setMetric(mse);
        resultadoMae=mse.calculate(resPredictionBaseline);
        System.out.println("mse baseline "+resultadoMae);
        
        resultadoItem=mse.calculate(resPredictionItem);
        System.out.println("mse item "+resultadoItem);
  
        EvaluationType rmse=new RMSE();
        accessMetrics.setMetric(rmse);
        resultadoMae=rmse.calculate(resPredictionBaseline);
        System.out.println("rmse baseline "+resultadoMae);
        
        resultadoItem=rmse.calculate(resPredictionItem);
        System.out.println("rmse item "+resultadoItem);
        
        ***********************************************************************/
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
