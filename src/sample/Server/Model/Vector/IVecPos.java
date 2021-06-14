package sample.Server.Model.Vector;

public interface IVecPos {

    double getPosX();
    double getPosY();
    void setPosX(double x);
    void setPosY(double y);

    void PosSet(double x, double y);
    void PosAdd(double x, double y);
    void PosMultiply(double m);
    double getPosLength();
    double getPosLength(double x, double y);
    void setPosLength(double L);
    void setPosAngle(double angleDegrees);
    double getPosAngle2Vectors(double x1, double y1, double x2, double y2);

}
