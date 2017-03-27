/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import javafx.scene.paint.Color;
import library.Piece;
import library.PieceFactory;
import library.Position;


/**
 *
 * @author panderium
 */
public class Modele extends library.Modele {
    
    
    public Modele(int gridW, int gridH) {
        super(gridW, gridH);
    }
    

    public void pieceAlea() {
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
        pieceAlea.setPosition(new Position(3, 0));
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

    

    
    
    
    
}
