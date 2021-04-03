package sample;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


public class Model {

    static class MyImage extends Image{

        private String ImageName;
        private boolean Type;

        public MyImage(String s, double v, double v1, boolean b, boolean b1, String ImageName, boolean Type) {
            super(s, v, v1, b, b1);
            this.ImageName = ImageName;
            this.Type = Type;
        }

        public void setName(String ImageName){
            this.ImageName = ImageName;
        }

        public void setType(boolean Type){
            this.Type = Type;
        }

        public String getName(){
            return this.ImageName;
        }

        public boolean getType(){
            return this.Type;
        }

    }

	//Функция получение случайных чисел в диапазоне
    public int getRandomNumber(int min , int max){
        if (min > max) return  max + new Random().nextInt(min - max + 1);
        else return  min + new Random().nextInt(max - min + 1);
    }

    //Функиця перевода английских букв в русские
    public String RusText(Character c){

        c = Character.toLowerCase(c);
        if (c == 'ё') c = 'е';

        ArrayList<Character> rus = new ArrayList<>(
                Arrays.asList('й','ц','у','к','е','н','г','ш','щ','з','х',
                        'ъ','ф','ы','в','а','п','р','о','л','д','ж',
                        'э','я','ч','с','м','и','т','ь','б','ю','е'));

        ArrayList<Character> eng = new ArrayList<>(
                Arrays.asList('q','w','e','r','t','y','u','i','o','p','[',
                        ']','a','s','d','f','g','h','j','k','l',';',
                        '\'','z','x','c','v','b','n','m',',','.','`'));

        if (rus.contains(c)) return c.toString();
        if (eng.contains(c)) return rus.get(eng.indexOf(c)).toString();
        return "";
    }

    public int IndexRusEng(ArrayList<String> arr){

        ArrayList<Character> rus = new ArrayList<>(
                Arrays.asList('й','ц','у','к','е','н','г','ш','щ','з','х',
                        'ъ','ф','ы','в','а','п','р','о','л','д','ж',
                        'э','я','ч','с','м','и','т','ь','б','ю','е'));

        ArrayList<Character> eng = new ArrayList<>(
                Arrays.asList('q','w','e','r','t','y','u','i','o','p','[',
                        ']','a','s','d','f','g','h','j','k','l',';',
                        '\'','z','x','c','v','b','n','m',',','.','`'));

        for (Character i: rus)
            if (arr.contains(i.toString()))
                return arr.indexOf(i.toString());

        for (Character i: eng)
            if (arr.contains(i.toString()))
                return arr.indexOf(i.toString());

        return -1;

    }


