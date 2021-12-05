package com.example.assignment_1.Model;

public class Nursery {
    private int NurseryID;          //P-Key
    private String NurseryName;
    private int PreCharge;
    private int PostCharge;
    private int MinHours;

    //Constructor

    public Nursery(int nurseryID, String nurseryName, int preCharge, int postCharge, int minHours) {
        NurseryID = nurseryID;
        NurseryName = nurseryName;
        PreCharge = preCharge;
        PostCharge = postCharge;
        MinHours = minHours;
    }

    public Nursery() {
    }

    //Functions

    public double amountToPay(int number_of_hours){
        double payment = 0;
        if(number_of_hours<getMinHours()){
            payment = getPreCharge() * number_of_hours;
        }
        else if(number_of_hours==getMinHours()){
            payment = getAvgCharge() * number_of_hours;
        }
        else{
            payment = getAvgCharge() * getMinHours();
            payment += getPostCharge() * (number_of_hours-getMinHours());
        }
        return payment;
    }

    public double getAvgCharge(){
        return (getPreCharge()+ getPostCharge())/2;
    }

    @Override
    public String toString() {
        return "Nursery{" +
                "NurseryID=" + NurseryID +
                ", NurseryName='" + NurseryName + '\'' +
                ", MinCharge=" + PreCharge +
                ", MaxCharge=" + PostCharge +
                ", MinHours=" + MinHours +
                '}';
    }

    //Getters

    public int getNurseryID() {
        return NurseryID;
    }

    public String getNurseryName() {
        return NurseryName;
    }

    public int getPreCharge() {
        return PreCharge;
    }

    public int getPostCharge() {
        return PostCharge;
    }

    public int getMinHours() {
        return MinHours;
    }

    //Setters


    public void setNurseryID(int nurseryID) {
        NurseryID = nurseryID;
    }

    public void setNurseryName(String nurseryName) {
        NurseryName = nurseryName;
    }

    public void setPreCharge(int preCharge) {
        PreCharge = preCharge;
    }

    public void setPostCharge(int postCharge) {
        PostCharge = postCharge;
    }

    public void setMinHours(int minHours) {
        MinHours = minHours;
    }
}
