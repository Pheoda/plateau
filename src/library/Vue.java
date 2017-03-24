package library;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Vue extends Application {

    public static final int CELL_SIZE = 40;
    public static final int GRID_WIDTH = 10;
    public static final int GRID_HEIGHT = 10;

    Modele m;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Modele m = new Modele(GRID_WIDTH, GRID_HEIGHT);

        BorderPane border = new BorderPane();
        GridPane gridP = new GridPane();

        //border.setRight(...);
        border.setCenter(gridP);

        ArrayList<Piece> p = new ArrayList<>();

        PieceFactory factory = new PieceFactory();
        p.add(factory.create('S', Color.CYAN));
        p.add(factory.create('O', Color.YELLOW));
        p.add(factory.create('J', Color.RED));
        p.get(1).setPosition(new Position(5, 0));
        p.get(2).setPosition(new Position(2, 6));

        border.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.UP) {
                m.translateUp(p.get(0), p);
            }
            if (ke.getCode() == KeyCode.DOWN) {
                m.translateDown(p.get(0), p);
            }
            if (ke.getCode() == KeyCode.LEFT) {
                m.translateLeft(p.get(0), p);
            }
            if (ke.getCode() == KeyCode.RIGHT) {
                m.translateRight(p.get(0), p);
            }
            /*if (ke.getCode() == KeyCode.R) {
                m.rotateRight(p.get(0), p);
            }
            if (ke.getCode() == KeyCode.A) {
                m.rotateLeft(p.get(0), p);
            }*/
        });

        // Ajout Observer
        m.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                // Clear gridpane
                gridP.getChildren().clear();

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

        primaryStage.setTitle("Jeu de plateau");
        primaryStage.setScene(new Scene(border));
        primaryStage.show();
        border.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
