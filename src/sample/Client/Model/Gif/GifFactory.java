package sample.Client.Model.Gif;

import javafx.scene.image.ImageView;

public class GifFactory {


    public static IGif CreateInstance(String Directory, double Size, int FPS){
        return new Gif(Directory,Size,FPS);
    }

    public static IGif CreateInstance(String Directory, ImageView imageview, int FPS){
        return new Gif(Directory,imageview,FPS);
    }

    public static IGif CreateInstance(String Name, double Size, int FPS, double x, double y){
        return new Gif(Name,Size,FPS,x,y);
    }
}
