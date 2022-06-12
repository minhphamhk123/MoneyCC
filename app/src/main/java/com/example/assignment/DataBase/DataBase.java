package com.example.assignment.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.assignment.Model.KhoangChi;
import com.example.assignment.Model.KhoangThu;
import com.example.assignment.Model.LoaiChi;
import com.example.assignment.Model.LoaiThu;
import com.example.assignment.Model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    private static final String DATABASE_TABLE_NAME = "TIEULUAN.sqlite";
    //fix 21:46
    //private static final int DATABASE_VERSION = 1;

    // bảng người dùng
    public static final String KEY_TABLE_NAME_NGUOIDUNG = "nguoidung";
    public static final String KEY_TABLE_ID_NGUOIDUNG = "id";
    public static final String KEY_TABLE_USERNAME_NGUOIDUNG = "ten";
    public static final String KEY_TABLE_MATKHAU_NGUOIDUNG = "matkhau";


    // bảng loại thu
    public static final String KEY_TABLE_NAME_LOAITHU = "loaithu";
    public static final String KEY_TABLE_ID_LOAITHU = "id";
    public static final String KEY_TABLE_TEN_LOAITHU = "ten";
    public static final String KEY_TABLE_DELETEFLAG_LOAITHU = "deleflag";

    //bảng khoản thu
    public static final String KEY_TABLE_NAME_KHOANGTHU = "khoangthu";
    public static final String KEY_TABLE_ID_KHOANGTHU = "id";
    public static final String KEY_TABLE_TENMUCTHU_KHOANGTHU = "ten";
    public static final String KEY_TABLE_DINHMUCTHU_KHOANGTHU = "dinhmucthu";
    public static final String KEY_TABLE_DONVITHU_KHOANGTHU = "donvithu";
    public static final String KEY_TABLE_THOIDIEM_KHOANGTHU = "thoidiem";
    public static final String KEY_TABLE_IDLOAITHU_KHOANGTHU = "idloaithu";
    public static final String KEY_TABLE_DANHGIA_KHOANGTHU = "danhgia";
    public static final String KEY_TABLE_DELETEFLAG_KHOANGTHU = "deleteflag";
    public static final String KEY_TABLE_MOTA_KHOANGTHU = "mota";

    //bảng loai chi
    public static final String KEY_TABLE_NAME_LOAICHI = "loaichi";
    public static final String KEY_TABLE_ID_LOAICHI = "id";
    public static final String KEY_TABLE_TEN_LOAICHI = "ten";
    public static final String KEY_TABLE_DELETEFLAG_LOAICHI = "deleteflag";

    //bảng khoản chi
    public static final String KEY_TABLE_NAME_KHOANGCHI = "khoangchi";
    public static final String KEY_TABLE_ID_KHOANGCHI = "id";
    public static final String KEY_TABLE_TENMUCTHU_KHOANGCHI = "ten";
    public static final String KEY_TABLE_DINHMUCTHU_KHOANGCHI = "dinhmucchi";
    public static final String KEY_TABLE_DONVITHU_KHOANGCHI = "donvichi";
    public static final String KEY_TABLE_THOIDIEM_KHOANGCHI = "thoidiem";
    public static final String KEY_TABLE_IDLOAICHI_KHOANGCHI = "idloaichi";
    public static final String KEY_TABLE_DANHGIA_KHOANGCHI = "danhgia";
    public static final String KEY_TABLE_DELETEFLAG_KHOANGCHI = "deleteflag";
    public static final String KEY_TABLE_MOTA_KHOANGCHI = "mota";


    public DataBase(Context context) {
        super(context, DATABASE_TABLE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    private static final String CREATE_TABLE_NGUOIDUNG = "CREATE TABLE " + KEY_TABLE_NAME_NGUOIDUNG + "("
            + KEY_TABLE_ID_NGUOIDUNG + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_TABLE_USERNAME_NGUOIDUNG + " TEXT,"
            + KEY_TABLE_MATKHAU_NGUOIDUNG + " TEXT" + ")";

    private static final String CREATE_TABLE_LOAITHU = "CREATE TABLE " + KEY_TABLE_NAME_LOAITHU + "("
            + KEY_TABLE_ID_LOAITHU + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_TABLE_TEN_LOAITHU + " TEXT,"
            + KEY_TABLE_DELETEFLAG_LOAITHU + " INTEGER" + ")";

    private static final String CREATE_TABLE_KHOANGTHU = "CREATE TABLE " + KEY_TABLE_NAME_KHOANGTHU + "("
            + KEY_TABLE_ID_KHOANGTHU + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_TABLE_TENMUCTHU_KHOANGTHU + " TEXT,"
            + KEY_TABLE_DINHMUCTHU_KHOANGTHU + " DECIMAL,"
            + KEY_TABLE_DONVITHU_KHOANGTHU + " TEXT,"
            + KEY_TABLE_THOIDIEM_KHOANGTHU + " DATE,"
            + KEY_TABLE_IDLOAITHU_KHOANGTHU + " TEXT,"
            + KEY_TABLE_DANHGIA_KHOANGTHU + " INTEGER,"
            + KEY_TABLE_DELETEFLAG_KHOANGTHU + " INTEGER,"
            + KEY_TABLE_MOTA_KHOANGTHU + " TEXT" + ")";

    private static final String CREATE_TABLE_LOAICHI = "CREATE TABLE " + KEY_TABLE_NAME_LOAICHI + "("
            + KEY_TABLE_ID_LOAICHI + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_TABLE_TEN_LOAICHI + " TEXT, "
            + KEY_TABLE_DELETEFLAG_LOAICHI + " INTEGER" + ")";

    private static final String CREATE_TABLE_KHOANGCHI = "CREATE TABLE " + KEY_TABLE_NAME_KHOANGCHI + "("
            + KEY_TABLE_ID_LOAICHI + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_TABLE_TENMUCTHU_KHOANGCHI + " TEXT, " +
            KEY_TABLE_DINHMUCTHU_KHOANGCHI + " DECIMAL, " +
            KEY_TABLE_DONVITHU_KHOANGCHI + " TEXT, " +
            KEY_TABLE_THOIDIEM_KHOANGCHI + " TEXT, " +
            KEY_TABLE_IDLOAICHI_KHOANGCHI + " TEXT, " +
            KEY_TABLE_DANHGIA_KHOANGCHI + " TEXT, " +
            KEY_TABLE_DELETEFLAG_KHOANGCHI + " INTEGER, " +
            KEY_TABLE_MOTA_KHOANGCHI + " TEXT " + ")";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_NGUOIDUNG);
        sqLiteDatabase.execSQL(CREATE_TABLE_LOAITHU);
        sqLiteDatabase.execSQL(CREATE_TABLE_KHOANGTHU);
        sqLiteDatabase.execSQL(CREATE_TABLE_LOAICHI);
        sqLiteDatabase.execSQL(CREATE_TABLE_KHOANGCHI);
    }

    public void QueryData(String sql) {
        SQLiteDatabase database = (SQLiteDatabase) getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    // thêm người dùng mới
    public boolean addNguoiDung(String useracc, String passacc) {

        SQLiteDatabase database = this.getWritableDatabase();
        Boolean result = true;
        //
        ContentValues contentValues = new ContentValues();
        //
        //contentValues.put(KEY_TABLE_ID_NGUOIDUNG, idacc);
        contentValues.put(KEY_TABLE_USERNAME_NGUOIDUNG, useracc);
        contentValues.put(KEY_TABLE_MATKHAU_NGUOIDUNG, passacc);
        //
        long r = database.insert(KEY_TABLE_NAME_NGUOIDUNG, null, contentValues);
        database.close();
        if (r == -1){result = false;}
        //
        return result;
    }

    public boolean kiemTraDangNhap(String user, String pass) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + KEY_TABLE_NAME_NGUOIDUNG + " WHERE " +
                KEY_TABLE_USERNAME_NGUOIDUNG + " = '" + user + "' AND " + KEY_TABLE_MATKHAU_NGUOIDUNG + " = '" + pass + "'", null);
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

    // Update pass cho username 23:09
    public boolean Updatepass(String usernameupdate, String passwordupdate){

        SQLiteDatabase database = this.getWritableDatabase();
        //
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TABLE_MATKHAU_NGUOIDUNG, passwordupdate);
        usernameupdate = "'"+usernameupdate+"'";
        String SQLStr = KEY_TABLE_USERNAME_NGUOIDUNG + "=" + usernameupdate;
        long result = database.update(KEY_TABLE_NAME_NGUOIDUNG, contentValues, SQLStr, null);
        if(result == -1){return false;}
        return true;
    }

    // thêm loại thu
    public void addLoaiThu(LoaiThu loaiThu) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //  cái lõi  count  = 0 ,, count = 0 là do câu lệnh tạo bảo sai,
        //  ko có trường ten, ko thêm dc thì count no =0
        contentValues.put(KEY_TABLE_TEN_LOAITHU, loaiThu.getTen());
        contentValues.put(KEY_TABLE_DELETEFLAG_LOAITHU, loaiThu.getDeleleflag());
        database.insert(KEY_TABLE_NAME_LOAITHU, null, contentValues);
        database.close();
    }

    // sửa loại thu
    public void editLoaiThu(LoaiThu loaiThu) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TABLE_TEN_LOAITHU, loaiThu.getTen());
        database.update(KEY_TABLE_NAME_LOAITHU, contentValues, KEY_TABLE_ID_LOAITHU + " = ?",
                new String[]{String.valueOf(loaiThu.getId())});
        database.close();
    }

    // danh sách loại thu
    public List<LoaiThu> getAllListLoaiThu() {
        List<LoaiThu> listLoaiThu = new ArrayList<>();
        SQLiteDatabase databas = getReadableDatabase();
        Cursor cursor = databas.rawQuery("SELECT * FROM " + KEY_TABLE_NAME_LOAITHU, null);
        Log.d("COUNT: ", cursor.getCount() + "");// o day count =-1 , ko co ko tim dc row nao
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String ten = cursor.getString(1);
                int deleflag = cursor.getInt(2);
                listLoaiThu.add(new LoaiThu(id, ten, deleflag));
            }
        }
        return listLoaiThu;
    }

    // danh sách loại chi
    public List<LoaiChi> getAllListLoaiChi() {
        List<LoaiChi> listLoaiChi = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + KEY_TABLE_NAME_LOAICHI, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String ten = cursor.getString(1);
                int deleteflag = cursor.getInt(2);
                listLoaiChi.add(new LoaiChi(id, deleteflag, ten));
            }
        }
        return listLoaiChi;
    }

    // Khoản Thu
    public List<KhoangThu> getAllListKhoangThu(String truyvan){
        SQLiteDatabase database = getReadableDatabase();
        List<KhoangThu> list=new ArrayList<>();
        Cursor cursor=database.rawQuery(truyvan,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                KhoangThu khoangThu=new KhoangThu();
                khoangThu.setId(cursor.getInt(0));
                khoangThu.setTen(cursor.getString(1));
                khoangThu.setGia(cursor.getString(2));
                khoangThu.setDonVi(cursor.getString(3));
                khoangThu.setNgay(cursor.getString(4));
                khoangThu.setId_loaithu(cursor.getInt(5));
                khoangThu.setGia(cursor.getString(6));
                khoangThu.setDeleflag(cursor.getInt(7));
                khoangThu.setMoTa(cursor.getString(8));
                list.add(khoangThu);

            }
        }

        //Log.d("checkNao", String.valueOf(list));
        return list;
    }

    // Khoản Chi
    public List<KhoangChi> getAllListKhoangChi(String truyvan){
        SQLiteDatabase database = getReadableDatabase();
        List<KhoangChi> list=new ArrayList<>();
        Cursor cursor=database.rawQuery(truyvan,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                KhoangChi khoangChi = new KhoangChi();
                khoangChi.setId(cursor.getInt(0));
                khoangChi.setTen(cursor.getString(1));
                khoangChi.setGia(cursor.getString(2));
                khoangChi.setDonVi(cursor.getString(3));
                khoangChi.setNgay(cursor.getString(4));
                khoangChi.setId_loaichi(cursor.getInt(5));
                khoangChi.setGia(cursor.getString(6));
                khoangChi.setDeleflag(cursor.getInt(7));
                khoangChi.setMoTa(cursor.getString(8));
                list.add(khoangChi);

            }
        }

        //Log.d("checkNao", String.valueOf(list));
        return list;
    }

    // thêm khoảng thu
    public void addKhoangThu(KhoangThu khoangThu) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TABLE_TENMUCTHU_KHOANGTHU, khoangThu.getTen());
        contentValues.put(KEY_TABLE_DINHMUCTHU_KHOANGTHU, khoangThu.getGia());
        contentValues.put(KEY_TABLE_DONVITHU_KHOANGTHU, khoangThu.getDonVi());
        contentValues.put(KEY_TABLE_THOIDIEM_KHOANGTHU, khoangThu.getNgay());
        contentValues.put(KEY_TABLE_IDLOAITHU_KHOANGTHU, khoangThu.getId_loaithu());
        contentValues.put(KEY_TABLE_DANHGIA_KHOANGTHU, khoangThu.getDanhGia());
        contentValues.put(KEY_TABLE_DELETEFLAG_KHOANGTHU, khoangThu.getDeleflag());
        contentValues.put(KEY_TABLE_MOTA_KHOANGTHU, khoangThu.getMoTa());
        database.insert(KEY_TABLE_NAME_KHOANGTHU, null, contentValues);
        database.close();
    }

    // sửa khoảng thu
    public void suaKhoangThu(KhoangThu khoangThu) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TABLE_TENMUCTHU_KHOANGTHU, khoangThu.getTen());
        contentValues.put(KEY_TABLE_DINHMUCTHU_KHOANGTHU, khoangThu.getGia());
        contentValues.put(KEY_TABLE_DONVITHU_KHOANGTHU, khoangThu.getDonVi());
        contentValues.put(KEY_TABLE_THOIDIEM_KHOANGTHU, khoangThu.getNgay());
        contentValues.put(KEY_TABLE_IDLOAITHU_KHOANGTHU, khoangThu.getId_loaithu());
        contentValues.put(KEY_TABLE_DANHGIA_KHOANGTHU, khoangThu.getDanhGia());
        contentValues.put(KEY_TABLE_DELETEFLAG_KHOANGTHU, khoangThu.getDeleflag());
        contentValues.put(KEY_TABLE_MOTA_KHOANGTHU, khoangThu.getMoTa());
        database.update(KEY_TABLE_NAME_KHOANGTHU, contentValues, KEY_TABLE_ID_KHOANGTHU +
                " = ?", new String[]{String.valueOf(khoangThu.getId())});
        database.close();
    }

    //thêm loại chi
    public void addLoaiChi(LoaiChi loaiChi) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TABLE_TEN_LOAICHI, loaiChi.getTen());
        contentValues.put(KEY_TABLE_DELETEFLAG_LOAICHI, loaiChi.getDeleleflag());
        database.insert(KEY_TABLE_NAME_LOAICHI, null, contentValues);
        database.close();
    }

    // sửa loại chi
    public void edtLoaiChi(LoaiChi loaiChi) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TABLE_TEN_LOAICHI, loaiChi.getTen());
        database.update(KEY_TABLE_NAME_LOAICHI, contentValues, KEY_TABLE_ID_LOAICHI + " = ?",
                new String[]{String.valueOf(loaiChi.getId())});
        database.close();
    }

    public void suaKhoangChi(KhoangChi khoangChi) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TABLE_TENMUCTHU_KHOANGCHI, khoangChi.getTen());
        contentValues.put(KEY_TABLE_DINHMUCTHU_KHOANGCHI, khoangChi.getGia());
        contentValues.put(KEY_TABLE_DONVITHU_KHOANGCHI, khoangChi.getDonVi());
        contentValues.put(KEY_TABLE_THOIDIEM_KHOANGCHI, khoangChi.getNgay());
        contentValues.put(KEY_TABLE_IDLOAICHI_KHOANGCHI, khoangChi.getId_loaichi());
        contentValues.put(KEY_TABLE_DANHGIA_KHOANGCHI, khoangChi.getDanhGia());
        contentValues.put(KEY_TABLE_DELETEFLAG_KHOANGCHI, khoangChi.getDeleflag());
        contentValues.put(KEY_TABLE_MOTA_KHOANGCHI, khoangChi.getMoTa());
        database.update(KEY_TABLE_NAME_KHOANGCHI, contentValues, KEY_TABLE_ID_KHOANGCHI +
                " = ? ", new String[]{String.valueOf(khoangChi.getId())});
        database.close();
    }

    public void addKhoangChi(KhoangChi khoangChi) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TABLE_TENMUCTHU_KHOANGCHI, khoangChi.getTen());
        contentValues.put(KEY_TABLE_DINHMUCTHU_KHOANGCHI, khoangChi.getGia());
        contentValues.put(KEY_TABLE_DONVITHU_KHOANGCHI, khoangChi.getDonVi());
        contentValues.put(KEY_TABLE_THOIDIEM_KHOANGCHI, khoangChi.getNgay());
        contentValues.put(KEY_TABLE_IDLOAICHI_KHOANGCHI, khoangChi.getId_loaichi());
        contentValues.put(KEY_TABLE_DANHGIA_KHOANGCHI, khoangChi.getDanhGia());
        contentValues.put(KEY_TABLE_DELETEFLAG_KHOANGCHI, khoangChi.getDeleflag());
        contentValues.put(KEY_TABLE_MOTA_KHOANGCHI, khoangChi.getMoTa());
        database.insert(KEY_TABLE_NAME_KHOANGCHI, null, contentValues);
        database.close();
    }

}
