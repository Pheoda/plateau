package tetris;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    
    public static final int COL_WIDTH = 7;
    public static final int COL_HEIGHT = 21;
    

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
        GridPane gridP_colRight = new GridPane();
        
        gridP_colRight.setPadding(new Insets(25, 0, 50, 0));
        gridP_colRight.setPrefSize(300, 300);
                
        Text textNext = new Text();
        textNext.setFont(new Font(20));
        textNext.setText("Piece suivante :");
        Text textScore = new Text();
        textScore.setFont(new Font(20));
        textScore.setText("Score : " + score);

        
        border.setRight(gridP_colRight);
        border.setLeft(textScore);
        border.setCenter(gridP);
        
        gridP_colRight.add(textNext, 0,0);
        
        // Ajout de marges
        BorderPane.setMargin(gridP, new Insets(TOP_MARGIN, RIGHT_MARGIN, BOTTOM_MARGIN, LEFT_MARGIN));
        BorderPane.setMargin(textScore, new Insets(0, 0, 0, LEFT_MARGIN));
        
        // Création de la grille center vide 
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
        m.pieceAlea();

        border.setOnKeyPressed((KeyEvent ke) -> {
            ArrayList<Piece> p = m.getPieces();

            if (ke.getCode() == KeyCode.DOWN) {
                m.initializePositionPieceCurrent();
                m.translateDown(p.get(p.size() - 2), p);
            }
            if (ke.getCode() == KeyCode.LEFT) {
                m.initializePositionPieceCurrent();
                m.translateLeft(p.get(p.size() - 2), p);
            }
            if (ke.getCode() == KeyCode.RIGHT) {
                m.initializePositionPieceCurrent();
                m.translateRight(p.get(p.size() - 2), p);
            }
            if (ke.getCode() == KeyCode.R) {
                m.initializePositionPieceCurrent();
                m.rotateRight(p.get(p.size() - 2), p);
            }
            if (ke.getCode() == KeyCode.A) {
                m.initializePositionPieceCurrent();
                m.rotateLeft(p.get(p.size() - 2), p);
            }

        });

        // Ajout Observer
        m.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                // Clear gridpane
                gridP.getChildren().clear();
                gridP_colRight.getChildren().clear();
                
                gridP_colRight.add(textNext, 0,0);
                
                ArrayList<Piece> p = m.getPieces();

                //Sauvegarde de la piece suivante
                Piece pieceNext = p.remove(p.size() - 1);

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
                     if (piece.getPosition() != null) {
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
                
                // Affichage de la piece suivante à droite

                for (Cellule cellCurrent : pieceNext.getShape()) {
                    Rectangle rectPieceCurrent = new Rectangle();
                    rectPieceCurrent.setWidth(CELL_SIZE);
                    rectPieceCurrent.setHeight(CELL_SIZE);
                    rectPieceCurrent.setFill(pieceNext.getColor());
                    rectPieceCurrent.setStroke(Color.BLACK);
                    gridP_colRight.add(rectPieceCurrent, 1 + cellCurrent.getPosition().getX(), 25 + cellCurrent.getPosition().getY());
                }
                p.add(pieceNext);

               
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
