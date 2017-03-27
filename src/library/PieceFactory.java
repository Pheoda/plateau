package library;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class PieceFactory {

    public Piece create(char shape) {
        ArrayList<Cellule> tabCell = new ArrayList<>();
        int taille;
        Color color;

        switch (shape) {
            case 'Z':
                taille = 3;
                tabCell.add(new Cellule(new Position(0, 0)));
                tabCell.add(new Cellule(new Position(1, 0)));
                tabCell.add(new Cellule(new Position(1, 1)));
                tabCell.add(new Cellule(new Position(2, 1)));
                color = Color.DARKGREEN;
                break;
                
            case 'O':
                taille = 2;
                for (int i = 0; i < taille; i++) {
                    for (int j = 0; j < taille; j++) {
                        tabCell.add(new Cellule(new Position(i, j)));
                    }
                }
                color = Color.GOLD;
                break;
                
            case 'I':
                taille = 4;
                for (int i = 0; i < taille; i++) {
                    tabCell.add(new Cellule(new Position(i, 1)));
                }
                color = Color.CYAN;
                break;
                
            case 'S':
                taille = 3;
                tabCell.add(new Cellule(new Position(1, 0)));
                tabCell.add(new Cellule(new Position(2, 0)));
                tabCell.add(new Cellule(new Position(0, 1)));
                tabCell.add(new Cellule(new Position(1, 1)));
                color = Color.RED;
                break;
                
            case 'L':
                taille = 3;
                for (int i = 0; i < taille; i++) {
                    tabCell.add(new Cellule(new Position(i, 0)));
                }
                tabCell.add(new Cellule(new Position(0, 1)));
                color = Color.DARKORANGE;
                break;
                
            case 'J':
                taille = 3;
                for (int i = 0; i < taille; i++) {
                    tabCell.add(new Cellule(new Position(i, 0)));
                }
                tabCell.add(new Cellule(new Position(2, 1)));
                color = Color.DEEPPINK;
                break;

            case 'T':
                taille = 3;
                for (int i = 0; i < taille; i++) {
                    tabCell.add(new Cellule(new Position(i, 0)));
                }
                tabCell.add(new Cellule(new Position(1, 1)));
                color = Color.MEDIUMVIOLETRED;
                break;
                
            default:
                taille = 0;
                tabCell = null;
                color = null;
        }

        return new Piece(color, new Position(0, 0), tabCell, taille);
    }

}
