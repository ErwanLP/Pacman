/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;

/**
 *
 * @author Erwan
 */
public class Ennemi {

    Color color;
    static int indice = 0;
    Position pos;
    static int positionInitX =(Main.tailleGrilleLargeur / 2)-3;
    static int positionInitY = (Main.tailleGrilleHauteur / 2)-1;
    TraitementGrille grille;
    int deplacement;
    int oldDeplacement;
    boolean deplacementReussi;
    int numero;
    static int vitesseEnnemi;
    Thread t;

    Ennemi(TraitementGrille grille) {
        deplacementReussi = true;
        deplacement = 0;
        vitesseEnnemi = 200;
        numero = indice;
        switch (indice) {
            case 0:
                color = Color.RED;
                break;
            case 1:
                color = Color.BLUE;
                break;
            case 2:
                color = Color.PINK;
                break;
            case 3:
                color = Color.GREEN;
                break;
            case 4:
                color = Color.CYAN;
                break;
            case 5:
                color = Color.MAGENTA;
                break;
            default:
                color = Color.RED;
        }
        indice++;
//        System.out.println(positionInitX);
//        System.out.println(positionInitY);
        this.pos = new Position(positionInitX, positionInitY);
        positionInitX++;
//        System.out.println(pos.x);
//        System.out.println(pos.y);
        this.grille = grille;
        this.grille.grilleItem[pos.x][pos.y].tabEnnemi.add(this);




    }

    void gererEnnemi() {

        Thread threadEnnemi = new Thread(new Runnable() {

            @Override
            public void run() {
                while (Main.partieIsRuning) { // boucle infinie*
//                    System.out.println(grille.go);
                    boolean condition = grille.go == 1;
                    if (condition) {
                        deplacerEnnemiRandom();
                    } if(!Main.partieIsRuning){
                    }

                }
            }
        });
        // lancer le thread
        this.t = threadEnnemi;
        threadEnnemi.start();
    }

    private void deplacerEnnemiRandom() {
        int lower = 0;
        int higher = 4;
        int random;
        do {
            random = (int) (Math.random() * (higher - lower)) + lower;
        } while (pasRetour(random, deplacement));
        oldDeplacement = deplacement;
        deplacement = random;
        this.grille.grilleItem[pos.x][pos.y].tabEnnemi.remove(this);
        if (grille.numeroGrille == 1) {
            if (pos.x > 12 && pos.x < 15 && pos.y > 11 && pos.y < 16) {
                deplacement = 0;
            }
        }
        switch (deplacement) {
            case 0:
                deplacementReussi = pos.deplacerHaut(grille.grilleItem, "ennemi");
                break;
            case 1:
                deplacementReussi = pos.deplacerBas(grille.grilleItem, "ennemi");
                break;
            case 2:
                deplacementReussi = pos.deplacerGauche(grille.grilleItem, "ennemi");
                break;
            case 3:
                deplacementReussi = pos.deplacerDroite(grille.grilleItem, "ennemi");

                break;
            default:
                System.out.println("nombre random non valid");
                deplacementReussi = false;
        }
        this.grille.grilleItem[pos.x][pos.y].tabEnnemi.add(this);

        if (!deplacementReussi) {
            deplacement = oldDeplacement;
        } else {
            if(vitesseEnnemi <50){ vitesseEnnemi = 50;}
            for (int i = 0; i < vitesseEnnemi; i++) {
                actionApresDeplacement();
                DetectionClavier.pause(1);
            }
        }

    }

//    private void deplacerEnnemiSuivre() {
//        int lower = 0;
//        int higher = 4;
//        int random;
//        random = (int) (Math.random() * (higher - lower)) + lower;
//        deplacementReussi = false;
//        Position oldPos = pos;
//        do {
//            switch (random) {
//                case 0:
//                    if (grille.perso.y >= pos.y) {
//                        deplacementReussi = pos.deplacerHaut(grille.grilleItem, "ennemi");
//                        deplacementReussi = true;
//                    }
//                    break;
//                case 1:
//                    if (grille.perso.y < pos.y) {
//                        deplacementReussi = pos.deplacerBas(grille.grilleItem, "ennemi");
//                        deplacementReussi = true;
//                    }
//                    break;
//                case 2:
//                    if (grille.perso.x < pos.x) {
//                        deplacementReussi = pos.deplacerGauche(grille.grilleItem, "ennemi");
//                        deplacementReussi = true;
//                    }
//                    break;
//                case 3:
//                    if (grille.perso.x >= pos.x) {
//                        deplacementReussi = pos.deplacerDroite(grille.grilleItem, "ennemi");
//                        deplacementReussi = true;
//                    }
//
//                    break;
//                default:
//                    System.out.println("nombre random non valid");
//            }
//
//        } while (!deplacementReussi);
//
//        this.grille.grilleItem[oldPos.x][oldPos.y].tabEnnemi.remove(this);
//        this.grille.grilleItem[pos.x][pos.y].tabEnnemi.add(this);
//
//
//        DetectionClavier.pause(400);
//
//
//    }
    private boolean pasRetour(int random, int deplacement) {
        if ((deplacement == 0 && random == 1) || (deplacement == 1 && random == 0) || (deplacement == 2 && random == 3) || (deplacement == 3 && random == 2)) {
            return true;
        } else {
            return false;
        }
    }

    private void actionApresDeplacement() {
        if (grille.perso.x == pos.x && grille.perso.y == pos.y) {
//            System.out.println("perdu");
            Main.traitement.collision();
        }
    }
}
