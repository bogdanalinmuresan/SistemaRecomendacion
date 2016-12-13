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
package Ratings;

import Algorithms.CosineSimilarity;
import Algorithms.RecommenderApi;
import Algorithms.SimilarityApi;
import Dao.AccessDataAPI;
import Dao.Events;
import Dao.Item;
import Dao.Pair;
import Dao.User;
import Evaluation.EvaluationType;
import Evaluation.MAE;
import Evaluation.MSE;
import Evaluation.MetricsAPI;
import Evaluation.PairEvaluation;
import Evaluation.RMSE;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *Clase que represinta un modelo para la evaluación de algoritmos 
 * @author bogdan
 * @version 1.0
 */
public class EvaluationModel implements InterfaceModel 
{
    private AccessDataAPI adapi;
    int k;
    SimilarityApi similarityapi;
    RecommenderApi algoritmo;
    private ArrayList<Events> testBlock0=new ArrayList<>();
    private ArrayList<Events> testBlock1=new ArrayList<>();
    private ArrayList<Events> testBlock2=new ArrayList<>();
    private ArrayList<Events> testBlock3=new ArrayList<>();
    private ArrayList<Events> testBlock4=new ArrayList<>();
    ArrayList<Events> trainBlock0=new ArrayList<>();
    ArrayList<Events> trainBlock1=new ArrayList<>();
    ArrayList<Events> trainBlock2=new ArrayList<>();
    ArrayList<Events> trainBlock3=new ArrayList<>();
    ArrayList<Events> trainBlock4=new ArrayList<>();
    HashMap<Item, ArrayList<Pair> >similarityMatrixModel0=new HashMap<> ();
    HashMap<Item, ArrayList<Pair> >similarityMatrixModel1=new HashMap<> ();
    HashMap<Item, ArrayList<Pair> >similarityMatrixModel2=new HashMap<> ();
    HashMap<Item, ArrayList<Pair> >similarityMatrixModel3=new HashMap<> ();
    HashMap<Item, ArrayList<Pair> >similarityMatrixModel4=new HashMap<> ();
    HashMap<Item,ArrayList<Events> > itemsEventsTrainBlock0=new HashMap<>();
    HashMap<Item,ArrayList<Events> > itemsEventsTrainBlock1=new HashMap<>();
    HashMap<Item,ArrayList<Events> > itemsEventsTrainBlock2=new HashMap<>();
    HashMap<Item,ArrayList<Events> > itemsEventsTrainBlock3=new HashMap<>();
    HashMap<Item,ArrayList<Events> > itemsEventsTrainBlock4=new HashMap<>();
    
    /**
     *Constructor por defecto
     */
    public EvaluationModel(){
        
    }
    
    /**
     *Constructor por parámetros
     * @param accessData
     * @param simapi
     */
    public EvaluationModel(AccessDataAPI accessData,SimilarityApi simapi){
        this.adapi=accessData; 
        this.k=0;
        this.similarityapi=simapi;
        divideBlocks();
        loadItemsTrainForTestBlock();
        loadItemsEventsForTrainBlock();
        buildModel(); 
    }
    
    /**
     *Constructor por parámetros
     * @param accessData
     * @param k
     * @param simapi
     */
    public EvaluationModel(AccessDataAPI accessData,int k,SimilarityApi simapi){
        this.adapi=accessData; 
        this.k=k;
        this.similarityapi=simapi;
        divideBlocks();
        loadItemsTrainForTestBlock();
        loadItemsEventsForTrainBlock();
        buildModel(); 
    }
    
    /**
     * Método  para elvaluacion del bloque k 
     * @param recapi acceso a los algoritmos de recomendacion 
     * @return  una lista de predicciones con valoraciones
     * @see EvaluationModel#setK(int) 
     */
    public ArrayList<PairEvaluation> evaluationK(RecommenderApi recapi){
        //variables
        double prediction=0;
        ArrayList<PairEvaluation> vectorPredictionRatings=new ArrayList<>();
        ArrayList<Events> testBlock=null;
        //cargar test bloque segun parámetro
        if (k==0){
            testBlock=getTestBlock0();
        }else{
            if(k==1)
                testBlock=getTestBlock1();
            else
                if(k==2)
                    testBlock=getTestBlock2();
                else
                    if(k==3)
                        testBlock=getTestBlock3();
                        //System.out.println("entra en k ==3");
                    
                    else
                        if(k==4)
                            testBlock=getTestBlock4();
                    
        }
        PairEvaluation entrada=new PairEvaluation();
        //para cade rating del testBlock realizamos una prediccion
        //System.out.println("testBlock.size()= "+testBlock.size());
         for(int i=0; i<testBlock.size();i++){
           //System.out.println("recorrer test block"+i+"tam test block "+testBlock.size());
          //System.out.println("item "+testBlock.get(i).getItem().getId()+" user  "+testBlock.get(i).getUser().getUserId()+" rating "+testBlock.get(i).getRating());
            Events evento=new Events(testBlock.get(i));
            prediction=recapi.prediction(evento.getUser(), evento.getItem());
           
            if(prediction!=0){
                //System.out.println("prediction !=0 "+prediction);
                entrada.setFirst(prediction);
                entrada.setSecond(evento.getRating());
                vectorPredictionRatings.add(entrada);
            }
         }
        
        return vectorPredictionRatings;
        
    }
    
   
   
