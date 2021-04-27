package sample.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

//Класс форма с текстом: расположение, длина и текст формы
public class TextToObject {

    public boolean Destroy;
    int NumberToHits;
    public double x, y;
    public double Width, Height;
    public String Text;
    public ArrayList<Image> FrameImage, FrameNumber;
    public ArrayList<ArrayList<MyImage>> FrameText;
    private final int MeteorID;

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

        Image FStart = new Image("Image/R1.png", this.Width, this.Height, false, false);
        Image FEnd = new Image("Image/R3.png", this.Width, this.Height, false, false);
        Image FMiddle = new Image("Image/R2.png", this.Width, this.Height, false, false);

        this.FrameImage = new ArrayList<>();
        this.FrameImage.add(FStart);
        for (int i = 0; i < Text.length() + (int) (MeteorID / 10); i++) {
            this.FrameImage.add(FMiddle);
        }
        this.FrameImage.add(FEnd);

        FrameNumber = new ArrayList<>();
        for (Character i : String.valueOf(MeteorID).toCharArray())
            FrameNumber.add(new Image("Image/NumberImage/" + i + ".png", this.Width, this.Height, false, false));

        this.FrameText = new ArrayList<>();
        for (Character i : this.Text.toCharArray()) {
            this.FrameText.add(new ArrayList<MyImage>());
            FrameText.get(FrameText.size() - 1).add(new MyImage("Image/TextImage/" + i + "unbroken.png",
                    this.Width, this.Height, false, false, i.toString(), false));
            FrameText.get(FrameText.size() - 1).add(new MyImage("Image/TextImage/" + i + "broken.png",
                    this.Width, this.Height, false, false, i.toString(), true));


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

    //Отрисовка объекта
    public void render(GraphicsContext gc) {
        gc.save();

        int TextCount = 0;
        int NumberCount = 0;

        gc.translate(this.x, this.y);

        for (Image i : this.FrameImage) {
            gc.translate(+this.Width, 0);
            gc.drawImage(i, 0, 0);

            if (NumberCount < FrameNumber.size()) {
                gc.drawImage(this.FrameNumber.get(NumberCount), 0, 0);
                NumberCount++;
            } else {
                if (TextCount < Text.length()) {
                    gc.drawImage(this.FrameText.get(TextCount).get(0), 0, 0);
                    TextCount++;
                }
            }
        }

        gc.restore();
    }

}
