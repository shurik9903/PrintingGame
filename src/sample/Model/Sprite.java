package sample.Model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//Класс обекта: Содержит описание объекта
public class Sprite {

    public Vector position;
    public Vector velocity;
    public double rotation;
    public Rectangle boundary;
    public Image image;

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

    public void setImage(String fileImageName, double sizeImage) {
        this.image = new Image(fileImageName, sizeImage, sizeImage, false, false);
    }

    public void setImage(String fileImageName, double Width, double Height) {
        this.image = new Image(fileImageName, Width, Height, false, false);
    }

    public void setImage(String fileImageName) {
        this.image = new Image(fileImageName);
    }

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

    //Отрисовка обекта на экране
    public void render(GraphicsContext gc) {

        gc.save();

        gc.translate(this.position.x, this.position.y);
        gc.rotate(this.rotation);
        gc.translate(-this.image.getWidth() / 2, -this.image.getHeight() / 2);
        gc.drawImage(this.image, 0, 0);

        gc.restore();
    }

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
