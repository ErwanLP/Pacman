/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author Erwan
 */
public class LireScore {

    public static ArrayList<ScoreItem> main(String[] argv) throws IOException {
        BufferedReader lecteurAvecBuffer = null;
        String ligne;
        try {
            lecteurAvecBuffer = new BufferedReader(new FileReader(argv[0]));
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
            System.out.println(argv[0]);
        }
        ArrayList<ScoreItem> listeScore = new ArrayList<ScoreItem>();
        String date;
        String name;
        String score;
        int i = 0;
        while ((ligne = lecteurAvecBuffer.readLine()) != null) {
//            System.out.println("---nouvelle ligne---");
            StringTokenizer st = new StringTokenizer(ligne, "/");
//            System.out.println(ligne);
            if (st.countTokens() == 3) {
                if(i ==0){
                   date = st.nextToken().substring(1, 11);
                } else {
                   date = st.nextToken().substring(0, 11);
                }
                
                name = st.nextToken();
                score = st.nextToken();

                ScoreItem s = new ScoreItem(date, name, Integer.parseInt(score));
                listeScore.add(s);
            }
            i++;
        }
        lecteurAvecBuffer.close();
        return listeScore;
    }
}
