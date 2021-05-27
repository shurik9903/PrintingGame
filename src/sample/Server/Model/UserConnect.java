package sample.Server.Model;

import sample.UserData;

import java.io.*;
import java.net.Socket;

class UserConnect {
    private final Socket SocketUser;
    private InputStream in = null;
    private OutputStream out = null;
    private UserData userData = null;
    private Model GameModel;
    private Player player;
    private String UserName;

    UserConnect(String Name, Socket User, Model GameModel) {
        this.UserName = Name;
        this.GameModel = GameModel;
        this.SocketUser = User;
        if (Name.contains("User1"))
            player = this.GameModel.setPlayer1();
        else
            player = this.GameModel.setPlayer2();
        Connection();
    }

    private boolean Connection() {
        try {
            in = SocketUser.getInputStream();
            out = SocketUser.getOutputStream();
            System.out.println(UserName + " Connection on...");
            return true;
        } catch (Exception e) {
            System.out.println(UserName + "Error to connection...");
            return false;
        }
    }

    public void OutData() {
        try {
            byte[] bts;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(player.getGameData());
            oos.flush();

            bts = bos.toByteArray();
            out.write(bts);

            oos.close();
            bos.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void InData() {
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
            if (e != null) {
                System.out.println("Нет данных");
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
