package sample.Server.Model.Player;

public class PlayerAimFactory {

    public static IPlayerAim CreateInstance(double BoxWidth, double BoxHeight, double RestrictedArea){
        return new PlayerAim(BoxWidth,BoxHeight,RestrictedArea);
    }


}
