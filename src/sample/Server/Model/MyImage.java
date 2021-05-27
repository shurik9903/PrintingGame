package sample.Server.Model;


import sample.GameData;

//Класс изображение: дополнение наследуемого класса Image
public class MyImage extends ImageDate {

    protected String ImageName;
    protected boolean Type;

    //Конструктор
    public MyImage(String fileImageName, double Width,double Height, String ImageName, boolean Type) {
        super(fileImageName, Width, Height);
        this.ImageName = ImageName;
        this.Type = Type;
    }

    //Установить имя
    public void setName(String ImageName) {
        this.ImageName = ImageName;
    }

    //Установить тип
    public void setType(boolean Type) {
        this.Type = Type;
    }

    //Получить имя
    public String getName() {
        return this.ImageName;
    }

    //Получить тип
    public boolean getType() {
        return this.Type;
    }

}


