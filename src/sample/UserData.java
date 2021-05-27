package sample;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class UserData implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757692L;
    ArrayList<String> KeyList;
    String UserName;

    public UserData(String UserName, ArrayList<String> KeyList){
        this.UserName = UserName;
        this.KeyList = KeyList;
    }

    public UserData(){
        this.UserName = null;
        this.KeyList = null;
    }

    public ArrayList<String> getKeyList() {
        return KeyList;
    }

    public String getUserName() {
        return UserName;
    }
}
