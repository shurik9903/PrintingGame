package sample.Server.Model;

import javafx.geometry.Point2D;
import sample.Data.DataInterface.*;
import sample.Server.Model.GameOptions.IGameOptions;
import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.Player.IPlayer;
import sample.Server.Model.Projectile.IProjectile;
import sample.Server.Model.ServerFactory.ServerFactory;
import sample.Server.Model.TextToObject.ITextToObject;
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
        Game = ServerFactory.GameOptionsCreateInstance(1, 10, WindowWidth, WindowHeight);

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
                        player.addNumber(player.getKeyList().get(0).toCharArray()[0]);
                        player.getKeyList().remove(ServerFactory.MyFunctionCreateInstance().IndexNumber(player.getKeyList()));
                    }

                    //Действие при нажатии правого альта
                    if (player.getKeyList().contains("ALT_GRAPH")) {
                        player.PlayerAimToMeteor(MeteorList, true);
                        player.getKeyList().remove("ALT_GRAPH");
                    }

                    //Действие при нажатии левого альта
                    if (player.getKeyList().contains("ALT")) {
                        player.PlayerAimToMeteor(MeteorList, false);
                        player.getKeyList().remove("ALT");
                    }

                    if (player.getKeyList().contains("SHIFT")) {
                        player.PlayerAimToMeteor(MeteorList, player.getNumbers());
                        player.getKeyList().remove("SHIFT");
                    }

                    //Действие при вводе буквы
                    if (ServerFactory.MyFunctionCreateInstance().IndexRusEng(player.getKeyList()) != -1) {
                        if (player.getPlayerAimTargetMeteor() != null &&
                                player.getPlayerAimTargetCaught() &&
                                !player.getPlayerAimTargetMeteor().overlaps(player) && player.getEnergy() != 0) {
                            player.getProjectileList().add(ServerFactory.ProjectileCreateInstance(
                                    player, player.getKeyList().get(0).toCharArray()[0],
                                    WindowHeight, WindowWidth, player.getPlayerAimTargetMeteor()));
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
                    if (Meteor.isDestroy()) Game.setScore(Game.getScore() + ((int) (500 * Game.getDifficulty() + Meteor.getVelX()))); ;
                }

                //Удаление уничтоженных метеоритов
                MeteorList.removeIf(IMeteor::isFall);
                MeteorList.removeIf(ITextToObject::isDestroy);

                //Удаление снарядов
                for (IPlayer player : AllPlayer) {
                    player.getProjectileList().removeIf(IProjectile::isDestroy);

                    for (IProjectile projectile : player.getProjectileList())
                        projectile.update(deltaTime);

                    //Обновление игрока
                    player.update(deltaTime);
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



                    if (player.getPlayerAimTargetMeteor() != null) {

                        Point2D vector =
                                new Point2D(player.getPosX() - player.getPlayerAimPosX(),
                                        player.getPosY() - player.getPlayerAimPosY());
                        double angle = vector.angle(1, 0);
                        if (vector.getY() > 0)
                            player.setRotation(angle + 180);
                    }


                    for (IProjectile projectile : player.getProjectileList())
                        projectileData.add(ServerFactory.ProjectileDataCreateInstance(projectile.getPosX(), projectile.getPosY(),
                                projectile.getRotation(), projectile.getImageWidth(),projectile.getImageHeight(),projectile.getFileImageName()));


                    GunData.add(ServerFactory.SpriteDataCreateInstance(player.getPosX(),
                            player.getPosY(), player.getRotation(), player.getImageWidth(),player.getImageHeight(),player.getFileImageName()));
                    aimData.add(ServerFactory.AimDataCreateInstance(player.getPlayerAimPosX(), player.getPlayerAimPosY(),
                            player.getPlayerAimRotation(), player.getPlayerAimImageWidth(),player.getPlayerAimImageHeight(),player.getPlayerAimFileImageName()));
                }

                for (IPlayer player : AllPlayer) {
                    IEnterToNumberData enterToNumberData = ServerFactory.EnterToNumberDataCreateInstance(player.getEntNumX(), player.getEntNumY(), player.getEntNumWidth(), player.getFrameNumber());
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
        return ServerFactory.MeteorDataCreateInstance(meteor.getPosX(), meteor.getPosY(),
                meteor.getRotation(), meteor.getImageWidth(),meteor.getImageHeight(),meteor.getFileImageName(),
                ServerFactory.TextDataCreateInstance(meteor.getTextX(), meteor.getTextY(),
                        meteor.getTextWidth(), meteor.getTextHeight(), meteor.getText().length(),
                        meteor.getFrameImage(), meteor.getFrameNumber(), meteor.getFrameText()));
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


