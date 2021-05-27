package sample.Server.Model;

import javafx.geometry.Point2D;


//Класс обекта: Содержит описание объекта
public class Sprite {

    public Vector position;
    public Vector velocity;
    public double rotation;
    public Rectangle boundary;
    public ImageDate image;

    //конуструктор
    public Sprite() {
        this.position = new Vector();
        this.velocity = new Vector();
        this.rotation = 0;
        this.boundary = new Rectangle();
    }

    public Sprite(String fileImageName, double sizeImage) {
        this();
        setImage(fileImageName, sizeImage);
    }

    public Sprite(String fileImageName) {
        this();
        setImage(fileImageName);
    }

    public Sprite(String fileImageName, double Width, double Height) {
        this();
        setImage(fileImageName, Width, Height);
    }

    //установка изображения
    public void setImage(String fileImageName, double sizeImage) {
        this.image = new ImageDate(fileImageName, sizeImage, sizeImage);
    }

    public void setImage(String fileImageName, double Width, double Height) {
        this.image = new ImageDate(fileImageName, Width, Height);
    }

    public void setImage(String fileImageName) {
        this.image = new ImageDate(fileImageName);
    }

    //Перемещение колизии объекта
    public Rectangle getBoundary() {
        this.boundary.setPosition(this.position.x, this.position.y);
        return this.boundary;
    }

    //Расчет соприкосновение с другим объектом
    public boolean overlaps(Sprite other) {
        return this.getBoundary().overlaps(other.getBoundary());
    }

    //Обновление объекта
    public void update(double deltaTime) {
        this.position.add(this.velocity.x * deltaTime, this.velocity.y * deltaTime);
    }

    //Получение угла поворота до объекта
    public double getAngleToTarget(Sprite other) {
        Point2D vector =
                new Point2D(other.position.x - position.x, other.position.y - position.y);
        double angle = vector.angle(1, 0);
        if (vector.getY() > 0)
            return angle;
        else
            return -angle;
    }

}
