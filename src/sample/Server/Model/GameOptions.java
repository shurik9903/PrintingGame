package sample.Server.Model;

import java.util.ArrayList;

//Класс настройки игры: Содержит описание и инициализацию всех игровых объектов
public class GameOptions{
    public int Difficulty;
    public int MeteorsCount, MeteorsSpawn;
    public double TotalTime;
    public double Period;
    public int Score, OldScore;
    public int Life;
    public final double GamePanelSizeX, GamePanelSizeY;
    public boolean GameStop;

    //Конструктор
    public GameOptions(int Difficulty, int MeteorsCount, double GamePanelSizeX, double GamePanelSizeY){
        this.Difficulty = Difficulty;
        this.MeteorsCount = MeteorsCount * Difficulty;
        TotalTime = 0;
        MeteorsSpawn = 0;
        GameStop = false;
        this.GamePanelSizeX = GamePanelSizeX;
        this.GamePanelSizeY = GamePanelSizeY;
        setPeriod();
        Score = 0;
        OldScore = -1;
        Life = 3;
    }

    private void setPeriod(){
        Period = new MyFunction().getRandomNumber(7 - Difficulty * 2, 12 - Difficulty * 2);
    }

    private boolean MeteorSpawnCheck(){
        if (TotalTime > Period) {
            TotalTime = 0;
            setPeriod();
            return true;
        }
        return false;
    }

    public void GameProcess(ArrayList<Meteor> MeteorList){
        TotalTime += 1./100;
        if (MeteorSpawnCheck() && MeteorsSpawn < MeteorsCount) {
            MeteorList.add(new Meteor(GamePanelSizeY, GamePanelSizeX, new MyFunction().setID(MeteorList),
                    new MyFunction().getRandomNumber(Difficulty * 10, 30 + Difficulty * 20)));
            MeteorsSpawn++;
        }
    }


}
