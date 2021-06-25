package sample.Client.Model.ClientFactory;

import javafx.scene.image.ImageView;
import sample.Client.Model.Gif.Gif;
import sample.Client.Model.Gif.GifFactory;
import sample.Client.Model.Gif.IGif;
import sample.Client.Model.IModel;
import sample.Client.Model.Model;
import sample.Client.Model.ServerThread.IServerThread;
import sample.Client.Model.ServerThread.ServerThread;
import sample.Data.DataInterface.*;
import sample.Data.GameData;
import sample.Data.GameDataFactory;
import sample.Data.UserData;
import sample.Data.DataInterface.IImageDate;
import sample.Server.Model.Aim.AimFactory;
import sample.Server.Model.EnterToNumber.EnterToNumberFactory;
import sample.Server.Model.GameOptions.GameOptionsFactory;
import sample.Server.Model.Meteor.MeteorFactory;
import sample.Server.Model.ModelFactory;
import sample.Server.Model.MyFunction.MyFunctionFactory;
import sample.Server.Model.MyImage.MyImageFactory;
import sample.Server.Model.Player.PlayerAimFactory;
import sample.Server.Model.Player.PlayerFactory;
import sample.Server.Model.Projectile.ProjectileFactory;
import sample.Server.Model.Rectangle.RectangleFactory;
import sample.Server.Model.Sprite.SpriteFactory;
import sample.Server.Model.TextToObject.TextToObjectFactory;
import sample.Server.Model.UserConnect.UserConnectFactory;
import sample.Server.Model.Vector.VectorFactory;
import sample.Server.Model.Vector.VectorPositionFactory;
import sample.Server.Model.Vector.VectorVelocityFactory;

import java.net.Socket;
import java.util.ArrayList;

public class ClientFactory {


    public static Object ClassFactory(String ClassName){
        switch (ClassName) {
            case "IModel" -> {
                return new ModelFactory();
            }
            case "Gif" -> {
                return new GifFactory();
            }
            default -> {
                return null;
            }
        }
    }

/*
    public static IModel  ModelCreateInstance(){
        return new Model();
    }

    public static ISpriteData SpriteDataCreateInstance(double PosX, double PosY, double rotation,double ImageDataWidth, double ImageDataHeight, String file){
        return new GameData.SpriteData(PosX,PosY,rotation, ImageDataWidth, ImageDataHeight, file);
    }

    public static IProjectileData ProjectileDataCreateInstance(double PosX, double PosY, double rotation,double ImageDataWidth, double ImageDataHeight, String file){
        return new GameData.ProjectileData( PosX,  PosY,  rotation, ImageDataWidth,  ImageDataHeight,  file);
    }

    public static IAimData AimDataCreateInstance(double PosX, double PosY, double rotation,double ImageDataWidth, double ImageDataHeight, String file){
        return new GameData.AimData(PosX,  PosY,  rotation, ImageDataWidth,  ImageDataHeight,  file);
    }

    public static IUserData UserDataCreateInstance(String UserName, ArrayList<String> KeyList){
        return new UserData(UserName,KeyList);
    }

    public static IUserData UserDataCreateInstance(){
        return new UserData();
    }

    public static IGameData GameDataCreateInstance(int UserScore, java.util.ArrayList<ISpriteData> PlayersGun, boolean GameProcess, ArrayList<IMeteorData> MeteorList, ArrayList<IProjectileData> ProjectileList, ArrayList<IAimData> PlayersAim, IEnterToNumberData enterToNumberData){
        return new GameData(UserScore, PlayersGun, GameProcess, MeteorList, ProjectileList, PlayersAim, enterToNumberData);
    }

    public static IGameData GameDataCreateInstance(){
        return new GameData();
    }

    public static IServerThread ServerThreadCreateInstance(Socket server, IUserData userData){
        return new ServerThread(server,userData);
    }

    public static IImageDate ImageDateCreateInstance(String fileImageName, double sizeImage) {
        return new GameData.ImageDate(fileImageName,sizeImage);
    }

    public static IImageDate ImageDateCreateInstance(String fileImageName, double Width, double Height) {
        return new GameData.ImageDate(fileImageName,Width,Height);

    }

    public static IImageDate ImageDateCreateInstance(String fileImageName) {
        return new GameData.ImageDate(fileImageName);
    }



    public static IGif GifCreateInstance(String Directory, double Size, int FPS){
        return new Gif(Directory,Size,FPS);
    }

    public static IGif GifCreateInstance(String Directory, ImageView imageview, int FPS){
        return new Gif(Directory,imageview,FPS);
    }

    public static IGif GifCreateInstance(String Name, double Size, int FPS, double x, double y){
        return new Gif(Name,Size,FPS,x,y);
    }

 */
}
