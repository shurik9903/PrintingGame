package sample.Server.Model.EnterToNumber;

import sample.Server.Model.ImageDate.IImageDate;

import java.util.ArrayList;

public interface IEnterToNumber {
    //Ввод номера
    void addNumber(Character c);

    //Получение введенного номера
    int getNumbers();

    //Инициализация путем добавление картинок в список
    void initialize();

    //Установка координат формы
    void setCoordinate();

    double getX();

    void setX(double x);

    double getY();

    void setY(double y);

    double getWidth();

    double getHeight();

    void setHeight(double height);

    void setWidth(double width);

    ArrayList<IImageDate> getFrameNumber();
}
