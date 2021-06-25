package sample.Server.Model.GameOptions;

public class GameOptionsFactory {

    public static IGameOptions CreateInstance(int Difficulty, int MeteorsCount, double GamePanelSizeX, double GamePanelSizeY){
        return new GameOptions(Difficulty, MeteorsCount, GamePanelSizeX, GamePanelSizeY);
    }

}
