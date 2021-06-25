package sample.Server.Model.TextToObject;

import sample.Data.DataInterface.IImageDate;
import sample.Data.GameDataFactory;
import sample.Server.Model.MyImage.IMyImage;
import sample.Server.Model.MyImage.MyImage;
import sample.Server.Model.MyImage.MyImageFactory;
import sample.Server.Model.ServerFactory.ServerFactory;

import java.util.ArrayList;

//Класс форма с текстом: расположение, длина и текст формы
public class TextToObject implements ITextToObject{

    private boolean Destroy;
    private int NumberToHits;
    private double x, y;
    private double Width, Height;
    private final String Text;
    private ArrayList<IImageDate> FrameImage, FrameNumber;
    private ArrayList<ArrayList<IMyImage>> FrameText;
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
        setTextCoordinate(x, y);
        System.out.println(Text);
    }

    //Установка координат формы
    @Override
    public void setTextCoordinate(double x, double y) {
        this.x = x - (((2 + Text.length() + (int) (MeteorID / 10)) * this.Width) / 2);
        this.y = y + 10;
    }

    //Описание формы и текста
    @Override
    public void CreateFrame() {
        IImageDate FStart = GameDataFactory.ImageDateCreateInstance("Image/R1.png", this.Width, this.Height);
        IImageDate FEnd = GameDataFactory.ImageDateCreateInstance("Image/R3.png", this.Width, this.Height);
        IImageDate FMiddle = GameDataFactory.ImageDateCreateInstance("Image/R2.png", this.Width, this.Height);

        this.FrameImage = new ArrayList<>();
        this.FrameImage.add(FStart);
        for (int i = 0; i < Text.length() + (int) (MeteorID / 10); i++) {
            this.FrameImage.add(FMiddle);
        }
        this.FrameImage.add(FEnd);

        FrameNumber = new ArrayList<>();
        for (Character i : String.valueOf(MeteorID).toCharArray())
            FrameNumber.add(GameDataFactory.ImageDateCreateInstance("Image/NumberImage/" + i + ".png", this.Width, this.Height));

        this.FrameText = new ArrayList<>();
        for (Character i : this.Text.toCharArray()) {
            this.FrameText.add(new ArrayList<>());
            FrameText.get(FrameText.size() - 1).add(MyImageFactory.CreateInstance("Image/TextImage/" + i + "unbroken.png",
                    this.Width, this.Height, i.toString(), false));
            FrameText.get(FrameText.size() - 1).add(MyImageFactory.CreateInstance("Image/TextImage/" + i + "broken.png",
                    this.Width, this.Height, i.toString(), true));


        }

    }

    //Смена изображения с целой буквы на поломанную и проверка на уничтожение метеорита
    @Override
    public boolean ChangeImage(Character c) {
        for (ArrayList<IMyImage> i : FrameText) {
            if (i.get(0).getName().equals(c.toString()) && !i.get(0).getType()) {
                IMyImage m = i.get(0);
                i.set(0, i.get(1));
                i.set(1, m);
                NumberToHits++;
                if (NumberToHits == FrameText.size())
                    Destroy = true;
                return true;
            }
            if (!i.get(0).getName().equals(c.toString()) && !i.get(0).getType())
                return false;
        }
        return false;
    }

    @Override
    public String getText(){
        return Text;
    }

    @Override
    public boolean isDestroy() {
        return Destroy;
    }

    @Override
    public double getTextX() {
        return x;
    }

    @Override
    public void setTextX(double x) {
        this.x = x;
    }

    @Override
    public double getTextY() {
        return y;
    }

    @Override
    public void setTextY(double y) {
        this.y = y;
    }

    @Override
    public double getTextWidth() {
        return Width;
    }

    @Override
    public double getTextHeight() {
        return Height;
    }

    @Override
    public void setTextHeight(double height) {
        Height = height;
    }

    @Override
    public void setTextWidth(double width) {
        Width = width;
    }

    @Override
    public ArrayList<IImageDate> getFrameImage() {
        return FrameImage;
    }

    @Override
    public ArrayList<IImageDate> getFrameNumber() {
        return FrameNumber;
    }

    @Override
    public ArrayList<ArrayList<IMyImage>> getFrameText() {
        return FrameText;
    }
}
