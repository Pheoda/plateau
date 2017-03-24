package library;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class PieceFactory {


    public Piece create(char shape, Color color, int taille) {
        ArrayList<Cellule> tabCell = new ArrayList<Cellule>();

        switch (shape) {
            /*case 'Z':
                newPiece = new Piece(color, );
                break;*/
            case 'O':
                for (int i = 0; i < 1; i++) {
                    for (int j = 0; j < 1; j++) {
                        tabCell.add(new Cellule(new Position(i, j)));
                    }   
                }    
                break;
            case 'I':
                for(int i = 0; i < taille; i++)
                    for(int j = 0; j < taille; j++)
                        if (i == 1) {
                            tabCell.add(new Cellule(new Position(i, j)));
                        }
                break;
            /*case 'S':
                newPiece = new Piece();
                break;
            case 'L':
                newPiece = new Piece();
                break;
            case 'J':
                newPiece = new Piece();
                break;
            case 'T':
                newPiece = new Piece();
                break;*/
            default:
                tabCell = null;
        }

        return new Piece(color, new Position(0, 0), tabCell, taille);
    }

}
