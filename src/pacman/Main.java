/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Erwan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static Fenetre fenetre;
    static Traitement traitement;
    static Score score;
    static JFrame jf;
    final static int tailleGrilleHauteur = 31;
    static boolean partieIsRuning;
    final static int tailleGrilleLargeur = 28;
//    Audio sonDebut;

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        jf = new JFrame();
        fenetre = new Fenetre(jf);
        newPartie();

    }

    static void newPartie() throws IOException {
//        Audio sonDebut = new Audio("pacman_beginning.wav");
//        sonDebut.start();
        partieIsRuning = true;
        traitement = new Traitement(fenetre);
        score = new Score();
    }
}
