package sample.Server.Model.MyImage;

public class MyImageFactory {

    public static IMyImage CreateInstance(String fileImageName, double Width, double Height, String ImageName, boolean Type) {
        return new MyImage(fileImageName,Width,Height,ImageName,Type);
    }


}
