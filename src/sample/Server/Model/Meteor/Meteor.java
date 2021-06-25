package sample.Server.Model.Meteor;

import sample.Data.DataInterface.IImageDate;
import sample.Server.Model.MyFunction.MyFunctionFactory;
import sample.Server.Model.MyImage.IMyImage;
import sample.Server.Model.Rectangle.IRectangle;
import sample.Server.Model.ServerFactory.ServerFactory;
import sample.Server.Model.Sprite.ISprite;
import sample.Server.Model.Sprite.SpriteFactory;
import sample.Server.Model.TextToObject.ITextToObject;
import sample.Server.Model.TextToObject.TextToObjectFactory;

import java.util.ArrayList;

//Класс Метеорит: Содержит описание метеоритов и их поведение
public class Meteor implements IMeteor, ISprite, ITextToObject{

    private final ISprite basic;
    private final double BoxHeight,BoxWidth;
    private final ITextToObject Text;
    private final int ID;
    private boolean Fall = false;

    public Meteor(double BoxHeight, double BoxWidth, int ID, int speed) {
        basic = SpriteFactory.CreateInstance("Image/Asteroid.png", MyFunctionFactory.CreateInstance().getRandomNumber(50, 150));
        this.BoxHeight = BoxHeight;
        this.BoxWidth = BoxWidth;
        PosSet(MyFunctionFactory.CreateInstance().getRandomNumber(-200, (int) BoxWidth + 200), -100);
        setVelLength(speed);
        setRotation(setFall());
        this.ID = ID;
        setVelAngle(getRotation());
        setRecSize(20, 20);
        Text = TextToObjectFactory.CreateInstance(getPosX(), getPosY() + getImageHeight() / 2,
                25, 25, MyFunctionFactory.CreateInstance().getRandomWord(), ID);
    }

    //Угол падения между случайным минимальным и максимальным углом
    public double setFall() {
        //Если метеорит появится в левой половине окна
        if (getPosX() <= BoxWidth / 2) {
            //Если метеорит появится близко к границе или за границей окна
            if (getPosX() < BoxWidth * 0.2) {
                return 90 - MyFunctionFactory.CreateInstance().getRandomNumber(
                        (int) getPosAngle2Vectors(getPosX(), BoxHeight,
                                BoxWidth / 2, BoxHeight),
                        (int) getPosAngle2Vectors(getPosX(), BoxHeight,
                                BoxWidth, BoxHeight));
            } else {
                return 90 - MyFunctionFactory.CreateInstance().getRandomNumber(
                        -(int) getPosAngle2Vectors(getPosX(), BoxHeight,
                                0, BoxHeight),
                        (int) getPosAngle2Vectors(getPosX(), BoxHeight,
                                BoxWidth, BoxHeight));
            }
        } else {
            if (getPosX() > BoxWidth - BoxWidth * 0.2) {
                return 90 + MyFunctionFactory.CreateInstance().getRandomNumber(
                        (int) getPosAngle2Vectors(getPosX(), BoxHeight,
                                BoxWidth / 2, BoxHeight),
                        (int) getPosAngle2Vectors(getPosX(), BoxHeight,
                                0, BoxHeight));
            } else {
                return 90 + MyFunctionFactory.CreateInstance().getRandomNumber(
                        -(int) getPosAngle2Vectors(getPosX(), BoxHeight,
                                BoxWidth, BoxHeight),
                        (int) getPosAngle2Vectors(getPosX(), BoxHeight,
                                0, BoxHeight));
            }
        }
    }

    @Override
    public int getID(){
        return ID;
    }

    @Override
    public void setImage(String fileImageName, double sizeImage) {
        basic.setImage(fileImageName, sizeImage);
    }

    @Override
    public void setImage(String fileImageName, double Width, double Height) {
        basic.setImage(fileImageName, Width, Height);
    }

    @Override
    public void setImage(String fileImageName) {
        basic.setImage(fileImageName);
    }

    @Override
    public double getAngleToTarget(ISprite other) {
        return basic.getAngleToTarget(other);
    }

    //Обновление объекта
    @Override
    public void update(double deltaTime) {
        basic.update(deltaTime);
        setTextCoordinate(getPosX(),
                getPosY() + getImageHeight() / 2);
        if (getPosY() > BoxHeight - 50) Fall = true;
    }

    @Override
    public double getRotation() {
        return basic.getRotation();
    }

    @Override
    public void setRotation(double rotation) {
        basic.setRotation(rotation);
    }

    @Override
    public boolean isFall() {
        return Fall;
    }

    @Override
    public double getImageWidth() {
        return basic.getImageWidth();
    }

