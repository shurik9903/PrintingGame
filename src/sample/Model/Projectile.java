package sample.Model;

import javafx.geometry.Point2D;

//Класс снаряда
public class Projectile extends Sprite {

    private final Meteor meteor;
    private boolean Miss;
    public boolean Destroy;
    private final char Letter;
    double BoxHeight, BoxWidth;
    private final Gun Player;

    public Projectile(Gun Player, char Letter, double BoxHeight, double BoxWidth, Meteor meteor) {
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

    private void Position() {
        double L = Player.image.getHeight() / 2;
        double angleRadian = Math.toRadians(Player.rotation);
        position.x = Player.position.x + L * Math.cos(angleRadian);
        position.y = Player.position.y + L * Math.sin(angleRadian);
    }

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
