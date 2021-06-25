package sample.Server.Model.Vector;

import sample.Server.Model.ServerFactory.ServerFactory;

public class VecVel implements IVecVel{

    private final IVector IV;

    public VecVel() {
        IV = VectorFactory.CreateInstance();
        this.VelSet(0,0);

    }

    public VecVel(double x, double y) {
        IV = VectorFactory.CreateInstance();
        this.VelSet(x, y);
    }


    @Override
    public double getVelX() {
        return IV.getX();
    }

    @Override
    public double getVelY() {
        return IV.getY();
    }

    @Override
    public void setVelX(double x) {
        IV.setX(x);
    }

    @Override
    public void setVelY(double y) {
        IV.setY(y);
    }

    @Override
    public void VelSet(double x, double y) {
        IV.set(x,y);
    }

    @Override
    public void VelAdd(double x, double y) {
        IV.add(x,y);
    }

    @Override
    public void VelMultiply(double m) {
        IV.multiply(m);
    }

    @Override
    public double getVelLength() {
        return IV.getLength();
    }

    @Override
    public double getVelLength(double x, double y) {
        return IV.getLength(x, y);
    }

    @Override
    public void setVelLength(double L) {
        IV.setLength(L);
    }

    @Override
    public void setVelAngle(double angleDegrees) {
        IV.setAngle(angleDegrees);
    }

    @Override
    public double getVelAngle2Vectors(double x1, double y1, double x2, double y2) {
        return IV.getAngle2Vectors(x1,y1,x2,y2);
    }
}
