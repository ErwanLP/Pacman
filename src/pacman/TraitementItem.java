/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pacman;

import java.util.ArrayList;

/**
 *
 * @author Erwan
 */
public class TraitementItem {

    boolean isPerso;
    boolean canMoving;
    boolean hasPawn;

    ArrayList<Ennemi> tabEnnemi = new ArrayList<Ennemi>();

    TraitementItem(){
        isPerso =  false;
        canMoving = true;
        hasPawn = false;

    }

}
