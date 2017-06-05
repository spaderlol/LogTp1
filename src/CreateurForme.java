import ca.etsmtl.log.util.IDLogger;

/**
 * Created by Jonathan on 2017-06-04.
 */
public class CreateurForme {
    /**
     * * Crée une nouvelle forme. Cette méthode reçoit la chaîne de
     * caractères provenant du serveur de formes, elle détermine de quelle
     * forme il s'agit et applique l'opérateur new sur le constructeur de
     * la forme désirée.
     * Il est ensuite ajouté a une liste Array de Fenetre Formes
     *
     * @param mgroup un group de string contenant les informations de la formes
     * @Source Nom et description inspirer des contraintes de conception de l'exercice du TP 1 du cours LOG121
     */
    FenetreFormes fenetreFormes;
    IDLogger logger = IDLogger.getInstance();

    /**
     * Constructeur
     *
     * @param fenetreFormes lien vers classe fenetreFormes
     */
    CreateurForme(FenetreFormes fenetreFormes) {
        this.fenetreFormes = fenetreFormes;
    }

    /**
     * Crée une forme depuis la classe abstraite Forme dependant du de l'information du premier groupe de "mgroup"
     *
     * @param mgroup groupe de String recu separer precedament par la classe decodeur
     * @see Decodeur
     */
    public void creerForme(String[] mgroup) {
        //TODO: mettre info (du serveur) des formes genre x,y,asnihlasg

        String[] sousInfoGroup = mgroup[2].split("\\s+");
        String[] separationEspaceNoSeq = mgroup[0].split("\\s+");
        Forme nouvelleForme = null;

        if (mgroup[1].equals("CARRE") || mgroup[1].equals("RECTANGLE")) {
            if (mgroup[1].equals("RECTANGLE")) {
                nouvelleForme = new rectangle(Integer.parseInt(separationEspaceNoSeq[0]), Integer.parseInt(sousInfoGroup[1]), Integer.parseInt(sousInfoGroup[2]), Integer.parseInt(sousInfoGroup[3]), Integer.parseInt(sousInfoGroup[4]));
            }
            else {
                nouvelleForme = new carre(Integer.parseInt(separationEspaceNoSeq[0]), Integer.parseInt(sousInfoGroup[1]), Integer.parseInt(sousInfoGroup[2]), Integer.parseInt(sousInfoGroup[3]), Integer.parseInt(sousInfoGroup[4]));
            }

        } else {
            if (mgroup[1].equals("OVALE") || mgroup[1].equals("CERCLE")) {
                if (mgroup[1].equals("OVALE")) {
                    nouvelleForme = new ovale(Integer.parseInt(separationEspaceNoSeq[0]), Integer.parseInt(sousInfoGroup[1]), Integer.parseInt(sousInfoGroup[2]), Integer.parseInt(sousInfoGroup[3]), Integer.parseInt(sousInfoGroup[4]));
                } else {
                    nouvelleForme = new cercle(Integer.parseInt(separationEspaceNoSeq[0]), Integer.parseInt(sousInfoGroup[1]), Integer.parseInt(sousInfoGroup[2]), Integer.parseInt(sousInfoGroup[3]), Integer.parseInt(sousInfoGroup[3]));
                }
            } else {
                if (mgroup[1].equals("LIGNE")) {
                    nouvelleForme = new ligne(Integer.parseInt(separationEspaceNoSeq[0]), Integer.parseInt(sousInfoGroup[1]), Integer.parseInt(sousInfoGroup[2]), Integer.parseInt(sousInfoGroup[3]), Integer.parseInt(sousInfoGroup[4]));
                }
            }
        }
        fenetreFormes.listeForme.add(nouvelleForme);
        fenetreFormes.listeNomForme.add(mgroup[1]);
        logger.logID(Integer.parseInt(separationEspaceNoSeq[0]));
        fenetreFormes.checkResetExcesForme();
        fenetreFormes.repaint();
        //Object jgklsag = fenetreFormes.listeForme.get(0);

    }
}

