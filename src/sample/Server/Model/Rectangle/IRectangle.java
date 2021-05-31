package sample.Server.Model.Rectangle;

public interface IRectangle {

    double getX();

    double getY();

    double getWidth();

    double getHeight();

    void setPosition(double x, double y);
    void setSize(double width, double height);
    boolean overlaps(IRectangle other);
}
