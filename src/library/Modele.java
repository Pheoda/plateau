package library;

import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.paint.Color;

public class Modele extends Observable {

    protected int gridW;
    protected int gridH;
    protected ArrayList<Piece> pieces;


    public Modele(int gridW, int gridH) {
        this.gridW = gridW;
        this.gridH = gridH;
        pieces = new ArrayList<>();

    }

    public void translateLeft(Piece piece, ArrayList<Piece> pieces) {
        Piece pieceNew = new Piece(piece);
        pieceNew.setPosition(new Position(piece.getPosition().getX() - 1, piece.getPosition().getY()));

        movePiece(piece, pieceNew, pieces);
    }

    public void translateRight(Piece piece, ArrayList<Piece> pieces) {
        Piece pieceNew = new Piece(piece);
        pieceNew.setPosition(new Position(piece.getPosition().getX() + 1, piece.getPosition().getY()));

        movePiece(piece, pieceNew, pieces);
    }

    public void translateUp(Piece piece, ArrayList<Piece> pieces) {
        Piece pieceNew = new Piece(piece);
        pieceNew.setPosition(new Position(piece.getPosition().getX(), piece.getPosition().getY() - 1));

        movePiece(piece, pieceNew, pieces);
    }

    public void translateDown(Piece piece, ArrayList<Piece> pieces) {
        Piece pieceNew = new Piece(piece);
        pieceNew.setPosition(new Position(piece.getPosition().getX(), piece.getPosition().getY() + 1));

        movePiece(piece, pieceNew, pieces);
    }

    public void rotateLeft(Piece piece, ArrayList<Piece> pieces) {
        Piece pieceNew = new Piece(piece);

        // Reset de la shape de la nouvelle piece
        pieceNew.setShape(new ArrayList<Cellule>());

        // Ajout un par un des cellules composants la piece
        for (Cellule cell : piece.getShape()) {
            pieceNew.getShape().add(new Cellule(new Position(cell.getPosition().getY(), piece.getTaille() - cell.getPosition().getX() - 1)));
        }

        movePiece(piece, pieceNew, pieces);
    }

    public void rotateRight(Piece piece, ArrayList<Piece> pieces) {
        Piece pieceNew = new Piece(piece);

        // Reset de la shape de la nouvelle piece
        pieceNew.setShape(new ArrayList<Cellule>());

        // Ajout un par un des cellules composants la piece
        for (Cellule cell : piece.getShape()) {
            pieceNew.getShape().add(new Cellule(new Position(piece.getTaille() - cell.getPosition().getY() - 1, cell.getPosition().getX())));
        }

        movePiece(piece, pieceNew, pieces);
    }

    // The piece is in the grid ?
    private boolean isInGrid(Piece piece) {
        int x, y;
        for (Cellule cell : piece.getShape()) {
            x = piece.getPosition().getX() + cell.getPosition().getX();
            y = piece.getPosition().getY() + cell.getPosition().getY();
            if (x < 0 || x >= this.gridW || y < 0 || y >= this.gridH) {
                return false;
            }
        }
        return true;
    }

    // Move the piece only if no collision is detected
    protected void movePiece(Piece piece, Piece pieceNew, ArrayList<Piece> pieces) {
        if (isInGrid(pieceNew)) {
            if (!checkCollision(piece, pieceNew, pieces)) {
                piece.setShape(pieceNew.getShape());        // Rotation
                piece.setPosition(pieceNew.getPosition());  // Translation
                pieceNew = null;
                setChanged();
                notifyObservers();
            }
        }
    }

    protected boolean checkCollision(Piece pieceOld, Piece pieceNew, ArrayList<Piece> pieces) {

        for (Piece piece : pieces) {
            if (piece != pieceOld && piece.getPosition() != null) {
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
    
    public void addPiece(char shape) {
        PieceFactory pFacto = new PieceFactory();
        Piece piece = pFacto.create(shape, Color.BLUE);
        pieces.add(piece);
    }

     public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPiece(Piece piece) {
        this.pieces.add(piece);
    }
}
