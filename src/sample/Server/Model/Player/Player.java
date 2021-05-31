package sample.Server.Model.Player;

import sample.Data.DataInterface.IGameData;
import sample.Server.Model.Aim.IAim;
import sample.Server.Model.EnterToNumber.IEnterToNumber;
import sample.Server.Model.Projectile.IProjectile;
import sample.Server.Model.ServerFactory.ServerFactory;
import sample.Server.Model.Sprite.ISprite;

import java.util.ArrayList;

//Класс орудие игрока: Содержит описание оружия
public class Player implements IPlayer {

    private ISprite basic;
    private int Energy;
    private final IAim PlayerAim;
    private final IEnterToNumber EnterNumber;
    private ArrayList<String> KeyList;
    private final ArrayList<IProjectile> ProjectileList;
    private IGameData gameData;

    public Player(double Size, double WindowSizeX, double WindowSizeY){
        basic = ServerFactory.SpriteCreateInstance("Image/Gun.png", Size);
        gameData = ServerFactory.GameDataCreateInstance();
        Energy = 10;
        basic.getPosition().setX(WindowSizeX);
        basic.getPosition().setY(WindowSizeY);
        basic.getBoundary().setSize(Size, Size);
        EnterNumber = ServerFactory.EnterToNumberCreateInstance((IPlayer) this, 30, 30);
        PlayerAim = ServerFactory.AimCreateInstance(WindowSizeX, WindowSizeY, basic.getPosition().getY());
        KeyList = new ArrayList<>();
        ProjectileList = new ArrayList<>();
    }

    @Override
    public void setGameData(IGameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public ArrayList<IProjectile> getProjectileList() {
        return ProjectileList;
    }

    @Override
    public void setKeyList(ArrayList<String> keyList) {
        this.KeyList.addAll(keyList);
    }

    @Override
    public ArrayList<String> getKeyList() {
        return KeyList;
    }

    @Override
    public int getEnergy() {
        return Energy;
    }

    @Override
    public IEnterToNumber getEnterNumber() {
        return EnterNumber;
    }

    @Override
    public void AddEnergy(){
        if (!(Energy + 1 > 10)) Energy++;
    }

    @Override
    public void SubEnergy(){
        if (Energy - 1 != -1) Energy--;
    }

    @Override
    public IAim getPlayerAim() {
        return PlayerAim;
    }

    @Override
    public IGameData getGameData(){
        return gameData;
    }

    @Override
    public ISprite getBasic() {
        return basic;
    }
}
