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
public class TraitementGrille {

    TraitementItem grilleItem[][];
    int grilleFichier[][];
    Position perso;
    ArrayList<Ennemi> tabEnnemi = new ArrayList<Ennemi>();
    int score;
    int nombreEnnemi;
    int numeroGrille;
    int go;
    Horloge horloge;
    Audio sonManger;
    Audio sonDebut;
    int pourcentage;
    int nombrePawn;

    TraitementGrille() throws IOException {
        score = 0;
        go = 0;
        sonManger = new Audio("pacman_chomp.wav");
        horloge = new Horloge(this);
        horloge.lancerHorloge();
        sonDebut = new Audio("pacman_beginning.wav");
        perso = new Position();

        grilleItem = new TraitementItem[Main.tailleGrilleLargeur][Main.tailleGrilleHauteur];
        grilleFichier = new int[Main.tailleGrilleLargeur][Main.tailleGrilleHauteur];
        for (int i = 0; i < Main.tailleGrilleLargeur; i++) {
            for (int j = 0; j < Main.tailleGrilleHauteur; j++) {
                grilleItem[i][j] = new TraitementItem();
            }
        }
        numeroGrille = 1;
        setGrilleSpe(numeroGrille);
        grilleItem[perso.x][perso.y].isPerso = true;
        for (int i = 0; i < nombreEnnemi; i++) {
            Ennemi e = new Ennemi(this);
            tabEnnemi.add(e);
            e.gererEnnemi();
        }
        setPawn();


    }

    void deplacerPersoHaut() {
        if (this.go == 1) {
            perso.deplacerHaut(grilleItem, "perso");
        }
    }

    void deplacerPersoBas() {
        if (this.go == 1) {

            perso.deplacerBas(grilleItem, "perso");
        }

    }

    void deplacerPersoGauche() {
        if (this.go == 1) {

            perso.deplacerGauche(grilleItem, "perso");
        }
    }

    void deplacerPersoDroite() {
        if (this.go == 1) {

            perso.deplacerDroite(grilleItem, "perso");
        }
    }

    private void setGrilleSpe(int numeroGrille) throws IOException {
        perso.x = Main.tailleGrilleLargeur / 2;
        perso.y = Main.tailleGrilleHauteur / 2 + 2;
        String[] data = new String[1];
        switch (numeroGrille) {
            case 1:
                nombreEnnemi = 4;
                data[0] = "grille1.txt";
                break;
            default:
                System.out.println("Grille non definie.");
        }
        grilleFichier = LireFichier.main(data);
        setWall();
    }

    private void setPawn() {
        this.nombrePawn = 0;
        for (int i = 0; i < Main.tailleGrilleLargeur; i++) {
            for (int j = 0; j < Main.tailleGrilleHauteur; j++) {
                if (!grilleItem[i][j].isPerso && grilleItem[i][j].canMoving) {
                    if (!(i > 10 && i < 17 && j > 11 && j < 17)) {
                        grilleItem[i][j].hasPawn = true;
                        nombrePawn++;
                    }
                }
            }
        }
    }

    public void actionApresDeplacement() {
        if (grilleItem[perso.x][perso.y].hasPawn) {
            this.score++;
            grilleItem[perso.x][perso.y].hasPawn = false;
        }

    }

    private void setWall() {
        for (int i = 0; i < Main.tailleGrilleLargeur; i++) {
            for (int j = 0; j < Main.tailleGrilleHauteur; j++) {
                if (grilleFichier[i][j] == 1) {
                    grilleItem[i][j].canMoving = false;

                }
            }
        }
    }

    void augmenterDificulter() {
        setPawn();
        Ennemi.vitesseEnnemi -= 50;

    }
}
