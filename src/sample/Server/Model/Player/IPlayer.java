package sample.Server.Model.Player;

import sample.Data.DataInterface.IGameData;
import sample.Server.Model.Aim.IAim;
import sample.Server.Model.EnterToNumber.IEnterToNumber;
import sample.Server.Model.Projectile.IProjectile;
import sample.Server.Model.Sprite.ISprite;

import java.util.ArrayList;

public interface IPlayer {
    void setGameData(IGameData gameData);

    ArrayList<IProjectile> getProjectileList();

    void setKeyList(ArrayList<String> keyList);

    ArrayList<String> getKeyList();

    int getEnergy();

    IEnterToNumber getEnterNumber();

    void AddEnergy();

    void SubEnergy();

    IAim getPlayerAim();

    IGameData getGameData();

    ISprite getBasic();
}
