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

    Modele m;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Modele m = new Modele();

        BorderPane border = new BorderPane();
        GridPane gridP = new GridPane();



        //border.setRight(...);
        border.setCenter(gridP);

        ArrayList<Piece> p = new ArrayList<>();

        PieceFactory factory = new PieceFactory();
        p.add(factory.create('I', Color.CYAN));
        p.add(factory.create('O', Color.YELLOW));
        p.get(1).getPosition().setX(5);


        border.setOnKeyPressed((KeyEvent ke) -> {
            if(ke.getCode() == KeyCode.UP)
                m.translateUp(p.get(0));
            if(ke.getCode() == KeyCode.DOWN)
                m.translateDown(p.get(0));
            if(ke.getCode() == KeyCode.LEFT)
                m.translateLeft(p.get(0));
            if(ke.getCode() == KeyCode.RIGHT)
                m.translateRight(p.get(0));
            if(ke.getCode() == KeyCode.R)
                m.rotateRight(p.get(0));
            if(ke.getCode() == KeyCode.A)
                m.rotateLeft(p.get(0));
        });

        
        // Ajout Observer
        m.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                // Création de la grille
                for(int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
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
                
                m.refreshPlateau(p);
                
                for(int k = 0; k < p.size(); k++)
                    for(int i = 0; i < p.get(k).getShape().length; i++)
                        for(int j = 0; j < p.get(k).getShape()[0].length; j++)
                        {
                            if (p.get(k).getShape()[i][j])
                            {
                                Rectangle piece = new Rectangle();
                                piece.setX(p.get(k).getPosition().getX());
                                piece.setY(p.get(k).getPosition().getY());
                                piece.setWidth(CELL_SIZE);
                                piece.setHeight(CELL_SIZE);
                                piece.setFill(p.get(k).getColor());
                                gridP.add(piece, p.get(k).getPosition().getX() + i, p.get(k).getPosition().getY() + j);
                            }
                        }
            }
        });

        primaryStage.setTitle("Jeu de plateau");
        //primaryStage.setTitle(grid.getTitle());
        primaryStage.setScene(new Scene(border));
        primaryStage.show();
        border.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
