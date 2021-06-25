package sample.Client.Model.ServerThread;

import sample.Data.DataInterface.IUserData;

import java.net.Socket;

public class ServerThreadFactory {

    public static IServerThread CreateInstance(Socket server, IUserData userData){
        return new ServerThread(server,userData);
    }

}