    /**
     *Método que prepara los k test bloques de la evaluación por validacion cruzada 
     */
    public void divideBlocks(){
        //System.out.print("entra en build model");
        for(int i=0; i<adapi.getEvents().size()-4;i+=5){
            if(i<=adapi.getEvents().size())
            {
                int indice0=i;
                int indice1=i+1;
                int indice2=i+2;
                int indice3=i+3;
                int indice4=i+4;
              
                Events evento0=new Events(adapi.getEvents().get(indice0));
                

                Events evento1=new Events(adapi.getEvents().get(indice1));
                
                Events evento2=new Events(adapi.getEvents().get(indice2));
                
                Events evento3=new Events(adapi.getEvents().get(indice3));
                
                Events evento4=new Events(adapi.getEvents().get(indice4));
                

                testBlock0.add(evento0);
                testBlock1.add(evento1);
                testBlock2.add(evento2);
                testBlock3.add(evento3);
                testBlock4.add(evento4);
                        
            }
                       
        }  
    }
    
    /**
     *Método selectro que devuelve el  bloque para el test número 0 
     * @return el bloque del test 0
     */
    public ArrayList<Events> getTestBlock0(){
        return testBlock0;
    }

    /**
     *Método selectro que devuelve el  bloque para el test número 1 
     * @return el bloque del test número 0
     */
    public ArrayList<Events> getTestBlock1(){
        return testBlock1;
    }

    /**
     *Método selectro que devuelve el  bloque para el test número 2
     * @return el bloque del test número 2
     */
    public ArrayList<Events> getTestBlock2(){
        return testBlock2;
    }

    /**
     *Método selectro que devuelve el  bloque para el test número 3
     * @return el bloque del test número 3
     */
    public ArrayList<Events> getTestBlock3(){
        return testBlock3;
    }

    /**
     *Método selectro que devuelve el  bloque para el test número 4
     * @return el bloque del test número 4
     */
    public ArrayList<Events> getTestBlock4(){
        return testBlock4 ;
    }

    /**
     *Método selectro que devuelve el  bloque para el entrenamiento número 0
     * @return el bloque del entrenamiento número 0
     */
    public ArrayList<Events> getTrainBlock0(){
        return trainBlock0;
    }

    /**
     *Método selectro que devuelve el  bloque para el entrenamiento número 1
     * @return el bloque del entrenamiento número 1
     */
    public ArrayList<Events> getTrainBlock1(){
        return trainBlock1;
    }

    /**
     *Método selectro que devuelve el  bloque para el entrenamiento número 2
     * @return el bloque del entrenamiento número 2
     */
    public ArrayList<Events> getTrainBlock2(){
        return trainBlock2;
    }

    /**
     *Método selectro que devuelve el  bloque para el entrenamiento número 3
     * @return el bloque del entrenamiento número 3
     */
    public ArrayList<Events> getTrainBlock3(){
        return trainBlock3;
    }

    /**
     *Método selectro que devuelve el  bloque para el entrenamiento número 4
     * @return el bloque del entrenamiento número 4
     */
    public ArrayList<Events> getTrainBlock4(){
        return trainBlock4;
    }
    
    /**
     *Método que devuelve el la matriz del modelo de evaluacion
     * @return la matriz del modelo 
     */
    @Override
    public HashMap<Item, ArrayList<Pair> > getItemsUniverse() {
        if (k==0){
            return similarityMatrixModel0;
        }else{
            if(k==1)
                return similarityMatrixModel1;
            else
                if(k==2)
                    return similarityMatrixModel2;
                else
                    if(k==3)
                        return similarityMatrixModel3;
                    else
                        if(k==4)
                            return similarityMatrixModel4;
                    
        }
        return null;
    }
       
    /**
     *Dado un elemento devuelve una lista de todo los eleemntos 
     * @param ite elemento
     * @return lista de elementos similares
     * @see InterfaceModel#getSimilarItems(Dao.Item) 
     */
    @Override
    public ArrayList<Pair> getSimilarItems(Item ite) {
       if (k==0){
            return getSimilarItemsTrainBlock0(ite);
        }else{
            if(k==1)
                return getSimilarItemsTrainBlock1(ite);
            else
                if(k==2)
                    return getSimilarItemsTrainBlock2(ite);
                else
                    if(k==3)
                        return getSimilarItemsTrainBlock3(ite);
                    else
                        if(k==4)
                            return getSimilarItemsTrainBlock4(ite);
                    
        }
        return null; 
    }

    /**
     *Devuelve la valoracion de un usuario a un elemento
     * @param i elemento
     * @param u usuario
     * @return la valoracion
     */
    @Override
    public double getRatingOfSimilarItemUserVoted(Item i, User u) {
        if (k==0){
            return getRatingsItemUserVotedTrainBlock0( i, u);
        }else{
            if(k==1)
                return getRatingsItemUserVotedTrainBlock1( i, u);
            else
                if(k==2)
                    return getRatingsItemUserVotedTrainBlock2( i, u);
                else
                    if(k==3)
                        return getRatingsItemUserVotedTrainBlock3( i, u);
                    else
                        if(k==4)
                            return getRatingsItemUserVotedTrainBlock4( i, u);
                    
        }
        return 0;
         
    }

