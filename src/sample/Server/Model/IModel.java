package sample.Server.Model;

import sample.Data.DataInterface.IMeteorData;
import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.Player.IPlayer;

public interface IModel {
    IPlayer setPlayer1();

    IPlayer setPlayer2();

    void DisconnectPlayer1();

    void DisconnectPlayer2();

    void Initialize();

    IMeteorData ConvertMeteorToData(IMeteor meteor);

    void StartGame();

    void StopGame();
}
