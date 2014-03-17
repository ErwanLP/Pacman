/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author Erwan
 */
class Horloge {

    int seconde;
    int minute;
    TraitementGrille grille;
    String temps;
    Thread t;

    Horloge(TraitementGrille grille) {
        this.grille = grille;
        seconde = 0;
        minute = 0;
        temps = "0:00";

    }

    void lancerHorloge() {
        Thread threadHorloge = new Thread(new Runnable() {

            @Override
            public void run() {
                while (Main.partieIsRuning) { // boucle infinie*
//                    System.out.println(grille.go);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    seconde++;
                    if (seconde == 60) {
                        seconde = 0;
                        minute++;
                        grille.augmenterDificulter();
                    }
                    if (seconde < 10) {
                        temps = Integer.toString(minute) + ":0" + Integer.toString(seconde);
                    } else {
                        temps = Integer.toString(minute) + ":" + Integer.toString(seconde);
                    }


                }
            }
        });
        // lancer le thread
        this.t = threadHorloge;


    }
}