    //Функция получения слачайного слово из файла словаря
    public String getRandomWord(){
        try (Stream<String> lines = Files.lines(Paths.get("./src/sample/Text.txt"), StandardCharsets.UTF_8)) {
            return lines.skip(getRandomNumber(0,10392)).findFirst().get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

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

        public double getAngle(){
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

        //Расчет угла между двумя векторами
        public double getAngle2Vectors(double x1, double y1, double x2, double y2){
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

        //Проверка на пересечение с другим прямоугольником
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
        public void render(GraphicsContext gc){

            gc.save();

            gc.translate(this.position.x, this.position.y);
            gc.rotate(this.rotation);
            gc.translate(-this.image.getWidth()/2,-this.image.getHeight()/2);
            gc.drawImage(this.image,0,0);

            gc.restore();
        }

    }

    //Класс Метеорит: Содержит описание метеоритов и их поведение
	public static class Meteor extends Sprite{

		double BoxHeight, BoxWidth;
		public TextToObject Text;

		public Meteor(String fileImageName,double BoxHeight, double BoxWidth ){
			super(fileImageName, new Model().getRandomNumber(50,150));
			this.BoxHeight = BoxHeight;
			this.BoxWidth = BoxWidth;
			position.set(new Model().getRandomNumber(-200, (int)BoxWidth+200), -100);
			velocity.setLength(25);
			rotation = setFall();
            velocity.setAngle(rotation);
            boundary.setSize(10,10);
			Text = new TextToObject(this.position.x, this.position.y + this.image.getHeight()/2,
                    25,25, new Model().getRandomWord());
		}

		//Угол падения между случайным минимальным и максимальным углом
		public double setFall(){
		    //Если метеорит появится в левой половине окна
			if (this.position.x <= BoxWidth/2) {
			    //Если метеорит появится близко к границе или за границей окна
			    if (this.position.x < BoxWidth*0.2){
                    return 90 - new Model().getRandomNumber(
                            (int)position.getAngle2Vectors(position.x, BoxHeight,
                                    BoxWidth/2, BoxHeight),
                            (int)position.getAngle2Vectors(position.x, BoxHeight,
                                    BoxWidth, BoxHeight));
                }else{
                    return 90 - new Model().getRandomNumber(
                            -(int)position.getAngle2Vectors(position.x, BoxHeight,
                                    0, BoxHeight),
                            (int)position.getAngle2Vectors(position.x, BoxHeight,
                                    BoxWidth, BoxHeight));
                }
            } else {
                if (this.position.x > BoxWidth - BoxWidth*0.2){
                    return 90 + new Model().getRandomNumber(
                            (int)position.getAngle2Vectors(position.x,BoxHeight,
                                    BoxWidth/2, BoxHeight),
                            (int)position.getAngle2Vectors(position.x, BoxHeight,
                                    0, BoxHeight));
                }else{
                    return 90 + new Model().getRandomNumber(
                            -(int)position.getAngle2Vectors(position.x,BoxHeight,
                                    BoxWidth, BoxHeight),
                            (int)position.getAngle2Vectors(position.x, BoxHeight,
                                    0, BoxHeight));
                }
            }
		}

		//Обновление объекта
		@Override
        public void update(double deltaTime){
            super.update(deltaTime);
            this.Text.setCoordinate(this.position.x, this.position.y + this.image.getHeight()/2);
        }

        //отрисовка объекта
		@Override
        public void render(GraphicsContext gc){
            super.render(gc);
            this.Text.render(gc);
        }

	}

    //Класс настройки игры: Содержит описание запущенной игры
    public static class GameOptions{
        public int Difficulty;
        public int MeteorsCount;
        public double gameTime;

        GameOptions(int Difficulty, int MeteorsCount){
            this.Difficulty = Difficulty;
            this.MeteorsCount = MeteorsCount * Difficulty;
            this.gameTime = 0;
        }
    }

    //Класс форма с текстом: расположение, длина и текст формы
    public static class TextToObject{

        public boolean Destroy;
        int NumberToHits;
        public double x,y;
        public double Width, Height;
        public int CountWord;
        public String Text;
        public ArrayList<Image> FrameImage;
        public ArrayList<ArrayList<MyImage>> FrameText;

        public TextToObject(double x, double y, double Width, double Height, String Text){
            this.Text = Text;
            this.CountWord = Text.length();
            this.Width = Width;
            this.Height = Height;
            CreateFrame();
            setCoordinate(x, y);
            Destroy = false;
            NumberToHits = 0;
        }

        //Установка координат формы
        public void setCoordinate(double x, double y){
            this.x = x - (((2 + this.CountWord) * this.Width) / 2);
            this.y = y + 10;
        }

        //Описание формы и текста
        public void CreateFrame(){
            Image FStart = new Image("Image/R1.png", this.Width,this.Height,false,false);
            Image FEnd = new Image("Image/R3.png", this.Width,this.Height,false,false);
            Image FMiddle = new Image("Image/R2.png", this.Width,this.Height,false,false);

            this.FrameImage = new ArrayList<>();
            this.FrameImage.add(FStart);
            for (int i = 0; i < this.CountWord; i++){
                this.FrameImage.add(FMiddle);
            }
            this.FrameImage.add(FEnd);

            this.FrameText = new ArrayList<>();
            for (Character i : this.Text.toCharArray()){
                this.FrameText.add(new ArrayList<MyImage>());
                FrameText.get(FrameText.size()-1).add(new MyImage("Image/TextImage/"+i+"unbroken.png",
                        this.Width,this.Height,false,false, i.toString(),false));
                FrameText.get(FrameText.size()-1).add(new MyImage("Image/TextImage/"+i+"broken.png",
                        this.Width,this.Height,false,false, i.toString(),true));



            }

        }


        //Смена изображения с целой буквы на поломанную и проверка на уничтожение метеорита
        public void ChangeImage(Character c){
            for (ArrayList<MyImage> i : FrameText) {
                if (i.get(0).ImageName.equals(c.toString()) && !i.get(0).Type) {
                    MyImage m = i.get(0);
                    i.set(0, i.get(1));
                    i.set(1, m);
                    NumberToHits++;
                    if (NumberToHits == FrameText.size())
                        Destroy = true;
                    return;
                }
                if (!i.get(0).ImageName.equals(c.toString()) && !i.get(0).Type)
                    return;

            }

        }

        //Отрисовка объекта
        public void render(GraphicsContext gc){
            gc.save();

            int Count = 0;
            gc.translate(this.x, this.y);

            for (Image i : this.FrameImage) {
                gc.translate(+ this.Width,0);

                gc.drawImage(i, 0, 0);
                if (Count <= this.CountWord && Count > 0)
                    gc.drawImage(this.FrameText.get(Count-1).get(0), 0, 0);
                Count++;
            }

            gc.restore();
        }

    }

    //Класс прицел: координаты, цель и описание пойманного в прицел объекта
    public static class Aim extends Sprite{

        double BoxHeight, BoxWidth;
        Meteor TargetMeteor;
        int NumberToMeteor;
        boolean TargetCaught;

        public Aim(double BoxHeight, double BoxWidth){
            super("Image/Aim.png", 100);
            this.BoxWidth = BoxWidth;
            this.BoxHeight = BoxHeight;
            this.boundary.setSize(10,10);
            TargetMeteor = null;
            NumberToMeteor = -1;
            TargetCaught = false;
            position.set(this.BoxWidth/2, this.BoxHeight/2);
            velocity.setLength(0);
        }

        //Нацеливание на метеорит из спика
        public void AimToMeteor(ArrayList<Meteor> meteor, boolean LR){

            TargetCaught = false;

            if (meteor.size() == 0) {
                TargetMeteor = null;
                velocity.setLength(0);
                return;
            }

            if (LR) {
                NumberToMeteor++;
                while (meteor.size() <= NumberToMeteor)
                    NumberToMeteor -= meteor.size();
            } else {
                NumberToMeteor--;
                while (NumberToMeteor < 0)
                    NumberToMeteor = meteor.size()-1;
            }

            velocity.setLength(300);
            TargetMeteor = meteor.get(NumberToMeteor);
        }

        //Движение к нацеленному метеориту
        public void MoveToTarget(){
          if (this.overlaps(TargetMeteor))
                TargetCaught = true;

            if (TargetCaught){
                this.position.y = TargetMeteor.position.y;
                this.position.x = TargetMeteor.position.x;
                velocity.setLength(0);
                return;
            }

            Point2D vector =
                    new Point2D(TargetMeteor.position.x - position.x, TargetMeteor.position.y - position.y);
            double angle = vector.angle(1, 0);
            if (vector.getY() > 0)
                velocity.setAngle(angle);
            else
                velocity.setAngle(-angle);
        }

        //Обновление объекта
        @Override
        public void update(double deltaTime){
            super.update(deltaTime);
            if (TargetMeteor != null)
                MoveToTarget();
        }

        //Отрисовка объекта
        @Override
        public void render(GraphicsContext gc){
            super.render(gc);

        }

    }

    public static class Bullet extends Sprite{

        public Bullet(double startX, double startY, Meteor meteor){
            super("Image/bullet.png",25);
            position.set(startX, startY);
            velocity.setLength(50);
            MoveToTarget(meteor);
        }

        public void MoveToTarget(Meteor meteor){

            LeadCalculation(meteor);
            velocity.setAngle(rotation);
        }

        public void LeadCalculation(Meteor meteor){

            System.out.println("Meteor: " + meteor.position.x +" | "+meteor.position.y);
            System.out.println("Bullet: " + position.x + " | " + position.y);
            double distance = position.getLength( meteor.position.x - position.x, position.y - meteor.position.y - position.y);
            System.out.println("Distance: " + distance);
            double Time = distance/velocity.x;


            Point2D A =
                    new Point2D(meteor.position.x - position.x, meteor.position.y  - position.y);
            double angle1 = A.angle(1, 0);
            if (A.getY() > 0)
                angle1 = angle1;
            else
                angle1 = -angle1;
            System.out.println("X: " + (meteor.position.x - position.x));
            System.out.println("Y: " + (meteor.position.y  - position.y));
            System.out.println("Angle1: " + angle1);
            //
            Point2D B =
                    new Point2D(meteor.position.x + (meteor.velocity.x * Time) - position.x,
                            meteor.position.y + (meteor.velocity.y * Time) - position.y);
            double angle2 = B.angle(1, 0);
            if (B.getY() > 0)
                angle2 = angle2;
            else
                angle2 = -angle2;

            rotation = angle2;

            System.out.println("X: " + (meteor.position.x + (meteor.velocity.x * Time) - position.x));
            System.out.println("Y: " + (meteor.position.y + (meteor.velocity.y * Time) - position.y));
            System.out.println("Angle2: " + angle2);

            //System.out.println(rotation);




        }
/*
        //Огонь по метеориту
        public void Fire(Character c){
                TargetMeteor.Text.ChangeImage(c);
        }
        */

        //Обновление объекта
        @Override
        public void update(double deltaTime){
            super.update(deltaTime);
        }

        //Отрисовка объекта
        @Override
        public void render(GraphicsContext gc){
            super.render(gc);
        }

    }
}
