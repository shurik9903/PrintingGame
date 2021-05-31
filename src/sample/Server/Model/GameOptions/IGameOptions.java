package sample.Server.Model.GameOptions;

import sample.Server.Model.Meteor.IMeteor;

import java.util.ArrayList;

public interface IGameOptions {
    void setPeriod();

    boolean MeteorSpawnCheck();
    void GameProcess(ArrayList<IMeteor> MeteorList);

    int getLife();

    void setLife(int Life);

    void setScore(int Score);

    int getScore();

    int getMeteorsCount();

    int getMeteorsSpawn();

    boolean getGameStop();

    void setGameStop(boolean GameStop);

    int getDifficulty();
}
