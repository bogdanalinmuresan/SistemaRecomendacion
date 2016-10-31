/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion.DAO;

/**
 *Clase que representa un pelicula 
 * @author bogdan
 */
class Movie {
    
    private int id ;
    private String title;
    private String genre;
    
    public int getId(){return id;}
    public String getTitle(){return title;}
    public String getGenre(){return genre;}
    
    public void setId(int nuevoID){this.id=nuevoID;}
    public void setTitle(String nuevotitle){this.title=nuevotitle;}
    public void setGenre(String nuevogenre){this.genre=nuevogenre;}
    
    
    
}
