package sample;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;

public class Method {
    //Класс вектор: Расположение, направление, скорость движения
    public static class Vector{

        public double x,y;

        public Vector(){
            this.set(0,0);
        }

        public Vector(double x, double y){
            this.set(x,y);
        }

        public void set(double x, double y){
            this.x = x;
            this.y = y;
        }

        public void add(double x, double y){
            this.x += x;
            this.y += y;
        }

        public void multiply(double m){
            this.x *= m;
            this.y *= m;
        }

        public double getLength(){
            return Math.sqrt(this.x * this.x + this.y * this.y);
        }

        public double getLength(double x, double y){
            return Math.sqrt(x * x + y * y);
        }

        public void setLength(double L){
            double currentLength = this.getLength();
            if (currentLength == 0){
                this.set(L,0);
            }else {
                this.multiply(1 / currentLength);

                this.multiply(L);
            }
        }

        public double getAngle()
        {
            if (this.getLength() == 0)
                return 0;
            else
                return Math.toDegrees( Math.atan2(this.y, this.x) );
        }

        public void setAngle(double angleDegrees){
            double L = this.getLength();
            double angleRadian = Math.toRadians(angleDegrees);
            this.x = L * Math.cos(angleRadian);
            this.y = L * Math.sin(angleRadian);
        }

        public  double getAngle2Vectors(double x1, double y1, double x2, double y2){
            x1 -= this.x;
            y1 -= this.y;
            x2 -= this.x;
            y2 -= this.y;
            System.out.println(x1 +" "+ y1 +" "+ x2 +" "+ y2);
            double ma = getLength(x1,y1);
            double mb = getLength(x2,y2);
            double sc = (x1 * x2) + (y1 * y2);
            System.out.println("Radius " + Math.toDegrees(Math.acos(sc / (ma * mb))));
            return  Math.toDegrees(Math.acos(sc / (ma * mb)));
        }


    }


    //Класс прямоугольник: Служит коллизией(точка соприкосновения) объектов
    public static class Rectangle{
        double x, y;
        double width, height;


        public Rectangle(){
            setPosition(0,0);
            setSize(0, 0);
        }

        public Rectangle(double x, double y, double width, double height){
            setPosition(x, y);
            setSize(width, height);
        }

        public void setPosition(double x, double y){
            this.x = x;
            this.y = y;
        }

        public void setSize(double width, double height){
            this.width = width;
            this.height = height;
        }

        public boolean overlaps(Rectangle other){
            boolean noOverlaps = this.x + this.width < other.x ||
                    other.x + other.width < this.x ||
                    this.y + this.height < other.y ||
                    other.y + other.height < this.y;

            return !noOverlaps;
        }

    }

    //Класс обекта: Содержит описание объекта
    public static class Sprite {

        public Vector position;
        public Vector velocity;
        public double rotation;
        public Rectangle boundary;
        public Image image;
        public double liveTime;

        public Sprite(){
            this.position = new Vector();
            this.velocity = new Vector();
            this.rotation = 0;
            this.boundary = new Rectangle();
            this.liveTime = 0;
        }

        public Sprite(String fileImageName, double sizeImage){
            this();
            setImage(fileImageName, sizeImage);
        }

        public Sprite(String fileImageName){
            this();
            setImage(fileImageName);
        }

        public void setImage(String fileImageName, double sizeImage){
            this.image = new Image(fileImageName, sizeImage,sizeImage,false,false);
        }

        public void setImage(String fileImageName){
            this.image = new Image(fileImageName);
        }

        public Rectangle getBoundary(){
            this.boundary.setPosition(this.position.x, this.position.y);
            return this.boundary;
        }

        //Расчет соприкосновение с другим объектом
        public boolean overlaps(Sprite other){
            return this.getBoundary().overlaps(other.getBoundary());
        }

        //Обновление объекта
        public void update(double deltaTime){
            this.liveTime += deltaTime;
            this.position.add(this.velocity.x * deltaTime, this.velocity.y * deltaTime);
        }

        //Отрисовка обекта на экране
        public void render(GraphicsContext gc, Canvas canvas){

            gc.save();

            gc.translate(this.position.x, this.position.y);
            gc.rotate(this.rotation);
            gc.translate(-this.image.getWidth()/2,-this.image.getHeight()/2);
            gc.drawImage(this.image,0,0);

            gc.restore();
        }

    }

    //Класс настройки игры: Содержит описание запущенной игры
    public static class GameOptions{
        public int Difficulty;
        public int MeteorsCount;
        public double gameTime;

        GameOptions(){
            this.Difficulty = 0;
            this.MeteorsCount = 0;
            this.gameTime = 0;
        }

        GameOptions(int Difficulty, int MeteorsCount){
            this.Difficulty = Difficulty;
            this.MeteorsCount = MeteorsCount * Difficulty;
            this.gameTime = 0;
        }



    }

    //Функция получение случайных чисел в диапазоне
    public double getRandomNumber(int min , int max){
        System.out.println("min " + min + " max " + max);
        double res;
        if (min > max) res =  max + new Random().nextInt(min - max + 1);
        else res =  min + new Random().nextInt(max - min + 1);
        System.out.println("Res " + res);
        return res;
    }
}
