package sample.Server.Model.Sprite;

public class SpriteFactory {

    public static ISprite CreateInstance(){
        return new Sprite();
    }

    public static ISprite CreateInstance(String fileImageName, double sizeImage) {
        return new Sprite(fileImageName,sizeImage);
    }

    public static ISprite CreateInstance(String fileImageName) {
        return new Sprite(fileImageName);
    }

    public static ISprite CreateInstance(String fileImageName, double Width, double Height) {
        return new Sprite(fileImageName, Width, Height);
    }

}
