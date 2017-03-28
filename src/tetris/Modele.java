package tetris;

import java.util.HashMap;
import java.util.Random;
import javafx.scene.paint.Color;
import library.Cellule;
import library.Piece;
import library.PieceFactory;
import library.Position;

public class Modele extends library.Modele implements Runnable {

    // Score ajoute pour chaque ligne detruite
    public static final int SCORE_LINE_DESTROYED = 100;

    // Ratio score/vitesse deplacement piece
    public static final int RATIO_TIME = 50;

    // Valeur minimale de rafraichissement du deplacement de la piece
    public static final int TIME_SLEEP_MIN = 70;

    int score;
    boolean gameOver;

    public Modele(int gridW, int gridH) {
        super(gridW, gridH);
        score = 0;
        gameOver = false;
    }

    public void pieceAlea() {
        HashMap int2PieceLettre = new HashMap();
        HashMap pieceLettre2PieceColor = new HashMap();

        int2PieceLettre.put(1, 'Z');
        int2PieceLettre.put(2, 'O');
        int2PieceLettre.put(3, 'I');
        int2PieceLettre.put(4, 'S');
        int2PieceLettre.put(5, 'L');
        int2PieceLettre.put(6, 'J');
        int2PieceLettre.put(7, 'T');

        pieceLettre2PieceColor.put('Z', Color.GREEN);
        pieceLettre2PieceColor.put('O', Color.YELLOW);
        pieceLettre2PieceColor.put('I', Color.CYAN);
        pieceLettre2PieceColor.put('S', Color.RED);
        pieceLettre2PieceColor.put('L', Color.ORANGE);
        pieceLettre2PieceColor.put('J', Color.BLUE);
        pieceLettre2PieceColor.put('T', Color.PURPLE);

        Random rand = new Random();
        int i = 1 + rand.nextInt(8 - 1);
        char pieceLettre = (char) int2PieceLettre.get(i);

        PieceFactory pFacto = new PieceFactory();
        Piece pieceAlea = pFacto.create(pieceLettre, (Color) pieceLettre2PieceColor.get(pieceLettre));

        // on place la piece en haut et au centre de la grille
        pieceAlea.setPosition(null);
        this.pieces.add(pieceAlea);
    }

    @Override
    public void translateDown(Piece piece) {
        Piece pieceNew = new Piece(piece);
        if (piece.getPosition() != null) {
            pieceNew.setPosition(new Position(piece.getPosition().getX(), piece.getPosition().getY() + 1));
            if (checkCollision(piece, pieceNew) || reachBottom(pieceNew)) {
                checkCompleteLines();
                pieceAlea();

                setChanged();
                notifyObservers();
            } else {
                movePiece(piece, pieceNew);
            }
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

    public void initializePositionPieceCurrent() {
        int indice = pieces.size() - 2;

        // On initialise au milieu de la grille
        if (pieces.size() >= 2) {
            if (pieces.get(indice).getPosition() == null) {
                pieces.get(indice).setPosition(new Position((gridW - pieces.get(indice).getTaille()) / 2, 0));

            }
        }
    }

    // Check et detruis les lines completes, et appelle fallPieces pour la gravite
    private boolean checkCompleteLines() {
        boolean[][] grid = new boolean[gridW][gridH];

        // Construction d'un tableau de bool pour faciliter
        for (int i = 0; i < gridW; i++) {
            for (int j = 0; j < gridH; j++) {
                grid[i][j] = false;
            }
        }
        for (Piece p : pieces) {
            if (p.getPosition() != null) {
                for (Cellule c : p.getShape()) {
                    grid[p.getPosition().getX() + c.getPosition().getX()][p.getPosition().getY() + c.getPosition().getY()] = true;
                }
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
                destroyLine(y);
                score += SCORE_LINE_DESTROYED;
                // On execute la fonction tant qu'on enleve une ligne
                checkCompleteLines();
                return true;
            }
        }
        return false;
    }

    // Detruis la ligne demandee
    private void destroyLine(int line) {

        // Methode pour supprimer les cellules selon une condition precise
        // (on ne peut pas supprimer en iterant -> solution alternative)
        for (Piece p : pieces) {
            if (p.getPosition() != null) {
                p.getShape().removeIf(c -> p.getPosition().getY() + c.getPosition().getY() == line);
            }
        }
        fallPieces(line);
    }

    // Fait tomber toutes les cellules au dessus de la ligne line
    private void fallPieces(int line) {
        for (Piece p : pieces) {
            for (Cellule c : p.getShape()) {
                if (p.getPosition() != null && p.getPosition().getY() + c.getPosition().getY() < line) {
                    c.getPosition().setY(c.getPosition().getY() + 1);
                }
            }
        }
    }

    private boolean checkEnd() {
        // On check si la derniere piece posee est situee tout en haut
        if (pieces.size() > 2) {
            return pieces.get(pieces.size() - 3).getPosition().getY() == 0;
        }
        return false;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public void run() {

        while (!checkEnd()) {
            initializePositionPieceCurrent();
            translateDown(pieces.get(pieces.size() - 2));
            try {
                // On augmente la vitesse en fonction du score
                int timeSleep = 500 - score / RATIO_TIME;
                if (timeSleep < TIME_SLEEP_MIN) {
                    timeSleep = TIME_SLEEP_MIN;
                }
                Thread.sleep(timeSleep);
            } catch (Exception e) {

            }

        }
        gameOver();
    }

    private void gameOver() {
        this.gameOver = true;
        setChanged();
        notifyObservers();
    }

    public boolean getGameOver() {
        return this.gameOver;
    }
}
