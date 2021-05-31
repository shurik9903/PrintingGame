package sample.Client.Model.Gif;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface IGif {
    //Ввод кординат отображения рисунка
    void setCoordinate(double x, double y);

    //Ввод поворота рисунка
    void setRotation(double rotation);

    //Загрузка всех изображений из введеной папки в список
    void LoadGif(String Name);

    //Получение следующего изображения в списке по кругу
    Image NextImage();

    //Проверка на кадры в секунду
    boolean CheckFPS();

    //Отрисовка изображения
    void Render(GraphicsContext gc);

    void RenderImage(GraphicsContext gc);

    void RenderImageView();
}
