package com.example.clickajob;

/**
 * Created by User on 7/11/2015.
 */
public class User {
    String Name, Email, UserID, Password;
    int Phone;

    public User(String Name,int Phone, String Email,String UserID, String Password){
        this.Name = Name;
        this.Phone = Phone;
        this.Email = Email;
        this.UserID = UserID;
        this.Password = Password;
    }

    public User(String name, String UserID, String Password){
        this.UserID = UserID;
        this.Password = Password;
        this.Email = "";
        this.Phone = -1;
        this.Name = "";
    }

    public User(String UserID, String Password) {
        this.UserID = UserID;
        this.Password = Password;
    }
}
