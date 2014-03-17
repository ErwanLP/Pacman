/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import javax.imageio.ImageIO;

/**
 *
 * @author Erwan
 */
public class Fenetre {

    static boolean begin;
    GridBagConstraints gbc = new GridBagConstraints();
    int hauteurContentGame;
    int largeurContentGame;
    JPanel content;
    JPanel contentHome;
    JPanel contentGame;
    JPanel contentScore;
    JPanel contentMenu;
    JPanel contentLost;
    CardLayout cl;
    JPanel menu;
    JButton buttonPresentation;
    JButton buttonJeux;
    JButton buttonScore;
    String[] listContent;
    PanneauItem grilleItemJpanel[][];
    JLabel labelScore;
    JLabel labelPerdu;
    JLabel labelPerduScore;
    JLabel labelName;
    JLabel labelHorloge;
    JTextField jtf;

    public Fenetre(JFrame jf) throws IOException {


        content = new JPanel();
        contentHome = new PanneauImage();
        contentGame = new JPanel();
        contentScore = new JPanel();
        contentMenu = new JPanel();
        contentLost = new JPanel();
        cl = new CardLayout();
        menu = new JPanel();
        buttonPresentation = new JButton("Présentation");
        buttonJeux = new JButton("Jeux");
        buttonScore = new JButton("Resumé Score");
        String[] listContent2 = {"Présentation", "Jeux", "Score", "Lost"};
        this.listContent = listContent2; // bizzard
        grilleItemJpanel = new PanneauItem[Main.tailleGrilleLargeur][Main.tailleGrilleHauteur];
        labelScore = new JLabel("Score : 0 ");
        labelHorloge = new JLabel(" 0:00");
        labelPerdu = new JLabel("PERDU");
        labelPerduScore = new JLabel();
        labelName = new JLabel("Veuillez entrer votre nom :");
        jtf = new JTextField();


        final int hauteurFenetre = 1000;
        final int largeurFenetre = 1000;
        Image img = ImageIO.read(new File("pacman.png"));
        jf.setAlwaysOnTop(true);
        jf.setIconImage(img);
        
        jf.setTitle("PacMan By Erwan Le Poder");
        jf.setSize(largeurFenetre, hauteurFenetre);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        contentScore.setBackground(Color.black);
        contentGame.setBackground(Color.black);
        contentLost.setBackground(Color.black);


        Font police = new Font("Tahoma", Font.BOLD, 16);
        labelScore.setFont(police);
        labelScore.setForeground(Color.BLACK);
        labelHorloge.setFont(police);
        labelHorloge.setForeground(Color.BLACK);

        Font policeTextPerdu = new Font("Tahoma", Font.BOLD, 30);
        Font policePerdu = new Font("Tahoma", Font.BOLD, 200);
        labelPerdu.setFont(policePerdu);
        labelPerdu.setForeground(Color.YELLOW);
        labelPerdu.setLocation(100, 100);
        labelPerduScore.setFont(police);
        labelPerduScore.setForeground(Color.YELLOW);
        labelPerdu.setLocation(100, 100);
        labelName.setFont(police);
        labelName.setForeground(Color.YELLOW);
        labelName.setLocation(100, 100);

        Font policeJtf = new Font("Arial", Font.BOLD, 14);
        jtf.setFont(policeJtf);
        jtf.setPreferredSize(new Dimension(150, 30));
        jtf.setForeground(Color.BLUE);
        jtf.addKeyListener(new ClavierListener());

        contentLost.setLayout(new FlowLayout());
        contentLost.add(labelPerdu);
        contentLost.add(labelPerduScore);
        contentLost.add(labelName);
        contentLost.add(jtf);

        contentMenu.add(buttonPresentation);
        contentMenu.add(buttonJeux);
        contentMenu.add(buttonScore);

        contentScore.setLayout(new GridLayout(15, 1));


        menu.setLayout(new BorderLayout());
        menu.add(contentMenu, BorderLayout.CENTER);
        menu.add(labelScore, BorderLayout.EAST);
        menu.add(labelHorloge, BorderLayout.WEST);

        buttonPresentation.addActionListener((ActionListener) new BoutonListener());
        buttonJeux.addActionListener(new BoutonListener());
        buttonScore.addActionListener(new BoutonListener());

        content.setLayout(cl);
        content.add(contentHome, listContent[0]);
        content.add(contentGame, listContent[1]);
        content.add(contentScore, listContent[2]);
        content.add(contentLost, listContent[3]);

        this.hauteurContentGame = hauteurFenetre - menu.getPreferredSize().height;
        this.largeurContentGame = largeurFenetre;

        contentGame.setSize(new Dimension(largeurContentGame, hauteurContentGame));
        contentGame.setLayout(new GridBagLayout());

        gbc.weightx = largeurContentGame;
        gbc.weighty = hauteurContentGame;
        gbc.fill = GridBagConstraints.BOTH; // permet que les item prenne toute la place dispo

        jf.getContentPane().add(menu, BorderLayout.NORTH);
        jf.getContentPane().add(content, BorderLayout.CENTER);

        content.addKeyListener(new DetectionClavier());
        content.setFocusable(true);
        content.requestFocus();

        jf.setVisible(true);
    }

