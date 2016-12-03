/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ratings;

import Algorithms.CosineSimilarity;
import Dao.AccessDataAPI;
import Dao.Events;
import Dao.Item;
import Dao.Pair;
import Dao.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author bogdan
 */
public class EvaluationModel implements InterfaceModel {
    private AccessDataAPI adapi;
    int k;
    private ArrayList<Events> testBlock0=new ArrayList<>();
    private ArrayList<Events> testBlock1=new ArrayList<>();
    private ArrayList<Events> testBlock2=new ArrayList<>();
    private ArrayList<Events> testBlock3=new ArrayList<>();
    private ArrayList<Events> testBlock4=new ArrayList<>();
    ArrayList<Events> trainBlock0=new ArrayList<Events>();
    ArrayList<Events> trainBlock1=new ArrayList<Events>();
    ArrayList<Events> trainBlock2=new ArrayList<Events>();
    ArrayList<Events> trainBlock3=new ArrayList<Events>();
    ArrayList<Events> trainBlock4=new ArrayList<Events>();
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
    
    
    public EvaluationModel(){
        
    }
    public EvaluationModel(AccessDataAPI accessData,int k){
        this.adapi=accessData; 
        this.k=k;
        divideBlocks();
        loadItemsTrainForTestBlock();
        loadItemsEventsForTrainBlock();
        buildModel(); 
    }
    
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
    
    public ArrayList<Events> getTestBlock0(){
        return testBlock0;
    }
    public ArrayList<Events> getTestBlock1(){
        return testBlock1;
    }
    public ArrayList<Events> getTestBlock2(){
        return testBlock2;
    }
    public ArrayList<Events> getTestBlock3(){
        return testBlock3;
    }
    public ArrayList<Events> getTestBlock4(){
        return testBlock4 ;
    }

    public ArrayList<Events> getTrainBlock0(){
        return trainBlock0;
    }
    public ArrayList<Events> getTrainBlock1(){
        return trainBlock1;
    }
    public ArrayList<Events> getTrainBlock2(){
        return trainBlock2;
    }
    public ArrayList<Events> getTrainBlock3(){
        return trainBlock3;
    }
    public ArrayList<Events> getTrainBlock4(){
        return trainBlock4;
    }
    
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
        return similarityMatrixModel0;
    }

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
        return getSimilarItemsTrainBlock0(ite); 
    }

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
    public void loadItemEventsForTrainBlock1(){
        try{
           // Iterator<Movie> itMovie = getItemsDAO().iterator();
            for(Item m:adapi.getItems())
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
    public void loadItemEventsForTrainBlock2(){
        try{
           // Iterator<Movie> itMovie = getItemsDAO().iterator();
            for(Item m:adapi.getItems())
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
    public void loadItemEventsForTrainBlock3(){
        try{
           // Iterator<Movie> itMovie = getItemsDAO().iterator();
            for(Item m:adapi.getItems())
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
                itemsEventsTrainBlock1.put(elemento, resEvent);   
            }
        }catch(Exception e ){
            
        }
    }
    public void loadItemEventsForTrainBlock4(){
        try{
           // Iterator<Movie> itMovie = getItemsDAO().iterator();
            for(Item m:adapi.getItems())
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
    
    public void buildModel0(){
        CosineSimilarity c=new CosineSimilarity();
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
                            similitud=c.determineSimilarity(ratingsA,ratingsB);
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
    public void buildModel1(){
        CosineSimilarity c=new CosineSimilarity();
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
                            similitud=c.determineSimilarity(ratingsA,ratingsB);
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
    public void buildModel2(){
        CosineSimilarity c=new CosineSimilarity();
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
                            similitud=c.determineSimilarity(ratingsA,ratingsB);
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
    public void buildModel3(){
        CosineSimilarity c=new CosineSimilarity();
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
                            similitud=c.determineSimilarity(ratingsA,ratingsB);
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
                            similitud=c.determineSimilarity(ratingsA,ratingsB);
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
          return similarItem;
        }
        return null;
    }
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
    public ArrayList<Pair> getSimilarItemsTrainBlock4(Item ite){
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
          return similarItem;
        }
        return null;
    }
    
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
    
    public void setK(int i){
        this.k=i;
    }

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
        return getTrainBlock0();
    }

    @Override
    public Set<User> getUser() {
        return adapi.getUser();
    }

    @Override
    public ArrayList<Item> getItems() {
        return adapi.getItems();
    }
}
