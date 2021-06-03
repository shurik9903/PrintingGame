package sample.Server.Model.Sprite;

import javafx.geometry.Point2D;
import sample.Server.Model.ImageDate.IImageDate;
import sample.Server.Model.ImageDate.ImageDate;
import sample.Server.Model.ServerFactory.ServerFactory;
import sample.Server.Model.Rectangle.IRectangle;
import sample.Server.Model.Rectangle.Rectangle;
import sample.Server.Model.Vector.IVector;


//Класс обекта: Содержит описание объекта
public class Sprite implements ISprite{

    private final IVector position;
    private final IVector velocity;
    private double rotation;
    private final IRectangle boundary;
    private IImageDate image;

    //конуструктор
    public Sprite() {
        this.rotation = 0;
        this.position =  ServerFactory.VectorCreateInstance();
        this.velocity =  ServerFactory.VectorCreateInstance();
        this.boundary =  ServerFactory.RectangleCreateInstance();
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
    @Override
    public void setImage(String fileImageName, double sizeImage) {
        this.image = new ImageDate(fileImageName, sizeImage, sizeImage);
    }

    @Override
    public void setImage(String fileImageName, double Width, double Height) {
        this.image = new ImageDate(fileImageName, Width, Height);
    }

    @Override
    public void setImage(String fileImageName) {
        this.image = new ImageDate(fileImageName);
    }

    //Перемещение колизии объекта
    @Override
    public IRectangle getBoundary() {
        this.boundary.setPosition(this.position.getX(), this.position.getY());
        return this.boundary;
    }

    //Расчет соприкосновение с другим объектом
    @Override
    public boolean overlaps(ISprite other) {
        return this.getBoundary().overlaps(other.getBoundary());
    }

    @Override
    public boolean overlaps(IRectangle other) {
        return boundary.overlaps(other);
    }


    //Получение угла поворота до объекта
    @Override
    public double getAngleToTarget(ISprite other) {
        Point2D vector = new Point2D(other.getPosition().getX() - getPosition().getX(),
                        other.getPosition().getY() - getPosition().getY());
        double angle = vector.angle(1, 0);
        if (vector.getY() > 0)
            return angle;
        else
            return -angle;
    }

    //Обновление объекта
    @Override
    public void update(double deltaTime) {
        this.position.add(this.velocity.getX() * deltaTime, this.velocity.getY() * deltaTime);
    }

    @Override
    public IVector getPosition(){
        return position;
    }

    @Override
    public IVector getVelocity(){
        return velocity;
    }

    @Override
    public IRectangle getRectangle(){
        return boundary;
    }

    @Override
    public IImageDate getImage(){
        return image;
    }

    @Override
    public double getRotation(){
        return rotation;
    }

    @Override
    public void setRotation(double rotation){
        this.rotation = rotation;
    }
}
