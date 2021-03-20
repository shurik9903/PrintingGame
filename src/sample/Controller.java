package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import sample.Method;

public class Controller {

@FXML
private AnchorPane APMenu;

    public void render(GraphicsContext gc){
        /*
        gc.save();

        gc.translate(0,0);
        gc.drawImage(new Image("Image/Asteroid.png"),0,0);

        gc.restore();

         */
    }

    @FXML
    public void initialize() {
        Method.Sprite Meteor = new Method.Sprite("");


    }

}
