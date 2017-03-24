package library;

import java.util.ArrayList;
import java.util.Observable;

public class Modele extends Observable {

    Grille plateau;

    public Modele() {
        plateau = Grille.getInstance(null, 10, 10);
    }

    public void translateLeft(Piece piece, ArrayList<Piece> pieces) {
        Piece pieceNew = new Piece(piece);
        pieceNew.setPosition(new Position(piece.getPosition().getX() - 1, piece.getPosition().getY()));
        
        if (!checkCollision(piece, pieceNew, pieces)) {
            piece.setPosition(pieceNew.getPosition());
            pieceNew = null;
            setChanged();
            notifyObservers();
        }
    }

    public void translateRight(Piece piece, ArrayList<Piece> pieces) {
        Piece pieceNew = new Piece(piece);
        pieceNew.setPosition(new Position(piece.getPosition().getX() + 1, piece.getPosition().getY()));
        
        if (!checkCollision(piece, pieceNew, pieces)) {
            piece.setPosition(pieceNew.getPosition());
            pieceNew = null;
            setChanged();
            notifyObservers();
        }
    }

    public void translateUp(Piece piece, ArrayList<Piece> pieces) {
        Piece pieceNew = new Piece(piece);
        pieceNew.setPosition(new Position(piece.getPosition().getX(), piece.getPosition().getY() - 1));
        
        if (!checkCollision(piece, pieceNew, pieces)) {
            piece.setPosition(pieceNew.getPosition());
            pieceNew = null;
            setChanged();
            notifyObservers();
        }

    }

    public void translateDown(Piece piece, ArrayList<Piece> pieces) {
        Piece pieceNew = new Piece(piece);
        pieceNew.setPosition(new Position(piece.getPosition().getX(), piece.getPosition().getY() + 1));
        
        if (!checkCollision(piece, pieceNew, pieces)) {
            piece.setPosition(pieceNew.getPosition());
            pieceNew = null;
            setChanged();
            notifyObservers();
        }
    }

    public void rotateRight(Piece piece, ArrayList<Piece> pieces) {
        Piece pieceNew = new Piece(piece);

        for (int i = 0; i < piece.getShape()[0].length; ++i) {
            for (int j = 0; j < piece.getShape().length; ++j) {
                rotated[i][j] = piece.getShape()[j][piece.getShape()[0].length - i - 1];
            }
        }

        piece.setShape(rotated);

        setChanged();
        notifyObservers();
    }

    public void rotateLeft(Piece piece, ArrayList<Piece> pieces) {
        boolean[][] rotated = new boolean[piece.getShape().length][piece.getShape()[0].length];

        for (int i = 0; i < piece.getShape()[0].length; ++i) {
            for (int j = 0; j < piece.getShape().length; ++j) {
                rotated[i][j] = piece.getShape()[piece.getShape().length - j - 1][i];
            }
        }

        piece.setShape(rotated);

        setChanged();
        notifyObservers();
    }

    private boolean checkCollision(Piece pieceOld, Piece pieceNew, ArrayList<Piece> pieces) {

        for (Piece piece : pieces) {
            if (piece != pieceOld) {
                for (Cellule cellN : pieceNew.getShape()) {
                    for (Cellule cell : piece.getShape()) {
                        if (cell.getPosition().getX() + piece.getPosition().getX() == cellN.getPosition().getX() + pieceNew.getPosition().getX()) {
                            if (cell.getPosition().getY() + piece.getPosition().getY() == cellN.getPosition().getY() + pieceNew.getPosition().getY()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
