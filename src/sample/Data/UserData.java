package sample.Data;

import sample.Data.DataInterface.IUserData;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class UserData implements Serializable, IUserData {
    @Serial
    private static final long serialVersionUID = 6529685098267757692L;
    private final ArrayList<String> KeyList;
    private final String UserName;

    public UserData(String UserName, ArrayList<String> KeyList){
        this.UserName = UserName;
        this.KeyList = KeyList;
    }

    public UserData(){
        this.UserName = null;
        this.KeyList = null;
    }

    @Override
    public ArrayList<String> getKeyList() {
        return KeyList;
    }

    @Override
    public String getUserName() {
        return UserName;
    }
}
