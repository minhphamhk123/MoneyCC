package com.example.assignment.Model;

public class KhoangThu {
    private int id;
    private String ten;
    private String gia;
    private String donVi;
    private String ngay;
    private String moTa;
    private int deleflag;
    private int id_loaithu;
    private int danhGia;

    public KhoangThu() {

    }

    public KhoangThu(int id, String ten, String gia, String donVi, String ngay, String moTa, int deleflag, int id_loaithu, int danhGia) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.donVi = donVi;
        this.ngay = ngay;
        this.moTa = moTa;
        this.deleflag = deleflag;
        this.id_loaithu = id_loaithu;
        this.danhGia = danhGia;
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

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getDeleflag() {
        return deleflag;
    }

    public void setDeleflag(int deleflag) {
        this.deleflag = deleflag;
    }

    public int getId_loaithu() {
        return id_loaithu;
    }

    public void setId_loaithu(int id_loaithu) {
        this.id_loaithu = id_loaithu;
    }

    public int getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }
}
