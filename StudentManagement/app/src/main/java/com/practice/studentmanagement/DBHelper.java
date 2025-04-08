package com.practice.studentmanagement;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    private static final String TEN_DATABASE = "QuanLySinhVien";

    public static final String TEN_BANG_SINH_VIEN = "SinhVien";

    public static final String COT_ID = "id";
    public static final String COT_NAME = "name";
    public static final String COT_CLASS = "class";

    private static final String TAO_BANG_SINH_VIEN = "create table " + TEN_BANG_SINH_VIEN + " ( "
            + COT_ID + " integer primary key autoincrement , "
            + COT_NAME + " text not null, "
            + COT_CLASS + " text not null );";

    public DBHelper(Context context) {
//        super(context, name, factory, version); cần truyền vào 4 tham số
        super(context, TEN_DATABASE, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TAO_BANG_SINH_VIEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
