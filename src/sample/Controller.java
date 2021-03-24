package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

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

        Method.GameOptions Game = new Method.GameOptions(1, 10);

        //Создание списка метеоритов
        ArrayList<Method.Sprite> MeteorList = new ArrayList<Method.Sprite>();
        for (int i = 0; i < Game.MeteorsCount; i++)
            MeteorList.add(new Method.Meteor("Image/Asteroid.png", cBasic.getHeight(), cBasic.getWidth()));



        //ArrayList<Method.Sprite> Bullet = new ArrayList<Method.Sprite>();


        AnimationTimer gameLoop = new AnimationTimer(){
            @Override
            public void handle(long nanotime){
                gcBasic.clearRect(0,0, cBasic.getWidth(), cBasic.getHeight());
                for (Method.Sprite Meteor : MeteorList)
                    Meteor.update(deltaTime);

                for (Method.Sprite Meteor : MeteorList)
                    Meteor.render(gcBasic);

            }
        };

        gameLoop.start();


    }

}
