package sample.Server.Model.Player;

import sample.GameData;
import sample.Server.Model.Aim.Aim;
import sample.Server.Model.EnterToNumber.EnterToNumber;
import sample.Server.Model.Projectile.Projectile;
import sample.Server.Model.Sprite.Sprite;

import java.util.ArrayList;

//Класс орудие игрока: Содержит описание оружия
public class Player extends Sprite {

    private int Energy;
    private final Aim PlayerAim;
    private final EnterToNumber EnterNumber;
    private ArrayList<String> KeyList;
    private final ArrayList<Projectile> ProjectileList;
    private GameData gameData;

    public Player(double Size, double WindowSizeX, double WindowSizeY){
        super("Image/Gun.png", Size);
        gameData = new GameData();
        Energy = 10;
        position.x = WindowSizeX;
        position.y = WindowSizeY;
        boundary.setSize(Size, Size);
        EnterNumber = new EnterToNumber(this, 30, 30);
        PlayerAim = new Aim(WindowSizeX, WindowSizeY, position.y);
        KeyList = new ArrayList<>();
        ProjectileList = new ArrayList<>();
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public ArrayList<Projectile> getProjectileList() {
        return ProjectileList;
    }

    public void setKeyList(ArrayList<String> keyList) {
        this.KeyList.addAll(keyList);
    }

    public ArrayList<String> getKeyList() {
        return KeyList;
    }

    public int getEnergy() {
        return Energy;
    }

    public EnterToNumber getEnterNumber() {
        return EnterNumber;
    }

    public void AddEnergy(){
        if (!(Energy + 1 > 10)) Energy++;
    }

    public void SubEnergy(){
        if (Energy - 1 != -1) Energy--;
    }

    public Aim getPlayerAim() {
        return PlayerAim;
    }

    public GameData getGameData(){
        return gameData;
    }

}
