package sample.Model;

import javafx.scene.canvas.GraphicsContext;

//Класс Метеорит: Содержит описание метеоритов и их поведение
public class Meteor extends Sprite {

    double BoxHeight, BoxWidth;
    public TextToObject Text;
    public final int ID;
    public boolean Fall = false;

    public Meteor(double BoxHeight, double BoxWidth, int ID) {
        super("Image/Asteroid.png", new Model().getRandomNumber(50, 150));
        this.BoxHeight = BoxHeight;
        this.BoxWidth = BoxWidth;
        position.set(new Model().getRandomNumber(-200, (int) BoxWidth + 200), -100);
        velocity.setLength(new Model().getRandomNumber(10, 50));
        rotation = setFall();
        this.ID = ID;
        velocity.setAngle(rotation);
        boundary.setSize(20, 20);
        Text = new TextToObject(this.position.x, this.position.y + this.image.getHeight() / 2,
                25, 25, new Model().getRandomWord(), ID);
    }

    //Угол падения между случайным минимальным и максимальным углом
    public double setFall() {
        //Если метеорит появится в левой половине окна
        if (this.position.x <= BoxWidth / 2) {
            //Если метеорит появится близко к границе или за границей окна
            if (this.position.x < BoxWidth * 0.2) {
                return 90 - new Model().getRandomNumber(
                        (int) position.getAngle2Vectors(position.x, BoxHeight,
                                BoxWidth / 2, BoxHeight),
                        (int) position.getAngle2Vectors(position.x, BoxHeight,
                                BoxWidth, BoxHeight));
            } else {
                return 90 - new Model().getRandomNumber(
                        -(int) position.getAngle2Vectors(position.x, BoxHeight,
                                0, BoxHeight),
                        (int) position.getAngle2Vectors(position.x, BoxHeight,
                                BoxWidth, BoxHeight));
            }
        } else {
            if (this.position.x > BoxWidth - BoxWidth * 0.2) {
                return 90 + new Model().getRandomNumber(
                        (int) position.getAngle2Vectors(position.x, BoxHeight,
                                BoxWidth / 2, BoxHeight),
                        (int) position.getAngle2Vectors(position.x, BoxHeight,
                                0, BoxHeight));
            } else {
                return 90 + new Model().getRandomNumber(
                        -(int) position.getAngle2Vectors(position.x, BoxHeight,
                                BoxWidth, BoxHeight),
                        (int) position.getAngle2Vectors(position.x, BoxHeight,
                                0, BoxHeight));
            }
        }
    }

    //Обновление объекта
    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        this.Text.setCoordinate(this.position.x, this.position.y + this.image.getHeight() / 2);
        if (position.y > BoxHeight - 50) Fall = true;
    }

    //отрисовка объекта
    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
        this.Text.render(gc);
    }

}