package sample.Server.Model.Rectangle;

import sample.Server.Model.Sprite.ISprite;

import javax.imageio.spi.ImageReaderSpi;

//Класс прямоугольник: Служит коллизией(точка соприкосновения) объектов
public class Rectangle implements IRectangle{
    private double x, y;
    private double Width, Height;

    //конструктор
    public Rectangle() {
        setRecPosition(0, 0);
        setRecSize(0, 0);
    }

    public Rectangle(double x, double y, double width, double height) {
        setRecPosition(x, y);
        setRecSize(width, height);
    }

    @Override
    public double getRecX(){
        return x;
    }

    @Override
    public double getRecY(){
        return y;
    }

    @Override
    public double getRecWidth(){
        return Width;
    }

    @Override
    public double getRecHeight(){
        return Height;
    }


    //установка позиции
    @Override
    public void setRecPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //установка размера
    @Override
    public void setRecSize(double width, double height) {
        this.Width = width;
        this.Height = height;
    }

    //Проверка на пересечение с другим прямоугольником
    @Override
    public boolean RecOverlaps(IRectangle other) {
        boolean noOverlaps = this.x + this.Width < other.getRecX() ||
                other.getRecX() + other.getRecWidth() < this.x ||
                this.y + this.Height < other.getRecY() ||
                other.getRecY() + other.getRecHeight() < this.y;

        return !noOverlaps;
    }

}