    /**
     *Metodo para cargar el modelo
     * @see InterfaceModel#buildModel() 
     */
    @Override
    public void buildModel() {
        if (k==0){
            buildModel0();
        }else{
            if(k==1)
                buildModel1();
            else
                if(k==2)
                    buildModel2();
                else
                    if(k==3)
                        buildModel3();
                    else
                        if(k==4)
                            buildModel4();
                    
        }
        
    }
    
    /**
     *Cargar el bloque de entrenamiento el bloque del test 0
     */
    public void loadIEventsTrainForTestBlock0(){
        //block 1 
        for(int i=0;i<getTestBlock1().size();i++){
            Events evento=new Events(getTestBlock1().get(i));
            trainBlock0.add(evento);
        }
        //block 2
        for(int i=0;i<getTestBlock2().size();i++){
           Events evento=new Events(getTestBlock2().get(i));
           trainBlock0.add(evento);
        }
        //block3
        for(int i=0;i<getTestBlock3().size();i++){
           Events evento=new Events(getTestBlock3().get(i));
           trainBlock0.add(evento);
        }
        //block 4
        for(int i=0;i<getTestBlock4().size();i++){
           Events evento=new Events(getTestBlock4().get(i));
           trainBlock0.add(evento);
        }
        System.out.println("train Block tam"+trainBlock0.size());
        
        
    } 

    /**
     *Cargar el bloque de entrenamiento el bloque del test 1
     */
    public void loadIEventsTrainForTestBlock1(){
        
        //block 0 
        for(int i=0;i<getTestBlock0().size();i++){
            Events evento=new Events(getTestBlock0().get(i));
            trainBlock1.add(evento);
        }
        //block 2
        for(int i=0;i<getTestBlock2().size();i++){
           Events evento=new Events(getTestBlock2().get(i));
           trainBlock1.add(evento);
        }
        //block3
        for(int i=0;i<getTestBlock3().size();i++){
           Events evento=new Events(getTestBlock3().get(i));
           trainBlock1.add(evento);
        }
        //block 4
        for(int i=0;i<getTestBlock4().size();i++){
           Events evento=new Events(getTestBlock4().get(i));
           trainBlock1.add(evento);
        }
        
     
    }

    /**
     *Cargar el bloque de entrenamiento el bloque del test 2
     */
    public void loadIEventsTrainForTestBlock2(){
         
        //block 0 
        for(int i=0;i<getTestBlock0().size();i++){
            Events evento=new Events(getTestBlock0().get(i));
            trainBlock2.add(evento);
        }
        //block 1
        for(int i=0;i<getTestBlock1().size();i++){
           Events evento=new Events(getTestBlock1().get(i));
           trainBlock2.add(evento);
        }
        //block3
        for(int i=0;i<getTestBlock3().size();i++){
           Events evento=new Events(getTestBlock3().get(i));
           trainBlock2.add(evento);
        }
        //block 4
        for(int i=0;i<getTestBlock4().size();i++){
           Events evento=new Events(getTestBlock4().get(i));
           trainBlock2.add(evento);
        }
        
    }

    /**
     *Cargar el bloque de entrenamiento el bloque del test 3
     */
    public void loadIEventsTrainForTestBlock3(){
        
        //block 0 
        for(int i=0;i<getTestBlock0().size();i++){
            Events evento=new Events(getTestBlock0().get(i));
            trainBlock3.add(evento);
        }
        //block 1
        for(int i=0;i<getTestBlock1().size();i++){
           Events evento=new Events(getTestBlock1().get(i));
           trainBlock3.add(evento);
        }
        //block2
        for(int i=0;i<getTestBlock2().size();i++){
           Events evento=new Events(getTestBlock2().get(i));
           trainBlock3.add(evento);
        }
        //block 4
        for(int i=0;i<getTestBlock4().size();i++){
           Events evento=new Events(getTestBlock4().get(i));
           trainBlock3.add(evento);
        }
       
    }

    /**
     *Cargar el bloque de entrenamiento el bloque del test 4
     */
    public void loadIEventsTrainForTestBlock4(){
         
        //block 0
        for(int i=0;i<getTestBlock0().size();i++){
           Events evento=new Events(getTestBlock0().get(i));
           trainBlock4.add(evento);
        }
        //block 1 
        for(int i=0;i<getTestBlock1().size();i++){
            Events evento=new Events(getTestBlock1().get(i));
            trainBlock4.add(evento);
        }
        //block 2
        for(int i=0;i<getTestBlock2().size();i++){
           Events evento=new Events(getTestBlock2().get(i));
           trainBlock4.add(evento);
        }
        //block3
        for(int i=0;i<getTestBlock3().size();i++){
           Events evento=new Events(getTestBlock3().get(i));
           trainBlock4.add(evento);
        }
       
    }
   
