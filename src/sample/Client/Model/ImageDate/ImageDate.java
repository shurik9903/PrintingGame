package sample.Client.Model.ImageDate;

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
    public double getImageWidth() {
        return Width;
    }

    @Override
    public double getImageHeight() {
        return Height;
    }

    @Override
    public String getFileImageName() {
        return fileImageName;
    }
}
