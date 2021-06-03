package sample.Server.Model.Projectile;

import javafx.geometry.Point2D;
import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.Meteor.Meteor;
import sample.Server.Model.Player.IPlayer;
import sample.Server.Model.Player.Player;
import sample.Server.Model.Rectangle.IRectangle;
import sample.Server.Model.ServerFactory.ServerFactory;
import sample.Server.Model.Sprite.ISprite;
import sample.Server.Model.Sprite.Sprite;

//Класс снаряда
public class Projectile implements IProjectile{

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
        basic.getVelocity().setLength(500);
        basic.getBoundary().setSize(20, 20);
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
        double L = Player.getBasic().getImage().getHeight() / 2;
        double angleRadian = Math.toRadians(Player.getBasic().getRotation());
        basic.getPosition().setX(Player.getBasic().getPosition().getX() + L * Math.cos(angleRadian));
        basic.getPosition().setY(Player.getBasic().getPosition().getY() + L * Math.sin(angleRadian));
    }

    //Движение к метеориту, проверка на попадание и выход за границу игры
    @Override
    public void MoveAndFireToTarget() {
        if (meteor == null) return;
        if (basic.overlaps(meteor.getBasic()) && !Miss)
            if (!meteor.getTextObject().ChangeImage(Letter)) Miss = true;
            else Destroy = true;
        else if (Miss) {
            if (basic.getPosition().getX() < -50 ||
                    BoxWidth + 50 < basic.getPosition().getX() ||
                    basic.getPosition().getY() < -50 ||
                    BoxHeight + 50 < basic.getPosition().getY()) Destroy = true;
        } else {
            basic.setRotation(getAngleToTarget(meteor.getBasic()));
            basic.getVelocity().setAngle(basic.getRotation());
        }
    }

    //Получение угла поворота снаряда
    @Override
    public double getAngleToTarget(ISprite other) {
        Point2D vector =
                new Point2D(other.getPosition().getX() - Player.getBasic().getPosition().getX(),
                        other.getPosition().getY() - Player.getBasic().getPosition().getY());
        double angle = vector.angle(1, 0);
        if (vector.getY() > 0)
            return angle;
        else
            return -angle;
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
    public ISprite getBasic() {
        return basic;
    }
}
