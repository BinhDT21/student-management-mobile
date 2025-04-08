package com.practice.studentmanagement;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ListView listView;

    public static ArrayList<SinhVien> danhSachSinhVien;

    public static MyDatabase database;

    private EditText editTextName, editTextClass;

    private String name, className;
    private static long id = -1;

    public void capNhatDuLieu (){
        if(danhSachSinhVien == null){
            danhSachSinhVien = new ArrayList<>();
        }else{
            danhSachSinhVien.removeAll(danhSachSinhVien);
        }

        Cursor cursor = database.layDanhSachSinhVien();

        if(cursor!=null){
            while (cursor.moveToNext()){
                SinhVien sinhVien = new SinhVien();

                //cursor.getColumnIndexOrThrow(DBHelper.COT_ID) trả về index của cột id => 0
                //cursor.getString(...) trả về giá trị
                sinhVien.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COT_ID))));
                sinhVien.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COT_NAME)));
                sinhVien.setClassName(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COT_CLASS)));

                danhSachSinhVien.add(sinhVien);
            }
        }


        if(danhSachSinhVien!=null){
            listView = findViewById(R.id.lvStudentList);
            editTextName = findViewById(R.id.editTextName);
            editTextClass = findViewById(R.id.editTextClass);
            listView.setAdapter(new MyAdapter(getApplicationContext(), editTextName, editTextClass));
        }
    }

    public SinhVien layDuLieuNguoiDung(){

        editTextName = findViewById(R.id.editTextName);
        editTextClass = findViewById(R.id.editTextClass);

        name = editTextName.getText().toString();
        className = editTextClass.getText().toString();

        if(name.trim().isEmpty() || className.trim().isEmpty())return null;

        SinhVien sv = new SinhVien(name, className);
        sv.setId(id);

        return sv;
    }

    public void themSinhVien (View view){
        SinhVien sv = layDuLieuNguoiDung();
        if(sv!=null){
            if(database.themSinhVien(sv)!=-1){
                danhSachSinhVien.add(sv);

                //cập nhật danh sách sinh viên
                capNhatDuLieu();

                //chạy lại cái list view
                listView = findViewById(R.id.lvStudentList);
                listView.invalidateViews();

                editTextName.setText(null);
                editTextClass.setText(null);
                id = -1;
            }
        }
    }

    public void suaSinhVien (View view){

        SinhVien sv = layDuLieuNguoiDung();
        sv.setId(MyAdapter.svId);
        if(sv!=null){
            if(database.suaSinhVien(sv) != -1){
                danhSachSinhVien.add(sv);

                capNhatDuLieu();

                //chạy lại cái list view
                listView = findViewById(R.id.lvStudentList);
                listView.invalidateViews();

                editTextName.setText(null);
                editTextClass.setText(null);
                id = -1;

                Log.d("Update success", "Sửa sinh viên thành công");
            }else {
                Log.d("Update failed", "Sửa sinh viên không thành công");
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = new MyDatabase(this);
        capNhatDuLieu();

        Button addButton = findViewById(R.id.buttonInsert);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themSinhVien(view);
            }
        });

        Button updateButton = findViewById(R.id.buttonUpdate);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suaSinhVien(view);
            }
        });
    }
}