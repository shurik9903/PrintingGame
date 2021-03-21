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

//Окно отрисовки объектов
@FXML
private Canvas cBasic;


    @FXML
    public void initialize() {
        //Частота отрисовки
        double deltaTime = 1./60;


        GraphicsContext gcBasic = cBasic.getGraphicsContext2D();

        Method.GameOptions Game = new Method.GameOptions(1, 20);

        //Создание списка метеоритов
        ArrayList<Method.Sprite> MeteorList = new ArrayList<Method.Sprite>();
        for (int i = 0; i < Game.MeteorsCount; i++){
            Method.Sprite Meteor = new Method.Sprite("Image/Asteroid.png", new Method().getRandomNumber(50,150));
            Meteor.position.set(new Method().getRandomNumber(-100, 700), -10);
            Meteor.velocity.setLength(100);

            if (Meteor.position.x <= cBasic.getWidth()/2) {

                Meteor.rotation = new Method().getRandomNumber(
                        (int)Meteor.position.getAngle2Vectors(Meteor.position.x, cBasic.getHeight(),
                                0, cBasic.getHeight()),
                        (int)Meteor.position.getAngle2Vectors(0, cBasic.getHeight(),
                                cBasic.getWidth(), cBasic.getHeight()));
            } else {
                Meteor.rotation = new Method().getRandomNumber(
                        90 + (int)Meteor.position.getAngle2Vectors(Meteor.position.x, cBasic.getHeight(),
                                cBasic.getWidth(), cBasic.getHeight()),
                        180 - (int)Meteor.position.getAngle2Vectors(cBasic.getWidth(), cBasic.getHeight(),
                               0, cBasic.getHeight()));
            }
            Meteor.velocity.setAngle(Meteor.rotation);
            MeteorList.add(Meteor);
        }


        //ArrayList<Method.Sprite> Bullet = new ArrayList<Method.Sprite>();
        

        AnimationTimer gameLoop = new AnimationTimer(){
            @Override
            public void handle(long nanotime){
                gcBasic.clearRect(0,0, cBasic.getWidth(), cBasic.getHeight());
                for (Method.Sprite Meteor : MeteorList)
                    Meteor.update(deltaTime);

                for (Method.Sprite Meteor : MeteorList)
                    Meteor.render(gcBasic,cBasic);

            }
        };

        gameLoop.start();


    }

}
