package sample.Server.Model.Vector;

public interface IVecVel {

    double getVelX();
    double getVelY();
    void setVelX(double x);
    void setVelY(double y);

    void VelSet(double x, double y);
    void VelAdd(double x, double y);
    void VelMultiply(double m);
    double getVelLength();
    double getVelLength(double x, double y);
    void setVelLength(double L);
    void setVelAngle(double angleDegrees);
    double getVelAngle2Vectors(double x1, double y1, double x2, double y2);

}
