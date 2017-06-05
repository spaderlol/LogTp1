import java.awt.*;

/**
 * Created by Jonathan on 2017-06-04.
 */
public abstract class Forme {
    protected int indentifier;
    protected int x;
    protected int y;
    protected Color color;

}

class rectangle extends Forme {
    protected int hauteur;
    protected int largeur;

    rectangle(int ind, int x, int y, int x2, int y2) {
        this.indentifier = ind;
        this.x = this.x;
        this.y = this.y;
        this.hauteur = y2 - y;
        this.largeur = x2 - x;
        this.color = Color.BLUE;
    }
}
class carre extends Forme {
    protected int hauteur;
    protected int largeur;

    carre(int ind, int x, int y, int x2, int y2) {
        this.indentifier = ind;
        this.x = this.x;
        this.y = this.y;
        this.hauteur = y2 - y;
        this.color = Color.ORANGE;
    }
}

class cercle extends Forme {
    protected int hauteur;
    protected int largeur;

    cercle(int ind, int x, int y, int rayonH, int rayonL) {
        this.indentifier = ind;
        this.x = x;
        this.y = y;
        this.hauteur = rayonH;
        this.largeur = rayonL;
        this.color = Color.RED;
    }
}
class ovale extends Forme {
    protected int hauteur;
    protected int largeur;

    ovale(int ind, int x, int y, int rayonH, int rayonL) {
        this.indentifier = ind;
        this.x = x;
        this.y = y;
        this.hauteur = rayonH;
        this.largeur = rayonL;
        this.color = Color.green;
    }
}

class ligne extends Forme {
    protected int x2;
    protected int y2;

    ligne(int ind, int x, int y, int x2, int y2) {
        this.indentifier = ind;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.color = Color.MAGENTA;
    }
}
