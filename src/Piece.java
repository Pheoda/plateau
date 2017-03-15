import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;


public class Piece {
    private Color color;
    private Position position;
    private boolean[][] shape;

    public Piece(Color color, Position position, boolean[][] shape) {
        this.color = color;
        this.shape = shape;
        this.position = position;
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

    public boolean[][] getShape() {
        return shape;
    }

    public void setShape(boolean[][] shape) {
        this.shape = shape;
    }
}