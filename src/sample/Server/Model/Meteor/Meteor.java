package sample.Server.Model.Meteor;

import sample.Server.Model.MyFunction.MyFunction;
import sample.Server.Model.ServerFactory.ServerFactory;
import sample.Server.Model.Sprite.ISprite;
import sample.Server.Model.Sprite.Sprite;
import sample.Server.Model.TextToObject.ITextToObject;
import sample.Server.Model.TextToObject.TextToObject;

//Класс Метеорит: Содержит описание метеоритов и их поведение
public class Meteor implements IMeteor {

    private final ISprite basic;
    private final double BoxHeight,BoxWidth;
    private final ITextToObject Text;
    private final int ID;
    private boolean Fall = false;

    public Meteor(double BoxHeight, double BoxWidth, int ID, int speed) {
        basic = ServerFactory.SpriteCreateInstance("Image/Asteroid.png", new MyFunction().getRandomNumber(50, 150));
        this.BoxHeight = BoxHeight;
        this.BoxWidth = BoxWidth;
        basic.getPosition().set(new MyFunction().getRandomNumber(-200, (int) BoxWidth + 200), -100);
        basic.getVelocity().setLength(speed);
        basic.setRotation(setFall());
        this.ID = ID;
        basic.getVelocity().setAngle(basic.getRotation());
        basic.getBoundary().setSize(20, 20);
        Text = ServerFactory.TextToObjectCreateInstance(this.basic.getPosition().getX(), this.basic.getPosition().getY() + this.basic.getImage().getHeight() / 2,
                25, 25, new MyFunction().getRandomWord(), ID);
    }

    //Угол падения между случайным минимальным и максимальным углом
    public double setFall() {
        //Если метеорит появится в левой половине окна
        if (this.basic.getPosition().getX() <= BoxWidth / 2) {
            //Если метеорит появится близко к границе или за границей окна
            if (this.basic.getPosition().getX() < BoxWidth * 0.2) {
                return 90 - new MyFunction().getRandomNumber(
                        (int) basic.getPosition().getAngle2Vectors(basic.getPosition().getX(), BoxHeight,
                                BoxWidth / 2, BoxHeight),
                        (int) basic.getPosition().getAngle2Vectors(basic.getPosition().getX(), BoxHeight,
                                BoxWidth, BoxHeight));
            } else {
                return 90 - new MyFunction().getRandomNumber(
                        -(int) basic.getPosition().getAngle2Vectors(basic.getPosition().getX(), BoxHeight,
                                0, BoxHeight),
                        (int) basic.getPosition().getAngle2Vectors(basic.getPosition().getX(), BoxHeight,
                                BoxWidth, BoxHeight));
            }
        } else {
            if (this.basic.getPosition().getX() > BoxWidth - BoxWidth * 0.2) {
                return 90 +new MyFunction().getRandomNumber(
                        (int) basic.getPosition().getAngle2Vectors(basic.getPosition().getX(), BoxHeight,
                                BoxWidth / 2, BoxHeight),
                        (int) basic.getPosition().getAngle2Vectors(basic.getPosition().getX(), BoxHeight,
                                0, BoxHeight));
            } else {
                return 90 + new MyFunction().getRandomNumber(
                        -(int) basic.getPosition().getAngle2Vectors(basic.getPosition().getX(), BoxHeight,
                                BoxWidth, BoxHeight),
                        (int) basic.getPosition().getAngle2Vectors(basic.getPosition().getX(), BoxHeight,
                                0, BoxHeight));
            }
        }
    }

    //Обновление объекта
    @Override
    public void update(double deltaTime) {
        basic.update(deltaTime);
        this.Text.setCoordinate(this.basic.getPosition().getX(),
                this.basic.getPosition().getY() + this.basic.getImage().getHeight() / 2);
        if (basic.getPosition().getY() > BoxHeight - 50) Fall = true;
    }

}
