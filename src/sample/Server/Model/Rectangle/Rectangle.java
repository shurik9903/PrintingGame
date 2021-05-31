package sample.Server.Model.Rectangle;

//Класс прямоугольник: Служит коллизией(точка соприкосновения) объектов
public class Rectangle implements IRectangle{
    private double x, y;
    private double Width, Height;

    @Override
    public double getX(){
        return x;
    }

    @Override
    public double getY(){
        return y;
    }

    @Override
    public double getWidth(){
        return Width;
    }

    @Override
    public double getHeight(){
        return Height;
    }


    //конструктор
    public Rectangle() {
        setPosition(0, 0);
        setSize(0, 0);
    }

    public Rectangle(double x, double y, double width, double height) {
        setPosition(x, y);
        setSize(width, height);
    }

    //установка позиции
    @Override
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //установка размера
    @Override
    public void setSize(double width, double height) {
        this.Width = width;
        this.Height = height;
    }

    //Проверка на пересечение с другим прямоугольником
    @Override
    public boolean overlaps(IRectangle other) {
        boolean noOverlaps = this.x + this.Width < other.getX() ||
                other.getX() + other.getWidth() < this.x ||
                this.y + this.Height < other.getY() ||
                other.getY() + other.getHeight() < this.y;

        return !noOverlaps;
    }

}
