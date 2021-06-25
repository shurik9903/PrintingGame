package sample.Server.Model.Player;

public class PlayerFactory {

    public static IPlayer CreateInstance(double Size, double WindowSizeX, double WindowSizeY){
        return new Player(Size,WindowSizeX,WindowSizeY);
    }

}
