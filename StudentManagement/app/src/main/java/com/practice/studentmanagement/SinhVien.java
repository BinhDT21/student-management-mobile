package com.practice.studentmanagement;

public class SinhVien {
    private long id;
    private String name;
    private String className;

    public SinhVien() {
    }

    public SinhVien(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
