package sample.Client.Model;

import sample.GameData;
import sample.UserData;

import java.net.Socket;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;


public class Model {
    GameData GD = new GameData();
    ServerThread server = null;
    public Model() {
    }

    public void Initialize(GraphicsContext GameCanvas, GraphicsContext GamePanelCanvas, AnchorPane APMenu, HBox HBScore, HBox HBEnergy) {

        int Score = 0, OldScore = 0;

        //Частота отрисовки
        double deltaTime = 1. / 60;

        //Проверка на зажатие кнопки
        AtomicBoolean KeyHold = new AtomicBoolean(false);

        //Графический контекста для отрисовки объектов
        GraphicsContext GCGame = GameCanvas;
        GraphicsContext GPCGame = GamePanelCanvas;

        //Создание списка Gif изображений энергии
        ArrayList<Gif> EnergyGifList = new ArrayList<>();

        for (Node IV : HBEnergy.getChildren())
            EnergyGifList.add(new Gif("Aim", (ImageView) IV, 2));

        //Список нажатых клавишь
        ArrayList<String> KeyList = new ArrayList<>();
        UserData userData = new UserData("User1", KeyList);


        try{
            Socket s = new Socket("127.0.0.1",1111);
            System.out.println("Local port: " +  s.getLocalPort());
            System.out.println("Remote port: " + s.getPort());

            server = new ServerThread(s, userData);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        //Добавление действий на форму | Действие при нажатие клавиши
        APMenu.setOnKeyPressed(
                (KeyEvent e) -> {
                    if (!GD.isGameProcess()) {
                        if (!KeyHold.get()) {
                            if (e.getCode().toString().equals("SHIFT"))
                                KeyList.add("SHIFT");
                            else if (e.getCode().toString().equals("CONTROL") || e.getCode().toString().equals("ALT_GRAPH"))
                                KeyList.add("ALT_GRAPH");
                            else if (e.getCode().toString().equals("ALT"))
                                KeyList.add(e.getCode().toString());
                            else if (e.getText().length() == 1)
                                if (e.getText().matches("[-+]?\\d+")) {
                                    KeyList.add(e.getText());
                                } else {
                                    KeyList.add(RusText(e.getText().toCharArray()[0]));
                                }
                            KeyList.remove("");
                            System.out.println(KeyList);
                            KeyHold.set(true);
                        }
                    }
                }
        );

        //Добавление действий на форму | Действие при отпускание клавиши
        APMenu.setOnKeyReleased((KeyEvent e) -> KeyHold.set(false));

        //Поток анимации объектов
        AnimationTimer GraphicsLoop = new AnimationTimer() {
            @Override
            public void handle(long nanotime) {
                if (server != null) {
                    server.OutData();
                    server.InData();
                    GD = server.getGameData();
                }
                //Очитска формы
                GCGame.clearRect(0, 0, GCGame.getCanvas().getWidth(), GCGame.getCanvas().getHeight());

                //Отрисовка списка метеоритов
                for (GameData.MeteorData Meteor : GD.getMeteorList())
                    Meteor.render(GCGame);

                //Отрисовка списка снарядов
                for (GameData.ProjectileData projectile : GD.getProjectileList())
                    projectile.render(GCGame);

                //Отрисовка прицела
                if (GD.getAim() != null)
                GD.getAim().render(GCGame);
                if (GD.getEnterToNumberData() != null)
                GD.getEnterToNumberData().render(GCGame);
            }
        };


        AnimationTimer PanelLoop = new AnimationTimer() {
            @Override
            public void handle(long nanotime) {
                GPCGame.clearRect(0, 0, GPCGame.getCanvas().getWidth(), GPCGame.getCanvas().getHeight());

                for (Gif EnergyGif : EnergyGifList)
                    EnergyGif.Render(GPCGame);

                DrawScore(Score, OldScore, HBScore);
            }
        };


        GraphicsLoop.start();
        PanelLoop.start();

    }



    public void DrawScore(int Score, int OldScore, HBox HBScore){
        if (OldScore != Score) {
            System.out.println("Score: " + Score);
            char[] CScore = ("" + Score).toCharArray();
            for (int i = 0; i < HBScore.getChildren().size(); i++) {
                if (i < HBScore.getChildren().size() - CScore.length)
                    ((ImageView) HBScore.getChildren().get(i)).setImage(new Image("Image/NumberImage/0.png"));
                else
                    ((ImageView) HBScore.getChildren().get(i)).setImage(
                            new Image("Image/NumberImage/" + CScore[i - (HBScore.getChildren().size() - CScore.length)] + ".png"));
            }
            OldScore = Score;
        }
    }

    //Функиця перевода английских букв в русские
    public String RusText(Character c) {

        c = Character.toLowerCase(c);
        if (c == 'ё') c = 'е';

        ArrayList<Character> rus = new ArrayList<>(
                Arrays.asList('й', 'ц', 'у', 'к', 'е', 'н', 'г', 'ш', 'щ', 'з', 'х',
                        'ъ', 'ф', 'ы', 'в', 'а', 'п', 'р', 'о', 'л', 'д', 'ж',
                        'э', 'я', 'ч', 'с', 'м', 'и', 'т', 'ь', 'б', 'ю', 'е'));

        ArrayList<Character> eng = new ArrayList<>(
                Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[',
                        ']', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';',
                        '\'', 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '`'));

        if (rus.contains(c)) return c.toString();
        if (eng.contains(c)) return rus.get(eng.indexOf(c)).toString();
        return "";
    }

    //Конвертирование анг. текста в рус.; получение индекса буквы
    public int IndexRusEng(ArrayList<String> arr) {

        ArrayList<Character> rus = new ArrayList<>(
                Arrays.asList('й', 'ц', 'у', 'к', 'е', 'н', 'г', 'ш', 'щ', 'з', 'х',
                        'ъ', 'ф', 'ы', 'в', 'а', 'п', 'р', 'о', 'л', 'д', 'ж',
                        'э', 'я', 'ч', 'с', 'м', 'и', 'т', 'ь', 'б', 'ю', 'е'));

        ArrayList<Character> eng = new ArrayList<>(
                Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[',
                        ']', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';',
                        '\'', 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '`'));

        for (Character i : rus)
            if (arr != null && arr.contains(i.toString()))
                return arr.indexOf(i.toString());

        for (Character i : eng)
            if (arr != null && arr.contains(i.toString()))
                return arr.indexOf(i.toString());

        return -1;

    }

    //Получение индекса цифры
    public int IndexNumber(ArrayList<String> arr) {
        ArrayList<Character> num = new ArrayList<>(
                Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));

        for (Character i : num)
            if (arr.contains(i.toString()))
                return arr.indexOf(i.toString());

        return -1;
    }
}


