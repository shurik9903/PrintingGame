package sample.Data.DataInterface;

import sample.Data.GameData;

import java.util.ArrayList;

public interface IGameData {

    IEnterToNumberData getEnterToNumberData();

    int getUserScore();

    ArrayList<ISpriteData> getPlayersGun();

    boolean isGameProcess();

    void setGameProcess(boolean gameProcess);

    ArrayList<IMeteorData> getMeteorList();

    ArrayList<IProjectileData> getProjectileList();

    ArrayList<IAimData> getAim();
}
