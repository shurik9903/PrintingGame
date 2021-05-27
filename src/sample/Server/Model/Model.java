package sample.Server.Model;

import javafx.geometry.Point2D;
import sample.GameData;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Model {

    private final ArrayList<UserConnect> Users;

    public Model(){
        Initialize();
        Users = new ArrayList<>();

        try {
            ServerSocket ss = new ServerSocket(1111);
            Socket s = null;
            int NumberToUsers = 0;

            while (true) {

                System.out.println("Waiting connection...");
                s = ss.accept();
                NumberToUsers++;
                System.out.println("Client join");
                if (NumberToUsers > 0 && NumberToUsers <= 2) {
                    Users.add(new UserConnect("User" + NumberToUsers, s, this));
                    System.out.println("Local port: " + s.getLocalPort());
                    System.out.println("Remote port: " + s.getPort());
                }


                if (NumberToUsers > 0)
                    StartGame();
                else if (NumberToUsers == 0) StopGame();
            }

            //s.close();
            //ss.close();

            //System.out.println("Ending...");

        }catch (Exception e) {
            System.out.println("Ошибка запуска:");
            System.out.println("Error: " + e);
        }
    }

    Player Player1;
    Player Player2;
    ArrayList<Player> AllPlayer;
    double WindowHeight, WindowWidth;
    GameOptions Game;
    Thread GameLoop;

    public Player setPlayer1() {
        Player1 = new Player(150, WindowWidth/4, WindowHeight - 75);
        AllPlayer.add(Player1);
        return Player1;
    }

    public Player setPlayer2() {
        Player2 = new Player(150, WindowWidth-WindowWidth/4, WindowHeight - 75);
        AllPlayer.add(Player2);
        return Player2;
    }

    public void DisconnectPlayer1(){
        AllPlayer.remove(Player1);
        Player1 = null;
    }

    public void DisconnectPlayer2(){
        AllPlayer.remove(Player2);
        Player2 = null;
    }

    public void Initialize() {

        AllPlayer = new ArrayList<>();

        WindowHeight = 600;
        WindowWidth = 800;

        //Частота отрисовки
        double deltaTime = 1. / 60;

        //Описание настроек игры
        Game = new GameOptions(1, 3, WindowWidth, WindowHeight);

        //Создание списка метеоритов
        ArrayList<Meteor> MeteorList = new ArrayList<>();

        //Поток обработки объектов
        GameLoop = new Thread(() -> {
            while (true) {
                for (UserConnect user : Users) user.InData();


                Game.GameProcess(MeteorList);
                if (Game.Life < 1 || (Game.MeteorsSpawn >= Game.MeteorsCount && MeteorList.isEmpty())) {
                    Game.GameStop = true;
                }

                for (Player player : AllPlayer) {

                    if (new MyFunction().IndexNumber(player.getKeyList()) != -1) {
                        player.getEnterNumber().addNumber(player.getKeyList().get(0).toCharArray()[0]);
                        player.getKeyList().remove(new MyFunction().IndexNumber(player.getKeyList()));
                    }

                    //Действие при нажатии правого альта
                    if (player.getKeyList().contains("ALT_GRAPH")) {
                        player.getPlayerAim().AimToMeteor(MeteorList, true);
                        player.getKeyList().remove("ALT_GRAPH");
                    }

                    //Действие при нажатии левого альта
                    if (player.getKeyList().contains("ALT")) {
                        player.getPlayerAim().AimToMeteor(MeteorList, false);
                        player.getKeyList().remove("ALT");
                    }

                    if (player.getKeyList().contains("SHIFT")) {
                        player.getPlayerAim().AimToMeteor(MeteorList, player.getEnterNumber().getNumbers());
                        player.getKeyList().remove("SHIFT");
                    }

                    //Действие при вводе буквы
                    if (new MyFunction().IndexRusEng(player.getKeyList()) != -1) {
                        if (player.getPlayerAim().TargetMeteor != null &&
                                player.getPlayerAim().TargetCaught &&
                                !player.getPlayerAim().TargetMeteor.overlaps(player) && player.getEnergy() != 0) {
                            player.getProjectileList().add(new Projectile(
                                    player, player.getKeyList().get(0).toCharArray()[0],
                                    WindowHeight, WindowWidth, player.getPlayerAim().TargetMeteor));
                            player.SubEnergy();
                        }
                        player.getKeyList().remove(new MyFunction().IndexRusEng(player.getKeyList()));
                    }

                    //Убавление энергии пушки
                    for (Projectile projectile : player.getProjectileList())
                        if (!projectile.Miss && projectile.Destroy)
                            player.AddEnergy();

                }

                //Проверка условий игры
                for (Meteor Meteor : MeteorList) {
                    if (Meteor.Fall) Game.Life--;
                    if (Meteor.Text.Destroy) Game.Score += (int) (500 * Game.Difficulty + Meteor.velocity.x);
                }

                //Удаление уничтоженных метеоритов
                MeteorList.removeIf(Meteor -> Meteor.Fall);
                MeteorList.removeIf(Meteor -> Meteor.Text.Destroy);

                //Удаление снарядов
                for (Player player : AllPlayer) {
                    player.getProjectileList().removeIf(projectile -> projectile.Destroy);

                    //Обновление орудия
                    player.update(deltaTime);
                }
                //Обновление списка метеоритов
                for (Meteor Meteor : MeteorList)
                    Meteor.update(deltaTime);

                ArrayList<GameData.MeteorData> meteorDataList = new ArrayList<>();
                for (Meteor meteor : MeteorList)
                    meteorDataList.add(ConvertMeteorToData(meteor));

                //Обновление списка снарядов
                for (Player player : AllPlayer) {
                    for (Projectile projectile : player.getProjectileList())
                        projectile.update(deltaTime);

                    //Обновление прицела
                    player.getPlayerAim().update(deltaTime);

                    if (player.getPlayerAim().TargetMeteor != null) {

                        Point2D vector =
                                new Point2D(player.position.x - player.getPlayerAim().position.x,
                                        player.position.y - player.getPlayerAim().position.y);
                        double angle = vector.angle(1, 0);
                        if (vector.getY() > 0)
                            player.rotation = angle + 180;
                    }

                    ArrayList<GameData.ProjectileData> projectileData = new ArrayList<>();
                    for (Projectile projectile : player.getProjectileList())
                        projectileData.add(new GameData.ProjectileData(projectile.position.x, projectile.position.y,
                                projectile.rotation, projectile.image));

                    GameData.AimData aimData = new GameData.AimData(player.getPlayerAim().position.x, player.getPlayerAim().position.y, player.getPlayerAim().rotation, player.getPlayerAim().image);
                    GameData.EnterToNumberData enterToNumberData = new GameData.EnterToNumberData(player.getEnterNumber().x, player.getEnterNumber().y, player.getEnterNumber().Width, player.getEnterNumber().FrameNumber);
                    player.setGameData(new GameData(Game.Score, player.rotation, Game.GameStop, meteorDataList, projectileData, aimData, enterToNumberData));

                }

                for (UserConnect user : Users) user.OutData();

                try {
                    Thread.sleep(1000/60);
                } catch (Exception e) {
                }
            }
        });
    }


    public GameData.MeteorData ConvertMeteorToData(Meteor meteor){
        return new GameData.MeteorData(meteor.position.x, meteor.position.y,
                meteor.rotation, meteor.image,
                new GameData.TextData(meteor.Text.x, meteor.Text.y,
                        meteor.Text.Width, meteor.Text.Height, meteor.Text.Text.length(),
                        meteor.Text.FrameImage, meteor.Text.FrameNumber, meteor.Text.FrameText));
    }

    public void StartGame(){
        System.out.println("Start Game");
        GameLoop.start();
    }

    public void StopGame(){
        GameLoop.stop();
    }


}


