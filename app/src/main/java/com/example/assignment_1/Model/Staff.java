package com.example.assignment_1.Model;

public class Staff {

    private int StaffID;            //P-Key
    private int NurseryID;          //F-Key
    private String StaffName;
    private String Password;
    private boolean Authorization;   //Admin / Staff
    private boolean super_Auth;


    //Constructor
    public Staff(int staffID, int nurseryID, String staffName, String password, boolean authorization) {
        StaffID = staffID;
        NurseryID = nurseryID;
        StaffName = staffName;
        Password = password;
        Authorization = authorization;
        super_Auth = false;
    }

    public Staff(int staffID, int nurseryID, String staffName, String password, boolean authorization,boolean super_auth) {
        StaffID = staffID;
        NurseryID = nurseryID;
        StaffName = staffName;
        Password = password;
        Authorization = authorization;
        super_Auth = super_auth;

        if(super_Auth){
            Authorization = true;
        }

    }

    public Staff() {
    }

    //toString


    @Override
    public String toString() {
        return "Staff{" +
                "StaffID=" + StaffID +
                ", NurseryID=" + NurseryID +
                ", StaffName='" + StaffName + '\'' +
                ", Password='" + Password + '\'' +
                ", Admin='" + Authorization + '\'' +
                ", Super='" + super_Auth + '\'' +
                '}';
    }

    //Getter
    public int getStaffID() {
        return StaffID;
    }

    public int getNurseryID() {
        return NurseryID;
    }

    public String getStaffName() {
        return StaffName;
    }

    public String getPassword() {
        return Password;
    }

    public boolean isAdmin() {
        return Authorization;
    }

    public boolean isSuper_Auth() {
        return super_Auth;
    }

    //Setter


    public void setStaffID(int staffID) {
        StaffID = staffID;
    }

    public void setNurseryID(int nurseryID) {
        NurseryID = nurseryID;
    }

    public void setStaffName(String staffName) {
        StaffName = staffName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setAuthorization(boolean authorization) {
        Authorization = authorization;
    }

    public void setSuper_Auth(boolean super_Auth) {
        this.super_Auth = super_Auth;
    }
}