    /**
     *Cargar el bloque de entrenamiento el bloque del test 
     */
    public void loadItemsTrainForTestBlock(){
        if (k==0){
            loadIEventsTrainForTestBlock0();
        }else{
            if(k==1)
                loadIEventsTrainForTestBlock1();
            else
                if(k==2)
                    loadIEventsTrainForTestBlock2();
                else
                    if(k==3)
                        loadIEventsTrainForTestBlock3();
                    else
                        if(k==4)
                            loadIEventsTrainForTestBlock4();
                    
        }
      
    }
    
    /**
     *Carga las calificaciones de los elementos para el train block
     */
    public void loadItemsEventsForTrainBlock(){
        if (k==0){
            loadItemEventsForTrainBlock0();
        }else{
            if(k==1)
                loadItemEventsForTrainBlock1();
            else
                if(k==2)
                    loadItemEventsForTrainBlock2();
                else
                    if(k==3)
                        loadItemEventsForTrainBlock3();
                    else
                        if(k==4)
                            loadItemEventsForTrainBlock4();
        }
    }
    
    /**
     *Carga las calificaciones de los elementos para el train block 0
     */
    public void loadItemEventsForTrainBlock0(){
        try{
           // Iterator<Movie> itMovie = getItemsDAO().iterator();
            for(Item m:getItems()){
                Item elemento=new Item(m);
                ArrayList<Events> resEvent=new ArrayList<>();
                //System.out.println("itemId: "+elemento.getId());
                for(Events e:trainBlock0) {
                    if(m.getId()==e.getItem().getId())
                    {
                        Events eve=new Events(e);
                        resEvent.add(eve);
                        //System.out.println(" itemId "+m.getId()+"event ItemId"+e.getItem().getId());
                    }
		}
                //insertamos
               // System.out.println("elemento"+elemento.getId());
                itemsEventsTrainBlock0.put(elemento, resEvent); 
                
            }
            
        }catch(Exception e ){
            System.err.println("e = "+e);
        }
    }

    /**
     *Carga las calificaciones de los elementos para el train block 1
     */
    public void loadItemEventsForTrainBlock1(){
        try{
           // Iterator<Movie> itMovie = getItemsDAO().iterator();
            for(Item m:getItems())
            {
                Item elemento=new Item(m);
                ArrayList<Events> resEvent=new ArrayList<>();
                //System.out.println("itemId: "+elemento.getId());
                for(Events e:trainBlock1) {
                    if(m.getId()==e.getItem().getId())
                    {
                        Events eve=new Events(e);
                        resEvent.add(eve);
                        //System.out.println(" itemId "+m.getId()+"event ItemId"+e.getItem().getId());
                    }
		}
                //insertamos
               // System.out.println("elemento"+elemento.getId());
                itemsEventsTrainBlock1.put(elemento, resEvent);   
            }
        }catch(Exception e ){
            
        }
    }

    /**
     *Carga las calificaciones de los elementos para el train block 2
     */
    public void loadItemEventsForTrainBlock2(){
        try{
           // Iterator<Movie> itMovie = getItemsDAO().iterator();
            for(Item m:getItems())
            {
                Item elemento=new Item(m);
                ArrayList<Events> resEvent=new ArrayList<>();
                //System.out.println("itemId: "+elemento.getId());
                for(Events e:trainBlock2) {
                    if(m.getId()==e.getItem().getId())
                    {
                        Events eve=new Events(e);
                        resEvent.add(eve);
                        //System.out.println(" itemId "+m.getId()+"event ItemId"+e.getItem().getId());
                    }
		}
                //insertamos
               // System.out.println("elemento"+elemento.getId());
                itemsEventsTrainBlock2.put(elemento, resEvent);   
            }
        }catch(Exception e ){
            
        }
    }

    /**
     *Carga las calificaciones de los elementos para el train block 3
     */
    public void loadItemEventsForTrainBlock3(){
        try{
           // Iterator<Movie> itMovie = getItemsDAO().iterator();
            for(Item m:getItems())
            {
                Item elemento=new Item(m);
                ArrayList<Events> resEvent=new ArrayList<>();
                //System.out.println("itemId: "+elemento.getId());
                for(Events e:trainBlock3) {
                    if(m.getId()==e.getItem().getId())
                    {
                        Events eve=new Events(e);
                        resEvent.add(eve);
                        //System.out.println(" itemId "+m.getId()+"event ItemId"+e.getItem().getId());
                    }
		}
                //insertamos
               // System.out.println("elemento"+elemento.getId());
                itemsEventsTrainBlock3.put(elemento, resEvent);   
            }
        }catch(Exception e ){
            
        }
    }

    /**
     *Carga las calificaciones de los elementos para el train block 4 
     */
    public void loadItemEventsForTrainBlock4(){
        try{
           // Iterator<Movie> itMovie = getItemsDAO().iterator();
            for(Item m:getItems())
            {
                Item elemento=new Item(m);
                ArrayList<Events> resEvent=new ArrayList<>();
                //System.out.println("itemId: "+elemento.getId());
                for(Events e:trainBlock4) {
                    if(m.getId()==e.getItem().getId())
                    {
                        Events eve=new Events(e);
                        resEvent.add(eve);
                        //System.out.println(" itemId "+m.getId()+"event ItemId"+e.getItem().getId());
                    }
		}
                //insertamos
               // System.out.println("elemento"+elemento.getId());
                itemsEventsTrainBlock4.put(elemento, resEvent);   
            }
        }catch(Exception e ){
            
        }
    }
    
