package sample.Server.Model.Rectangle;

//Класс прямоугольник: Служит коллизией(точка соприкосновения) объектов
public class Rectangle implements IRectangle{
    private double x, y;
    private double width, height;

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
        this.width = width;
        this.height = height;
    }

    //Проверка на пересечение с другим прямоугольником
    @Override
    public boolean overlaps(Rectangle other) {
        boolean noOverlaps = this.x + this.width < other.x ||
                other.x + other.width < this.x ||
                this.y + this.height < other.y ||
                other.y + other.height < this.y;

        return !noOverlaps;
    }

}
