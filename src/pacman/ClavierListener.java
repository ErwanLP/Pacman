/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erwan
 * enter = 10
 */
class ClavierListener implements KeyListener {

//    ArrayList<Character> charList = new ArrayList<Character>();
    static String name = "";

    public void keyPressed(KeyEvent event) {
//        System.out.println("caractère touche pressée : " + event.getKeyChar());
//        charList.add(event.getKeyChar());
//        System.out.println(event.getKeyCode());
        if (event.getKeyCode() == 10) {
            try {
                Main.traitement.ecrireScore(name);
                System.out.println(name);
            } catch (IOException ex) {
                Logger.getLogger(ClavierListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
//            System.out.println(event.getKeyCode());
            if (event.getKeyCode() == 8) {
                if (name.length() != 0) {
                    name = name.substring(0, name.length()-1);
                    System.out.println(name);
                }
            } else {
                name += event.getKeyChar();
            }
        }


//        pause();
    }

    public void keyReleased(KeyEvent event) {
//        System.out.println("Code touche relâchée : " + event.getKeyCode() + " - caractère touche relâchée : " + event.getKeyChar());
//        pause();
    }

    public void keyTyped(KeyEvent event) {
//        System.out.println("Code touche tapée : " + event.getKeyCode() + " - caractère touche tapée : " + event.getKeyChar());
//        pause();
    }

    private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
