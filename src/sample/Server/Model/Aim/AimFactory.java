package sample.Server.Model.Aim;

public class AimFactory {

    public static IAim CreateInstance(double BoxWidth, double BoxHeight, double RestrictedArea){
        return new Aim(BoxWidth,BoxHeight,RestrictedArea);
    }

}