    /**
     *Construye el modelo para el train block y test block 0
     */
    public void buildModel0(){
        //CosineSimilarity c=new CosineSimilarity();
        ArrayList<Pair> itemSimilarity;
        //System.out.println("itemSimilarity lo primero en builmodels"+itemSimilarity.size());
        double similitud=0.0;
        Pair p;
        
        //for each item get ratings 
        for (Map.Entry<Item, ArrayList<Events>> entryA : itemsEventsTrainBlock0.entrySet()) {
            Item item1=entryA.getKey();
            ArrayList<Events> ratingsA=entryA.getValue();
           //System.out.println("itemId "+item1.getId());
            //System.out.println("tam rating "+ratingsA.size());
            
            itemSimilarity=new ArrayList<>();
            //select ItemVectorRating greater than threshold 
            if(ratingsA.size() >= InterfaceModel.minRatings){
                //System.out.println("itemAId = "+item1.getId());
                //System.out.println("tam ratingsA = "+ratingsA.size());
                //for each item, get ratings 
                for (Map.Entry<Item, ArrayList<Events>> entryB : itemsEventsTrainBlock0.entrySet()) {
                    
                    Item item2=entryB.getKey();
                    ArrayList<Events> ratingsB=entryB.getValue();
                    if(item1.getId()!=item2.getId()){
                        //not calculate the similarity for the same item
                        if(ratingsB.size() >= InterfaceModel.minRatings){
                            //calculate the similarity between items 
                            similitud=similarityapi.compare(ratingsA,ratingsB);
                            //System.out.println("simiitud ="+similitud);
                            p=new Pair(item2, similitud);
                            itemSimilarity.add(p);
                            //System.out.println("item "+item1.getId()+" itemSimilaritySize() "+itemSimilarity.size());
                        }
                    }
                }
                similarityMatrixModel0.put(item1, itemSimilarity);
                //System.out.println("elemento  "+item1.getId());
            }   
        }
        /*
        for (Map.Entry<Item,ArrayList<Pair> > entry : similarityMatrixModel0.entrySet()) {
            Item key = entry.getKey();
            ArrayList<Pair> value = entry.getValue();
            System.out.println("-------------------------------------------------------------------------------------------- ");
            System.out.println("elem = "+key.getId());
            // ...
            for(Pair s:value){
                System.out.println("similar elem = "+s.getItem1().getId());
                System.out.println("similitud = "+s.getSimilitud());
            }
        }
                */
    }

    /**
     *Construye el modelo para el train block y test block 1
     */
    public void buildModel1(){
        //CosineSimilarity c=new CosineSimilarity();
        ArrayList<Pair> itemSimilarity;
        //System.out.println("itemSimilarity lo primero en builmodels"+itemSimilarity.size());
        double similitud=0.0;
        Pair p;
        
        //for each item get ratings 
        for (Map.Entry<Item, ArrayList<Events>> entryA : itemsEventsTrainBlock1.entrySet()) {
            Item item1=entryA.getKey();
            ArrayList<Events> ratingsA=entryA.getValue();
           //System.out.println("itemId "+item1.getId());
            //System.out.println("tam rating "+ratingsA.size());
            
            itemSimilarity=new ArrayList<>();
            //select ItemVectorRating greater than threshold 
            if(ratingsA.size() >= InterfaceModel.minRatings){
                //System.out.println("itemAId = "+item1.getId());
                //System.out.println("tam ratingsA = "+ratingsA.size());
                //for each item, get ratings 
                for (Map.Entry<Item, ArrayList<Events>> entryB : itemsEventsTrainBlock1.entrySet()) {
                    
                    Item item2=entryB.getKey();
                    ArrayList<Events> ratingsB=entryB.getValue();
                    if(item1.getId()!=item2.getId()){
                        //not calculate the similarity for the same item
                        if(ratingsB.size() >= InterfaceModel.minRatings){
                            //calculate the similarity between items 
                            similitud=similarityapi.compare(ratingsA,ratingsB);
                            //System.out.println("simiitud ="+similitud);
                            p=new Pair(item2, similitud);
                            itemSimilarity.add(p);
                            //System.out.println("item "+item1.getId()+" itemSimilaritySize() "+itemSimilarity.size());
                        }
                    }
                }
                
                similarityMatrixModel1.put(item1, itemSimilarity);
            }   
        }
    }

