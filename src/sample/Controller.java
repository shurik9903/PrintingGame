package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import sample.Model.*;

public class Controller {

//Окно отрисовки объектов
@FXML
private Canvas GameCanvas;

@FXML
private Canvas GamePanelCanvas;

@FXML
private AnchorPane APMenu;

//Инициализация при запуске формы
@FXML
public void initialize(){

        //Частота отрисовки
        double deltaTime = 1./60;

        //Проверка на зажатие кнопки
        AtomicBoolean KeyHold = new AtomicBoolean(false);

        //Графический контекста для отрисовки объектов
        GraphicsContext GCGame = GameCanvas.getGraphicsContext2D();
        GraphicsContext GPCGame = GamePanelCanvas.getGraphicsContext2D();

        //Орудие игрока
        Gun Player1 = new Gun(150, GameCanvas.getWidth()/4, GameCanvas.getHeight() - 75);

        //Прицел игрока
         Aim aim = new Aim(GameCanvas.getHeight(), GameCanvas.getWidth(), Player1);

        //Форма отображения набранных цифр
        EnterToNumber EntNumber = new EnterToNumber(Player1,40,40);

        //Описание настроек игры
        GameOptions Game = new GameOptions(1, 10);

        //Создание списка метеоритов
        ArrayList<Meteor> MeteorList = new ArrayList<>();

        Gif im = new Gif("Aim", 40);
        im.setCoordinate(100,100);

        for (int i = 0; i < Game.MeteorsCount; i++)
            MeteorList.add(new Meteor(GameCanvas.getHeight(), GameCanvas.getWidth(), new Model().setID(MeteorList)));

        //Создание списка пуль
        ArrayList<Projectile> ProjectileList = new ArrayList<>();

        //Список нажатых клавишь
        ArrayList<String> KeyList = new ArrayList<>();

        //Добавление действий на форму | Действие при нажатие клавиши
        APMenu.setOnKeyPressed(
                (KeyEvent e) ->{

                    if (!KeyHold.get()){
                        if (e.getCode().toString().equals("SHIFT"))
                            KeyList.add("SHIFT");
                        else if (e.getCode().toString().equals("CONTROL") || e.getCode().toString().equals("ALT_GRAPH"))
                            KeyList.add("ALT_GRAPH");
                        else if (e.getCode().toString().equals("ALT"))
                            KeyList.add(e.getCode().toString());
                        else if (e.getText().length() == 1)
                            if (e.getText().matches("[-+]?\\d+")) {
                                KeyList.add(e.getText().toString());
                            } else {
                                KeyList.add(new Model().RusText(e.getText().toCharArray()[0]));
                            }
                        KeyList.remove("");
                        System.out.println(KeyList);
                        KeyHold.set(true);
                    }

                }
        );

        //Добавление действий на форму | Действие при отпускание клавиши
        APMenu.setOnKeyReleased((KeyEvent e) -> KeyHold.set(false));

        //Поток анимации объектов
        AnimationTimer GameLoop = new AnimationTimer(){
            @Override
            public void handle(long nanotime){

                if (new Model().IndexNumber(KeyList) != -1){
                    EntNumber.addNumber(KeyList.get(0).toCharArray()[0]);
                    KeyList.remove(new Model().IndexNumber(KeyList));
                }

                //Действие при нажатии правого альта
                if (KeyList.contains("ALT_GRAPH")){
                    aim.AimToMeteor(MeteorList, true);
                    KeyList.remove("ALT_GRAPH");
                }

                //Действие при нажатии левого альта
                if (KeyList.contains("ALT")){
                    aim.AimToMeteor(MeteorList, false);
                    KeyList.remove("ALT");
                }

                if (KeyList.contains("SHIFT")){
                    aim.AimToMeteor(MeteorList, EntNumber.getNumbers());
                    KeyList.remove("SHIFT");
                }

                //Действие при вводе буквы
                if (new Model().IndexRusEng(KeyList) != -1){
                    if (aim.TargetMeteor != null && aim.TargetCaught && !aim.TargetMeteor.overlaps(Player1))
                        ProjectileList.add(new Projectile(
                                Player1, KeyList.get(0).toCharArray()[0],
                                GameCanvas.getHeight(), GameCanvas.getWidth(), aim.TargetMeteor));
                    KeyList.remove(new Model().IndexRusEng(KeyList));
                }

                for (Meteor Meteor : MeteorList) {
                    if (Meteor.Fall) Game.Life--;
                    if (Meteor.Text.Destroy) Game.Score += 500 * Game.Difficulty;
                }

                /*
                if (Game.Life < 1) {
                    System.out.println("End Game");
                    System.out.println(Game.Score);
                    super.stop();
                }
                 */

                //Удаление уничтоженных метеоритов
                MeteorList.removeIf(Meteor -> Meteor.Fall);
                MeteorList.removeIf(Meteor -> Meteor.Text.Destroy);

                //Удаление снарядов
                ProjectileList.removeIf(projectile -> projectile.Destroy);

                //Очитска формы
                GCGame.clearRect(0,0, GCGame.getCanvas().getWidth(), GCGame.getCanvas().getHeight());

                //Обновление орудия
                Player1.update(deltaTime);

                //Обновление списка метеоритов
                for (Meteor Meteor : MeteorList)
                    Meteor.update(deltaTime);

                //Обновление списка снарядов
                for (Projectile projectile : ProjectileList)
                    projectile.update(deltaTime);

                //Обновление прицела
                aim.update(deltaTime);

                //Отрисовка орудия
                Player1.render(GCGame);

                //Отрисовка списка метеоритов
                for (Meteor Meteor : MeteorList)
                    Meteor.render(GCGame);

                //Отрисовка списка снарядов
                for (Projectile projectile : ProjectileList)
                    projectile.render(GCGame);

                //Отрисовка прицела
                aim.render(GCGame);
                EntNumber.render(GCGame);

                if (aim.TargetMeteor != null) {

                    Point2D vector =
                            new Point2D(Player1.position.x - aim.position.x, Player1.position.y - aim.position.y);
                    double angle = vector.angle(1, 0);
                    if (vector.getY() > 0)
                        Player1.rotation = angle + 180;
                }
            }
        };



    AnimationTimer PanelLoop = new AnimationTimer() {
        @Override
        public void handle(long nanotime) {

            GPCGame.clearRect(0,0, GPCGame.getCanvas().getWidth(), GPCGame.getCanvas().getHeight());

            im.render(GPCGame, deltaTime);

        }
    };

        GameLoop.start();
        PanelLoop.start();

    }
}
