package sample.Server.Model.Player;

import javafx.scene.image.Image;
import sample.Server.Model.Aim.IAim;
import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.ServerFactory.ServerFactory;
import sample.Server.Model.Sprite.ISprite;

import java.util.ArrayList;

public class PlayerAim implements IPlayerAim{

    private final IAim aim;

    public PlayerAim(double BoxWidth, double BoxHeight, double RestrictedArea){
        aim = ServerFactory.AimCreateInstance(BoxWidth,BoxHeight,RestrictedArea);
    }

    @Override
    public void PlayerAimToMeteor(ArrayList<IMeteor> meteor, boolean LR) {
        aim.AimToMeteor(meteor,LR);
    }

    //Нацеливание на метеорит c указанным номером
    @Override
    public void PlayerAimToMeteor(ArrayList<IMeteor> meteor, int Numb) {
        aim.AimToMeteor(meteor, Numb);
    }

    //Движение к нацеленному метеориту
    @Override
    public void PlayerAimMoveToTarget() {
        aim.MoveToTarget();
    }

    @Override
    public void setPlayerAimImage(String fileImageName, double sizeImage) {
        aim.setImage(fileImageName, sizeImage);
    }

    @Override
    public void setPlayerAimImage(String fileImageName, double Width, double Height) {
        aim.setImage(fileImageName, Width, Height);
    }

    @Override
    public void setPlayerAimImage(String fileImageName) {
        aim.setImage(fileImageName);
    }

    @Override
    public boolean PlayerAimOverlaps(ISprite other) {
        return aim.overlaps(other);
    }

    @Override
    public double getPlayerAimAngleToTarget(ISprite other) {
        return aim.getAngleToTarget(other);
    }

    //Обновление объекта
    @Override
    public void update(double deltaTime) {
        aim.update(deltaTime);
    }

    @Override
    public double getPlayerAimRotation() {
        return aim.getRotation();
    }

    @Override
    public void setPlayerAimRotation(double rotation) {
        aim.setRotation(rotation);
    }

    @Override
    public IMeteor getPlayerAimTargetMeteor() {
        return aim.getTargetMeteor();
    }

    @Override
    public boolean getPlayerAimTargetCaught(){
        return aim.getTargetCaught();
    }

    @Override
    public double getPlayerAimImageWidth() {
        return aim.getImageWidth();
    }

    @Override
    public double getPlayerAimImageHeight() {
        return aim.getImageHeight();
    }

    @Override
    public String getPlayerAimFileImageName() {
        return aim.getFileImageName();
    }

    @Override
    public double getPlayerAimRecX() {
        return aim.getRecX();
    }

    @Override
    public double getPlayerAimRecY() {
        return aim.getRecY();
    }

    @Override
    public double getPlayerAimRecWidth() {
        return aim.getRecWidth();
    }

    @Override
    public double getPlayerAimRecHeight() {
        return aim.getRecHeight();
    }

    @Override
    public void setPlayerAimRecPosition(double x, double y) {
        aim.setRecPosition(x, y);
    }

    @Override
    public void setPlayerAimRecSize(double width, double height) {
        aim.setRecSize(width, height);
    }

    @Override
    public boolean PlayerAimRecOverlaps(ISprite other) {
        return aim.RecOverlaps(other);
    }

    @Override
    public double getPlayerAimPosX() {
        return aim.getPosX();
    }

    @Override
    public double getPlayerAimPosY() {
        return aim.getPosY();
    }

    @Override
    public void setPlayerAimPosX(double x) {
        aim.setPosX(x);
    }

    @Override
    public void setPlayerAimPosY(double y) {
        aim.setPosY(y);
    }

    @Override
    public void PlayerAimPosSet(double x, double y) {
        aim.PosSet(x, y);
    }

    @Override
    public void PlayerAimPosAdd(double x, double y) {
        aim.PosAdd(x, y);
    }

    @Override
    public void PlayerAimPosMultiply(double m) {
        aim.PosMultiply(m);
    }

    @Override
    public double getPlayerAimPosLength() {
        return aim.getPosLength();
    }

    @Override
    public double getPlayerAimPosLength(double x, double y) {
        return aim.getPosLength(x, y);
    }

    @Override
    public void setPlayerAimPosLength(double L) {
        aim.setPosLength(L);
    }

    @Override
    public void setPlayerAimPosAngle(double angleDegrees) {
        aim.setPosAngle(angleDegrees);
    }

    @Override
    public double getPlayerAimPosAngle2Vectors(double x1, double y1, double x2, double y2) {
        return aim.getPosAngle2Vectors(x1, y1, x2, y2);
    }

    @Override
    public double getPlayerAimVelX() {
        return aim.getVelX();
    }

    @Override
    public double getPlayerAimVelY() {
        return aim.getVelY();
    }

    @Override
    public void setPlayerAimVelX(double x) {
        aim.setVelX(x);
    }

    @Override
    public void setPlayerAimVelY(double y) {
        aim.setVelY(y);
    }

    @Override
    public void PlayerAimVelSet(double x, double y) {
        aim.VelSet(x, y);
    }

    @Override
    public void PlayerAimVelAdd(double x, double y) {
        aim.VelAdd(x, y);
    }

    @Override
    public void PlayerAimVelMultiply(double m) {
        aim.VelMultiply(m);
    }

    @Override
    public double getPlayerAimVelLength() {
        return aim.getVelLength();
    }

    @Override
    public double getPlayerAimVelLength(double x, double y) {
        return aim.getVelLength(x,y);
    }

    @Override
    public void setPlayerAimVelLength(double L) {
        aim.setVelLength(L);
    }

    @Override
    public void setPlayerAimVelAngle(double angleDegrees) {
        aim.setVelAngle(angleDegrees);
    }

    @Override
    public double getPlayerAimVelAngle2Vectors(double x1, double y1, double x2, double y2) {
        return aim.getVelAngle2Vectors(x1, y1, x2, y2);
    }


}
