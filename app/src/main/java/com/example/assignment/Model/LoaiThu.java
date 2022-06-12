package com.example.assignment.Model;

public class LoaiThu  {
    private int id;
    private int deleleflag;
    private String ten;

    public LoaiThu() {

    }

    public int getDeleleflag() {
        return deleleflag;
    }

    public void setDeleleflag(int deleleflag) {
        this.deleleflag = deleleflag;
    }


    public LoaiThu(int id, String ten,int deleleflag) {
        this.id = id;
        this.ten = ten;
        this.deleleflag = deleleflag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
