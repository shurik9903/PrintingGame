package sample.Server.Main;
import sample.Server.Model.IModel;
import sample.Server.Model.Model;
import sample.Server.Model.ServerFactory.ServerFactory;


public class ServerStart{
    public static void main(String[] args) {
        IModel Game = ServerFactory.ModelCreateInstance();
    }
}