    /**
     *Construye el modelo para el train block y test block 2
     */
    public void buildModel2(){
        //CosineSimilarity c=new CosineSimilarity();
        ArrayList<Pair> itemSimilarity;
        //System.out.println("itemSimilarity lo primero en builmodels"+itemSimilarity.size());
        double similitud=0.0;
        Pair p;
        
        //for each item get ratings 
        for (Map.Entry<Item, ArrayList<Events>> entryA : itemsEventsTrainBlock2.entrySet()) {
            Item item1=entryA.getKey();
            ArrayList<Events> ratingsA=entryA.getValue();
           //System.out.println("itemId "+item1.getId());
            //System.out.println("tam rating "+ratingsA.size());
            
            itemSimilarity=new ArrayList<>();
            //select ItemVectorRating greater than threshold 
            if(ratingsA.size() >= InterfaceModel.minRatings){
                //System.out.println("itemAId = "+item1.getId());
                //System.out.println("tam ratingsA = "+ratingsA.size());
                //for each item, get ratings 
                for (Map.Entry<Item, ArrayList<Events>> entryB : itemsEventsTrainBlock2.entrySet()) {
                    
                    Item item2=entryB.getKey();
                    ArrayList<Events> ratingsB=entryB.getValue();
                    if(item1.getId()!=item2.getId()){
                        //not calculate the similarity for the same item
                        if(ratingsB.size() >= InterfaceModel.minRatings){
                            //calculate the similarity between items 
                            similitud=similarityapi.compare(ratingsA,ratingsB);
                            //System.out.println("simiitud ="+similitud);
                            p=new Pair(item2, similitud);
                            itemSimilarity.add(p);
                            //System.out.println("item "+item1.getId()+" itemSimilaritySize() "+itemSimilarity.size());
                        }
                    }
                }
                
                similarityMatrixModel2.put(item1, itemSimilarity);
            }   
        }
    }

    /**
     *Construye el modelo para el train block y test block 3
     */
    public void buildModel3(){
        //CosineSimilarity c=new CosineSimilarity();
        ArrayList<Pair> itemSimilarity;
        //System.out.println("itemSimilarity lo primero en builmodels"+itemSimilarity.size());
        double similitud=0.0;
        Pair p;
        
        //for each item get ratings 
        for (Map.Entry<Item, ArrayList<Events>> entryA : itemsEventsTrainBlock3.entrySet()) {
            Item item1=entryA.getKey();
            ArrayList<Events> ratingsA=entryA.getValue();
           //System.out.println("itemId "+item1.getId());
            //System.out.println("tam rating "+ratingsA.size());
            
            itemSimilarity=new ArrayList<>();
            //select ItemVectorRating greater than threshold 
            if(ratingsA.size() >= InterfaceModel.minRatings){
                //System.out.println("itemAId = "+item1.getId());
                //System.out.println("tam ratingsA = "+ratingsA.size());
                //for each item, get ratings 
                for (Map.Entry<Item, ArrayList<Events>> entryB : itemsEventsTrainBlock3.entrySet()) {
                    
                    Item item2=entryB.getKey();
                    ArrayList<Events> ratingsB=entryB.getValue();
                    if(item1.getId()!=item2.getId()){
                        //not calculate the similarity for the same item
                        if(ratingsB.size() >= InterfaceModel.minRatings ){
                            //calculate the similarity between items 
                            similitud=similarityapi.compare(ratingsA,ratingsB);
                            //System.out.println("simiitud ="+similitud);
                            p=new Pair(item2, similitud);
                            itemSimilarity.add(p);
                            //System.out.println("item "+item1.getId()+" itemSimilaritySize() "+itemSimilarity.size());
                        }
                    }
                }
                
                similarityMatrixModel3.put(item1, itemSimilarity);
            }   
        }
    }

    /**
     *Construye el modelo para el train block y test block 4
     */
    public void buildModel4(){
        CosineSimilarity c=new CosineSimilarity();
        ArrayList<Pair> itemSimilarity;
        //System.out.println("itemSimilarity lo primero en builmodels"+itemSimilarity.size());
        double similitud=0.0;
        Pair p;
        
        //for each item get ratings 
        for (Map.Entry<Item, ArrayList<Events>> entryA : itemsEventsTrainBlock4.entrySet()) {
            Item item1=entryA.getKey();
            ArrayList<Events> ratingsA=entryA.getValue();
           //System.out.println("itemId "+item1.getId());
            //System.out.println("tam rating "+ratingsA.size());
            
            itemSimilarity=new ArrayList<>();
            //select ItemVectorRating greater than threshold 
            if(ratingsA.size() >= InterfaceModel.minRatings){
                //System.out.println("itemAId = "+item1.getId());
                //System.out.println("tam ratingsA = "+ratingsA.size());
                //for each item, get ratings 
                for (Map.Entry<Item, ArrayList<Events>> entryB : itemsEventsTrainBlock4.entrySet()) {
                    
                    Item item2=entryB.getKey();
                    ArrayList<Events> ratingsB=entryB.getValue();
                    if(item1.getId()!=item2.getId()){
                        //not calculate the similarity for the same item
                        if(ratingsB.size() >= InterfaceModel.minRatings && item1.getId()!=item2.getId()){
                            //calculate the similarity between items 
                            similitud=similarityapi.compare(ratingsA,ratingsB);
                            //System.out.println("simiitud ="+similitud);
                            p=new Pair(item2, similitud);
                            itemSimilarity.add(p);
                            //System.out.println("item "+item1.getId()+" itemSimilaritySize() "+itemSimilarity.size());
                        }
                    }
                }
                
                similarityMatrixModel4.put(item1, itemSimilarity);
            }   
        }
    }
    
