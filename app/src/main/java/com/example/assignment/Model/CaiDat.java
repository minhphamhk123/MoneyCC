package com.example.assignment.Model;

public class CaiDat {
    private String ten;
    private int hinh;

    public CaiDat() {

    }

    public CaiDat(String ten, int hinh) {
        this.ten = ten;
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
