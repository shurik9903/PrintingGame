package sample.Server.Model.TextToObject;

import sample.Data.DataInterface.IImageDate;
import sample.Server.Model.MyImage.IMyImage;

import java.util.ArrayList;

public interface ITextToObject {
    //Установка координат формы
    void setTextCoordinate(double x, double y);

    //Описание формы и текста
    void CreateFrame();

    //Смена изображения с целой буквы на поломанную и проверка на уничтожение метеорита
    boolean ChangeImage(Character c);

    String getText();

    boolean isDestroy();

    double getTextX();

    void setTextX(double x);

    double getTextY();

    void setTextY(double y);

    double getTextWidth();

    double getTextHeight();

    void setTextHeight(double height);

    void setTextWidth(double width);

    ArrayList<IImageDate> getFrameImage();

    ArrayList<IImageDate> getFrameNumber();

    ArrayList<ArrayList<IMyImage>> getFrameText();
}
