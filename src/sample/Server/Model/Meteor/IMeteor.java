package sample.Server.Model.Meteor;

import sample.Server.Model.Sprite.ISprite;
import sample.Server.Model.TextToObject.ITextToObject;

public interface IMeteor {
    int getID();

    ITextToObject getTextObject();

    ISprite getBasic();

    //Обновление объекта
    void update(double deltaTime);

    boolean isFall();
}
