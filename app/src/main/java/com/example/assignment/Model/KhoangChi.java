package com.example.assignment.Model;

public class KhoangChi {
    private int id;
    private String ten;
    private String gia;
    private String donVi;
    private String ngay;
    private String moTa;
    private int deleflag;
    private int id_loaichi;
    private int danhGia;

    public KhoangChi() {
    }

    public KhoangChi(int id, String ten, String gia, String donVi, String ngay, String moTa, int deleflag, int id_loaichi, int danhGia) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.donVi = donVi;
        this.ngay = ngay;
        this.moTa = moTa;
        this.deleflag = deleflag;
        this.id_loaichi = id_loaichi;
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

    public int getId_loaichi() {
        return id_loaichi;
    }

    public void setId_loaichi(int id_loaichi) {
        this.id_loaichi = id_loaichi;
    }

    public int getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }
}
