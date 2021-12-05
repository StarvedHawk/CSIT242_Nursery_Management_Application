package com.example.assignment_1.Model;

public class Expenses {
    private int ExpenseID;          //P-Key
    private int NurseryID;          //F-key
    private int AmountPaid;
    private int Month;
    private int Year;
    private String Type;            // Rent/Salary/Utility etc

    //Constructor

    public Expenses(int expenseID, int nurseryID, int amountPaid, int month, int year, String type) {
        ExpenseID = expenseID;
        NurseryID = nurseryID;
        AmountPaid = amountPaid;
        Month = month;
        Year = year;
        Type = type;
    }

    public Expenses() {
    }
    //Functions

    @Override
    public String toString() {
        return "$" + getAmountPaid() + " paid on " + getMonth() + "/" + getYear() + " for " + getType();
    }


    //Getters

    public int getExpenseID() {
        return ExpenseID;
    }

    public int getNurseryID() {
        return NurseryID;
    }

    public int getAmountPaid() {
        return AmountPaid;
    }

    public int getMonth() {
        return Month;
    }

    public int getYear() {
        return Year;
    }

    public String getType() {
        return Type;
    }

    //Setters


    public void setExpenseID(int expenseID) {
        ExpenseID = expenseID;
    }

    public void setNurseryID(int nurseryID) {
        NurseryID = nurseryID;
    }

    public void setAmountPaid(int amountPaid) {
        AmountPaid = amountPaid;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public void setYear(int year) {
        Year = year;
    }

    public void setType(String type) {
        Type = type;
    }
}
