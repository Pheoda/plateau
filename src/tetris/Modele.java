package tetris;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import javafx.scene.paint.Color;
import library.Cellule;
import library.Piece;
import library.PieceFactory;
import library.Position;

public class Modele extends library.Modele {

    public Modele(int gridW, int gridH) {
        super(gridW, gridH);
    }

    public void pieceAlea() {
        Hashtable intToPieceLettre = new Hashtable();
        intToPieceLettre.put(1, 'Z');
        intToPieceLettre.put(2, 'O');
        intToPieceLettre.put(3, 'I');
        intToPieceLettre.put(4, 'S');
        intToPieceLettre.put(5, 'L');
        intToPieceLettre.put(6, 'J');
        intToPieceLettre.put(7, 'T');

        Random rand = new Random();
        int i = 1 + rand.nextInt(8 - 1);

        PieceFactory pFacto = new PieceFactory();
        Piece pieceAlea = pFacto.create((char) intToPieceLettre.get(i));

        //on place la piece en haut et au centre de la grille
        pieceAlea.setPosition(new Position((gridW - pieceAlea.getTaille()) / 2, 0));
        pieces.add(pieceAlea);
    }

    @Override
    public void translateDown(Piece piece, ArrayList<Piece> pieces) {
        Piece pieceNew = new Piece(piece);
        pieceNew.setPosition(new Position(piece.getPosition().getX(), piece.getPosition().getY() + 1));
        if (checkCollision(piece, pieceNew, pieces) || reachBottom(pieceNew)) {
            checkCompleteLines(pieces);
            pieceAlea();

            setChanged();
            notifyObservers();
        } else {
            movePiece(piece, pieceNew, pieces);
        }
    }

    private boolean reachBottom(Piece piece) {
        for (Cellule cell : piece.getShape()) {
            if (piece.getPosition().getY() + cell.getPosition().getY() == this.gridH) {
                return true;
            }
        }
        return false;
    }

    // Check et detruis les lines completes, et appelle fallPieces pour la gravite
    private boolean checkCompleteLines(ArrayList<Piece> pieces) {
        boolean[][] grid = new boolean[gridW][gridH];

        // Construction d'un tableau de bool pour faciliter
        for (int i = 0; i < gridW; i++) {
            for (int j = 0; j < gridH; j++) {
                grid[i][j] = false;
            }
        }
        for (Piece p : pieces) {
            for (Cellule c : p.getShape()) {
                grid[p.getPosition().getX() + c.getPosition().getX()][p.getPosition().getY() + c.getPosition().getY()] = true;
            }
        }

        // Check des lignes pleines de bas en haut
        for (int y = gridH - 1; y >= 0; y--) {
            int x = 0;
            while (x < gridW && grid[x][y]) {
                x++;
            }
            if (x == gridW) // On a une ligne pleine 
            {
                System.out.println("Destroying line " + y);
                destroyLine(pieces, y);
                // On execute la fonction tant qu'on enleve une ligne
                checkCompleteLines(pieces);
                return true;
            }
        }
        return false;
    }

    // Detruis la ligne demandee
    private void destroyLine(ArrayList<Piece> pieces, int line) {
        
        // Methode pour supprimer les cellules selon une condition precise
        // (on ne peut pas supprimer en iterant -> solution alternative)
        for (Piece p : pieces) {
            p.getShape().removeIf(c -> p.getPosition().getY() + c.getPosition().getY() == line);
        }
        fallPieces(pieces, line);
    }

    // Fait tomber toutes les cellules au dessus de la ligne line
    private void fallPieces(ArrayList<Piece> pieces, int line) {
        for(Piece p : pieces) {
            for(Cellule c : p.getShape()) {
                if(p.getPosition().getY() + c.getPosition().getY() < line)
                    c.getPosition().setY(c.getPosition().getY() + 1);
            }
        }
    }

}
