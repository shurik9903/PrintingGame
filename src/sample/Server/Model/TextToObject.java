package sample.Server.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sample.GameData;

import java.util.ArrayList;

//Класс форма с текстом: расположение, длина и текст формы
public class TextToObject {

    public boolean Destroy;
    int NumberToHits;
    public double x, y;
    public double Width, Height;
    public String Text;
    public ArrayList<ImageDate> FrameImage, FrameNumber;
    public ArrayList<ArrayList<MyImage>> FrameText;
    private final int MeteorID;

    //конструктор
    public TextToObject(double x, double y, double Width, double Height, String Text, int ID) {
        this.Text = Text;
        this.Width = Width;
        this.Height = Height;
        Destroy = false;
        NumberToHits = 0;
        MeteorID = ID;
        CreateFrame();
        setCoordinate(x, y);
        System.out.println(Text);
    }

    //Установка координат формы
    public void setCoordinate(double x, double y) {
        this.x = x - (((2 + Text.length() + (int) (MeteorID / 10)) * this.Width) / 2);
        this.y = y + 10;
    }

    //Описание формы и текста
    public void CreateFrame() {

        ImageDate FStart = new ImageDate("Image/R1.png", this.Width, this.Height);
        ImageDate FEnd = new ImageDate("Image/R3.png", this.Width, this.Height);
        ImageDate FMiddle = new ImageDate("Image/R2.png", this.Width, this.Height);

        this.FrameImage = new ArrayList<>();
        this.FrameImage.add(FStart);
        for (int i = 0; i < Text.length() + (int) (MeteorID / 10); i++) {
            this.FrameImage.add(FMiddle);
        }
        this.FrameImage.add(FEnd);

        FrameNumber = new ArrayList<>();
        for (Character i : String.valueOf(MeteorID).toCharArray())
            FrameNumber.add(new ImageDate("Image/NumberImage/" + i + ".png", this.Width, this.Height));

        this.FrameText = new ArrayList<>();
        for (Character i : this.Text.toCharArray()) {
            this.FrameText.add(new ArrayList<MyImage>());
            FrameText.get(FrameText.size() - 1).add(new MyImage("Image/TextImage/" + i + "unbroken.png",
                    this.Width, this.Height, i.toString(), false));
            FrameText.get(FrameText.size() - 1).add(new MyImage("Image/TextImage/" + i + "broken.png",
                    this.Width, this.Height, i.toString(), true));


        }

    }


    //Смена изображения с целой буквы на поломанную и проверка на уничтожение метеорита
    public boolean ChangeImage(Character c) {
        for (ArrayList<MyImage> i : FrameText) {
            if (i.get(0).ImageName.equals(c.toString()) && !i.get(0).Type) {
                MyImage m = i.get(0);
                i.set(0, i.get(1));
                i.set(1, m);
                NumberToHits++;
                if (NumberToHits == FrameText.size())
                    Destroy = true;
                return true;
            }
            if (!i.get(0).ImageName.equals(c.toString()) && !i.get(0).Type)
                return false;
        }
        return false;
    }

}
