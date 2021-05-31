package sample.Server.Model.TextToObject;

import sample.Server.Model.ImageDate.IImageDate;
import sample.Server.Model.ImageDate.ImageDate;
import sample.Server.Model.MyImage.IMyImage;
import sample.Server.Model.MyImage.MyImage;

import java.util.ArrayList;

public interface ITextToObject {
    //Установка координат формы
    void setCoordinate(double x, double y);

    //Описание формы и текста
    void CreateFrame();

    //Смена изображения с целой буквы на поломанную и проверка на уничтожение метеорита
    boolean ChangeImage(Character c);

    String getText();

    boolean isDestroy();

    double getX();

    void setX(double x);

    double getY();

    void setY(double y);

    double getWidth();

    double getHeight();

    void setHeight(double height);

    void setWidth(double width);

    ArrayList<IImageDate> getFrameImage();

    ArrayList<IImageDate> getFrameNumber();

    ArrayList<ArrayList<IMyImage>> getFrameText();
}
