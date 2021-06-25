package sample.Server.Model.TextToObject;

public class TextToObjectFactory {

    public static ITextToObject CreateInstance(double x, double y, double Width, double Height, String Text, int ID) {
        return new TextToObject(x,y,Width,Height,Text,ID);
    }

}
