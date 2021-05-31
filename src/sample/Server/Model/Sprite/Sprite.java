package sample.Server.Model.Sprite;

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
    public Rectangle getBoundary() {
        this.boundary.setPosition(this.position.getX(), this.position.getY());
        return (Rectangle) this.boundary;
    }

    //Расчет соприкосновение с другим объектом
    @Override
    public boolean overlaps(Sprite other) {
        return this.getBoundary().overlaps(other.getBoundary());
    }

    @Override
    public boolean overlaps(Rectangle other) {
        return boundary.overlaps(other);
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
