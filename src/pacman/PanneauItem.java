/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author Erwan
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauItem extends JPanel {

    Color color;
    TraitementItem item;

    PanneauItem(Color color, TraitementItem item) {
        this.color = color;
        this.item = item;
    }

    public void paintComponent(Graphics g) {
        //x1, y1, width, height, arcWidth, arcHeight
        g.setColor(color);
        if (!item.canMoving) {
            g.setColor(Color.BLACK);
        }
        g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 0, 0);

        if (item.hasPawn) {
            g.setColor(Color.WHITE);
            g.fillOval(3 * this.getWidth() / 7, 3 * this.getHeight() / 7, this.getWidth() / 7, this.getHeight() / 7);
        }
        if (!item.tabEnnemi.isEmpty()) {
            Ennemi e = item.tabEnnemi.get(item.tabEnnemi.size() - 1); // initialiser
            int i = 0;
            while (e == null && i < item.tabEnnemi.size()) {
                e = item.tabEnnemi.get(i);
                i++;
            }
            if (e != null) {
                g.setColor(e.color);
                g.fillRoundRect(this.getWidth() / 4, this.getHeight() / 4, this.getWidth() / 2, this.getHeight() / 2, 0, 0);
            }
        }
        if (item.isPerso) {
            g.setColor(Color.YELLOW);
            g.fillOval(this.getWidth() /6,this.getHeight()/6, 4*this.getWidth()/6, 4*this.getHeight()/6);
        }


    }

    public void setItem(TraitementItem item) {
        this.item = item;
    }
}
