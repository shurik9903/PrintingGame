package sample.Client.Model.MyImage;

import sample.Data.DataInterface.IImageDate;

public interface IMyImage extends IImageDate {

    void setName(String ImageName);

    //Установить тип
    void setType(boolean Type);

    //Получить имя
    String getName();

    //Получить тип
    boolean getType();

}
