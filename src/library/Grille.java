package library;

public class Grille {
    private String title;
    private int height;
    private int width;

    public Grille(String t, int h, int w) {
        title = t;
        height = h;
        width = w;
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
}
