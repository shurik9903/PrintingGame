package sample.Server.Model.UserConnect;

import sample.Server.Model.Model;
import sample.Server.Model.Player.Player;
import sample.UserData;

import java.io.*;
import java.net.Socket;

class UserConnect implements Runnable {
    private final Thread thread;
    private final Socket SocketUser;
    private InputStream in = null;
    private OutputStream out = null;
    private UserData userData = null;
    private Model GameModel;
    private Player player;


    UserConnect(String Name, Socket User, Model GameModel) {
        this.GameModel = GameModel;
        this.SocketUser = User;
        if (Name.contains("User1"))
            player = this.GameModel.setPlayer1();
        else
            player = this.GameModel.setPlayer2();
        this.thread = new Thread(this, Name);
        if (Connection()) thread.start();
    }

    private boolean Connection() {
        try {
            in = SocketUser.getInputStream();
            out = SocketUser.getOutputStream();
            System.out.println(thread + " Connection on...");
            return true;
        } catch (Exception e) {
            System.out.println(thread + "Error to connection...");
            return false;
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                byte[] bts;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(player.getGameData());
                oos.flush();
                //System.out.println(player.getGameData().getMeteorList());
                bts = bos.toByteArray();
                out.write(bts);

                oos.close();
                bos.close();


            } catch (IOException e) {
                e.printStackTrace();
            }


            try {

                byte[] bts = new byte[in.available()];
                int count = in.read(bts);


                ByteArrayInputStream bis = new ByteArrayInputStream(bts);
                ObjectInputStream ois = new ObjectInputStream(bis);
                Object obj = ois.readObject();
                userData = (UserData) obj;

                //System.out.println("Access: " + userData.getUserName());
                //System.out.println("User Key: " + userData.getKeyList());
                player.setKeyList(userData.getKeyList());
                ois.close();
                bis.close();

            } catch (Exception e) {
                if (e.getMessage() != null) {
                    System.out.println("Нет данных");
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }

            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
