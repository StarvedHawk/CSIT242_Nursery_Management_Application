package com.example.assignment_1.Model;

public class Student {
    private int StudentID;          //P-key
    private int NurseryID;          //F-Key
    private String Name;
    private String SurName;
    private String Parent_Number;
    private String DOB;
    private String Email;
    private int Image;

    //Constructor
    public Student(int studentID, int nurseryID, String name, String surName, String parent_Number, String DOB, String email) {
        StudentID = studentID;
        NurseryID = nurseryID;
        Name = name;
        SurName = surName;
        Parent_Number = parent_Number;
        this.DOB = DOB;
        Email = email;
    }

    public Student(int studentID, int nurseryID, String name, String surName, String parent_Number, String DOB, String email, int image) {
        StudentID = studentID;
        NurseryID = nurseryID;
        Name = name;
        SurName = surName;
        Parent_Number = parent_Number;
        this.DOB = DOB;
        Email = email;
        Image = image;
    }

    public Student(int image, int studentID, String name, String surName, String parent_Number) {
        StudentID = studentID;
        Name = name;
        SurName = surName;
        Parent_Number = parent_Number;
        Image = image;
    }

    public Student() {
    }

    //Getters
    public int getStudentID() {
        return StudentID;
    }

    public int getNurseryID() {
        return NurseryID;
    }

    public String getName() {
        return Name;
    }

    public String getSurName() {
        return SurName;
    }

    public String getParent_Number() {
        return Parent_Number;
    }

    public String getDOB() {
        return DOB;
    }

    public String getEmail() {
        return Email;
    }

    public int getImage() {
        return Image;
    }

    //Setters


    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public void setNurseryID(int nurseryID) {
        NurseryID = nurseryID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public void setParent_Number(String parent_Number) {
        Parent_Number = parent_Number;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setImage(int image) {
        Image = image;
    }
}
