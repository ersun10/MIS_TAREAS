/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoestudio;

//Reproducir un MP3 en Java
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.decoder.JavaLayerException;

import javazoom.jl.player.Player;

//esta el clase para reproducior MP3 (ponerla en un archivo solo)
public class MP3 {

    private String filename;
    private static Player player;
private boolean isStopped = false;
// constructor que toma el nombre el arhivo MP3
    public MP3(String filen) {
        this.filename = filen;
    }

    public void close() {
        if (player != null) {
            player.close();
        }
    }

// reproduce le mp3 en la tarjeta de sonido predterminada
    public void play() throws JavaLayerException {
        
        if(player!= null)player.close();
        while(!isStopped){
        try {
            
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
           player.close();
        }

// correo el proceso en un nuevo hilo para deterner la ejecucion del programa
        
                    player.play();
  

       
    }
    }

    public void cerrar() {
            this.isStopped = true;
            this.close();
            //filename = "";
        }
}
