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
