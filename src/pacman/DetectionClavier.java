/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Erwan
 */
class DetectionClavier implements KeyListener {

    //38 : haut
    //40 : bas
    //37 : gauhe
    //39 : droite
    static boolean isReleased = true;

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("keyPressed :" + e.getKeyCode());
        if (isReleased) {
            Main.traitement.toucheAppuyer(e.getKeyCode());
//        pause(50); // paut etre pas au bon endroitF
            isReleased = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("keyReleased: " + e);
        isReleased = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("keyTyped: " + e);
    }

    static public void pause(int temps) {
        try {
            Thread.sleep(temps);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
