package sample.Client2.Model.ServerThread;

import sample.Data.DataInterface.IGameData;

public interface IServerThread {
    IGameData getGameData();

    boolean Connection();

    void CloseConnecting();
}
