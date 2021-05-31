package sample.Server.Model.Aim;

import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.Meteor.Meteor;
import sample.Server.Model.Rectangle.IRectangle;
import sample.Server.Model.ServerFactory.ServerFactory;
import sample.Server.Model.Sprite.ISprite;

import java.util.ArrayList;

//Класс прицел: координаты, цель и описание пойманного в прицел объекта
public class Aim implements IAim{

    private ISprite basic;
    private double BoxHeight, BoxWidth;
    private IMeteor TargetMeteor;
    private int NumberToMeteor;
    private boolean TargetCaught;
    private double RestrictedArea;

    //Конструктор класса
    public Aim(double BoxWidth, double BoxHeight, double RestrictedArea) {

        this.basic = ServerFactory.SpriteCreateInstance("Image/Aim.png", 100);
        this.BoxWidth = BoxWidth;
        this.BoxHeight = BoxHeight;
        this.basic.getBoundary().setSize(10, 10);
        this.RestrictedArea = RestrictedArea;
        TargetMeteor = null;
        NumberToMeteor = -1;
        TargetCaught = false;
        basic.getPosition().set(this.BoxWidth / 2, this.BoxHeight / 2);
        basic.getVelocity().setLength(0);

    }

    //Нацеливание на метеорит из спика
    @Override
    public void AimToMeteor(ArrayList<IMeteor> meteor, boolean LR) {

        TargetCaught = false;
        if (meteor.size() == 0) {
            TargetMeteor = null;
            basic.getVelocity().setLength(0);
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

        basic.getVelocity().setLength(500);
        TargetMeteor = meteor.get(NumberToMeteor);
    }

    //Нацеливание на метеорит c указанным номером
    @Override
    public void AimToMeteor(ArrayList<IMeteor> meteor, int Numb) {
        TargetCaught = false;
        if (meteor.size() == 0) {
            TargetMeteor = null;
            basic.getVelocity().setLength(0);
            return;
        }

        for (IMeteor m : meteor) {
            NumberToMeteor++;
            while (meteor.size() <= NumberToMeteor)
                NumberToMeteor -= meteor.size();
            if (m.getID() == Numb) {
                basic.getVelocity().setLength(300);
                TargetMeteor = m;
            }
        }
    }

    //Движение к нацеленному метеориту
    @Override
    public void MoveToTarget() {

        if (TargetMeteor.getTextObject().isDestroy() || TargetMeteor.getBasic().getPosition().getY() >= RestrictedArea) {
            TargetCaught = false;
            TargetMeteor = null;
            return;
        }

        if (this.basic.overlaps((IRectangle) TargetMeteor))
            TargetCaught = true;

        if (TargetCaught) {
            this.basic.getPosition().setY(TargetMeteor.getBasic().getPosition().getY());
            this.basic.getPosition().setX(TargetMeteor.getBasic().getPosition().getX());
            basic.getVelocity().setLength(0);
            return;
        }

        basic.getVelocity().setAngle(basic.getAngleToTarget((ISprite) TargetMeteor));
    }

    //Обновление объекта
    @Override
    public void update(double deltaTime) {
        basic.update(deltaTime);
        if (TargetMeteor != null)
            MoveToTarget();
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
    public ISprite getBasic() {
        return basic;
    }
}
