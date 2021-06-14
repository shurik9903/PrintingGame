package sample.Server.Model.Projectile;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.Meteor.Meteor;
import sample.Server.Model.Player.IPlayer;
import sample.Server.Model.Player.Player;
import sample.Server.Model.Rectangle.IRectangle;
import sample.Server.Model.ServerFactory.ServerFactory;
import sample.Server.Model.Sprite.ISprite;
import sample.Server.Model.Sprite.Sprite;

//Класс снаряда
public class Projectile implements IProjectile, ISprite{

    private final ISprite basic;
    private final IMeteor meteor;
    private boolean Miss;
    private boolean Destroy;
    private final char Letter;
    private double BoxHeight, BoxWidth;
    private final IPlayer Player;

    //Конструктор
    public Projectile(IPlayer Player, char Letter, double BoxHeight, double BoxWidth, IMeteor meteor) {
        basic = ServerFactory.SpriteCreateInstance("Image/bullet.png", 25);
        this.Player = Player;
        Position();
        setVelLength(500);
        setRecSize(20, 20);
        Miss = false;
        Destroy = false;
        this.Letter = Letter;
        this.meteor = meteor;
        this.BoxHeight = BoxHeight;
        this.BoxWidth = BoxWidth;
    }

    //Расчет следующей позиции
    @Override
    public void Position() {
        double L = getImageHeight() / 2;
        double angleRadian = Math.toRadians(Player.getRotation());
        setPosX(Player.getPosX() + L * Math.cos(angleRadian));
        setPosY(Player.getPosY() + L * Math.sin(angleRadian));
    }

    //Движение к метеориту, проверка на попадание и выход за границу игры
    @Override
    public void MoveAndFireToTarget() {
        if (meteor == null) return;
        if (overlaps(meteor) && !Miss)
            if (!meteor.ChangeImage(Letter)) Miss = true;
            else Destroy = true;
        else if (Miss) {
            if (getPosX() < -50 ||
                    BoxWidth + 50 < getPosX() ||
                    getPosY() < -50 ||
                    BoxHeight + 50 < getPosY()) Destroy = true;
        } else {
            setRotation(getAngleToTarget(meteor));
            setVelAngle(getRotation());
        }
    }

    //Обновление объекта
    @Override
    public void update(double deltaTime) {
        basic.update(deltaTime);
        MoveAndFireToTarget();
    }

    @Override
    public boolean isMiss(){
        return Miss;
    }

    @Override
    public boolean isDestroy() {
        return Destroy;
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
    public double getRotation() {
        return basic.getRotation();
    }

    @Override
    public void setRotation(double rotation) {
        basic.setRotation(rotation);
    }


}
