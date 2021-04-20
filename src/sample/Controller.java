package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

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

        //Графический контекст для отрисовки объектов
        GraphicsContext gcBasic = GameCanvas.getGraphicsContext2D();

        //Орудие игрока
        Model.Gun Player1 = new Model.Gun(150, GameCanvas.getWidth()/4, GameCanvas.getHeight() - 75);

        //Прицел игрока
         Model.Aim aim = new Model.Aim(GameCanvas.getHeight(), GameCanvas.getWidth(), Player1);

        //Форма отображения набранных цифр
        Model.EnterToNumber EntNumber = new Model.EnterToNumber(Player1,40,40);

        //Описание настроек игры
        Model.GameOptions Game = new Model.GameOptions(1, 10);

        //Создание списка метеоритов
        ArrayList<Model.Meteor> MeteorList = new ArrayList<>();


        for (int i = 0; i < Game.MeteorsCount; i++)
            MeteorList.add(new Model.Meteor(GameCanvas.getHeight(), GameCanvas.getWidth(), new Model().setID(MeteorList)));

        //Создание списка пуль
        ArrayList<Model.Projectile> ProjectileList = new ArrayList<>();

        //Список нажатых клавишь
        ArrayList<String> KeyList = new ArrayList<>();
        ArrayList<String> NumberToTarget = new ArrayList<>();
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
        APMenu.setOnKeyReleased(
                (KeyEvent e) -> KeyHold.set(false)

        );

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
                        ProjectileList.add(new Model.Projectile(
                                Player1, KeyList.get(0).toCharArray()[0],
                                GameCanvas.getHeight(), GameCanvas.getWidth(), aim.TargetMeteor));
                    KeyList.remove(new Model().IndexRusEng(KeyList));
                }

                for (Model.Meteor Meteor : MeteorList) {
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
                gcBasic.clearRect(0,0, GameCanvas.getWidth(), GameCanvas.getHeight());

                //Обновление орудия
                Player1.update(deltaTime);

                //Обновление списка метеоритов
                for (Model.Meteor Meteor : MeteorList)
                    Meteor.update(deltaTime);

                //Обновление списка снарядов
                for (Model.Projectile projectile : ProjectileList)
                    projectile.update(deltaTime);

                //Обновление прицела
                aim.update(deltaTime);

                //Отрисовка орудия
                Player1.render(gcBasic);

                //Отрисовка списка метеоритов
                for (Model.Meteor Meteor : MeteorList)
                    Meteor.render(gcBasic);

                //Отрисовка списка снарядов
                for (Model.Projectile projectile : ProjectileList)
                    projectile.render(gcBasic);

                //Отрисовка прицела
                aim.render(gcBasic);
                EntNumber.render(gcBasic);

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

        }
    };

        GameLoop.start();
        PanelLoop.start();

    }
}
