package library;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class PieceFactory {

    public Piece create(char shape, Color color) {
        ArrayList<Cellule> tabCell = new ArrayList<>();
        int taille;

        switch (shape) {
            case 'Z':
                taille = 3;
                tabCell.add(new Cellule(new Position(0, 0)));
                tabCell.add(new Cellule(new Position(1, 0)));
                tabCell.add(new Cellule(new Position(1, 1)));
                tabCell.add(new Cellule(new Position(2, 1)));
                break;
                
            case 'O':
                taille = 2;
                for (int i = 0; i < 1; i++) {
                    for (int j = 0; j < 1; j++) {
                        tabCell.add(new Cellule(new Position(i, j)));
                    }
                }
                break;
                
            case 'I':
                taille = 4;
                for (int i = 0; i < taille; i++) {
                    tabCell.add(new Cellule(new Position(i, 1)));
                }
                break;
                
            case 'S':
                taille = 3;
                tabCell.add(new Cellule(new Position(1, 0)));
                tabCell.add(new Cellule(new Position(2, 0)));
                tabCell.add(new Cellule(new Position(0, 1)));
                tabCell.add(new Cellule(new Position(1, 1)));
                break;
                
            case 'L':
                taille = 3;
                for (int i = 0; i < taille; i++) {
                    tabCell.add(new Cellule(new Position(i, 0)));
                }
                tabCell.add(new Cellule(new Position(0, 1)));
                break;
                
            case 'J':
                taille = 3;
                for (int i = 0; i < taille; i++) {
                    tabCell.add(new Cellule(new Position(i, 0)));
                }
                tabCell.add(new Cellule(new Position(2, 1)));
                break;

            case 'T':
                taille = 3;
                for (int i = 0; i < taille; i++) {
                    tabCell.add(new Cellule(new Position(i, 0)));
                }
                tabCell.add(new Cellule(new Position(1, 1)));
                break;
                
            default:
                taille = 0;
                tabCell = null;
        }

        return new Piece(color, new Position(0, 0), tabCell, taille);
    }

}
