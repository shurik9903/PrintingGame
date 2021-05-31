package sample.Client2.Model.ImageDate;

import javafx.scene.image.Image;
import sample.Client2.Model.ImageDate.IImageDate;

import java.io.Serializable;

public class ImageDate implements Serializable, IImageDate {

    private final String fileImageName;
    private final double Width;
    private final double Height;

    public ImageDate(String fileImageName, double sizeImage) {
        this.fileImageName = fileImageName;
        this.Height = sizeImage;
        this.Width = sizeImage;
    }

    public ImageDate(String fileImageName, double Width, double Height) {
        this.fileImageName = fileImageName;
        this.Width = Width;
        this.Height = Height;

    }

    public ImageDate(String fileImageName) {
        this.fileImageName = fileImageName;
        this.Width = 0;
        this.Height = 0;
    }

    @Override
    public double getWidth() {
        return Width;
    }

    @Override
    public double getHeight() {
        return Height;
    }

    @Override
    public String getFileImageName() {
        return fileImageName;
    }

    @Override
    public Image getImage() {
        if (Width == 0 || Height == 0)
            return new Image(fileImageName);
        else
            return new Image(fileImageName, Width, Height, false, false);
    }
}
