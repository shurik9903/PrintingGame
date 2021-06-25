package sample.Server.Model.Meteor;

public class MeteorFactory {

    public static IMeteor CreateInstance(double BoxHeight, double BoxWidth, int ID, int speed){
        return new Meteor(BoxHeight,BoxWidth,ID,speed);
    }

}
