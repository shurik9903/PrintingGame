package sample.Client.Model;

import sample.GameData;
import sample.UserData;

import java.io.*;
import java.net.Socket;

class ServerThread{


    private final Socket socket;
    private InputStream in = null;
    private OutputStream out = null;
    private final UserData userData;
    private GameData gameData;


    ServerThread(Socket server, UserData userData){
        this.socket = server;
        this.userData = userData;
        this.gameData = null;
        Connection();
    }

    public GameData getGameData(){
        return gameData;
    }

    private boolean Connection() {
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            System.out.println("Connection on...");
            return true;
        } catch (Exception e) {
            System.out.println("Error to connection...");
            return false;
        }
    }

    public void OutData(){
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(userData);
            oos.flush();

            out.write(bos.toByteArray());

            userData.getKeyList().clear();
            oos.close();
            bos.close();

        } catch (Exception e) {
            System.out.println("The message was not sent to the server...");
            System.out.println("Error: " + e.getMessage());

        }
    }

    public void InData(){
        try {
            byte[] bts = new byte[in.available()];
            in.read(bts);
            ByteArrayInputStream bis = new ByteArrayInputStream(bts);
            ObjectInputStream ois = new ObjectInputStream(bis);
            gameData = (GameData) ois.readObject();

            ois.close();
            bis.close();

        } catch (Exception e) {
            if (e.getMessage() != null)
                System.out.println(e.getMessage());
        }
    }

    public void CloseConnecting(){
        try {
            System.out.println("Exit");
            socket.close();
            in.close();
            out.close();
        } catch (
                Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
