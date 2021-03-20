package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import sample.Method;

import java.util.ArrayList;

public class Controller {

@FXML
private Canvas cBasic;


    @FXML
    public void initialize() {


        GraphicsContext gcBasic = cBasic.getGraphicsContext2D();

        Method.Sprite Meteor = new Method.Sprite("Image/Asteroid.png", 50);
        Meteor.position.set(100,100);
        Meteor.rotation = 45;
        Meteor.velocity.setLength(50);
        Meteor.velocity.setAngle(Meteor.rotation);


        AnimationTimer gameLoop = new AnimationTimer(){
            @Override
            public void handle(long nanotime){

                Meteor.update(1/50.0);
                Meteor.render(gcBasic, cBasic);

            }
        };

        gameLoop.start();


    }

}
