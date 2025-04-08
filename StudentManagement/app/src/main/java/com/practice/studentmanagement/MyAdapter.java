package com.practice.studentmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;  //Dùng để "thổi phồng" (inflate) XML layout thành View thực tế.
    private TextView textView;  //Biến tạm để trỏ tới từng TextView trong item layout.
    private Context context;  //Ngữ cảnh của Activity dùng để inflate layout hoặc xử lý liên quan đến tài nguyên.

    private EditText editTextName, editTextClass;

    public static long svId = -1;


    public MyAdapter(Context context, EditText nameField, EditText classField) { //Gán context và khởi tạo layoutInflater.
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.editTextName = nameField;
        this.editTextClass = classField;

    }

    @Override
    public int getCount() {

        return MainActivity.danhSachSinhVien.size();
    }

    @Override
    public Object getItem(int i) {
        return MainActivity.danhSachSinhVien.get(i);
    }

    @Override
    public long getItemId(int i) {
        return MainActivity.danhSachSinhVien.get(i).getId();
    }

    // ViewHolder giúp lưu các view con
    static class ViewHolder {
        TextView textViewName;
        TextView textViewClass;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        //convertView là một View cũ mà Android đã từng tạo ra,
        // và tái sử dụng lại khi bạn scroll trong ListView.

        ViewHolder holder;

        if (convertView == null) {
            // Inflate layout chỉ khi cần
            // "Hãy lấy file design_item_list_view.xml,
            //    tạo một View từ đó, dùng ngữ cảnh layout từ parent,
            //    nhưng đừng add nó vào parent lúc này."
            convertView = layoutInflater.inflate(R.layout.design_item_list_view, parent, false);

            // Khởi tạo ViewHolder và gán các view
            holder = new ViewHolder();
            holder.textViewName = convertView.findViewById(R.id.textViewName);
            holder.textViewClass = convertView.findViewById(R.id.textViewClass);

            // Gắn ViewHolder vào View
            convertView.setTag(holder);
        } else {
            // Tái sử dụng ViewHolder
            holder = (ViewHolder) convertView.getTag();
        }

        // Gán dữ liệu vào view
        SinhVien sv = MainActivity.danhSachSinhVien.get(i);
        holder.textViewName.setText(sv.getName());
        holder.textViewClass.setText(sv.getClassName());

        holder.textViewName.setOnClickListener(view -> {
            editTextName.setText(sv.getName());
            editTextClass.setText(sv.getClassName());
            svId = getItemId(i);
        });

        ((ImageView) convertView.findViewById(R.id.imageView)).setOnClickListener(view -> {
            MainActivity.database.xoaSinhVien(MainActivity.danhSachSinhVien.get(i));
            MainActivity.danhSachSinhVien.remove(i);

            MainActivity.listView.invalidateViews();
        });

        return convertView;
    }
}
