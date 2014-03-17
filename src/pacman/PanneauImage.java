/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pacman;

/**
 *
 * @author Erwan
 */
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauImage extends JPanel {
  public void paintComponent(Graphics g){
    try {
      Image img = ImageIO.read(new File("imagepacman.jpeg"));
      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}