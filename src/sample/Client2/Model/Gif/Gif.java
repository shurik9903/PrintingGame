package sample.Client2.Model.Gif;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Client2.Model.Gif.IGif;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

//Класс гиф изображений: покадровая смена изображения
public class Gif implements IGif {
    private ArrayList<Image> ArrayImage;
    private double TotalTime;
    private final double Size;
    private double x, y;
    private double rotation;
    private int ImageIndex;
    private final int FPS;
    private final ImageView IV;

    //конструктор
    public Gif(String Directory, double Size, int FPS){
        ArrayImage = new ArrayList<>();
        TotalTime = 0;
        this.Size = Size;
        x = 0;
        y = 0;
        this.FPS = FPS;
        rotation = 0;
        ImageIndex = 0;
        IV = null;
        LoadGif(Directory);
    }

    public Gif(String Directory, ImageView imageview, int FPS){
        ArrayImage = new ArrayList<>();
        TotalTime = 0;
        this.Size = 0;
        x = 0;
        y = 0;
        this.FPS = FPS;
        rotation = 0;
        ImageIndex = 0;
        IV = imageview;
        LoadGif(Directory);
    }

    public Gif(String Name, double Size, int FPS, double x, double y){
        ArrayImage = new ArrayList<>();
        TotalTime = 0;
        this.Size = Size;
        setCoordinate(x, y);
        this.FPS = FPS;
        rotation = 0;
        ImageIndex = 0;
        IV = null;
        LoadGif(Name);
    }

    //Ввод кординат отображения рисунка
    @Override
    public void setCoordinate(double x, double y){
        this.x = x;
        this.y = y;
    }

    //Ввод поворота рисунка
    @Override
    public void setRotation(double rotation){
        this.rotation = rotation;
    }

    //Загрузка всех изображений из введеной папки в список
    @Override
    public void LoadGif(String Name){
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

    //Получение следующего изображения в списке по кругу
    @Override
    public Image NextImage(){
        ImageIndex++;
        if (ImageIndex >= ArrayImage.size()) ImageIndex = 0;
        return ArrayImage.get(ImageIndex);
    }

    //Проверка на кадры в секунду
    @Override
    public boolean CheckFPS(){
        if (TotalTime > 1./FPS) {
            TotalTime = 0;
            return true;
        }
        return false;
    }



    //Отрисовка изображения
    @Override
    public void Render(GraphicsContext gc){
        TotalTime += 1./100;
        if (IV == null)
            RenderImage(gc);
        else
            RenderImageView();
    }

    @Override
    public void RenderImage(GraphicsContext gc){
        gc.save();
        gc.translate(x, y);
        gc.rotate(rotation);
        gc.translate(-this.Size / 2, -this.Size / 2);
        if (CheckFPS())
            gc.drawImage(NextImage(), 0, 0);
        else
            gc.drawImage(ArrayImage.get(ImageIndex), 0 , 0);
        gc.restore();
    }

    @Override
    public void RenderImageView(){
        if (CheckFPS())
            IV.setImage(NextImage());
        else
            IV.setImage(ArrayImage.get(ImageIndex));
    }

}
