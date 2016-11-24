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
    
    private User user;
    private Movie movie;
    private double rating;
    private int timestamp;

    public Events() {
        this.movie = new Movie();
        this.user=new User();
        this.rating=0.0;
        this.timestamp=0;
    }
    
    public Events(User u,Movie i,Double r,int time){
        this.movie=new Movie(i);
        this.user=new User(u);
        this.rating=r;
        this.timestamp=time;
    }
    
    public Events(Events e ){
        setItem(e.getItem());
        setUser(e.getUser());
        setRating(e.getRating());
        setTimestamp(e.getTimestamp());
        
    }
    
    
    //metodos modificadores
    //public void setUserID(int newuserID){this.userID.setUserId(newuserID);}
    //public void setMovieID(int newmovieID){this.movieID.setId(newmovieID);}
    public  void setRating(Double newrating){this.rating=newrating;}
    public  void setTimestamp(int newtimestamp){this.timestamp=newtimestamp;}
    public  void setUser(User u){this.user=u;}
    public  void setItem(Item i){this.movie=(Movie) i;}
    
    
    //metodos selectores
    //public int getUserID(){return (int) userID.getUserId();}
    //public int getMovieID(){return (int) movieID.getId();}
    public double getRating(){return rating;}
    public int getTimestamp(){return timestamp;}
    public User getUser(){return user;}
    public Item getItem(){return movie;}
    
    
    
    
}
