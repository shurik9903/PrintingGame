package sample.Server.Model.GameOptions;

import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.Meteor.Meteor;
import sample.Server.Model.Meteor.MeteorFactory;
import sample.Server.Model.MyFunction.MyFunction;
import sample.Server.Model.MyFunction.MyFunctionFactory;
import sample.Server.Model.ServerFactory.ServerFactory;

import java.util.ArrayList;

//Класс настройки игры: Содержит описание и инициализацию всех игровых объектов
public class GameOptions implements IGameOptions{
    private final int Difficulty;
    private final int MeteorsCount;
    private int MeteorsSpawn;
    private double TotalTime;
    private double Period;
    private int Score;
    private final int OldScore;
    private int Life;
    private final double GamePanelSizeX, GamePanelSizeY;
    private boolean GameStop;

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

    @Override
    public void setPeriod(){
        Period = MyFunctionFactory.CreateInstance().getRandomNumber(3 - Difficulty * 2, 7 - Difficulty * 2);
    }

    @Override
    public boolean MeteorSpawnCheck(){
        if (TotalTime > Period) {
            TotalTime = 0;
            setPeriod();
            return true;
        }
        return false;
    }

    @Override
    public void GameProcess(ArrayList<IMeteor> MeteorList){
        TotalTime += 1./100;
        if (MeteorSpawnCheck() && MeteorsSpawn < MeteorsCount) {
            MeteorList.add(MeteorFactory.CreateInstance(GamePanelSizeY, GamePanelSizeX, MyFunctionFactory.CreateInstance().setID(MeteorList),
                    MyFunctionFactory.CreateInstance().getRandomNumber(Difficulty * 10, 30 + Difficulty * 20)));
            MeteorsSpawn++;
        }
    }

    @Override
    public int getLife() {
        return Life;
    }

    @Override
    public void setLife(int Life){
        this.Life = Life;
    }

    @Override
    public void setScore(int Score){
        this.Score = Score;
    }

    @Override
    public int getScore() {
        return Score;
    }

    @Override
    public int getMeteorsCount() {
        return MeteorsCount;
    }

    @Override
    public int getMeteorsSpawn() {
        return MeteorsSpawn;
    }

    @Override
    public boolean getGameStop(){
        return GameStop;
    }

    @Override
    public void setGameStop(boolean GameStop){
        this.GameStop = GameStop;
    }

    @Override
    public int getDifficulty() {
        return Difficulty;
    }
}
