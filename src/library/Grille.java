/***************/
/* SERT À RIEN */
/***************/
package library;

public class Grille {
    private String title;
    private int height;
    private int width;
    private Cellule [][] grille;
    private static Grille instance;

    private Grille(String t, int h, int w) {
        title = t;
        height = h;
        width = w;
        grille = new Cellule[height][width];
    }
    
    public static Grille getInstance(String t, int h, int w) {
        if (instance == null) {
            instance = new Grille(t, h, w);            
        }
        return instance;
    }

    public String getTitle() {
        return title;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cellule[][] getGrille() {
        return grille;
    }

    
}
