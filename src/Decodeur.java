import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Recupere ligne de commande du serveur, separe les informations et les envois a la classe createurForme
 *
 * @author Jonathan Simard
 * @date 15-05-2017
 * @source Tiré de l'exemple pour la classe java.util.regex du cours LOG121
 */
public class Decodeur {
    Pattern p = Pattern.compile("(.*)<(.*)>(.*)<\\/(.*)>");
    CreateurForme createurForme;

    Decodeur(CreateurForme createurForme){this.createurForme = createurForme;}

    /**
     * Separe les informations recu a l'aide de regex
     *
     * @param entree Phrase a decode
     */
    void separeTag(String entree) {
        String[] group = new String[3];
        Matcher m = p.matcher(entree);
        boolean result = m.find();
        while (result) {
            //System.out.println("Found noSeq: " + m.group(1) + "Found tag: " + m.group(2) + ", inner string = "
            //        + m.group(3));
            group[0] = m.group(1);
            group[1] = m.group(2);
            group[2] = m.group(3);
            result = m.find();
        }

        createurForme.creerForme(group);
    }

}
