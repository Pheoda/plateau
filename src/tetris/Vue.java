package tetris;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.Cellule;
import library.Piece;
import library.PieceFactory;
import library.Position;


public class Vue extends Application {
    
    public static final int CELL_SIZE = 30;
    public static final int GRID_WIDTH = 10;
    public static final int GRID_HEIGHT = 20;
    
    public static final int TOP_MARGIN = 15;
    public static final int BOTTOM_MARGIN = 15;
    public static final int LEFT_MARGIN = 50;
    public static final int RIGHT_MARGIN = 50;

    library.Modele m;
    
    private int score;

    public void start(Stage primaryStage) throws Exception {

        Modele m = new Modele(GRID_WIDTH, GRID_HEIGHT);
        
        score = 0;

        BorderPane border = new BorderPane();
        GridPane gridP = new GridPane();
                
        Text textNext = new Text();
        textNext.setFont(new Font(20));
        textNext.setText("Piece suivante");
        Text textHold = new Text();
        textHold.setFont(new Font(20));
        textHold.setText("Piece en cours");

        border.setRight(textNext);
        border.setLeft(textHold);
        border.setCenter(gridP);
        
        // Ajout de marges
        border.setMargin(gridP, new Insets(TOP_MARGIN, RIGHT_MARGIN, BOTTOM_MARGIN, LEFT_MARGIN));
        border.setMargin(textNext, new Insets(0, RIGHT_MARGIN, 0, 0));
        border.setMargin(textHold, new Insets(0, 0, 0, LEFT_MARGIN));
        
        // Création de la grille vide 
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                Rectangle r = new Rectangle();
                r.setX(i * CELL_SIZE);
                r.setY(j * CELL_SIZE);
                r.setWidth(CELL_SIZE);
                r.setHeight(CELL_SIZE);
                r.setStroke(Color.BLACK);
                r.setFill(Color.WHITE);
                gridP.add(r, i, j); // Ajout à la gridpane
            }
        }
        
        
        m.pieceAlea();

        
        border.setOnKeyPressed((KeyEvent ke) -> {
            ArrayList<Piece> p = m.getPieces();
            
            if (ke.getCode() == KeyCode.UP) {
                m.translateUp(p.get(p.size() - 1), p);
            }
            if (ke.getCode() == KeyCode.DOWN) {
                m.translateDown(p.get(p.size() - 1), p);
            }
            if (ke.getCode() == KeyCode.LEFT) {
                m.translateLeft(p.get(p.size() - 1), p);
            }
            if (ke.getCode() == KeyCode.RIGHT) {
                m.translateRight(p.get(p.size() - 1), p);
            }
            if (ke.getCode() == KeyCode.R) {
                m.rotateRight(p.get(p.size() - 1), p);
            }
            if (ke.getCode() == KeyCode.A) {
                m.rotateLeft(p.get(p.size() - 1), p);
            }
            
        });

        // Ajout Observer
        m.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                // Clear gridpane
                gridP.getChildren().clear();
                ArrayList<Piece> p = m.getPieces();
                
                // Création de la grille vide 
                for (int i = 0; i < GRID_WIDTH; i++) {
                    for (int j = 0; j < GRID_HEIGHT; j++) {
                        Rectangle r = new Rectangle();
                        r.setX(i * CELL_SIZE);
                        r.setY(j * CELL_SIZE);
                        r.setWidth(CELL_SIZE);
                        r.setHeight(CELL_SIZE);
                        r.setStroke(Color.BLACK);
                        r.setFill(Color.WHITE);
                        gridP.add(r, i, j); // Ajout à la gridpane
                    }
                }

                for (Piece piece : p) {
                    for (Cellule cell : piece.getShape()) {
                        Rectangle rect = new Rectangle();
                        rect.setX(piece.getPosition().getX() + cell.getPosition().getX());
                        rect.setY(piece.getPosition().getY() + cell.getPosition().getY());
                        rect.setWidth(CELL_SIZE);
                        rect.setHeight(CELL_SIZE);
                        rect.setFill(piece.getColor());
                        gridP.add(rect, piece.getPosition().getX() + cell.getPosition().getX(), piece.getPosition().getY() + cell.getPosition().getY());
                    }
                }
            }
        });

        primaryStage.setTitle("Tetris");
        primaryStage.setScene(new Scene(border));
        primaryStage.show();
        border.requestFocus();
    
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
