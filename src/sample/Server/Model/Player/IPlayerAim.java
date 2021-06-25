package sample.Server.Model.Player;

import javafx.scene.image.Image;
import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.Rectangle.IRectangle;
import sample.Server.Model.Sprite.ISprite;

import java.util.ArrayList;

public interface IPlayerAim {
    void PlayerAimToMeteor(ArrayList<IMeteor> meteor, boolean LR);

    //Нацеливание на метеорит c указанным номером
    void PlayerAimToMeteor(ArrayList<IMeteor> meteor, int Numb);

    //Движение к нацеленному метеориту
    void PlayerAimMoveToTarget();

    void setPlayerAimImage(String fileImageName, double sizeImage);

    void setPlayerAimImage(String fileImageName, double Width, double Height);

    void setPlayerAimImage(String fileImageName);

    double getPlayerAimAngleToTarget(ISprite other);

    //Обновление объекта
    void update(double deltaTime);

    double getPlayerAimRotation();

    void setPlayerAimRotation(double rotation);

    IMeteor getPlayerAimTargetMeteor();

    boolean getPlayerAimTargetCaught();

    double getPlayerAimImageWidth();

    double getPlayerAimImageHeight();

    String getPlayerAimFileImageName();

    double getPlayerAimRecX();

    double getPlayerAimRecY();

    double getPlayerAimRecWidth();

    double getPlayerAimRecHeight();

    void setPlayerAimRecPosition(double x, double y);

    void setPlayerAimRecSize(double width, double height);

    boolean PlayerAimRecOverlaps(IRectangle other);

    double getPlayerAimPosX();

    double getPlayerAimPosY();

    void setPlayerAimPosX(double x);

    void setPlayerAimPosY(double y);

    void PlayerAimPosSet(double x, double y);

    void PlayerAimPosAdd(double x, double y);

    void PlayerAimPosMultiply(double m);

    double getPlayerAimPosLength();

    double getPlayerAimPosLength(double x, double y);

    void setPlayerAimPosLength(double L);

    void setPlayerAimPosAngle(double angleDegrees);

    double getPlayerAimPosAngle2Vectors(double x1, double y1, double x2, double y2);

    double getPlayerAimVelX();

    double getPlayerAimVelY();

    void setPlayerAimVelX(double x);

    void setPlayerAimVelY(double y);

    void PlayerAimVelSet(double x, double y);

    void PlayerAimVelAdd(double x, double y);

    void PlayerAimVelMultiply(double m);

    double getPlayerAimVelLength();

    double getPlayerAimVelLength(double x, double y);

    void setPlayerAimVelLength(double L);

    void setPlayerAimVelAngle(double angleDegrees);

    double getPlayerAimVelAngle2Vectors(double x1, double y1, double x2, double y2);

}
