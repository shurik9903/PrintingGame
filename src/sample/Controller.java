package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Controller {

//Окно отрисовки объектов
@FXML
private Canvas cBasic;

@FXML
private AnchorPane APMenu;

@FXML
public void initialize(){
        //Частота отрисовки

        double deltaTime = 1./60;
        AtomicBoolean KeyHold = new AtomicBoolean(false);
        GraphicsContext gcBasic = cBasic.getGraphicsContext2D();

        //Прицел игрока
        Method.Aim aim = new Method.Aim(cBasic.getHeight(), cBasic.getWidth());

        //Описание настроек игры
        Method.GameOptions Game = new Method.GameOptions(1, 10);

        //Создание списка метеоритов
        ArrayList<Method.Sprite> MeteorList = new ArrayList<Method.Sprite>();
        for (int i = 0; i < Game.MeteorsCount; i++)
            MeteorList.add(new Method.Meteor("Image/Asteroid.png", cBasic.getHeight(), cBasic.getWidth()));

        ArrayList<String> KeyList = new ArrayList<String>();

        APMenu.setOnKeyPressed(
                (KeyEvent e) ->{
                    if (!KeyHold.get()) {
                        System.out.println(e.getCode().toString());
                        KeyList.add(e.getCode().toString());
                        KeyHold.set(true);
                    }
                }
        );

        APMenu.setOnKeyReleased(
                (KeyEvent e) ->{
                    KeyHold.set(false);
                }
        );

        //ArrayList<Method.Sprite> Bullet = new ArrayList<Method.Sprite>();


        AnimationTimer gameLoop = new AnimationTimer(){
            @Override
            public void handle(long nanotime){

                if (KeyList.contains("ALT_GRAPH")){
                    aim.AimToMeteor(MeteorList, true);
                    KeyList.remove("ALT_GRAPH");
                }

                if (KeyList.contains("ALT")){
                    aim.AimToMeteor(MeteorList, false);
                    KeyList.remove("ALT");
                }

                gcBasic.clearRect(0,0, cBasic.getWidth(), cBasic.getHeight());
                for (Method.Sprite Meteor : MeteorList)
                    Meteor.update(deltaTime);

                aim.update(deltaTime);

                for (Method.Sprite Meteor : MeteorList)
                    Meteor.render(gcBasic);

                aim.render(gcBasic);

            }
        };

        gameLoop.start();


    }
}
