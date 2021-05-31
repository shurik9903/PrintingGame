package sample.Server.Model.TextToObject;

import sample.Server.Model.ImageDate.ImageDate;
import sample.Server.Model.MyImage.MyImage;

import java.util.ArrayList;

public interface ITextToObject {
    //Установка координат формы
    void setCoordinate(double x, double y);

    //Описание формы и текста
    void CreateFrame();

    //Смена изображения с целой буквы на поломанную и проверка на уничтожение метеорита
    boolean ChangeImage(Character c);

}
