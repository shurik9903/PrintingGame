package sample.Server.Model.MyFunction;

public class MyFunctionFactory {

    public static IMyFunction CreateInstance(){
        return new MyFunction();
    }

}
