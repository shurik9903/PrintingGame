package sample.Client.Model.ServerThread;

import sample.Data.DataInterface.IGameData;

public interface IServerThread {
    IGameData getGameData();

    boolean Connection();

    void CloseConnecting();
}
