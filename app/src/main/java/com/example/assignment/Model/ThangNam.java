package com.example.assignment.Model;

public class ThangNam {
    private int id;
    private String thang;

    public ThangNam() {

    }

    public ThangNam(int id, String thang) {
        this.id = id;
        this.thang = thang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }
}
