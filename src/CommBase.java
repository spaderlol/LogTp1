/******************************************************
 Cours:  LOG121
 Projet: Squelette du laboratoire #1
 Nom du fichier: CommBase.java
 Date créé: 2013-05-03
 *******************************************************
 Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-05-03 Version initiale
 *******************************************************/

import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;

import javax.swing.SwingWorker;

public class CommBase {
    /**
     * Base d'une communication via un fil d'execution parallele.
     * Crée la communication avec le serveur et envoi les réponse a la classe decodeur.
     */

    private final int DELAI = 1000;
    private SwingWorker threadComm = null;
    private PropertyChangeListener listener = null;
    private boolean isActif = false;
    private String[] mGroupReception;
    private CreateurForme createurForme = null;
    private Decodeur decodeur = null;


    /**
     * Constructeur
     */
    public CommBase() {
    }

    /**
     * Lie les différents liens entre classe
     *
     * @param createurForme lien vers classe CreateurForme
     */
    public void setLink(CreateurForme createurForme) {
        this.createurForme = createurForme;
        decodeur = new Decodeur(createurForme);
    }

    /**
     * Definir le recepteur de l'information recue dans la communication avec le serveur
     *
     * @param listener sera alerter lors de l'appel de "firePropertyChanger" par le SwingWorker
     */
    public void setPropertyChangeListener(PropertyChangeListener listener) {
        this.listener = listener;
    }

    /**
     * Demarre la communication
     */
    public void start() {
        creerCommunication();
    }

    /**
     * Arrete la communication
     */
    public void stop() {
        if (threadComm != null)
            threadComm.cancel(true);
        isActif = false;
    }

    /**
     * Creer le necessaire pour la communication avec le serveur
     */
    protected void creerCommunication() {
        // Cree un fil d'execution parallele au fil courant
        /**
         * @Source Inspirer de l'exemple du site http://www.avajava.com/tutorials/lessons/how-do-i-make-a-socket-connection-to-a-server.html
         */
        //TODO: mettre source du code?

        threadComm = new SwingWorker() {
            @Override
            protected Object doInBackground() {
                int nbReception = 0;
                System.out.println("Le fils d'execution parallele est lance");

                //TODO:  entré par l'utilisateur du socket
                //TODO: test si connection a fonctionner
                try {
                    String adresse = JOptionPane.showInputDialog("Entrer l'adresse du serveur de forme", "localhost:10000");
                    String[] sAdresse = adresse.split(":+");
                    Socket clientSocket = new Socket(sAdresse[0], Integer.parseInt(sAdresse[1]));
                    DataOutputStream directionServer = new DataOutputStream(clientSocket.getOutputStream());
                    BufferedReader receptionDeServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    while (true) {
                        nbReception++;
                        Thread.sleep(DELAI);
                        directionServer.writeBytes("GET" + '\n');
                        String stringRecu = receptionDeServer.readLine();
                        if (nbReception % 2 == 0) {
                            //System.out.println(stringRecu);
                            decodeur.separeTag(stringRecu);
                        }

                        //La methode suivante alerte l'observateur
                        if (listener != null)
                            firePropertyChange("ENVOIE-TEST", null, (Object) ".");
                    }
                }
                catch (UnknownHostException e){
                    System.err.print("Veuillez redemarrer l'application." + e.getMessage());
                    System.exit(1);
                }
                catch (IOException e){
                    System.err.print("Probleme avec la connection, veuillez redemarrer l'application." + e.getMessage());
                    System.exit(1);
                }
                catch (InterruptedException e) {
                    System.err.print("La communication avec le serveur a été interrompu, vérifier si le serveur est démaré et redémarrer l'application" + e.getMessage());
                    System.exit(1);
                }
                return nbReception;
            }
        };
        if (listener != null)
            threadComm.addPropertyChangeListener(listener); // La methode "propertyChange" de ApplicationFormes sera donc appelee lorsque le SwinkWorker invoquera la methode "firePropertyChanger"
        threadComm.execute(); // Lance le fil d'execution parallele.
        isActif = true;
    }

    /**
     * @return si le fil d'execution parallele est actif
     */
    public boolean isActif() {
        return isActif;
    }
}
