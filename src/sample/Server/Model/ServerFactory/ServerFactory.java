package sample.Server.Model.ServerFactory;

import sample.Data.*;
import sample.Data.DataInterface.*;
import sample.Server.Model.Aim.Aim;
import sample.Server.Model.Aim.IAim;
import sample.Server.Model.EnterToNumber.EnterToNumber;
import sample.Server.Model.EnterToNumber.IEnterToNumber;
import sample.Server.Model.GameOptions.GameOptions;
import sample.Server.Model.GameOptions.IGameOptions;
import sample.Server.Model.IModel;
import sample.Server.Model.ImageDate.IImageDate;
import sample.Server.Model.ImageDate.ImageDate;
import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.Meteor.Meteor;
import sample.Server.Model.MyFunction.IMyFunction;
import sample.Server.Model.MyFunction.MyFunction;
import sample.Server.Model.MyImage.IMyImage;
import sample.Server.Model.MyImage.MyImage;
import sample.Server.Model.Player.IPlayer;
import sample.Server.Model.Player.Player;
import sample.Server.Model.Projectile.IProjectile;
import sample.Server.Model.Projectile.Projectile;
import sample.Server.Model.Rectangle.IRectangle;
import sample.Server.Model.Rectangle.Rectangle;
import sample.Server.Model.Sprite.ISprite;
import sample.Server.Model.Sprite.Sprite;
import sample.Server.Model.TextToObject.ITextToObject;
import sample.Server.Model.TextToObject.TextToObject;
import sample.Server.Model.UserConnect.IUserConnect;
import sample.Server.Model.UserConnect.UserConnect;
import sample.Server.Model.Vector.IVector;
import sample.Server.Model.Vector.Vector;

import java.net.Socket;
import java.util.ArrayList;

public class ServerFactory{

    public static IEnterToNumberData EnterToNumberDataCreateInstance(double PosX, double PosY, double Size, ArrayList<IImageDate> FrameNumber){
        return new GameData.EnterToNumberData(PosX,PosY,Size,FrameNumber);
    }

    public static IAimData AimDataCreateInstance(double PosX, double PosY, double rotation,IImageDate image){
        return new GameData.AimData(PosX,PosY,rotation,image);
    }

    public static IProjectileData ProjectileDataCreateInstance(double PosX, double PosY, double rotation, IImageDate image){
        return new GameData.ProjectileData(PosX,PosY,rotation,image);
    }

    public static ITextData TextDataCreateInstance(double x, double y, double Width, double Height,int TextLength,
                                                   ArrayList<IImageDate> FrameImage, ArrayList<IImageDate> FrameNumber,
                                                   ArrayList<ArrayList<IMyImage>> FrameText){
        return new GameData.TextData(x,y,Width,Height,TextLength,FrameImage,FrameNumber, FrameText);
    }

    public static IMeteorData MeteorDataCreateInstance(double PosX, double PosY, double rotation, IImageDate image, ITextData Text){
        return new GameData.MeteorData(PosX,PosY,rotation,image,Text);
    }

    public static IProjectile ProjectileCreateInstance(IPlayer Player, char Letter, double BoxHeight, double BoxWidth, IMeteor meteor){
        return new Projectile(Player,Letter,BoxHeight,BoxWidth,meteor);
    }

    public static IPlayer PlayerCreateInstance(double Size, double WindowSizeX, double WindowSizeY){
        return new Player(Size,WindowSizeX,WindowSizeY);
    }

    public static IMeteor MeteorCreateInstance(double BoxHeight, double BoxWidth, int ID, int speed){
        return new Meteor(BoxHeight,BoxWidth,ID,speed);
    }

    public static IGameOptions GameOptionsCreateInstance(int Difficulty, int MeteorsCount, double GamePanelSizeX, double GamePanelSizeY){
        return new GameOptions(Difficulty, MeteorsCount, GamePanelSizeX, GamePanelSizeY);
    }

    public static IUserConnect UserConnectCreateInstance(String Name, Socket User, IModel GameModel){
        return new UserConnect(Name, User, GameModel);
    }

    public static ISpriteData SpriteDataCreateInstance(double PosX, double PosY, double rotation, IImageDate image){
        return new GameData.SpriteData(PosX,PosY,rotation,image);
    }

    public static IUserData UserDataCreateInstance(String UserName, ArrayList<String> KeyList){
        return new UserData(UserName,KeyList);
    }

    public static IUserData UserDataCreateInstance(){
        return new UserData();
    }

    public static IEnterToNumber EnterToNumberCreateInstance(IPlayer player, double Width, double Height){
        return new EnterToNumber(player,Width,Height);
    }

    public static IGameData GameDataCreateInstance(int UserScore, java.util.ArrayList<ISpriteData> PlayersGun, boolean GameProcess, ArrayList<IMeteorData> MeteorList, ArrayList<IProjectileData> ProjectileList, ArrayList<IAimData> PlayersAim, IEnterToNumberData enterToNumberData){
        return new GameData(UserScore, PlayersGun, GameProcess, MeteorList, ProjectileList, PlayersAim, enterToNumberData);
    }

    public static IGameData GameDataCreateInstance(){
        return new GameData();
    }

    public static IMyFunction MyFunctionCreateInstance(){
        return new MyFunction();
    }

    public static IAim AimCreateInstance(double BoxWidth, double BoxHeight, double RestrictedArea){
        return new Aim(BoxWidth,BoxHeight,RestrictedArea);
    }

    public static ITextToObject TextToObjectCreateInstance(double x, double y, double Width, double Height, String Text, int ID) {
        return new TextToObject(x,y,Width,Height,Text,ID);
    }

    public static IMyImage MyImageCreateInstance(String fileImageName, double Width, double Height, String ImageName, boolean Type) {
        return new MyImage(fileImageName,Width,Height,ImageName,Type);
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

    public static IRectangle RectangleCreateInstance() {
        return new Rectangle();
    }

    public static IRectangle RectangleCreateInstance(double x, double y, double width, double height){
        return new Rectangle(x,y,width,height);
    }

    public static ISprite SpriteCreateInstance(){
        return new Sprite();
    }

    public static ISprite SpriteCreateInstance(String fileImageName, double sizeImage) {
        return new Sprite(fileImageName,sizeImage);
    }

    public static ISprite SpriteCreateInstance(String fileImageName) {
        return new Sprite(fileImageName);
    }

    public static ISprite SpriteCreateInstance(String fileImageName, double Width, double Height) {
        return new Sprite(fileImageName, Width, Height);
    }

    public static IVector VectorCreateInstance() {
        return new Vector();
    }

    public static IVector VectorCreateInstance(double x, double y) {
        return new Vector(x,y);
    }
}
