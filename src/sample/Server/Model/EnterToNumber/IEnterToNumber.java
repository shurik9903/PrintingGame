package sample.Server.Model.EnterToNumber;

import sample.Data.DataInterface.IImageDate;

import java.util.ArrayList;

public interface IEnterToNumber {
    //Ввод номера
    void addNumber(Character c);

    //Получение введенного номера
    int getNumbers();

    //Инициализация путем добавление картинок в список
    void initialize();

    double getEntNumX();

    void setEntNumX(double x);

    double getEntNumY();

    void setEntNumY(double y);

    double getEntNumWidth();

    double getEntNumHeight();

    void setEntNumHeight(double height);

    void setEntNumWidth(double width);

    ArrayList<IImageDate> getFrameNumber();
}
