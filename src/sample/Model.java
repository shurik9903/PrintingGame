package sample;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


public class Model {

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

    public static class EnterToNumber{
        private double x;
        private double y;
        private final double Width, Height;
        private String Numbers;
        private final ImageView Gun;
        private ArrayList<Image> FrameNumber;


        EnterToNumber(ImageView Gun, double Width, double Height){
            Numbers = "";
            this.Width = Width;
            this.Height = Height;
            this.Gun = Gun;
            setCoordinate();
        }

        public void addNumber(Character c){
            if (Numbers.length() < 3) Numbers += c;
            else Numbers = "";
            initialize();
        }

        public int getNumbers(){
            int R = 0;
            if (Numbers.length() != 0)
                R = Integer.parseInt(Numbers);
            Numbers = "";
            initialize();
            return R;
        }

        public void initialize(){
            FrameNumber = new ArrayList<>();
            for (Character i: String.valueOf(Numbers).toCharArray())
                FrameNumber.add(new Image("Image/NumberImage/"+ i +".png", Width,Height,false,false));

        }

        //Установка координат формы
        public void setCoordinate(){
                x = Gun.getLayoutX();
                y = Gun.getLayoutY() + Gun.getFitHeight()-Height;
        }

        //Отрисовка объекта
        public void render(GraphicsContext gc){
            gc.save();

            gc.translate(this.x, this.y);
            if (FrameNumber != null)
                for (Image i : this.FrameNumber) {
                    gc.translate(+ this.Width,0);
                    gc.drawImage(i, 0, 0);
                }

            gc.restore();
        }

    }

    public int setID(ArrayList<Meteor> meteors){

        if (meteors.size() == 0) return 1;

        ArrayList<Integer> BusyID = new ArrayList<>();
        for (Meteor meteor : meteors)
            BusyID.add(meteor.ID);
        for (int i = 1; i < 1000; i++){
            if (!BusyID.contains(i)) return i;
        }
        return 0;
    }

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

    public int IndexNumber(ArrayList<String> arr){
        ArrayList<Character> num = new ArrayList<>(
                Arrays.asList('0','1','2','3','4','5','6','7','8','9'));

        for (Character i: num)
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

        public double getAngleToTarget(Sprite other){

            Point2D vector =
                    new Point2D(other.position.x - position.x, other.position.y - position.y);
            double angle = vector.angle(1, 0);
            if (vector.getY() > 0)
                return angle;
            else
                return -angle;
        }

    }

    //Класс Метеорит: Содержит описание метеоритов и их поведение
	public static class Meteor extends Sprite{

		double BoxHeight, BoxWidth;
		public TextToObject Text;
		public final int ID;

