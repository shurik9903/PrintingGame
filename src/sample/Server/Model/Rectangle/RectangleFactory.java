package sample.Server.Model.Rectangle;

public class RectangleFactory {

    public static IRectangle CreateInstance() {
        return new Rectangle();
    }

    public static IRectangle CreateInstance(double x, double y, double width, double height){
        return new Rectangle(x,y,width,height);
    }

}
