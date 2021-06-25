package sample.Server.Model.EnterToNumber;

import sample.Data.GameData;
import sample.Data.DataInterface.IImageDate;

import java.util.ArrayList;

//Класс ввод номера: Отображение и ввод номера метеорита
public class EnterToNumber implements IEnterToNumber{
    private double x,y;
    private double Width, Height;
    private String Numbers;
    private ArrayList<IImageDate> FrameNumber;

    //Конструктор
    public EnterToNumber(double Width, double Height,double PosX, double PosY, double ImageWidth, double ImageHeight) {
        Numbers = "";
        this.Width = Width;
        this.Height = Height;
        this.x = PosX - ImageWidth / 2;
        this.y = PosY + ImageHeight / 2;
    }

    //Ввод номера
    @Override
    public void addNumber(Character c) {
        if (Numbers.length() < 3) Numbers += c;
        else Numbers = "";
        initialize();
    }

    //Получение введенного номера
    @Override
    public int getNumbers() {
        int R = 0;
        if (Numbers.length() != 0)
            R = Integer.parseInt(Numbers);
        Numbers = "";
        initialize();
        return R;
    }

    //Инициализация путем добавление картинок в список
    @Override
    public void initialize() {
        FrameNumber = new ArrayList<>();
        for (Character i : String.valueOf(Numbers).toCharArray())
            FrameNumber.add(new GameData.ImageDate("Image/NumberImage/" + i + ".png", Width, Height));

    }

    @Override
    public double getEntNumX() {
        return x;
    }

    @Override
    public void setEntNumX(double x) {
        this.x = x;
    }

    @Override
    public double getEntNumY() {
        return y;
    }

    @Override
    public void setEntNumY(double y) {
        this.y = y;
    }

    @Override
    public double getEntNumWidth() {
        return Width;
    }

    @Override
    public double getEntNumHeight() {
        return Height;
    }

    @Override
    public void setEntNumHeight(double height) {
        Height = height;
    }

    @Override
    public void setEntNumWidth(double width) {
        Width = width;
    }

    @Override
    public ArrayList<IImageDate> getFrameNumber() {
        return FrameNumber;
    }
}


