/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author Erwan
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class LireFichier {

    public static int[][] main(String[] argv) throws IOException {
        BufferedReader lecteurAvecBuffer = null;
        String ligne;
        try {
            lecteurAvecBuffer = new BufferedReader(new FileReader(argv[0]));
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
            System.out.println(argv[0]);
        }
        int grilleFichier[][] = new int[Main.tailleGrilleLargeur][Main.tailleGrilleHauteur];
        int j = 0;
        while ((ligne = lecteurAvecBuffer.readLine()) != null) {
//            System.out.println(ligne);
            for (int i = 0; i < ligne.length(); i++) {
                if (i < Main.tailleGrilleLargeur && j < Main.tailleGrilleHauteur) {
                    int n = Character.getNumericValue(ligne.charAt(i));
//                    System.out.println(n);
                    grilleFichier[i][j] = n;
                }

            }
            j++;
        }
        lecteurAvecBuffer.close();
        return grilleFichier;
    }
}
