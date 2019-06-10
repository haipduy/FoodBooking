package fptu.sumer.foodstore_api.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Entity
@ApiModel(description = "All details about the Account. ")
@Table(name = "Account", schema = "dbo", catalog = "FoodSystem")
public class AccountEntity {
    private String userId;
    private String userPassword;
    private String userName;
    private String userAddress;
    private int phone;
    private String email;
    private int Status;
    private int roleId;

    public AccountEntity() {
    }


    @Id
    @Column(name = "UserId")
    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "UserPassword")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "UserName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "UserAddress")
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Basic
    @Column(name = "UserPhoneNo")
    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "UserEmail")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "UserStatus")
    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    @Basic
    @Column(name = "RoleId")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

}
