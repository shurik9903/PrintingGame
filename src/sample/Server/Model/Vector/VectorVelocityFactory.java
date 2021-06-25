package sample.Server.Model.Vector;

public class VectorVelocityFactory {

    public static IVecVel CreateInstance() {
        return new VecVel();
    }

    public static IVecVel CreateInstance(double x, double y) {
        return new VecVel(x,y);
    }

}
