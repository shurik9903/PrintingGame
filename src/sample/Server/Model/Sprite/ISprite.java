package sample.Server.Model.Sprite;

import sample.Server.Model.ImageDate.IImageDate;
import sample.Server.Model.Rectangle.IRectangle;
import sample.Server.Model.Rectangle.Rectangle;
import sample.Server.Model.Vector.IVector;

public interface ISprite {
    void setImage(String fileImageName, double sizeImage);

    void setImage(String fileImageName, double Width, double Height);

    void setImage(String fileImageName);

    IRectangle getBoundary();

    boolean overlaps(ISprite other);

    boolean overlaps(IRectangle other);

    //Получение угла поворота до объекта
    double getAngleToTarget(ISprite other);

    //Обновление объекта
    void update(double deltaTime);

    IVector getPosition();

    IVector getVelocity();

    IRectangle getRectangle();

    IImageDate getImage();

    double getRotation();

    void setRotation(double rotation);
}
