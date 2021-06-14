package sample.Client.Model.ServerThread;

import sample.Data.DataInterface.IGameData;
import sample.Data.DataInterface.IUserData;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable,IServerThread{

    private Thread thread;
    private final Socket socket;
    private InputStream in = null;
    private OutputStream out = null;
    private final IUserData userData;
    private IGameData gameData;


    public ServerThread(Socket server, IUserData userData){
        this.socket = server;
        this.userData = userData;
        this.gameData = null;
        thread = new Thread(this,"Server");
        if (Connection()) thread.start();
    }

    @Override
    public IGameData getGameData(){
        return gameData;
    }

    @Override
    public boolean Connection() {
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

    @Override
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

    @Override
    public void run() {
        while (true) {
            try {
                //Конвертирование данных в байты
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

            try {
                //Размер допустимых получаемых данных
                byte[] bts = new byte[in.available()];
                //Попытка считывания
                in.read(bts);

                ByteArrayInputStream bis = new ByteArrayInputStream(bts);
                ObjectInputStream ois = new ObjectInputStream(bis);
                gameData = (IGameData) ois.readObject();

                ois.close();
                bis.close();

            } catch (Exception e) {
                if (e.getMessage() != null)
                    System.out.println(e.getMessage());
            }

            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
