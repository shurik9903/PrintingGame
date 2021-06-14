package sample.Server.Model.Meteor;

import sample.Server.Model.Sprite.ISprite;
import sample.Server.Model.TextToObject.ITextToObject;

public interface IMeteor extends ISprite, ITextToObject {
    int getID();

    //Обновление объекта
    void update(double deltaTime);

    boolean isFall();
}
