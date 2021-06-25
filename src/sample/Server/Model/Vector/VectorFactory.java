package sample.Server.Model.Vector;

public class VectorFactory {

    public static IVector CreateInstance() {
        return new Vector();
    }

    public static IVector CreateInstance(double x, double y) {
        return new Vector(x,y);
    }

}
