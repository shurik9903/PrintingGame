package sample.Server.Model.Aim;

import javafx.scene.image.Image;
import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.Meteor.Meteor;
import sample.Server.Model.Rectangle.IRectangle;
import sample.Server.Model.ServerFactory.ServerFactory;
import sample.Server.Model.Sprite.ISprite;
import sample.Server.Model.Sprite.SpriteFactory;

import java.util.ArrayList;

//Класс прицел: координаты, цель и описание пойманного в прицел объекта
public class Aim implements IAim, ISprite{

    private final ISprite basic;
    private final double BoxHeight, BoxWidth;
    private IMeteor TargetMeteor;
    private int NumberToMeteor;
    private boolean TargetCaught;
    private final double RestrictedArea;

    //Конструктор класса
    public Aim(double BoxWidth, double BoxHeight, double RestrictedArea) {

        basic = SpriteFactory.CreateInstance("Image/Aim.png", 100);
        this.BoxWidth = BoxWidth;
        this.BoxHeight = BoxHeight;
        setRecSize(10, 10);
        this.RestrictedArea = RestrictedArea;
        TargetMeteor = null;
        NumberToMeteor = -1;
        TargetCaught = false;
        PosSet(this.BoxWidth / 2, this.BoxHeight / 2);
        setVelLength(0);

    }

    //Нацеливание на метеорит из спика
    @Override
    public void AimToMeteor(ArrayList<IMeteor> meteor, boolean LR) {

        TargetCaught = false;
        if (meteor.size() == 0) {
            TargetMeteor = null;
            setVelLength(0);
            return;
        }

        if (LR) {
            NumberToMeteor++;
            while (meteor.size() <= NumberToMeteor)
                NumberToMeteor -= meteor.size();
        } else {
            NumberToMeteor--;
            while (NumberToMeteor < 0)
                NumberToMeteor = meteor.size() - 1;
        }

        setVelLength(500);
        TargetMeteor = meteor.get(NumberToMeteor);
    }

    //Нацеливание на метеорит c указанным номером
    @Override
    public void AimToMeteor(ArrayList<IMeteor> meteor, int Numb) {
        TargetCaught = false;
        if (meteor.size() == 0) {
            TargetMeteor = null;
            setVelLength(0);
            return;
        }

        for (IMeteor m : meteor) {
            NumberToMeteor++;
            while (meteor.size() <= NumberToMeteor)
                NumberToMeteor -= meteor.size();
            if (m.getID() == Numb) {
                setVelLength(300);
                TargetMeteor = m;
            }
        }
    }

    //Движение к нацеленному метеориту
    @Override
    public void MoveToTarget() {

        if (TargetMeteor.isDestroy() || TargetMeteor.getPosY() >= RestrictedArea) {
            TargetCaught = false;
            TargetMeteor = null;

            return;
        }

        if (basic.RecOverlaps(TargetMeteor))
            TargetCaught = true;

        if (TargetCaught) {
            setPosY(TargetMeteor.getPosY());
            setPosX(TargetMeteor.getPosX());
            setVelLength(0);
            return;
        }


        setVelAngle(getAngleToTarget(TargetMeteor));
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
        if (TargetMeteor != null)
            MoveToTarget();
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
    public IMeteor getTargetMeteor() {
        return TargetMeteor;
    }

    @Override
    public boolean getTargetCaught(){
        return TargetCaught;
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
}
