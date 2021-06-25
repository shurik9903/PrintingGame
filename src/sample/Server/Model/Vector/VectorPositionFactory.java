package sample.Server.Model.Vector;

public class VectorPositionFactory {

    public static IVecPos CreateInstance() {
        return new VecPos();
    }

    public static IVecPos CreateInstance(double x, double y) {
        return new VecPos(x,y);
    }
}