    /**
     *Devuelve los elementos similares para un elemnto dado para el bloque de entrenamiento 0
     * @param ite elemento 
     * @return lista de elemntos similares
     */
    public ArrayList<Pair> getSimilarItemsTrainBlock0(Item ite){
        ArrayList<Pair> similarItem=similarityMatrixModel0.get(ite);
        //System.out.println("tam similarItem en getSimilarItem()"+similarItem.size());
        //System.out.println("el elemento es"+ite.getId());
        if(similarItem!=null){
            Collections.sort(similarItem , new Comparator<Pair>() {
                    @Override
                    public int compare(Pair p1, Pair p2)
                    {
                        //return p1.getSimilitud().compareTo(p2.getSimilitud());
                        if(p1.getSimilitud()>p2.getSimilitud())
                            return -1;
                        else if(p1.getSimilitud()<p2.getSimilitud())
                            return 1;
                        else return 0;
                    }
            });
            ArrayList<Pair> resultado=new ArrayList<>();
            for(int i=0; i<similarItem.size() && i<neighborhoodSize_N; i++){
                resultado.add(similarItem.get(i));
            }
          return  resultado;
        }
        return null;
    }

    /**
     *Devuelve los elementos similares para un elemnto dado para el bloque de entrenamiento 1
     * @param ite elemento 
     * @return lista de elemntos similares
     */
    public ArrayList<Pair> getSimilarItemsTrainBlock1(Item ite){
        ArrayList<Pair> similarItem=similarityMatrixModel1.get(ite);
        //System.out.println("tam similarItem en getSimilarItem()"+similarItem.size());
        //System.out.println("el elemento es"+ite.getId());
        if(similarItem!=null){
            Collections.sort(similarItem , new Comparator<Pair>() {
                    @Override
                    public int compare(Pair p1, Pair p2)
                    {
                        //return p1.getSimilitud().compareTo(p2.getSimilitud());
                        if(p1.getSimilitud()>p2.getSimilitud())
                            return -1;
                        else if(p1.getSimilitud()<p2.getSimilitud())
                            return 1;
                        else return 0;
                    }
            });
          return similarItem;
        }
        return null;
    }

    /**
     *Devuelve los elementos similares para un elemnto dado para el bloque de entrenamiento 2
     * @param ite elemento 
     * @return lista de elemntos similares
     */
    public ArrayList<Pair> getSimilarItemsTrainBlock2(Item ite){
        ArrayList<Pair> similarItem=similarityMatrixModel2.get(ite);
        //System.out.println("tam similarItem en getSimilarItem()"+similarItem.size());
        //System.out.println("el elemento es"+ite.getId());
        if(similarItem!=null){
            Collections.sort(similarItem , new Comparator<Pair>() {
                    @Override
                    public int compare(Pair p1, Pair p2)
                    {
                        //return p1.getSimilitud().compareTo(p2.getSimilitud());
                        if(p1.getSimilitud()>p2.getSimilitud())
                            return -1;
                        else if(p1.getSimilitud()<p2.getSimilitud())
                            return 1;
                        else return 0;
                    }
            });
          return similarItem;
        }
        return null;
    }

    /**
     *Devuelve los elementos similares para un elemnto dado para el bloque de entrenamiento 3
     * @param ite elemento 
     * @return lista de elemntos similares
     */
    public ArrayList<Pair> getSimilarItemsTrainBlock3(Item ite){
        ArrayList<Pair> similarItem=similarityMatrixModel3.get(ite);
        //System.out.println("tam similarItem en getSimilarItem()"+similarItem.size());
        //System.out.println("el elemento es"+ite.getId());
        if(similarItem!=null){
            Collections.sort(similarItem , new Comparator<Pair>() {
                    @Override
                    public int compare(Pair p1, Pair p2)
                    {
                        //return p1.getSimilitud().compareTo(p2.getSimilitud());
                        if(p1.getSimilitud()>p2.getSimilitud())
                            return -1;
                        else if(p1.getSimilitud()<p2.getSimilitud())
                            return 1;
                        else return 0;
                    }
            });
          return similarItem;
        }
        return null;
    }

    /**
     *Devuelve los elementos similares para un elemnto dado para el bloque de entrenamiento 4
     * @param ite elemento 
     * @return lista de elemntos similares
     */
    public ArrayList<Pair> getSimilarItemsTrainBlock4(Item ite){
        ArrayList<Pair> similarItem=similarityMatrixModel4.get(ite);
        //System.out.println("tam similarItem en getSimilarItem()"+similarItem.size());
        //System.out.println("el elemento es"+ite.getId());
        if(similarItem!=null){
            Collections.sort(similarItem , new Comparator<Pair>() {
                    @Override
                    public int compare(Pair p1, Pair p2)
                    {
                        //return p1.getSimilitud().compareTo(p2.getSimilitud());
                        if(p1.getSimilitud()>p2.getSimilitud())
                            return -1;
                        else if(p1.getSimilitud()<p2.getSimilitud())
                            return 1;
                        else return 0;
                    }
            });
          return similarItem;
        }
        return null;
    }
    
