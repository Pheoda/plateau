import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Vue extends Application {
    //Controleur c;
    Modele m;



    @Override
    public void start(Stage primaryStage) throws Exception{
        m = new Modele();
        //c = new Controleur();

        BorderPane border = new BorderPane();
        GridPane grid = new GridPane();

        border.setCenter(grid);

        // Ajout Observer
        /*m.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {

            }
        });*/

        primaryStage.setTitle("Jeu de plateau");
        primaryStage.setScene(new Scene(border, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
