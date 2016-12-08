/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

/**
 *Clase que representa un pelicula 
 * @author bogdan
 * @version 1.0
 */
public class Movie extends Item{
    
    
    private String title;
    private String genre;
    
    /**
     * Constructor con parámetros
     * @param i el identificador de la pelicula
     */
    public Movie(int i){
        super(i);
        this.title="";
        this.genre="";
    }
    /**
     * Constructor por defecto
     */
    public Movie(){
        super();
        title="";
        genre="";
    }
    /**
     * Constructor de copia
     * @param m la nueva peliculas
     */
    public Movie(Movie m){
        super(m.getId());
        this.setTitle(m.getTitle());
        this.setGenre(m.getGenre());
    }
    
    
    /**
     * Método selector
     * @return el titulo de la pelicula
     */
    public String getTitle(){return title;}
    /**
     * Método selector 
     * @return el género de la pelicula
     */
    public String getGenre(){return genre;}
    
   /**
    * Método modificador 
    * @param nuevotitle nuevo título
    */
    public void setTitle(String nuevotitle){this.title=nuevotitle;}
    /**
     * Método modificador
     * @param nuevogenre nuevo género
     */
    public void setGenre(String nuevogenre){this.genre=nuevogenre;}
    /*
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
    */
  
}
