package sample.Server.Model;

import javafx.scene.image.Image;

import java.util.ArrayList;

//Класс ввод номера: Отображение и ввод номера метеорита
public class EnterToNumber {
    public double x;
    public double y;
    public final double Width, Height;
    private String Numbers;
    private final Player player;
    public ArrayList<ImageDate> FrameNumber;

    //Конструктор
    public EnterToNumber(Player player, double Width, double Height) {
        Numbers = "";
        this.Width = Width;
        this.Height = Height;
        this.player = player;
        setCoordinate();
    }

    //Ввод номера
    public void addNumber(Character c) {
        if (Numbers.length() < 3) Numbers += c;
        else Numbers = "";
        initialize();
    }

    //Получение введенного номера
    public int getNumbers() {
        int R = 0;
        if (Numbers.length() != 0)
            R = Integer.parseInt(Numbers);
        Numbers = "";
        initialize();
        return R;
    }

    //Инициализация путем добавление картинок в список
    public void initialize() {
        FrameNumber = new ArrayList<>();
        for (Character i : String.valueOf(Numbers).toCharArray())
            FrameNumber.add(new ImageDate("Image/NumberImage/" + i + ".png", Width, Height));

    }

    //Установка координат формы
    public void setCoordinate() {
        x = player.position.x - player.image.getWidth() / 2;
        y = player.position.y + player.image.getHeight() / 2 - Height;
    }

}


