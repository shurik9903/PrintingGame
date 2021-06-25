package sample.Server.Model.UserConnect;

import sample.Server.Model.IModel;

import java.net.Socket;

public class UserConnectFactory {

    public static IUserConnect CreateInstance(String Name, Socket User, IModel GameModel){
        return new UserConnect(Name, User, GameModel);
    }
}
