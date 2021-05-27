package sample.Server.Model;

import java.util.ArrayList;

//Класс прицел: координаты, цель и описание пойманного в прицел объекта
public class Aim extends Sprite {

    double BoxHeight, BoxWidth;
    public Meteor TargetMeteor;
    int NumberToMeteor;
    public boolean TargetCaught;
    double RestrictedArea;

    //Конструктор класса
    public Aim(double BoxWidth, double BoxHeight, double RestrictedArea) {
        super("Image/Aim.png", 100);
        this.BoxWidth = BoxWidth;
        this.BoxHeight = BoxHeight;
        this.boundary.setSize(10, 10);
        this.RestrictedArea = RestrictedArea;
        TargetMeteor = null;
        NumberToMeteor = -1;
        TargetCaught = false;
        position.set(this.BoxWidth / 2, this.BoxHeight / 2);
        velocity.setLength(0);

    }

    //Нацеливание на метеорит из спика
    public void AimToMeteor(ArrayList<Meteor> meteor, boolean LR) {

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
                NumberToMeteor = meteor.size() - 1;
        }

        velocity.setLength(500);
        TargetMeteor = meteor.get(NumberToMeteor);
    }

    //Нацеливание на метеорит c указанным номером
    public void AimToMeteor(ArrayList<Meteor> meteor, int Numb) {
        TargetCaught = false;
        if (meteor.size() == 0) {
            TargetMeteor = null;
            velocity.setLength(0);
            return;
        }

        for (Meteor m : meteor) {
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
    public void MoveToTarget() {

        if (TargetMeteor.Text.Destroy || TargetMeteor.position.y >= RestrictedArea) {
            TargetCaught = false;
            TargetMeteor = null;
            return;
        }

        if (this.overlaps(TargetMeteor))
            TargetCaught = true;

        if (TargetCaught) {
            this.position.y = TargetMeteor.position.y;
            this.position.x = TargetMeteor.position.x;
            velocity.setLength(0);
            return;
        }

        velocity.setAngle(getAngleToTarget(TargetMeteor));
    }

    //Обновление объекта
    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        if (TargetMeteor != null)
            MoveToTarget();
    }
}
