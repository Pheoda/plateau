package tetris;

import java.util.Hashtable;
import java.util.Random;
import javafx.scene.paint.Color;
import library.Piece;
import library.PieceFactory;
import library.Position;


public class Modele extends library.Modele {
    
    public Modele(int gridW, int gridH) {
        super(gridW, gridH);
    }

    public Piece pieceAlea() {
        Hashtable intToPieceLettre = new Hashtable();
        intToPieceLettre.put(1,'Z');
        intToPieceLettre.put(2,'O');
        intToPieceLettre.put(3,'I');
        intToPieceLettre.put(4,'S');
        intToPieceLettre.put(5,'L');
        intToPieceLettre.put(6,'J');
        intToPieceLettre.put(7,'T');
        
        Random rand = new Random();
        int i = 1 + rand.nextInt(8 - 1);
        
        PieceFactory pFacto = new PieceFactory();
        Piece pieceAlea = pFacto.create((char) intToPieceLettre.get(i));
        //on place la piece en haut et au centre de la grille
        pieceAlea.setPosition(new Position(pieceAlea.getTaille(), 0));
        
        return pieceAlea;
    }
    
    
    
}
