package sample.Server.Model.Rectangle;

public interface IRectangle {

    void setPosition(double x, double y);
    void setSize(double width, double height);
    boolean overlaps(Rectangle other);
}
