package sample.Server.Model.Vector;

import sample.Server.Model.ServerFactory.ServerFactory;

public class VecPos implements IVecPos {
    private final IVector IV;

    public VecPos() {
        IV = VectorFactory.CreateInstance();
        this.PosSet(0,0);

    }

    public VecPos(double x, double y) {
        IV = VectorFactory.CreateInstance();
        this.PosSet(x, y);
    }


    @Override
    public double getPosX() {
        return IV.getX();
    }

    @Override
    public double getPosY() {
        return IV.getY();
    }

    @Override
    public void setPosX(double x) {
        IV.setX(x);
    }

    @Override
    public void setPosY(double y) {
        IV.setY(y);
    }

    @Override
    public void PosSet(double x, double y) {
        IV.set(x,y);
    }

    @Override
    public void PosAdd(double x, double y) {
        IV.add(x,y);
    }

    @Override
    public void PosMultiply(double m) {
        IV.multiply(m);
    }

    @Override
    public double getPosLength() {
        return IV.getLength();
    }

    @Override
    public double getPosLength(double x, double y) {
        return IV.getLength(x, y);
    }

    @Override
    public void setPosLength(double L) {
        IV.setLength(L);
    }

    @Override
    public void setPosAngle(double angleDegrees) {
        IV.setAngle(angleDegrees);
    }

    @Override
    public double getPosAngle2Vectors(double x1, double y1, double x2, double y2) {
        return IV.getAngle2Vectors(x1,y1,x2,y2);
    }
}
