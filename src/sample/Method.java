package sample;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;

public class Method {
	
	//Функция получение случайных чисел в диапазоне
    public double getRandomNumber(int min , int max){
        if (min > max) return  max + new Random().nextInt(min - max + 1);
        else return  min + new Random().nextInt(max - min + 1);
    }
	
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

        //Скорость объекта
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

        //Расчет угла движения объекта
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
            double ma = getLength(x1,y1);
            double mb = getLength(x2,y2);
            double sc = (x1 * x2) + (y1 * y2);
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
	
	public static class Meteor extends Sprite{
		
		double BoxHeight, BoxWidth;
		
		public Meteor(){
			super();
			BoxHeight = 0;
			BoxWidth = 0;
		}
		
		public Meteor(String fileImageName,double BoxHeight, double BoxWidth ){
			super(fileImageName, new Method().getRandomNumber(50,150));
			this.BoxHeight = BoxHeight;
			this.BoxWidth = BoxWidth;
			position.set(new Method().getRandomNumber(-200, (int)BoxWidth+200), -100);
			velocity.setLength(50);
			rotation = setFall();
			velocity.setAngle(rotation);
			
		}

		//Угол падения между случайным минимальным и максимальным углом
		public double setFall(){
		    //Если метеорит появится в левой половине окна
			if (this.position.x <= BoxWidth/2) {
			    //Если метеорит появится близко к границе или за границей окна
			    if (this.position.x < BoxWidth*0.2){
                    return 90 - new Method().getRandomNumber(
                            (int)position.getAngle2Vectors(position.x, BoxHeight,
                                    BoxWidth/2, BoxHeight),
                            (int)position.getAngle2Vectors(position.x, BoxHeight,
                                    BoxWidth, BoxHeight));
                }else{
                    return 90 - new Method().getRandomNumber(
                            -(int)position.getAngle2Vectors(position.x, BoxHeight,
                                    0, BoxHeight),
                            (int)position.getAngle2Vectors(position.x, BoxHeight,
                                    BoxWidth, BoxHeight));
                }
            } else {
                if (this.position.x > BoxWidth - BoxWidth*0.2){
                    return 90 + new Method().getRandomNumber(
                            (int)position.getAngle2Vectors(position.x,BoxHeight,
                                    BoxWidth/2, BoxHeight),
                            (int)position.getAngle2Vectors(position.x, BoxHeight,
                                    0, BoxHeight));
                }else{
                    return 90 + new Method().getRandomNumber(
                            -(int)position.getAngle2Vectors(position.x,BoxHeight,
                                    BoxWidth, BoxHeight),
                            (int)position.getAngle2Vectors(position.x, BoxHeight,
                                    0, BoxHeight));
                }
            }
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
  
}
