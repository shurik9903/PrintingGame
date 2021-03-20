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
private Canvas cMoon;

@FXML
private Canvas cSpace;

    @FXML
    public void initialize() {


        GraphicsContext gcBasic = cBasic.getGraphicsContext2D();
        GraphicsContext gcSpace = cSpace.getGraphicsContext2D();
        GraphicsContext gcMoon = cMoon.getGraphicsContext2D();

        Method.Sprite Space = new Method.Sprite("Image/stock_draw-sphere.png");
        Method.Sprite Moon = new Method.Sprite("Image/Screenshot_1.png");

        Method.Sprite Meteor = new Method.Sprite("Image/Asteroid.png", 50);
        Meteor.position.set(100,100);
        Meteor.velocity.set(50,50);
        Meteor.rotation = 45;

        Meteor.velocity.setAngle(Meteor.rotation);

        AnimationTimer gameLoop = new AnimationTimer(){
            @Override
            public void handle(long nanotime){
                Space.render(gcSpace);
                Moon.render(gcMoon);
                Meteor.update(1/50.0);
                Meteor.render(gcBasic);

            }
        };

        gameLoop.start();


    }

}
