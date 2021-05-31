package sample.Client2.Model.MyImage;

import javafx.scene.image.Image;

public interface IMyImage {

    void setName(String ImageName);

    //Установить тип
    void setType(boolean Type);

    //Получить имя
    String getName();

    //Получить тип
    boolean getType();

    Image getImage();
}
