package sample.Server.Model.MyImage;

public interface IMyImage {

    void setName(String ImageName);

    //Установить тип
    void setType(boolean Type);

    //Получить имя
    String getName();

    //Получить тип
    boolean getType();
}