    /**
     *Devuelve la valoracion de un usuario a un elemento del bloque de entrenamiento 0
     * @param i elemento 
     * @param u usuario
     * @return valoración
     */
    public double getRatingsItemUserVotedTrainBlock0(Item i,User u){
        double rating=-99;
        boolean esta =false;
        for(Events e:trainBlock0)
        {
           if(i.getId()==e.getItem().getId() && u.getUserId()==e.getUser().getUserId()){
                rating=e.getRating();
                esta=true;
                //System.out.println("entra en getRatingOfSimilarItemUserVoted(Item  i,User u) ");
           }
        }
        if(esta)
            return rating;
        else
            return -99;
    }

   /**
     *Devuelve la valoracion de un usuario a un elemento del bloque de entrenamiento 1
     * @param i elemento 
     * @param u usuario
     * @return valoración
     */
    public double getRatingsItemUserVotedTrainBlock1(Item i,User u){
        double rating=-99;
        boolean esta =false;
        for(Events e:trainBlock1)
        {
           if(i.getId()==e.getItem().getId() && u.getUserId()==e.getUser().getUserId()){
                rating=e.getRating();
                esta=true;
                //System.out.println("entra en getRatingOfSimilarItemUserVoted(Item  i,User u) ");
           }
        }
        if(esta)
            return rating;
        else
            return -99;
    }

    /**
     *Devuelve la valoracion de un usuario a un elemento del bloque de entrenamiento 2
     * @param i elemento 
     * @param u usuario
     * @return valoración
     */
    public double getRatingsItemUserVotedTrainBlock2(Item i,User u){
        double rating=-99;
        boolean esta =false;
        for(Events e:trainBlock2)
        {
           if(i.getId()==e.getItem().getId() && u.getUserId()==e.getUser().getUserId()){
                rating=e.getRating();
                esta=true;
                //System.out.println("entra en getRatingOfSimilarItemUserVoted(Item  i,User u) ");
           }
        }
        if(esta)
            return rating;
        else
            return -99;
    }

    /**
     *Devuelve la valoracion de un usuario a un elemento del bloque de entrenamiento 3
     * @param i elemento 
     * @param u usuario
     * @return valoración
     */
    public double getRatingsItemUserVotedTrainBlock3(Item i,User u){
        double rating=-99;
        boolean esta =false;
        for(Events e:trainBlock3)
        {
           if(i.getId()==e.getItem().getId() && u.getUserId()==e.getUser().getUserId()){
                rating=e.getRating();
                esta=true;
                //System.out.println("entra en getRatingOfSimilarItemUserVoted(Item  i,User u) ");
           }
        }
        if(esta)
            return rating;
        else
            return -99;
    }

    /**
     *Devuelve la valoracion de un usuario a un elemento del bloque de entrenamiento 4
     * @param i elemento 
     * @param u usuario
     * @return valoración
     */
    public double getRatingsItemUserVotedTrainBlock4(Item i,User u){
        double rating=-99;
        boolean esta =false;
        for(Events e:trainBlock4)
        {
           if(i.getId()==e.getItem().getId() && u.getUserId()==e.getUser().getUserId()){
                rating=e.getRating();
                esta=true;
                //System.out.println("entra en getRatingOfSimilarItemUserVoted(Item  i,User u) ");
           }
        }
        if(esta)
            return rating;
        else
            return -99;
    }
    
    /**
     *Método modificador 
     * @param i valor que indica que bloque se debe cargar 
     */
    public void setK(int i){
        this.k=i;
        divideBlocks();
        loadItemsTrainForTestBlock();
        loadItemsEventsForTrainBlock();
        buildModel(); 
    }

    /**
     *Método que devuelve las calificaciones
     * @return lista de calificaciones
     */
    @Override
    public ArrayList<Events> getEvents() {
        if (k==0){
            return getTrainBlock0();
        }else{
            if(k==1)
                getTrainBlock1();
            else
                if(k==2)
                    return getTrainBlock2();
                else
                    if(k==3)
                        return getTrainBlock3();
                    else
                        if(k==4)
                            return getTrainBlock4();
                    
        }
        return null;
    }

    /**
     *Método que devuelve los usuarios 
     * @return lista de usuario
     */
    @Override
    public Set<User> getUser() {
        return adapi.getUser();
    }

    /**
     *Método que devuelve los elementos
     * @return return lista de elementos
     */
    @Override
    public ArrayList<Item> getItems() {
        return adapi.getItems();
    }

    /**
     *Método que devuelve las calificaciones de los usuario
     * @return lista de calificacioens de todos los usuarios
     */
    @Override
    public HashMap<User, ArrayList<Events>> getUserEventDAO() {
        return adapi.getUserEvent();
    }

    /**
     *Método que devuelve las calificaciones de todos los elementos 
     * @return
     */
    @Override
    public HashMap<Item, ArrayList<Events>> getItemEventDAO() {
        return adapi.getItemEvent();
    }
}
