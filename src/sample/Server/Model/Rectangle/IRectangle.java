package sample.Server.Model.Rectangle;

import sample.Server.Model.Sprite.ISprite;

public interface IRectangle {

    double getRecX();

    double getRecY();

    double getRecWidth();

    double getRecHeight();

    void setRecPosition(double x, double y);
    void setRecSize(double width, double height);
    boolean RecOverlaps(ISprite other);
}
