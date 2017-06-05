/******************************************************
 Cours:  LOG121
 Projet: Squelette du laboratoire #1
 Nom du fichier: FenetreFormes.java
 Date créé: 2013-05-03
 *******************************************************
 Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-05-03 Version initiale
 *******************************************************/

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * Cette fenetre gere l'affichage des formes
 *
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetreFormes extends JComponent {

    private static final long serialVersionUID = -2262235643903749505L;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final Dimension dimension = new Dimension(500, 500);
    public ArrayList listeForme = new ArrayList();
    public ArrayList listeNomForme = new ArrayList();

    /**
     * Constructeur
     */
    public FenetreFormes() {
        //...
    }
    public void checkResetExcesForme(){
        if (listeNomForme.size() > 10){
            listeNomForme.remove(0);
            listeForme.remove(0);
        }
    }

    /*
     * Affiche la liste de formes
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Testing...
        //System.out.println(listeNomForme.size());

        if (listeNomForme.size() > 0) {
            for (int i = 0; i < listeNomForme.size(); i++) {
                System.out.println(listeNomForme.get(i));
                if (listeNomForme.get(i).equals("CARRE") || listeNomForme.get(i).equals("RECTANGLE")) {
                    if(listeNomForme.get(i).equals("RECTANGLE")) {
                        rectangle temp = (rectangle) listeForme.get(i);
                        g.setColor(temp.color);
                        g.fillRect(temp.x, temp.y, temp.largeur, temp.hauteur);
                    }
                    else {
                        carre temp = (carre) listeForme.get(i);
                        g.setColor(temp.color);
                        g.fillRect(temp.x, temp.y, temp.largeur, temp.hauteur);
                    }

                } else {
                    if (listeNomForme.get(i).equals("CERCLE") || listeNomForme.get(i).equals("OVALE")) {
                        if(listeNomForme.get(i).equals("CERCLE")) {
                            cercle temp = (cercle) listeForme.get(i);
                            g.setColor(temp.color);
                            g.fillOval(temp.x, temp.y, temp.largeur, temp.hauteur);
                        }
                        else {
                            ovale temp = (ovale) listeForme.get(i);
                            g.setColor(temp.color);
                            g.fillOval(temp.x, temp.y, temp.largeur, temp.hauteur);
                        }
                    } else {
                        if (listeNomForme.get(i).equals("LIGNE")) {
                            ligne temp = (ligne) listeForme.get(i);
                            g.setColor(temp.color);
                            g.drawLine(temp.x, temp.y, temp.x2, temp.y2);
                        }
                    }
                }
            }
        }
        //g.drawOval(45, 45, 355, 355);
    }

    /*
     * Le Layout qui utilise (contient) FenetreFormes doit reserver
     * l'espace necessaire à son affichage
     */
    @Override
    public Dimension getPreferredSize() {
        return dimension;
    }
}
