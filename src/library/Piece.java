package library;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Piece {
    private Color color;
    private Position position;
    private ArrayList<Cellule> shape;
    private int taille;
    
    public Piece(Color color, Position position, ArrayList<Cellule> shape, int taille) {
        this.color = color;
        this.shape = shape;
        this.position = position;
        this.taille = taille;
    }

    public boolean isEmpty() {

        return false;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public ArrayList<Cellule> getShape() {
        return shape;
    }

    public void setShape(ArrayList<Cellule> shape) {
        this.shape = shape;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }
}