package library;

import java.util.ArrayList;
import java.util.Observable;

public class Modele extends Observable {

    Grille plateau;
    
    public Modele() {
        plateau = Grille.getInstance(null, 10, 10);
    }

    public void translateLeft(Piece piece) {
        if (translate(piece)[0]) {
            piece.getPosition().setX(piece.getPosition().getX() - 1);
            setChanged();
            notifyObservers();
        }
    }

    public void translateRight(Piece piece) {
        if (translate(piece)[1]) {  
            piece.getPosition().setX(piece.getPosition().getX() + 1);
            setChanged();
            notifyObservers();
        }
    }

    public void translateUp(Piece piece) {
        if (translate(piece)[2]) {
             piece.getPosition().setY(piece.getPosition().getY() - 1);
            setChanged();
            notifyObservers();
        }
       
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

    
    private boolean checkCollision(Piece pieceOld, Piece pieceNew, ArrayList<Piece> pieces) {
        
        for(Piece piece : pieces) {
            if (piece.getPosition() != pieceOld.getPosition()) {
                
            }
        }
        
    }
    
    
    
}
