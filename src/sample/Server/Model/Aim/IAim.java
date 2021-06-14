package sample.Server.Model.Aim;

import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.Sprite.ISprite;

import java.util.ArrayList;

public interface IAim extends ISprite {

    //Нацеливание на метеорит из спика
    void AimToMeteor(ArrayList<IMeteor> meteor, boolean LR);

    //Нацеливание на метеорит c указанным номером
    void AimToMeteor(ArrayList<IMeteor> meteor, int Numb);

    //Движение к нацеленному метеориту
    void MoveToTarget();

    //Обновление объекта
    void update(double deltaTime);

    IMeteor getTargetMeteor();

    boolean getTargetCaught();
}
