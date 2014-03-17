/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pacman;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Erwan
 */
public class EcrireScore {

    public static void main(String[] argv) throws IOException {
        PrintWriter ecrivain;

        ecrivain = new PrintWriter(new BufferedWriter(new FileWriter(argv[0],true)));
        ecrivain.println(argv[1] + "/" + argv[2]+"/" + argv[3]+ "/");
        ecrivain.close();
    }
}

