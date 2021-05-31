package sample.Client.Model.ClientFactory;

import javafx.scene.image.ImageView;
import sample.Client.Model.Gif.Gif;
import sample.Client.Model.Gif.IGif;
import sample.Client.Model.ImageDate.IImageDate;
import sample.Client.Model.ImageDate.ImageDate;
import sample.Client.Model.ServerThread.IServerThread;
import sample.Client.Model.ServerThread.ServerThread;
import sample.Data.DataInterface.*;
import sample.Data.GameData;
import sample.Data.UserData;

import java.net.Socket;
import java.util.ArrayList;

public class ClientFactory {

    public static ISpriteData SpriteDataCreateInstance(double PosX, double PosY, double rotation, sample.Server.Model.ImageDate.IImageDate image){
        return new GameData.SpriteData(PosX,PosY,rotation,image);
    }

    public static IProjectileData ProjectileDataCreateInstance(double PosX, double PosY, double rotation, sample.Server.Model.ImageDate.IImageDate image){
        return new GameData.ProjectileData(PosX,PosY,rotation,image);
    }

    public static IAimData AimDataCreateInstance(double PosX, double PosY, double rotation, sample.Server.Model.ImageDate.IImageDate image){
        return new GameData.AimData(PosX,PosY,rotation,image);
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
        return new ImageDate(fileImageName,sizeImage);
    }

    public static IImageDate ImageDateCreateInstance(String fileImageName, double Width, double Height) {
        return new ImageDate(fileImageName,Width,Height);
    }

    public static IImageDate ImageDateCreateInstance(String fileImageName) {
        return new ImageDate(fileImageName);
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
}
