/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Erwan
 */
public class Traitement {

    TraitementGrille grille;
    Fenetre f;
    Thread threadUpdate;
    Audio sonEnd;

    public Traitement(final Fenetre f) throws IOException {
        grille = new TraitementGrille();
        this.f = f;
        sonEnd = new Audio("pacman_death.wav");
        f.init(grille.grilleItem);
        threadUpdate = new Thread(new Runnable() {

            @Override
            public void run() {
                while (Main.partieIsRuning) { // boucle infinie
                    f.update(grille.grilleItem);
                    f.updateScore(grille.score);
                    grille.pourcentage = this.calculerPourcentage();
                    f.updateHorloge(grille.horloge.temps,grille.score,grille.horloge, grille.pourcentage);
                }
            }

            private int  calculerPourcentage() {
                int currentPourcent = 0;
                for(int i =0; i<grille.horloge.minute+1; i++){
                    currentPourcent += (100*grille.score)/grille.nombrePawn;
                }
//                System.out.println(currentPourcent);
                return currentPourcent / (grille.horloge.minute+1);
            }
        });
        // lancer le thread
        threadUpdate.start();


    }

    void toucheAppuyer(int keyCode) {

        //38 : haut
        //40 : bas
        //37 : gauhe
        //39 : droite
        if (Main.partieIsRuning) {
            switch (keyCode) {
                case 38:
//                System.out.println("vous vous deplacer vers le haut");
                    grille.deplacerPersoHaut();
                    break;
                case 40:
//                System.out.println("vous vous deplacer vers le bas");
                    grille.deplacerPersoBas();
                    break;
                case 37:
//                System.out.println("vous vous deplacer vers la gauche");
                    grille.deplacerPersoGauche();
                    break;
                case 39:
//                System.out.println("vous vous deplacer vers la droite");
                    grille.deplacerPersoDroite();
                    break;
                default:
                    System.out.println("touche non prise en compte");
            }
            grille.actionApresDeplacement();
        }


    }

    void collision() {
        Main.partieIsRuning = false;
//        System.out.println("collision");
        sonEnd.start();
        ClavierListener.name = "";
        Main.fenetre.jtf.setText("");
//        threadUpdate.stop();
//        for(int i =0; i<grille.tabEnnemi.size(); i++){
//            grille.tabEnnemi.get(i).t.stop();
//        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        f.afficheLost();
        for (int i = 0; i < grille.tabEnnemi.size(); i++) {
            grille.tabEnnemi.get(i).t.stop();
        }
        threadUpdate.stop();
        grille.sonDebut.stop();
        grille.horloge.t.stop();
        sonEnd.stop();

    }

    void ecrireScore(String name) throws IOException {
        Main.score.addScore(grille.score, name);
    }

    void afficherAllScore(ArrayList<ScoreItem> listeScore) {
        f.afficherAllScore(listeScore);
    }

    void finish() throws IOException {
         f.afficherFenetre1();
         Ennemi.indice = 0;
         Ennemi.positionInitX =(Main.tailleGrilleLargeur / 2)-2;
         Ennemi.positionInitY = (Main.tailleGrilleHauteur / 2)-1;
         Ennemi.vitesseEnnemi = 400;

         Main.newPartie();
    }
}
