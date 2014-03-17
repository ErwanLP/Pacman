/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author Erwan
 */
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

public class Audio extends Thread{


AudioInputStream audioInputStream = null;
    SourceDataLine line;
    String choixSon;

    Audio(String choixson) {
        this.choixSon = choixson;
    }



    public void run() {
        File fichier = new File(choixSon);
        try {
            try {
                AudioFileFormat format = AudioSystem.getAudioFileFormat(fichier);
            } catch (IOException ex) {
                Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } //catch (IOException e1) {
         //   e1.printStackTrace();
      //  }

        try {
            audioInputStream = AudioSystem.getAudioInputStream(fichier);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioFormat audioFormat = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

        try {
            line = (SourceDataLine) AudioSystem.getLine(info);

        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        }

        try {
            line.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        }
        line.start();
        Fenetre.begin = true;
        try {
            byte bytes[] = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1) {
                line.write(bytes, 0, bytesRead);
            }
        } catch (IOException io) {
            io.printStackTrace();
            return;
        }
    }
}