		public Meteor(String fileImageName,double BoxHeight, double BoxWidth, int ID ){
			super(fileImageName, new Model().getRandomNumber(50,150));
			this.BoxHeight = BoxHeight;
			this.BoxWidth = BoxWidth;
			position.set(new Model().getRandomNumber(-200, (int)BoxWidth+200), -100);
			velocity.setLength(25);
			rotation = setFall();
			this.ID = ID;
            velocity.setAngle(rotation);
            boundary.setSize(20,20);
			Text = new TextToObject(this.position.x, this.position.y + this.image.getHeight()/2,
                    25,25, new Model().getRandomWord(), ID);
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

    //Класс форма с текстом: расположение, длина и текст формы
    public static class TextToObject{

        public boolean Destroy;
        int NumberToHits;
        public double x,y;
        public double Width, Height;
        public String Text;
        public ArrayList<Image> FrameImage, FrameNumber;
        public ArrayList<ArrayList<MyImage>> FrameText;
        private final int MeteorID;

        public TextToObject(double x, double y, double Width, double Height, String Text, int ID){
            this.Text = Text;
            this.Width = Width;
            this.Height = Height;
            Destroy = false;
            NumberToHits = 0;
            MeteorID = ID;
            CreateFrame();
            setCoordinate(x, y);
            System.out.println(Text);
        }

        //Установка координат формы
        public void setCoordinate(double x, double y){
            this.x = x - (((2 + Text.length() + (int)(MeteorID / 10)) * this.Width) / 2);
            this.y = y + 10;
        }

        //Описание формы и текста
        public void CreateFrame(){

            Image FStart = new Image("Image/R1.png", this.Width,this.Height,false,false);
            Image FEnd = new Image("Image/R3.png", this.Width,this.Height,false,false);
            Image FMiddle = new Image("Image/R2.png", this.Width,this.Height,false,false);

            this.FrameImage = new ArrayList<>();
            this.FrameImage.add(FStart);
            for (int i = 0; i < Text.length() + (int)(MeteorID / 10); i++){
                this.FrameImage.add(FMiddle);
            }
            this.FrameImage.add(FEnd);

            FrameNumber = new ArrayList<>();
            for (Character i: String.valueOf(MeteorID).toCharArray())
                FrameNumber.add(new Image("Image/NumberImage/"+ i +".png", this.Width,this.Height,false,false));

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
        public boolean ChangeImage(Character c){
            for (ArrayList<MyImage> i : FrameText) {
                if (i.get(0).ImageName.equals(c.toString()) && !i.get(0).Type) {
                    MyImage m = i.get(0);
                    i.set(0, i.get(1));
                    i.set(1, m);
                    NumberToHits++;
                    if (NumberToHits == FrameText.size())
                        Destroy = true;
                    return true;
                }
                if (!i.get(0).ImageName.equals(c.toString()) && !i.get(0).Type)
                    return false;
            }
            return false;
        }

        //Отрисовка объекта
        public void render(GraphicsContext gc){
            gc.save();

            int TextCount = 0;
            int NumberCount = 0;

            gc.translate(this.x, this.y);

            for (Image i : this.FrameImage) {
                gc.translate(+ this.Width,0);
                gc.drawImage(i, 0, 0);

                if (NumberCount < FrameNumber.size()) {
                    gc.drawImage(this.FrameNumber.get(NumberCount), 0, 0);
                    NumberCount++;
                } else {
                    if (TextCount < Text.length()) {
                        gc.drawImage(this.FrameText.get(TextCount).get(0), 0, 0);
                        TextCount++;
                    }
                }
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

        public void AimToMeteor(ArrayList<Meteor> meteor, int Numb){
            TargetCaught = false;
            if (meteor.size() == 0) {
                TargetMeteor = null;
                velocity.setLength(0);
                return;
            }

            for (Meteor m: meteor) {
                NumberToMeteor++;
                while (meteor.size() <= NumberToMeteor)
                    NumberToMeteor -= meteor.size();
                if (m.ID == Numb) {
                    velocity.setLength(300);
                    TargetMeteor = m;
                }
            }
        }

        //Движение к нацеленному метеориту
        public void MoveToTarget(){

            if (TargetMeteor.Text.Destroy) {
                TargetCaught = false;
                TargetMeteor = null;
                return;
            }

            if (this.overlaps(TargetMeteor))
                TargetCaught = true;

            if (TargetCaught){
                  this.position.y = TargetMeteor.position.y;
                  this.position.x = TargetMeteor.position.x;
                  velocity.setLength(0);
                  return;
            }

            velocity.setAngle(getAngleToTarget(TargetMeteor));
        }

        //Обновление объекта
        @Override
        public void update(double deltaTime){
            super.update(deltaTime);
            if (TargetMeteor != null)
                MoveToTarget();
        }
    }

    //Класс снаряда
    public static class Projectile extends Sprite {

        private Meteor meteor;
        private boolean Miss;
        public boolean Destroy;
        private final char Letter;
        double BoxHeight, BoxWidth;

        public Projectile(double StartX, double StartY, char Letter, double BoxHeight, double BoxWidth, Meteor meteor) {
            super("Image/bullet.png",25);
            position.x = StartX;
            position.y = StartY;
            velocity.setLength(500);
            boundary.setSize(10,10);
            Miss = false;
            Destroy = false;
            this.Letter = Letter;
            this.meteor = meteor;
            this.BoxHeight = BoxHeight;
            this.BoxWidth = BoxWidth;
        }

        private void MoveAndFireToTarget(){
            if (meteor == null) return;
            if (overlaps(meteor) && !Miss) {
                if (!meteor.Text.ChangeImage(Letter)) Miss = true;
                else Destroy = true;
            } else if (Miss){
                if (position.x  < -50 ||
                        BoxWidth + 50 < position.x ||
                        position.y  < -50 ||
                        BoxHeight + 50 < position.y) Destroy = true;
            } else {
                rotation = getAngleToTarget(meteor);
                velocity.setAngle(rotation);
            }
        }


        //Обновление объекта
        @Override
        public void update(double deltaTime) {
            super.update(deltaTime);
            MoveAndFireToTarget();
        }

    }
}

