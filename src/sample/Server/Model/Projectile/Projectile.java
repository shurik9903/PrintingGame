package sample.Server.Model.Projectile;

import javafx.geometry.Point2D;
import sample.Server.Model.Meteor.Meteor;
import sample.Server.Model.Player.Player;
import sample.Server.Model.Sprite.Sprite;

//Класс снаряда
public class Projectile extends Sprite {

    private final Meteor meteor;
    public boolean Miss;
    public boolean Destroy;
    private final char Letter;
    double BoxHeight, BoxWidth;
    private final sample.Server.Model.Player.Player Player;

    //Конструктор
    public Projectile(Player Player, char Letter, double BoxHeight, double BoxWidth, Meteor meteor) {
        super("Image/bullet.png", 25);
        this.Player = Player;
        Position();
        velocity.setLength(500);
        boundary.setSize(20, 20);
        Miss = false;
        Destroy = false;
        this.Letter = Letter;
        this.meteor = meteor;
        this.BoxHeight = BoxHeight;
        this.BoxWidth = BoxWidth;
    }

    //Расчет следующей позиции
    private void Position() {
        double L = Player.image.getHeight() / 2;
        double angleRadian = Math.toRadians(Player.rotation);
        position.x = Player.position.x + L * Math.cos(angleRadian);
        position.y = Player.position.y + L * Math.sin(angleRadian);
    }

    //Движение к метеориту, проверка на попадание и выход за границу игры
    private void MoveAndFireToTarget() {
        if (meteor == null) return;
        if (overlaps(meteor) && !Miss)
            if (!meteor.Text.ChangeImage(Letter)) Miss = true;
            else Destroy = true;
        else if (Miss) {
            if (position.x < -50 ||
                    BoxWidth + 50 < position.x ||
                    position.y < -50 ||
                    BoxHeight + 50 < position.y) Destroy = true;
        } else {
            rotation = getAngleToTarget(meteor);
            velocity.setAngle(rotation);
        }
    }

    //Получение угла поворота снаряда
    @Override
    public double getAngleToTarget(Sprite other) {
        Point2D vector =
                new Point2D(other.position.x - Player.position.x, other.position.y - Player.position.y);
        double angle = vector.angle(1, 0);
        if (vector.getY() > 0)
            return angle;
        else
            return -angle;
    }

    //Обновление объекта
    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        MoveAndFireToTarget();
    }

}
