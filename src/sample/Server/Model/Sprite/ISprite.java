package sample.Server.Model.Sprite;

import sample.Data.DataInterface.IImageDate;
import sample.Server.Model.Rectangle.IRectangle;
import sample.Server.Model.Vector.IVecPos;
import sample.Server.Model.Vector.IVecVel;

public interface ISprite extends IVecPos, IVecVel, IRectangle,IImageDate {

    void setImage(String fileImageName, double sizeImage);

    void setImage(String fileImageName, double Width, double Height);

    void setImage(String fileImageName);

    //Получение угла поворота до объекта
    double getAngleToTarget(ISprite other);

    //Обновление объекта
    void update(double deltaTime);

    double getRotation();

    void setRotation(double rotation);
}
