public class Grille {
    private string title;
    private int height;
    private int width;

    public Grille(string t, int h, int w) {
        title = t;
        height = h;
        width = w;
    }

    public string getTitle() {
        return title;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
