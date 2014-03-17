/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.util.Date;

/**
 *
 * @author Erwan
 */
public class ScoreItem {

    int score;
    String name;
    String date;
    boolean notPass;

    ScoreItem(String date, String name, int score) {
        this.date = date;
        this.name = name;
        this.score = score;
        notPass = true;


    }
}