    void afficheLost() {
        System.out.println("afficheLost");
        cl.show(content, listContent[3]);
        this.jtf.requestFocusInWindow();


    }

    void afficherAllScore(ArrayList<ScoreItem> listeScore) {
        contentScore.removeAll();
        Font policeScore = new Font("Tahoma", Font.BOLD, 20);
        JLabel labelSCoreTitre = new JLabel("BEST SCORE : ");
        labelSCoreTitre.setFont(policeScore);
        labelSCoreTitre.setForeground(Color.YELLOW);
        labelSCoreTitre.setLocation(100, 100);
        contentScore.add(labelSCoreTitre);
        for (int i = 0; i < listeScore.size(); i++) {
            JLabel labelSCore = new JLabel(listeScore.get(i).name + " : " + listeScore.get(i).score);
            labelSCore.setFont(policeScore);
            labelSCore.setForeground(Color.YELLOW);
            labelSCore.setLocation(100, 100);
            contentScore.add(labelSCore);

        }
    }

    void afficherFenetre1() {
        cl.show(content, listContent[0]);
        buttonPresentation.setEnabled(true);
        buttonScore.setEnabled(true);
        buttonJeux.setEnabled(true);
    }

    class BoutonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonPresentation) {
//                System.out.println("presentation");
                cl.show(content, listContent[0]);
            }
            if (e.getSource() == buttonJeux) {
                buttonPresentation.setEnabled(false);
                buttonScore.setEnabled(false);
                buttonJeux.setEnabled(false);
                cl.show(content, listContent[1]);
//                System.out.println("jeux");
                Main.fenetre.content.requestFocus();
                Main.traitement.grille.go = 1;
                Main.traitement.grille.sonDebut.start();
                Main.traitement.grille.horloge.t.start();

            }
            if (e.getSource() == buttonScore) {
                cl.show(content, listContent[2]);
//                System.out.println("score");
            }

        }
    }

    public void init(TraitementItem grilleItem[][]) {
        Color color;
        for (int j = 0; j < Main.tailleGrilleHauteur; j++) {
            for (int i = 0; i < Main.tailleGrilleLargeur; i++) {
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
                    color = Color.GRAY;
                } else {
                    color = Color.LIGHT_GRAY;
                }
                PanneauItem panneauItemGrille = new PanneauItem(color, grilleItem[i][j]);
                panneauItemGrille.setSize(new Dimension(largeurContentGame / Main.tailleGrilleLargeur, hauteurContentGame / Main.tailleGrilleHauteur));
                gbc.gridx = i;
                gbc.gridy = j;
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                contentGame.add(panneauItemGrille, gbc);
                grilleItemJpanel[i][j] = panneauItemGrille;
            }
            gbc.gridwidth = GridBagConstraints.REMAINDER;
        }
    }

    public void update(TraitementItem grilleItem[][]) {
        Color color;
        for (int j = 0; j < Main.tailleGrilleHauteur; j++) {
            for (int i = 0; i < Main.tailleGrilleLargeur; i++) {
                grilleItemJpanel[i][j].setItem(grilleItem[i][j]);
                grilleItemJpanel[i][j].repaint();

            }
        }
    }

    void updateScore(int score) {
        labelScore.setText("Score : " + score + " ");
        if (score > 300) {
            labelPerduScore.setText("Vous avez atteint le score de  " + score + " points ! Bravo !");
        } else {
            labelPerduScore.setText("Vous avez atteint le score de  " + score + " points ! NUL !");
        }
    }

    void updateHorloge(String temps, int score, Horloge g, int pourcent) {
        if (!(g.minute == 0 && g.seconde == 0)) {
            int ratio = score / (g.minute * 60 + g.seconde);
            int manche = g.minute + 1;
            labelHorloge.setText(" " + temps + " - Ratio : " + ratio + " - Poucent : " + pourcent + "%" + " - Mache: " + manche + "   ");
        }
    }
}
