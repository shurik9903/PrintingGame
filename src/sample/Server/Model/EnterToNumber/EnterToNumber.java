package sample.Server.Model.EnterToNumber;

import sample.Server.Model.ImageDate.IImageDate;
import sample.Server.Model.ImageDate.ImageDate;
import sample.Server.Model.Player.IPlayer;
import sample.Server.Model.Player.Player;

import java.util.ArrayList;

//Класс ввод номера: Отображение и ввод номера метеорита
public class EnterToNumber implements IEnterToNumber{
    private double x,y;
    private double Width, Height;
    private String Numbers;
    private final IPlayer player;
    private ArrayList<IImageDate> FrameNumber;

    //Конструктор
    public EnterToNumber(IPlayer player, double Width, double Height) {
        Numbers = "";
        this.Width = Width;
        this.Height = Height;
        this.player = player;
        setCoordinate();
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
            FrameNumber.add(new ImageDate("Image/NumberImage/" + i + ".png", Width, Height));

    }

    //Установка координат формы
    @Override
    public void setCoordinate() {
        x = player.getBasic().getPosition().getX() - player.getBasic().getImage().getWidth() / 2;
        y = player.getBasic().getPosition().getY() + player.getBasic().getImage().getHeight() / 2;

    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getWidth() {
        return Width;
    }

    @Override
    public double getHeight() {
        return Height;
    }

    @Override
    public void setHeight(double height) {
        Height = height;
    }

    @Override
    public void setWidth(double width) {
        Width = width;
    }

    @Override
    public ArrayList<IImageDate> getFrameNumber() {
        return FrameNumber;
    }
}


