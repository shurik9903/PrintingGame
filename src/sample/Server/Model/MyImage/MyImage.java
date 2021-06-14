package sample.Server.Model.MyImage;


import javafx.scene.image.Image;
import sample.Server.Model.ImageDate.IImageDate;
import sample.Server.Model.ImageDate.ImageDate;
import sample.Server.Model.ServerFactory.ServerFactory;

import java.io.Serial;
import java.io.Serializable;

//Класс изображение: дополнение наследуемого класса Image
public class MyImage implements IMyImage, IImageDate, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757693L;
    protected String ImageName;
    protected IImageDate imageDate;
    protected boolean Type;

    //Конструктор
    public MyImage(String fileImageName, double Width,double Height, String ImageName, boolean Type) {
        imageDate = ServerFactory.ImageDateCreateInstance(fileImageName, Width, Height);
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
    public double getImageWidth() {
        return imageDate.getImageWidth();
    }

    @Override
    public double getImageHeight() {
        return imageDate.getImageHeight();
    }

    @Override
    public String getFileImageName() {
        return imageDate.getFileImageName();
    }

}


