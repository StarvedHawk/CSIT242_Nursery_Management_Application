package com.example.assignment_1.Model;

public class Payment_Record {       //Pr
    private int PrID;               //P-Key
    private int StdID;              //F-Key
    private int NurseryID;          //F-Key
    private double AmountPaid;
    private int NumberOfHours;
    private int day;
    private int Month;
    private int Year;

    //Constructors
    public Payment_Record(int prID, int stdID,int nurseryID, double amountPaid, int numberOfHours, int day, int month, int year) {
        PrID = prID;
        StdID = stdID;
        AmountPaid = amountPaid;
        NumberOfHours = numberOfHours;
        NurseryID=nurseryID;
        this.day = day;
        Month = month;
        Year = year;
    }

    public Payment_Record() {
    }
    //Functions

    @Override
    public String toString() {
        return "$" + getAmountPaid() + " by #" + getStdID() + " on " + getDay() + "-" + getMonth() + "-" + getYear();
    }

    //Getters

    public int getPrID() {
        return PrID;
    }

    public int getStdID() {
        return StdID;
    }

    public int getNurseryID() {
        return NurseryID;
    }

    public double getAmountPaid() {
        return AmountPaid;
    }

    public int getNumberOfHours() {
        return NumberOfHours;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return Month;
    }

    public int getYear() {
        return Year;
    }

    //Setters


    public void setNurseryID(int nurseryID) {
        NurseryID = nurseryID;
    }

    public void setPrID(int prID) {
        PrID = prID;
    }

    public void setStdID(int stdID) {
        StdID = stdID;
    }

    public void setAmountPaid(double amountPaid) {
        AmountPaid = amountPaid;
    }

    public void setNumberOfHours(int numberOfHours) {
        NumberOfHours = numberOfHours;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public void setYear(int year) {
        Year = year;
    }
}
