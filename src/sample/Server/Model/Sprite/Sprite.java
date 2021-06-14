package sample.Server.Model.Sprite;

import javafx.geometry.Point2D;

import javafx.scene.image.Image;
import sample.Server.Model.ImageDate.IImageDate;
import sample.Server.Model.ServerFactory.ServerFactory;
import sample.Server.Model.Rectangle.IRectangle;
import sample.Server.Model.Vector.IVecPos;
import sample.Server.Model.Vector.IVecVel;



//Класс обекта: Содержит описание объекта
public class Sprite implements ISprite, IVecPos, IVecVel, IRectangle, IImageDate {

    private final IVecPos position;
    private final IVecVel velocity;
    private final IRectangle boundary;
    private IImageDate image;
    private double rotation;

    //конуструктор
    public Sprite() {
        this.rotation = 0;
        this.position =  ServerFactory.VecPosCreateInstance();
        this.velocity =  ServerFactory.VecVelCreateInstance();
        this.boundary =  ServerFactory.RectangleCreateInstance();
    }

    public Sprite(String fileImageName, double sizeImage) {
        this();
        this.image = ServerFactory.ImageDateCreateInstance(fileImageName, sizeImage);
    }

    public Sprite(String fileImageName) {
        this();
        this.image = ServerFactory.ImageDateCreateInstance(fileImageName);
    }

    public Sprite(String fileImageName, double Width, double Height) {
        this();
        this.image = ServerFactory.ImageDateCreateInstance(fileImageName, Width, Height);
    }

    //установка изображения
    @Override
    public void setImage(String fileImageName, double sizeImage) {
        this.image = ServerFactory.ImageDateCreateInstance(fileImageName, sizeImage, sizeImage);
    }

    @Override
    public void setImage(String fileImageName, double Width, double Height) {
        this.image = ServerFactory.ImageDateCreateInstance(fileImageName, Width, Height);
    }

    @Override
    public void setImage(String fileImageName) {
        this.image = ServerFactory.ImageDateCreateInstance(fileImageName);
    }

    //Расчет соприкосновение с другим объектом
    @Override
    public boolean overlaps(ISprite other) {
        return this.RecOverlaps(other);
    }

    //Получение угла поворота до объекта
    @Override
    public double getAngleToTarget(ISprite other) {
        Point2D vector = new Point2D(other.getPosX() - getPosX(),
                        other.getPosY() - getPosY());
        double angle = vector.angle(1, 0);
        if (vector.getY() > 0)
            return angle;
        else
            return -angle;
    }

    //Обновление объекта
    @Override
    public void update(double deltaTime) {
        this.PosAdd(this.getVelX() * deltaTime, this.getVelX() * deltaTime);
    }

    @Override
    public double getRotation(){
        return rotation;
    }

    @Override
    public void setRotation(double rotation){
        this.rotation = rotation;
    }


    @Override
    public double getRecX() {
        return boundary.getRecX();
    }

    @Override
    public double getRecY() {
        return boundary.getRecY();
    }

    @Override
    public double getRecWidth() {
        return boundary.getRecWidth();
    }

    @Override
    public double getRecHeight() {
        return boundary.getRecHeight();
    }

    @Override
    public void setRecPosition(double x, double y) {
        boundary.setRecPosition(x,y);
    }

    @Override
    public void setRecSize(double width, double height) {
        boundary.setRecSize(width,height);
    }

    @Override
    public boolean RecOverlaps(ISprite other) {
        return boundary.RecOverlaps(other);
    }

    @Override
    public double getPosX() {
        return position.getPosX();
    }

    @Override
    public double getPosY() {
        return position.getPosY();
    }

    @Override
    public void setPosX(double x) {
        position.setPosX(x);
    }

    @Override
    public void setPosY(double y) {
        position.setPosY(y);
    }

    @Override
    public void PosSet(double x, double y) {
        position.PosSet(x,y);
    }

    @Override
    public void PosAdd(double x, double y) {
        position.PosAdd(x, y);
    }

    @Override
    public void PosMultiply(double m) {
        position.PosMultiply(m);
    }

    @Override
    public double getPosLength() {
        return position.getPosLength();
    }

    @Override
    public double getPosLength(double x, double y) {
        return position.getPosLength(x, y);
    }

    @Override
    public void setPosLength(double L) {
        position.setPosLength(L);
    }

    @Override
    public void setPosAngle(double angleDegrees) {
        position.setPosAngle(angleDegrees);
    }

    @Override
    public double getPosAngle2Vectors(double x1, double y1, double x2, double y2) {
        return position.getPosAngle2Vectors(x1, y1, x2, y2);
    }

    @Override
    public double getVelX() {
        return velocity.getVelX();
    }

    @Override
    public double getVelY() {
        return velocity.getVelY();
    }

    @Override
    public void setVelX(double x) {
        velocity.setVelX(x);
    }

    @Override
    public void setVelY(double y) {
        velocity.setVelY(y);
    }

    @Override
    public void VelSet(double x, double y) {
        velocity.VelSet(x, y);
    }

    @Override
    public void VelAdd(double x, double y) {
        velocity.VelAdd(x, y);
    }

    @Override
    public void VelMultiply(double m) {
        velocity.VelMultiply(m);
    }

    @Override
    public double getVelLength() {
        return velocity.getVelLength();
    }

    @Override
    public double getVelLength(double x, double y) {
        return velocity.getVelLength(x, y);
    }

    @Override
    public void setVelLength(double L) {
        velocity.setVelLength(L);
    }

    @Override
    public void setVelAngle(double angleDegrees) {
        velocity.setVelAngle(angleDegrees);
    }

    @Override
    public double getVelAngle2Vectors(double x1, double y1, double x2, double y2) {
        return velocity.getVelAngle2Vectors(x1, y1, x2, y2);
    }

    @Override
    public double getImageWidth() {
        return image.getImageWidth();
    }

    @Override
    public double getImageHeight() {
        return image.getImageHeight();
    }

    @Override
    public String getFileImageName() {
        return image.getFileImageName();
    }

}