    @Override
    public double getImageHeight() {
        return basic.getImageHeight();
    }

    @Override
    public String getFileImageName() {
        return basic.getFileImageName();
    }

    @Override
    public double getRecX() {
        return basic.getRecX();
    }

    @Override
    public double getRecY() {
        return basic.getRecY();
    }

    @Override
    public double getRecWidth() {
        return basic.getRecWidth();
    }

    @Override
    public double getRecHeight() {
        return basic.getRecHeight();
    }

    @Override
    public void setRecPosition(double x, double y) {
        basic.setRecPosition(x, y);
    }

    @Override
    public void setRecSize(double width, double height) {
        basic.setRecSize(width, height);
    }

    @Override
    public boolean RecOverlaps(IRectangle other) {
        return basic.RecOverlaps(other);
    }

    @Override
    public double getPosX() {
        return basic.getPosX();
    }

    @Override
    public double getPosY() {
        return basic.getPosY();
    }

    @Override
    public void setPosX(double x) {
        basic.setPosX(x);
    }

    @Override
    public void setPosY(double y) {
        basic.setPosY(y);
    }

    @Override
    public void PosSet(double x, double y) {
        basic.PosSet(x, y);
    }

    @Override
    public void PosAdd(double x, double y) {
        basic.PosAdd(x, y);
    }

    @Override
    public void PosMultiply(double m) {
        basic.PosMultiply(m);
    }

    @Override
    public double getPosLength() {
        return basic.getPosLength();
    }

    @Override
    public double getPosLength(double x, double y) {
        return basic.getPosLength(x, y);
    }

    @Override
    public void setPosLength(double L) {
        basic.setPosLength(L);
    }

    @Override
    public void setPosAngle(double angleDegrees) {
        basic.setPosAngle(angleDegrees);
    }

    @Override
    public double getPosAngle2Vectors(double x1, double y1, double x2, double y2) {
        return basic.getPosAngle2Vectors(x1, y1, x2, y2);
    }

    @Override
    public double getVelX() {
        return basic.getVelX();
    }

    @Override
    public double getVelY() {
        return basic.getVelY();
    }

    @Override
    public void setVelX(double x) {
        basic.setVelX(x);
    }

    @Override
    public void setVelY(double y) {
        basic.setVelY(y);
    }

    @Override
    public void VelSet(double x, double y) {
        basic.VelSet(x, y);
    }

    @Override
    public void VelAdd(double x, double y) {
        basic.VelAdd(x, y);
    }

    @Override
    public void VelMultiply(double m) {
        basic.VelMultiply(m);
    }

    @Override
    public double getVelLength() {
        return basic.getVelLength();
    }

    @Override
    public double getVelLength(double x, double y) {
        return basic.getVelLength(x,y);
    }

    @Override
    public void setVelLength(double L) {
        basic.setVelLength(L);
    }

    @Override
    public void setVelAngle(double angleDegrees) {
        basic.setVelAngle(angleDegrees);
    }

    @Override
    public double getVelAngle2Vectors(double x1, double y1, double x2, double y2) {
        return basic.getVelAngle2Vectors(x1, y1, x2, y2);
    }


    @Override
    public void setTextCoordinate(double x, double y) {
        Text.setTextCoordinate(x, y);
    }

    @Override
    public void CreateFrame() {
        Text.CreateFrame();
    }

    @Override
    public boolean ChangeImage(Character c) {
        return Text.ChangeImage(c);
    }

    @Override
    public String getText() {
        return Text.getText();
    }

    @Override
    public boolean isDestroy() {
        return Text.isDestroy();
    }

    @Override
    public double getTextX() {
        return Text.getTextX();
    }

    @Override
    public void setTextX(double x) {
        Text.setTextX(x);
    }

    @Override
    public double getTextY() {
        return Text.getTextY();
    }

    @Override
    public void setTextY(double y) {
        Text.setTextY(y);
    }

    @Override
    public double getTextWidth() {
        return Text.getTextWidth();
    }

    @Override
    public double getTextHeight() {
        return Text.getTextHeight();
    }

    @Override
    public void setTextHeight(double height) {
        Text.setTextHeight(height);
    }

    @Override
    public void setTextWidth(double width) {
        Text.setTextWidth(width);
    }

    @Override
    public ArrayList<IImageDate> getFrameImage() {
        return Text.getFrameImage();
    }

    @Override
    public ArrayList<IImageDate> getFrameNumber() {
        return Text.getFrameNumber();
    }

    @Override
    public ArrayList<ArrayList<IMyImage>> getFrameText() {
        return Text.getFrameText();
    }
}
