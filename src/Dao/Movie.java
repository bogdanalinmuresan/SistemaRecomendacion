/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.Objects;

/**
 *Clase que representa un pelicula 
 * @author bogdan
 */
public class Movie extends Item{
    
    
    private String title;
    private String genre;
    
    public Movie(int i){
        super(i);
        this.title="";
        this.genre="";
    }
    public Movie(){
        super();
        title="";
        genre="";
    }
    
    public Movie(Movie m){
        super(m.getId());
        this.setTitle(m.getTitle());
        this.setGenre(m.getGenre());
    }
    
    
    
    public String getTitle(){return title;}
    public String getGenre(){return genre;}
    
   
    public void setTitle(String nuevotitle){this.title=nuevotitle;}
    public void setGenre(String nuevogenre){this.genre=nuevogenre;}
    
    @Override
    public int hashCode(){
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        
        return Objects.equals(this.getId(), other.getId());
    }
  
}
