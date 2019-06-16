package fptu.summer.skypeapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class AccountRoom implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "userId")
    private String userId;
    @ColumnInfo(name = "userPassword")
    private String userPassword;
    @ColumnInfo(name = "timeLogin")
    private long timeLogin;

    public AccountRoom() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public long getTimeLogin() {
        return timeLogin;
    }

    public void setTimeLogin(long timeLogin) {
        this.timeLogin = timeLogin;
    }
}
