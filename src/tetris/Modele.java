package tetris;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.paint.Color;
import library.Piece;
import library.PieceFactory;
import library.Position;


public class Modele extends library.Modele {
    
    
    public Modele(int gridW, int gridH) {
        super(gridW, gridH);
    }
    

    public void pieceAlea() {
        HashMap int2PieceLettre = new HashMap();
        HashMap pieceLettre2PieceColor = new HashMap();
        
        int2PieceLettre.put(1,'Z');
        int2PieceLettre.put(2,'O');
        int2PieceLettre.put(3,'I');
        int2PieceLettre.put(4,'S');
        int2PieceLettre.put(5,'L');
        int2PieceLettre.put(6,'J');
        int2PieceLettre.put(7,'T');
        
        pieceLettre2PieceColor.put('Z',Color.DARKGREEN);
        pieceLettre2PieceColor.put('O',Color.GOLD);
        pieceLettre2PieceColor.put('I',Color.CYAN);
        pieceLettre2PieceColor.put('S',Color.RED);
        pieceLettre2PieceColor.put('L',Color.DARKORANGE);
        pieceLettre2PieceColor.put('J',Color.DEEPPINK);
        pieceLettre2PieceColor.put('T',Color.MEDIUMVIOLETRED);
        
        
        Random rand = new Random();
        int i = 1 + rand.nextInt(8 - 1);
        char pieceLettre = (char) int2PieceLettre.get(i);
        
        PieceFactory pFacto = new PieceFactory();
        Piece pieceAlea = pFacto.create(pieceLettre, (Color) pieceLettre2PieceColor.get(pieceLettre));
        
        
        // on place la piece en haut et au centre de la grille
        pieceAlea.setPosition(null);
        pieces.add(pieceAlea);
    }

    @Override
    public void translateDown(Piece piece, ArrayList<Piece> pieces) {
        Piece pieceNew = new Piece(piece);
        pieceNew.setPosition(new Position(piece.getPosition().getX(), piece.getPosition().getY() + 1));
        if (checkCollision(piece, pieceNew, pieces) || reachBottom(pieceNew)) 
            pieceAlea();
            
        movePiece(piece, pieceNew, pieces);
        
        
    }

    private boolean reachBottom(Piece piece) { 
        return (piece.getPosition().getY() + 1 ) == this.gridH;
    }

    void initializePositionPieceCurrent() {
        if (pieces.get(pieces.size() - 2).getPosition() == null) {
            pieces.get(pieces.size() - 2).setPosition(new Position(3, 0));
                    
        }
    }

    
}
