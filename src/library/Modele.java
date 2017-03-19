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
        if (translate(piece)[0]) {
            piece.getPosition().setX(piece.getPosition().getX() - 1);
            setChanged();
            notifyObservers();
        }
        else {
            
        }
        
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

    private boolean [] translate(Piece p) {
        boolean [] tValidate = {true, true, true, true};
        
        //vérification d'une translation possible à gauche
        int xInPlateau = p.getPosition().getX() - 1;
        int yInPlateau = p.getPosition().getY();
        
        for (int i = 0 ; i < p.getShape().length; i++) {
            
            System.out.println(plateau.getGrille()[xInPlateau + yInPlateau * plateau.getWidth()]);
            
            if (plateau.getGrille()[xInPlateau + yInPlateau * plateau.getWidth()]) {
                int xPiece = 0;
                int yPiece = yInPlateau - p.getPosition().getY();
                System.out.println(xPiece);
                System.out.println(yPiece);
                if (p.getShape()[xPiece][yPiece]) {
                    tValidate[0] = false;
                }
            }
            yInPlateau++;
        }
        return tValidate;
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
        
        //test
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(plateau.getGrille()[i + j *10] + " ");
            }
            System.out.println("|");
        }
        System.out.println();
        System.out.println();
    }
    
}
