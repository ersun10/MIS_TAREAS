/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import modelo.Figura;
/**
 *
 * @author luisj
 */
public class Celula {
    
    private final int x, y;
    private Figura figura;
    
   public Celula(int x, int y, Figura figura){
        this.x = x;
        this.y = y;
        this.figura = figura;
    }
   
 public Celula(int x, int y){
        this.x = x;
        this.y = y;
        figura = null;
    }
 
    public Figura getFigura() {
        return figura;
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
