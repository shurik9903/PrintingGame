package sample.Server.Model.Vector;

public interface IVector {

    double getX();
    double getY();
    void setX(double x);
    void setY(double y);

    void set(double x, double y);
    void add(double x, double y);
    void multiply(double m);
    double getLength();
    double getLength(double x, double y);
    void setLength(double L);
    void setAngle(double angleDegrees);
    double getAngle2Vectors(double x1, double y1, double x2, double y2);
}
