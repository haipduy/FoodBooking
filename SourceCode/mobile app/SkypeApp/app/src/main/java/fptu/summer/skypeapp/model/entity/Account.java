package fptu.summer.skypeapp.model.entity;

import java.io.Serializable;
import java.sql.Date;

public class Account implements Serializable {

    private String userId;
    private String userPassword;
    private String userName;
    private String userAddress;
    private int phone;
    private String email;
    private int Status;
    private int roleId;

    public Account() {
    }

    public Account(String userId, String userPassword, String userName, String userAddress, int phone, String email, int status, int roleId) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAddress = userAddress;
        this.phone = phone;
        this.email = email;
        Status = status;
        this.roleId = roleId;
    }

    public Account(String userId, String userPassword, String userName, String userAddress, int phone, String email) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAddress = userAddress;
        this.phone = phone;
        this.email = email;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
