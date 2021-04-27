package sample.Model;

import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class MyImage extends Image {

    protected String ImageName;
    protected boolean Type;

    public MyImage(String s, double v, double v1, boolean b, boolean b1, String ImageName, boolean Type) {
        super(s, v, v1, b, b1);
        this.ImageName = ImageName;
        this.Type = Type;
    }

    public void setName(String ImageName) {
        this.ImageName = ImageName;
    }

    public void setType(boolean Type) {
        this.Type = Type;
    }

    public String getName() {
        return this.ImageName;
    }

    public boolean getType() {
        return this.Type;
    }

}


