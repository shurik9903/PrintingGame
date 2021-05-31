package sample.Server.Model.ServerFactory;

import sample.Server.Model.ImageDate.IImageDate;
import sample.Server.Model.ImageDate.ImageDate;
import sample.Server.Model.MyImage.IMyImage;
import sample.Server.Model.MyImage.MyImage;
import sample.Server.Model.Rectangle.IRectangle;
import sample.Server.Model.Rectangle.Rectangle;
import sample.Server.Model.Sprite.ISprite;
import sample.Server.Model.Sprite.Sprite;
import sample.Server.Model.TextToObject.ITextToObject;
import sample.Server.Model.TextToObject.TextToObject;
import sample.Server.Model.Vector.IVector;
import sample.Server.Model.Vector.Vector;

public class ServerFactory{

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
        new Rectangle(x,y,width,height);
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
