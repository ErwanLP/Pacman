/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author Erwan
 */
class Position {

    int x;
    int y;
    TraitementItem grilleItem[][];
    String type;

    Position() {
    }

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean deplacerHaut(TraitementItem grilleItem[][], String type) {
        this.grilleItem = grilleItem;
        this.type = type;
        return deplacer(this.x, this.y - 1);
    }

    boolean deplacerBas(TraitementItem grilleItem[][], String type) {
        this.grilleItem = grilleItem;
        this.type = type;
        return deplacer(this.x, this.y + 1);

    }

    boolean deplacerGauche(TraitementItem grilleItem[][], String type) {
        this.grilleItem = grilleItem;
        this.type = type;
        return deplacer(this.x - 1, this.y);
    }

    boolean deplacerDroite(TraitementItem grilleItem[][], String type) {
        this.grilleItem = grilleItem;
        this.type = type;
        return deplacer(this.x + 1, this.y);

    }

    private boolean deplacementValid(int newPositionX, int newPositionY) {
        if (newPositionX < 0 || newPositionX == Main.tailleGrilleLargeur || newPositionY < 0 || newPositionY == Main.tailleGrilleHauteur) {
            return false;
        }
        return grilleItem[newPositionX][newPositionY].canMoving;
    }

    boolean deplacer(int newPositionX, int newPositionY) {
        if (newPositionX == -1) {
            newPositionX = Main.tailleGrilleLargeur - 1;
        }
        if (newPositionX == Main.tailleGrilleLargeur) {
            newPositionX = 0;
        }
        if (newPositionY == -1) {
            newPositionY = Main.tailleGrilleHauteur - 1;
        }
        if (newPositionY == Main.tailleGrilleHauteur) {
            newPositionY = 0;
        }

        if (deplacementValid(newPositionX, newPositionY)) {
            if (type.equalsIgnoreCase("perso")) {
                grilleItem[this.x][this.y].isPerso = false;
            }
            this.x = newPositionX;
            this.y = newPositionY;
            if (type.equalsIgnoreCase("perso")) {
                grilleItem[this.x][this.y].isPerso = true;
            }
            return true;
           

        } else {
            return false;
        }
    }
}
