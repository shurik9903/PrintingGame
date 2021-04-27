package sample.Model;

//Класс настройки игры: Содержит описание запущенной игры
public class GameOptions{
    public int Difficulty;
    public int MeteorsCount;
    public double gameTime;
    public double Score;
    public int Life;

    public GameOptions(int Difficulty, int MeteorsCount){
        this.Difficulty = Difficulty;
        this.MeteorsCount = MeteorsCount * Difficulty;
        gameTime = 0;
        Score = 0;
        Life = 3;
    }
}
