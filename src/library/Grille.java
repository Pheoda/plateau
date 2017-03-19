package library;

public class Grille {
    private String title;
    private int height;
    private int width;
    private boolean [] grille;
    private static Grille instance;

    private Grille(String t, int h, int w) {
        title = t;
        height = h;
        width = w;
        grille = new boolean[width*height];
        for (int i = 0; i < this.grille.length; i++) {
            grille[i] = Boolean.FALSE;
        }
        
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

    public boolean[] getGrille() {
        return grille;
    }

    public void setGrille(Position p, boolean bool) {
        this.grille[p.getX() + p.getY() * this.width] = bool;
    }
    
    
    
}
