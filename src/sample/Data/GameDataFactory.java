package sample.Data;

import sample.Data.DataInterface.*;
import sample.Server.Model.MyImage.IMyImage;

import java.util.ArrayList;

public class GameDataFactory {

    public static IEnterToNumberData EnterToNumberDataCreateInstance(double PosX, double PosY, double Size, ArrayList<IImageDate> FrameNumber){
        return new GameData.EnterToNumberData(PosX,PosY,Size,FrameNumber);
    }

    public static IAimData AimDataCreateInstance(double PosX, double PosY, double rotation, double ImageDataWidth, double ImageDataHeight, String file){
        return new GameData.AimData(PosX,  PosY,  rotation, ImageDataWidth,  ImageDataHeight,  file);
    }

    public static IProjectileData ProjectileDataCreateInstance(double PosX, double PosY, double rotation, double ImageDataWidth, double ImageDataHeight, String file){
        return new GameData.ProjectileData(PosX,  PosY,  rotation, ImageDataWidth,  ImageDataHeight,  file);
    }

    public static ITextData TextDataCreateInstance(double x, double y, double Width, double Height, int TextLength,
                                                   ArrayList<IImageDate> FrameImage, ArrayList<IImageDate> FrameNumber,
                                                   ArrayList<ArrayList<IMyImage>> FrameText){
        return new GameData.TextData(x,y,Width,Height,TextLength,FrameImage,FrameNumber, FrameText);
    }

    public static IMeteorData MeteorDataCreateInstance(double PosX, double PosY, double rotation,double ImageDataWidth, double ImageDataHeight, String file, ITextData Text){
        return new GameData.MeteorData(PosX,  PosY,  rotation, ImageDataWidth,  ImageDataHeight,  file,Text);
    }

    public static ISpriteData SpriteDataCreateInstance(double PosX, double PosY, double rotation,double ImageDataWidth, double ImageDataHeight, String file){
        return new GameData.SpriteData( PosX,  PosY,  rotation, ImageDataWidth,  ImageDataHeight,  file);
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

    public static IImageDate ImageDateCreateInstance(String fileImageName, double sizeImage) {
        return new GameData.ImageDate(fileImageName,sizeImage);
    }

    public static IImageDate ImageDateCreateInstance(String fileImageName, double Width, double Height) {
        return new GameData.ImageDate(fileImageName,Width,Height);

    }

    public static IImageDate ImageDateCreateInstance(String fileImageName) {
        return new GameData.ImageDate(fileImageName);
    }


}
