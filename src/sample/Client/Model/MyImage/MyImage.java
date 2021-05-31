package sample.Client.Model.MyImage;


import javafx.scene.image.Image;
import sample.Client.Model.ClientFactory.ClientFactory;
import sample.Client.Model.ImageDate.IImageDate;

import java.io.Serial;
import java.io.Serializable;

//Класс изображение: дополнение наследуемого класса Image
public class MyImage implements IMyImage, IImageDate, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757692L;
    protected String ImageName;
    protected IImageDate imageDate;
    protected boolean Type;

    //Конструктор
    public MyImage(String fileImageName, double Width,double Height, String ImageName, boolean Type) {
        imageDate = ClientFactory.ImageDateCreateInstance(fileImageName, Width, Height);
        this.ImageName = ImageName;
        this.Type = Type;
    }

    //Установить имя
    @Override
    public void setName(String ImageName) {
        this.ImageName = ImageName;
    }

    //Установить тип
    @Override
    public void setType(boolean Type) {
        this.Type = Type;
    }

    //Получить имя
    @Override
    public String getName() {
        return this.ImageName;
    }

    //Получить тип
    @Override
    public boolean getType() {
        return this.Type;
    }

    @Override
    public double getWidth() {
        return imageDate.getWidth();
    }

    @Override
    public double getHeight() {
        return imageDate.getHeight();
    }

    @Override
    public String getFileImageName() {
        return imageDate.getFileImageName();
    }

    @Override
    public Image getImage() {
        return imageDate.getImage();
    }
}


