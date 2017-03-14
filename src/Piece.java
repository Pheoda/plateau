import java.util.ArrayList;
import java.util.List;


public class Piece {
    private Color color;
    private Position p ;
    private boolean[][] shape;

    public Piece(Color color, Position p, boolean[][] shape) {
        this.color = color;
        this.shape = shape;
        this.p = p;
    }

    public boolean isEmpty() {
        return shape.isEmpty();
    }

}