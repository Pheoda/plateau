package library;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class Modele extends Observable {

    Grille plateau;
    
    public Modele() {
        plateau = Grille.getInstance(null, 10, 10);
    }

    public void translateLeft(Piece piece) {
        piece.getPosition().setX(piece.getPosition().getX() - 1);
        setChanged();
        notifyObservers();
    }

    public void translateRight(Piece piece) {
        piece.getPosition().setX(piece.getPosition().getX() + 1);
        setChanged();
        notifyObservers();
    }

    public void translateUp(Piece piece) {
        piece.getPosition().setY(piece.getPosition().getY() - 1);
        setChanged();
        notifyObservers();
    }

    public void translateDown(Piece piece) {
        piece.getPosition().setY(piece.getPosition().getY() + 1);
        setChanged();
        notifyObservers();
    }

    public void rotateRight(Piece piece) {
        boolean[][] rotated = new boolean[piece.getShape().length][piece.getShape()[0].length];

        for (int i = 0; i < piece.getShape()[0].length; ++i) {
            for (int j = 0; j < piece.getShape().length; ++j) {
                rotated[i][j] = piece.getShape()[j][piece.getShape()[0].length - i - 1];
            }
        }

        piece.setShape(rotated);

        setChanged();
        notifyObservers();
    }
    public void rotateLeft(Piece piece) {
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

    void refreshPlateau(ArrayList<Piece> p) {
        
        for (Piece currentP : p) {
            for (int i = 0; i < currentP.getShape().length; i++) {
                for (int j = 0; j < currentP.getShape()[0].length; j++) {
                    if (currentP.getShape()[i][j]) {
                        plateau.setGrille(new Position(currentP.getPosition().getX() + i, currentP.getPosition().getY() + j), true); 
                    }
                    else {
                        plateau.setGrille(new Position(currentP.getPosition().getX() + i, currentP.getPosition().getY() + j), false); 
                    }
                }
            }  
        }
    }
    
}
