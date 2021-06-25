package sample.Server.Model.EnterToNumber;

public class EnterToNumberFactory {

    public static IEnterToNumber CreateInstance(double Width, double Height,double PosX, double PosY, double ImageWidth, double ImageHeight){
        return new EnterToNumber( Width,  Height, PosX,  PosY,  ImageWidth,  ImageHeight);
    }

}
