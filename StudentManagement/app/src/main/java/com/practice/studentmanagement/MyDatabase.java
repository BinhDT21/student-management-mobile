package com.practice.studentmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabase {

    // khai báo 2 lớp SQLiteDatabase và DBHelper

    //Hỗ trợ tương tác với cơ sở dữ liệu
    SQLiteDatabase database;

    //Tạo cơ sở dữ liệu và bảng
    DBHelper helper;

    public MyDatabase(Context context) {
        helper = new DBHelper(context);

        database = helper.getWritableDatabase();
    }

    //Phương thức lấy tất cả dữ liệu
    public Cursor layDanhSachSinhVien() {
        //Khai báo biến cot là các cột cần lấy
        String[] cot = {DBHelper.COT_ID, DBHelper.COT_NAME, DBHelper.COT_CLASS};

        //Cursor như là 1 bảng cơ sở dữ liệu lấy được sau khi truy vấn
        Cursor cursor = null;

        cursor = database.query(DBHelper.TEN_BANG_SINH_VIEN, cot, null,
                null, null, null, null);

        return cursor;
    }


    // Phương thức thêm vào cơ sở dữ liệu
    public long themSinhVien(SinhVien sinhVien) {

        //ContentValues giúp chứa dữ liệu sinhVien để SQLiteDatabase thực hiện thêm vào DB
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_NAME, sinhVien.getName());
        values.put(DBHelper.COT_CLASS, sinhVien.getClassName());

        return database.insert(DBHelper.TEN_BANG_SINH_VIEN, null, values);
    }

    //Phương thức xóa
    public long xoaSinhVien(SinhVien sinhVien) {
        return database.delete(DBHelper.TEN_BANG_SINH_VIEN, DBHelper.COT_NAME + "=" + " '" + sinhVien.getName() + "' ", null);
    }


    //Phương thức sửa sinh viên
    public long suaSinhVien(SinhVien sinhVien) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_NAME, sinhVien.getName());
        values.put(DBHelper.COT_CLASS, sinhVien.getClassName());

        return database
                .update(DBHelper.TEN_BANG_SINH_VIEN, values, DBHelper.COT_ID + " = " + sinhVien.getId(), null);
    }
}
