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
    }

    public void translateRight(Piece piece) {
        if (translate(piece)[1]) {  
            piece.getPosition().setX(piece.getPosition().getX() + 1);
            setChanged();
            notifyObservers();
        }
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
        //Parcours de la matrice de la pièce pour trouver la partie de la pièce la plus à gauche
        int i = 0;
        int j = 0;
        while(i < p.getShape().length && !p.getShape()[j][i]) {
            j = 0;
            while(j < p.getShape()[0].length - 1 && !p.getShape()[j][i]) {
                j++;
            }
            i++;
        }
        i--;
        j--;
        
        int xInPlateau = p.getPosition().getX() + i ;
        int yInPlateau = p.getPosition().getY() + j; 
        
        for (int k = 1 ; k < p.getShape()[0].length; k++) {
            System.out.println(xInPlateau);
            System.out.println(yInPlateau);
            
            if (plateau.getGrille()[xInPlateau + yInPlateau * plateau.getWidth()]) {
                tValidate[0] = false;
            }
            yInPlateau++;
        }
        
        
        //Vérification translation droite
        i = p.getShape().length - 1;
        j = 0;
        while(i <= 0 && !p.getShape()[j][i]) {
            j = 0;
            while(j < p.getShape()[0].length - 1 && !p.getShape()[j][i]) {
                j++;
            }
            i++;
        }
        i--;
        j--;
        
        xInPlateau = p.getPosition().getX() + i ;
        yInPlateau = p.getPosition().getY() + j; 
        
        for (int k = 1 ; k < p.getShape()[0].length; k++) {
            System.out.println(xInPlateau);
            System.out.println(yInPlateau);
            
            if (plateau.getGrille()[xInPlateau + yInPlateau * plateau.getWidth()]) {
                tValidate[1] = false;
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
