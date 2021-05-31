package sample.Client2.Model;

import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

//Класс изображение: дополнение наследуемого класса Image
public class MyImage extends Image {

    protected String ImageName;
    protected boolean Type;

    //Конструктор
    public MyImage(String s, double v, double v1, boolean b, boolean b1, String ImageName, boolean Type) {
        super(s, v, v1, b, b1);
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


