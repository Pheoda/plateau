import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;

import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Vue extends Application {
    public static final int CELL_SIZE = 40;

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane border = new BorderPane();
        GridPane grid = new GridPane();

        // Création de la grille
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                Rectangle r = new Rectangle();
                r.setX(i * CELL_SIZE);
                r.setY(j * CELL_SIZE);
                r.setWidth(CELL_SIZE);
                r.setHeight(CELL_SIZE);
                r.setStroke(Color.BLACK);
                r.setFill(Color.WHITE);
                grid.add(r, i, j); // Ajout à la gridpane
            }
        }

        //border.setRight(...);
        border.setCenter(grid);

        // Ajout Observer
        /*m.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {

            }
        });*/

        primaryStage.setTitle("Jeu de plateau");
        primaryStage.setScene(new Scene(border));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
