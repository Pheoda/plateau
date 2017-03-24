package library;

public class Cellule {
    private Position position;

    public Cellule(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "+";
    }
}
