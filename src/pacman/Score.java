/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Erwan
 */
class Score {

    ArrayList<ScoreItem> listeScore = new ArrayList<ScoreItem>();
    ArrayList<ScoreItem> listeBestScore = new ArrayList<ScoreItem>();

    public Score() throws IOException {
        updateScore();
    }

    public void addScore(int score, String name) throws IOException {
        if (listeScore.size() < 10) {
            ecrireScore(score, name);
        } else {
            if (score > listeBestScore.get(9).score) {
                ecrireScore(score, name);
            }
        }


        Main.traitement.finish();

    }

    private void updateScore() throws IOException {
        String[] data = new String[1];
        data[0] = "score.txt";
        listeScore = LireScore.main(data);
        trierBestScore();
        Main.traitement.afficherAllScore(listeBestScore);
    }

    private void ecrireScore(int score, String name) throws IOException {
        Date date = new Date();
//        date.toString();
        ScoreItem s = new ScoreItem(date.toString(), name, score);
        listeScore.add(s);

        String[] data = new String[4];
        data[0] = "score.txt";
        data[1] = s.date;
        data[2] = s.name;
        data[3] = Integer.toString(s.score);
        EcrireScore.main(data);
        updateScore();
    }

    private void trierBestScore() {
        listeBestScore.clear();
        for (int i = 0; i < listeScore.size(); i++) {
            listeScore.get(i).notPass = true;
        }
        int currentMaxValue;
        int currentMaxIndice;
        int limite;
        if(Integer.compare(10, listeScore.size())>0){
            limite = listeScore.size();
        } else {
            limite = 10;
        }
        for (int j = 0; j < limite; j++) {
            currentMaxIndice = 0;
            currentMaxValue = 0;
            for (int i = 0; i < listeScore.size(); i++) {
                if (listeScore.get(i).score > currentMaxValue && listeScore.get(i).notPass) {
                    currentMaxValue = listeScore.get(i).score;
                    currentMaxIndice = i;
                }
            }
            listeScore.get(currentMaxIndice).notPass = false;
            listeBestScore.add(listeScore.get(currentMaxIndice));


        }
    }
}

