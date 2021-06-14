package sample.Server.Model.MyImage;

import javafx.scene.image.Image;
import sample.Server.Model.ImageDate.IImageDate;

public interface IMyImage extends IImageDate {

    void setName(String ImageName);

    //Установить тип
    void setType(boolean Type);

    //Получить имя
    String getName();

    //Получить тип
    boolean getType();

}
