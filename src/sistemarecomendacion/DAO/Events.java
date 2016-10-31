/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion.DAO;

/**
 *Clase que representa un rating de la base de datos 
 * @author bogdan
 * 
 */
public class Events {
    
    private int userID;
    private int movieID;
    private double rating;
    private int timestamp;
    
    
    //metodos modificadores
    public void setUserID(int newuserID){this.userID=newuserID;}
    public void setMovieID(int newmovieID){this.movieID=newmovieID;}
    public void setRating(Double newrating){this.rating=newrating;}
    public void setTimestamp(int newtimestamp){this.timestamp=newtimestamp;}
    
    
    //metodos selectores
    public int getUserID(){return userID;}
    public int getMovieID(){return movieID;}
    public double getRating(){return rating;}
    public int getTimestamp(){return timestamp;}
    
    
    
    
}
