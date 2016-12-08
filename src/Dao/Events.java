/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

/**
 *Clase que representa un calificación entre un usuario y un elemento
 * @author bogdan
 * 
 */
public class Events {
    
    private User user;
    private Movie movie;
    private double rating;
    private int timestamp;

    /**
     * Constructor por defecto
     */
    public Events() {
        this.movie = new Movie();
        this.user=new User();
        this.rating=0.0;
        this.timestamp=0;
    }
    /**
     * Constructor con parámetros
     * @param u
     * @param i
     * @param r
     * @param time 
     */
    public Events(User u,Movie i,Double r,int time){
        this.movie=new Movie(i);
        this.user=new User(u);
        this.rating=r;
        this.timestamp=time;
    }
    /**
     * Constructor de copia
     * @param e 
     */
    public Events(Events e ){
        setItem(e.getItem());
        setUser(e.getUser());
        setRating(e.getRating());
        setTimestamp(e.getTimestamp());
        
    }
    
    
    //metodos modificadores
    /**
     * Método modificador
     * @param newrating nueva valoración
     */
    public  void setRating(Double newrating){this.rating=newrating;}
    /**
     * Método modificador
     * @param newtimestamp nuevo timestamp 
     */
    public  void setTimestamp(int newtimestamp){this.timestamp=newtimestamp;}
    /**
     * Método modificador
     * @param u nuevo usuario
     */
    public  void setUser(User u){this.user=u;}
    /**
     * Metodo modificador
     * @param i nuevo elemento
     */
    public  void setItem(Item i){this.movie=(Movie) i;}
    
    
    //metodos selectores
    /**
     * Método selector
     * @return la valoracion 
     */
    public double getRating(){return rating;}
    /**
     * Método selector
     * @return devuelve el timestamp
     */
    public int getTimestamp(){return timestamp;}
    /**
     * Método selector
     * @return devueve el usuario
     */
    public User getUser(){return user;}
    /**
     * Método selector
     * @return devuelve la pelicula
     */
    public Item getItem(){return movie;}
    
    
    
    
}
