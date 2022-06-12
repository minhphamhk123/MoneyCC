package com.example.assignment.Model;

public class NguoiDung {
    private int id;
    private String userName,pass;

    public NguoiDung(){

    }

    public NguoiDung(int id, String userName, String pass){
        this.id = id;
        this.userName = userName;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
