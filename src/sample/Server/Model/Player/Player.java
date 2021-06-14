package sample.Server.Model.Player;

import javafx.scene.image.Image;
import sample.Data.DataInterface.IGameData;
import sample.Data.GameData;
import sample.Server.Model.Aim.IAim;
import sample.Server.Model.EnterToNumber.IEnterToNumber;
import sample.Server.Model.ImageDate.IImageDate;
import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.Projectile.IProjectile;
import sample.Server.Model.ServerFactory.ServerFactory;
import sample.Server.Model.Sprite.ISprite;

import java.util.ArrayList;

//Класс орудие игрока: Содержит описание оружия
public class Player implements IPlayer, ISprite, IEnterToNumber, IPlayerAim  {

    private final ISprite basic;
    private int Energy;
    private final IPlayerAim playerAim;
    private final IEnterToNumber EnterNumber;
    private final ArrayList<String> KeyList;
    private final ArrayList<IProjectile> ProjectileList;
    private IGameData gameData;

    public Player(double Size, double WindowSizeX, double WindowSizeY){
        basic = ServerFactory.SpriteCreateInstance("Image/Gun.png", Size);
        gameData = ServerFactory.GameDataCreateInstance();
        Energy = 10;
        setPosX(WindowSizeX);
        setPosY(WindowSizeY);
        setRecSize(Size, Size);
        EnterNumber = ServerFactory.EnterToNumberCreateInstance(
                30, 30, getPosX(),getPosY(),getImageWidth(),getImageHeight());
        playerAim = ServerFactory.PlayerAimCreateInstance(WindowSizeX, WindowSizeY, getPosY());
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
    public void AddEnergy(){
        if (!(Energy + 1 > 10)) Energy++;
    }

    @Override
    public void SubEnergy(){
        if (Energy - 1 != -1) Energy--;
    }

    @Override
    public IGameData getGameData() {
        return gameData;
    }


    @Override
    public double getImageWidth() {
        return basic.getImageWidth();
    }

    @Override
    public double getImageHeight() {
        return basic.getImageHeight();
    }

    @Override
    public String getFileImageName() {
        return basic.getFileImageName();
    }

    @Override
    public double getRecX() {
        return basic.getRecX();
    }

    @Override
    public double getRecY() {
        return basic.getRecY();
    }

    @Override
    public double getRecWidth() {
        return basic.getRecWidth();
    }

    @Override
    public double getRecHeight() {
        return basic.getRecHeight();
    }

    @Override
    public void setRecPosition(double x, double y) {
        basic.setRecPosition(x, y);
    }

    @Override
    public void setRecSize(double width, double height) {
        basic.setRecSize(width, height);
    }

    @Override
    public boolean RecOverlaps(ISprite other) {
        return basic.RecOverlaps(other);
    }

    @Override
    public double getPosX() {
        return basic.getPosX();
    }

    @Override
    public double getPosY() {
        return basic.getPosY();
    }

    @Override
    public void setPosX(double x) {
        basic.setPosX(x);
    }

    @Override
    public void setPosY(double y) {
        basic.setPosY(y);
    }

    @Override
    public void PosSet(double x, double y) {
        basic.PosSet(x, y);
    }

    @Override
    public void PosAdd(double x, double y) {
        basic.PosAdd(x, y);
    }

    @Override
    public void PosMultiply(double m) {
        basic.PosMultiply(m);
    }

    @Override
    public double getPosLength() {
        return basic.getPosLength();
    }

    @Override
    public double getPosLength(double x, double y) {
        return basic.getPosLength(x, y);
    }

    @Override
    public void setPosLength(double L) {
        basic.setPosLength(L);
    }

    @Override
    public void setPosAngle(double angleDegrees) {
        basic.setPosAngle(angleDegrees);
    }

    @Override
    public double getPosAngle2Vectors(double x1, double y1, double x2, double y2) {
        return basic.getPosAngle2Vectors(x1, y1, x2, y2);
    }

    @Override
    public double getVelX() {
        return basic.getVelX();
    }

    @Override
    public double getVelY() {
        return basic.getVelY();
    }

    @Override
    public void setVelX(double x) {
        basic.setVelX(x);
    }

    @Override
    public void setVelY(double y) {
        basic.setVelY(y);
    }

    @Override
    public void VelSet(double x, double y) {
        basic.VelSet(x, y);
    }

    @Override
    public void VelAdd(double x, double y) {
        basic.VelAdd(x, y);
    }

    @Override
    public void VelMultiply(double m) {
        basic.VelMultiply(m);
    }

    @Override
    public double getVelLength() {
        return basic.getVelLength();
    }

    @Override
    public double getVelLength(double x, double y) {
        return basic.getVelLength(x,y);
    }

    @Override
    public void setVelLength(double L) {
        basic.setVelLength(L);
    }

    @Override
    public void setVelAngle(double angleDegrees) {
        basic.setVelAngle(angleDegrees);
    }

    @Override
    public double getVelAngle2Vectors(double x1, double y1, double x2, double y2) {
        return basic.getVelAngle2Vectors(x1, y1, x2, y2);
    }

    @Override
    public void setImage(String fileImageName, double sizeImage) {
        basic.setImage(fileImageName,sizeImage);
    }

    @Override
    public void setImage(String fileImageName, double Width, double Height) {
        basic.setImage(fileImageName, Width, Height);
    }

    @Override
    public void setImage(String fileImageName) {
        basic.setImage(fileImageName);
    }

    @Override
    public boolean overlaps(ISprite other) {
        return basic.overlaps(other);
    }

    @Override
    public double getAngleToTarget(ISprite other) {
        return basic.getAngleToTarget(other);
    }

    @Override
    public void PlayerAimToMeteor(ArrayList<IMeteor> meteor, boolean LR) {
        playerAim.PlayerAimToMeteor(meteor,LR);
    }

    @Override
    public void PlayerAimToMeteor(ArrayList<IMeteor> meteor, int Numb) {
        playerAim.PlayerAimToMeteor(meteor,Numb);
    }

    @Override
    public void PlayerAimMoveToTarget() {
        playerAim.PlayerAimMoveToTarget();
    }

    @Override
    public void setPlayerAimImage(String fileImageName, double sizeImage) {
        playerAim.setPlayerAimImage(fileImageName,sizeImage);
    }

    @Override
    public void setPlayerAimImage(String fileImageName, double Width, double Height) {
        playerAim.setPlayerAimImage(fileImageName, Width, Height);
    }

    @Override
    public void setPlayerAimImage(String fileImageName) {
        playerAim.setPlayerAimImage(fileImageName);
    }

    @Override
    public boolean PlayerAimOverlaps(ISprite other) {
        return playerAim.PlayerAimOverlaps(other);
    }

    @Override
    public double getPlayerAimAngleToTarget(ISprite other) {
        return playerAim.getPlayerAimAngleToTarget(other);
    }

    @Override
    public void update(double deltaTime) {
        basic.update(deltaTime);
        playerAim.update(deltaTime);
    }

    @Override
    public double getPlayerAimRotation() {
        return playerAim.getPlayerAimRotation();
    }

    @Override
    public void setPlayerAimRotation(double rotation) {
        playerAim.setPlayerAimRotation(rotation);
    }

    @Override
    public IMeteor getPlayerAimTargetMeteor() {
        return playerAim.getPlayerAimTargetMeteor();
    }

    @Override
    public boolean getPlayerAimTargetCaught() {
        return playerAim.getPlayerAimTargetCaught();
    }

    @Override
    public double getPlayerAimImageWidth() {
        return playerAim.getPlayerAimImageWidth();
    }

    @Override
    public double getPlayerAimImageHeight() {
        return playerAim.getPlayerAimImageHeight();
    }

    @Override
    public String getPlayerAimFileImageName() {
        return playerAim.getPlayerAimFileImageName();
    }

    @Override
    public double getPlayerAimRecX() {
        return playerAim.getPlayerAimRecX();
    }

    @Override
    public double getPlayerAimRecY() {
        return playerAim.getPlayerAimRecY();
    }

    @Override
    public double getPlayerAimRecWidth() {
        return playerAim.getPlayerAimRecWidth();
    }

    @Override
    public double getPlayerAimRecHeight() {
        return playerAim.getPlayerAimRecHeight();
    }

    @Override
    public void setPlayerAimRecPosition(double x, double y) {
        playerAim.setPlayerAimRecPosition(x, y);
    }

    @Override
    public void setPlayerAimRecSize(double width, double height) {
        playerAim.setPlayerAimRecSize(width,height);
    }

    @Override
    public boolean PlayerAimRecOverlaps(ISprite other) {
        return playerAim.PlayerAimRecOverlaps(other);
    }

    @Override
    public double getPlayerAimPosX() {
        return playerAim.getPlayerAimPosX();
    }

    @Override
    public double getPlayerAimPosY() {
        return playerAim.getPlayerAimPosY();
    }

    @Override
    public void setPlayerAimPosX(double x) {
        playerAim.setPlayerAimPosX(x);
    }

    @Override
    public void setPlayerAimPosY(double y) {
        playerAim.setPlayerAimPosY(y);
    }

    @Override
    public void PlayerAimPosSet(double x, double y) {
        playerAim.PlayerAimPosSet(x, y);
    }

    @Override
    public void PlayerAimPosAdd(double x, double y) {
        playerAim.PlayerAimPosAdd(x, y);
    }

    @Override
    public void PlayerAimPosMultiply(double m) {
        playerAim.PlayerAimPosMultiply(m);
    }

    @Override
    public double getPlayerAimPosLength() {
        return playerAim.getPlayerAimPosLength();
    }

    @Override
    public double getPlayerAimPosLength(double x, double y) {
        return playerAim.getPlayerAimPosLength(x,y);
    }

    @Override
    public void setPlayerAimPosLength(double L) {
        playerAim.setPlayerAimPosLength(L);
    }

    @Override
    public void setPlayerAimPosAngle(double angleDegrees) {
        playerAim.setPlayerAimPosAngle(angleDegrees);
    }

    @Override
    public double getPlayerAimPosAngle2Vectors(double x1, double y1, double x2, double y2) {
        return playerAim.getPlayerAimPosAngle2Vectors(x1, y1, x2, y2);
    }

    @Override
    public double getPlayerAimVelX() {
        return playerAim.getPlayerAimVelX();
    }

    @Override
    public double getPlayerAimVelY() {
        return playerAim.getPlayerAimVelY();
    }

    @Override
    public void setPlayerAimVelX(double x) {
        playerAim.setPlayerAimVelX(x);
    }

    @Override
    public void setPlayerAimVelY(double y) {
        playerAim.setPlayerAimVelY(y);
    }

    @Override
    public void PlayerAimVelSet(double x, double y) {
        playerAim.PlayerAimVelSet(x, y);
    }

    @Override
    public void PlayerAimVelAdd(double x, double y) {
        playerAim.PlayerAimVelAdd(x, y);
    }

    @Override
    public void PlayerAimVelMultiply(double m) {
        playerAim.PlayerAimVelMultiply(m);
    }

    @Override
    public double getPlayerAimVelLength() {
        return playerAim.getPlayerAimVelLength();
    }

    @Override
    public double getPlayerAimVelLength(double x, double y) {
        return playerAim.getPlayerAimVelLength(x,y);
    }

    @Override
    public void setPlayerAimVelLength(double L) {
        playerAim.PlayerAimVelMultiply(L);
    }

    @Override
    public void setPlayerAimVelAngle(double angleDegrees) {
        playerAim.setPlayerAimVelAngle(angleDegrees);
    }

    @Override
    public double getPlayerAimVelAngle2Vectors(double x1, double y1, double x2, double y2) {
        return playerAim.getPlayerAimVelAngle2Vectors(x1, y1, x2, y2);
    }

    @Override
    public double getRotation() {
        return basic.getRotation();
    }

    @Override
    public void setRotation(double rotation) {
        basic.setRotation(rotation);
    }

    @Override
    public void addNumber(Character c) {
        EnterNumber.addNumber(c);
    }

    @Override
    public int getNumbers() {
        return EnterNumber.getNumbers();
    }

    @Override
    public void initialize() {
        EnterNumber.initialize();
    }

    @Override
    public double getEntNumX() {
        return EnterNumber.getEntNumX();
    }

    @Override
    public void setEntNumX(double x) {
        EnterNumber.setEntNumX(x);
    }

    @Override
    public double getEntNumY() {
        return EnterNumber.getEntNumY();
    }

    @Override
    public void setEntNumY(double y) {
        EnterNumber.setEntNumY(y);
    }

    @Override
    public double getEntNumWidth() {
        return EnterNumber.getEntNumWidth();
    }

    @Override
    public double getEntNumHeight() {
        return EnterNumber.getEntNumHeight();
    }

    @Override
    public void setEntNumHeight(double height) {
        EnterNumber.setEntNumHeight(height);
    }

    @Override
    public void setEntNumWidth(double width) {
        EnterNumber.setEntNumWidth(width);
    }

    @Override
    public ArrayList<IImageDate> getFrameNumber() {
        return EnterNumber.getFrameNumber();
    }
}
