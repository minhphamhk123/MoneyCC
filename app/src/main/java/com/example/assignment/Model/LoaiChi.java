package com.example.assignment.Model;

public class LoaiChi {
    private int id;
    private int deleleflag;
    private String ten;

    public LoaiChi() {

    }

    public LoaiChi(int id, int deleleflag, String ten) {
        this.id = id;
        this.deleleflag = deleleflag;
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeleleflag() {
        return deleleflag;
    }

    public void setDeleleflag(int deleleflag) {
        this.deleleflag = deleleflag;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
