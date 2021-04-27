package sample.Model;

//Класс орудие
public class Gun extends Sprite{
    public Gun(double Size, double PosX, double PosY){
        super("Image/Gun.png", Size);
        position.x = PosX;
        position.y = PosY;
        boundary.setSize(Size, Size);
    }
}
