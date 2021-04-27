package sample.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class EnterToNumber {
    private double x;
    private double y;
    private final double Width, Height;
    private String Numbers;
    private final Gun Player;
    private ArrayList<Image> FrameNumber;

    public EnterToNumber(Gun Player, double Width, double Height) {
        Numbers = "";
        this.Width = Width;
        this.Height = Height;
        this.Player = Player;
        setCoordinate();
    }

    public void addNumber(Character c) {
        if (Numbers.length() < 3) Numbers += c;
        else Numbers = "";
        initialize();
    }

    public int getNumbers() {
        int R = 0;
        if (Numbers.length() != 0)
            R = Integer.parseInt(Numbers);
        Numbers = "";
        initialize();
        return R;
    }

    public void initialize() {
        FrameNumber = new ArrayList<>();
        for (Character i : String.valueOf(Numbers).toCharArray())
            FrameNumber.add(new Image("Image/NumberImage/" + i + ".png", Width, Height, false, false));

    }

    //Установка координат формы
    public void setCoordinate() {
        x = Player.position.x - Player.image.getWidth() / 2;
        y = Player.position.y + Player.image.getHeight() / 2 - Height;
    }

    //Отрисовка объекта
    public void render(GraphicsContext gc) {
        gc.save();

        gc.translate(this.x, this.y);
        if (FrameNumber != null)
            for (Image i : this.FrameNumber) {
                gc.translate(+this.Width, 0);
                gc.drawImage(i, 0, 0);
            }

        gc.restore();
    }

}


