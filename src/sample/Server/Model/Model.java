package sample.Server.Model;

import javafx.geometry.Point2D;
import sample.Data.DataInterface.*;
import sample.Server.Model.GameOptions.IGameOptions;
import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.Player.IPlayer;
import sample.Server.Model.Projectile.IProjectile;
import sample.Server.Model.Rectangle.IRectangle;
import sample.Server.Model.ServerFactory.ServerFactory;
import sample.Server.Model.UserConnect.IUserConnect;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Model implements IModel {

    private final ArrayList<IUserConnect> Users;

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
                    Users.add(ServerFactory.UserConnectCreateInstance("User" + NumberToUsers, s, this));
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

    IPlayer Player1;
    IPlayer Player2;
    ArrayList<IPlayer> AllPlayer;
    double WindowHeight, WindowWidth;
    IGameOptions Game;
    Thread GameLoop;

    @Override
    public IPlayer setPlayer1() {
        Player1 = ServerFactory.PlayerCreateInstance(150, WindowWidth/6, WindowHeight - 100);
        AllPlayer.add(Player1);
        return Player1;
    }

    @Override
    public IPlayer setPlayer2() {
        Player2 = ServerFactory.PlayerCreateInstance(150, WindowWidth-WindowWidth/6, WindowHeight - 100);
        AllPlayer.add(Player2);
        return Player2;
    }

    @Override
    public void DisconnectPlayer1(){
        AllPlayer.remove(Player1);
        Player1 = null;
    }

    @Override
    public void DisconnectPlayer2(){
        AllPlayer.remove(Player2);
        Player2 = null;
    }

    @Override
    public void Initialize() {

        AllPlayer = new ArrayList<>();

        WindowHeight = 470;
        WindowWidth = 800;

        //Частота отрисовки
        double deltaTime = 1. / 24;

        //Описание настроек игры
        Game = ServerFactory.GameOptionsCreateInstance(1, 3, WindowWidth, WindowHeight);

        //Создание списка метеоритов
        ArrayList<IMeteor> MeteorList = new ArrayList<>();

        //Поток обработки объектов
        GameLoop = new Thread(() -> {
            while (true) {


                Game.GameProcess(MeteorList);
                if (Game.getLife() < 1 || (Game.getMeteorsSpawn() >= Game.getMeteorsCount() && MeteorList.isEmpty())) {
                    Game.setGameStop(true);
                }

                for (IPlayer player : AllPlayer) {

                    if (ServerFactory.MyFunctionCreateInstance().IndexNumber(player.getKeyList()) != -1) {
                        player.getEnterNumber().addNumber(player.getKeyList().get(0).toCharArray()[0]);
                        player.getKeyList().remove(ServerFactory.MyFunctionCreateInstance().IndexNumber(player.getKeyList()));
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
                    if (ServerFactory.MyFunctionCreateInstance().IndexRusEng(player.getKeyList()) != -1) {
                        if (player.getPlayerAim().getTargetMeteor() != null &&
                                player.getPlayerAim().getTargetCaught() &&
                                !player.getPlayerAim().getTargetMeteor().getBasic().overlaps((IRectangle) player) && player.getEnergy() != 0) {
                            player.getProjectileList().add(ServerFactory.ProjectileCreateInstance(
                                    player, player.getKeyList().get(0).toCharArray()[0],
                                    WindowHeight, WindowWidth, player.getPlayerAim().getTargetMeteor()));
                            player.SubEnergy();
                        }
                        player.getKeyList().remove(ServerFactory.MyFunctionCreateInstance().IndexRusEng(player.getKeyList()));
                    }

                    //Убавление энергии пушки
                    for (IProjectile projectile : player.getProjectileList())
                        if (!projectile.isMiss() && projectile.isDestroy())
                            player.AddEnergy();

                }

                //Проверка условий игры
                for (IMeteor Meteor : MeteorList) {
                    if (Meteor.isFall()) Game.setLife(Game.getLife()-1);
                    if (Meteor.getTextObject().isDestroy()) Game.setScore(Game.getScore() + ((int) (500 * Game.getDifficulty() + Meteor.getBasic().getVelocity().getX()))); ;
                }

                //Удаление уничтоженных метеоритов
                MeteorList.removeIf(IMeteor::isFall);
                MeteorList.removeIf(IMeteor -> IMeteor.getTextObject().isDestroy());

                //Удаление снарядов
                for (IPlayer player : AllPlayer) {
                    player.getProjectileList().removeIf(IProjectile::isDestroy);

                    for (IProjectile projectile : player.getProjectileList())
                        projectile.update(deltaTime);

                    //Обновление орудия
                    player.getBasic().update(deltaTime);
                }

                //Обновление списка метеоритов
                for (IMeteor Meteor : MeteorList)
                    Meteor.update(deltaTime);


                ArrayList<IMeteorData> meteorDataList = new ArrayList<>();
                for (IMeteor meteor : MeteorList)
                    meteorDataList.add(ConvertMeteorToData(meteor));

                ArrayList<IProjectileData> projectileData = new ArrayList<>();
                ArrayList<ISpriteData> GunData = new ArrayList<>();
                ArrayList<IAimData> aimData = new ArrayList<>();

                for (IPlayer player : AllPlayer) {

                    //Обновление прицела
                    player.getPlayerAim().update(deltaTime);

                    if (player.getPlayerAim().getTargetMeteor() != null) {

                        Point2D vector =
                                new Point2D(player.getBasic().getPosition().getX() - player.getPlayerAim().getBasic().getPosition().getX(),
                                        player.getBasic().getPosition().getY() - player.getPlayerAim().getBasic().getPosition().getY());
                        double angle = vector.angle(1, 0);
                        if (vector.getY() > 0)
                            player.getBasic().setRotation(angle + 180);
                    }


                    for (IProjectile projectile : player.getProjectileList())
                        projectileData.add(ServerFactory.ProjectileDataCreateInstance(projectile.getBasic().getPosition().getX(), projectile.getBasic().getPosition().getY(),
                                projectile.getBasic().getRotation(), projectile.getBasic().getImage()));


                    GunData.add(ServerFactory.SpriteDataCreateInstance(player.getBasic().getPosition().getX(),
                            player.getBasic().getPosition().getY(), player.getBasic().getRotation(), player.getBasic().getImage()));
                    aimData.add(ServerFactory.AimDataCreateInstance(player.getPlayerAim().getBasic().getPosition().getX(), player.getPlayerAim().getBasic().getPosition().getY(),
                            player.getPlayerAim().getBasic().getRotation(), player.getPlayerAim().getBasic().getImage()));
                }

                for (IPlayer player : AllPlayer) {
                    IEnterToNumberData enterToNumberData = ServerFactory.EnterToNumberDataCreateInstance(player.getEnterNumber().getX(), player.getEnterNumber().getY(), player.getEnterNumber().getWidth(), player.getEnterNumber().getFrameNumber());
                    player.setGameData(ServerFactory.GameDataCreateInstance(Game.getScore(), GunData, Game.getGameStop(), meteorDataList, projectileData, aimData, enterToNumberData));

                }


                try {
                    Thread.sleep(1000 / 24);
                } catch (Exception e) {
                }
            }
        });
    }

    @Override
    public IMeteorData ConvertMeteorToData(IMeteor meteor){
        return ServerFactory.MeteorDataCreateInstance(meteor.getBasic().getPosition().getX(), meteor.getBasic().getPosition().getY(),
                meteor.getBasic().getRotation(), meteor.getBasic().getImage(),
                ServerFactory.TextDataCreateInstance(meteor.getTextObject().getX(), meteor.getTextObject().getY(),
                        meteor.getTextObject().getWidth(), meteor.getTextObject().getHeight(), meteor.getTextObject().getText().length(),
                        meteor.getTextObject().getFrameImage(), meteor.getTextObject().getFrameNumber(), meteor.getTextObject().getFrameText()));
    }

    @Override
    public void StartGame(){
        System.out.println("Start Game");
        GameLoop.start();
    }

    @Override
    public void StopGame(){
        GameLoop.stop();
    }


}


