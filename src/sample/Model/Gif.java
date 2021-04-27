package sample.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Gif {
    private ArrayList<Image> ArrayImage;
    private double LiveTime;
    private final double Size;
    private double x, y;
    private double rotation;
    private int ImageIndex;

    public Gif(String Name, double Size){
        ArrayImage = new ArrayList<>();
        LiveTime = 0;
        this.Size = Size;
        x = 0;
        y = 0;
        rotation = 0;
        ImageIndex = -1;
        LoadGif(Name);
    }

    public void setCoordinate(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setRotation(double rotation){
        this.rotation = rotation;
    }

    private void LoadGif(String Name){
        try (Stream<Path> paths = Files.walk(Paths.get("src/Image/GifImage/" + Name))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(
                            path -> ArrayImage.add(new Image(String.valueOf(path.toUri()), Size, Size, false, false))
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image NextImage(){

        ImageIndex++;
        if (ImageIndex >= ArrayImage.size()) ImageIndex = 0;

        return ArrayImage.get(ImageIndex);
    }

    public void render(GraphicsContext gc, double deltaTime){
        LiveTime += deltaTime;
        System.out.println(LiveTime);

        gc.save();

        gc.translate(x, y);
        gc.rotate(rotation);
        gc.translate(-this.Size / 2, -this.Size / 2);
        gc.drawImage(NextImage(), 0, 0);

        gc.restore();
    }

}
