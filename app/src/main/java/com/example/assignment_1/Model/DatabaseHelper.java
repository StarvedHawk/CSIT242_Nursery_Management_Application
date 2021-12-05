package com.example.assignment_1.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.assignment_1.R;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //NURSERY
    public static final String NURSERY_TABLE = "NURSERY";
    public static final String STUDENT_NURSERY_ID = "NURSERY_ID";
    public static final String NURSERY_ID = STUDENT_NURSERY_ID;
    public static final String STUDENT_NAME = "NAME";
    public static final String NURSERY_NAME = "NURSERY_" + STUDENT_NAME;
    public static final String NURSERY_PRE_CHARGE = "MIN_CHARGE";
    public static final String NURSERY_POST_CHARGE = "MAX_CHARGE";
    public static final String NURSERY_MIN_HOURS = "MIN_HOURS";
    //EXPENSES
    public static final String EXPENSES_NURSERY_ID = STUDENT_NURSERY_ID;
    public static final String NURSERY_EXPENSES_TABLE = "NURSERY_EXPENSES";
    public static final String NURSERY_EXPENSE_ID = "EXPENSE_ID";
    public static final String EXPENSES_AMOUNT_PAID = "AMOUNT_PAID";
    public static final String EXPENSES_MONTH = "MONTH";
    public static final String EXPENSES_YEAR = "YEAR";
    //STUDENT
    public static final String STUDENT_TABLE = "STUDENT";
    public static final String STUDENT_ID = "STUDENT_ID";
    public static final String STUDENT_SURNAME = "SURNAME";
    public static final String STUDENT_PARENT_NUMBER = "PARENT_NUMBER";
    public static final String STUDENT_DOB = "DOB";
    public static final String STUDENT_EMAIL = "EMAIL";
    //STAFF
    public static final String STAFF_TABLE = "STAFF";
    public static final String STAFF_ID = "STAFF_ID";
    public static final String STAFF_NURSERY_ID = "NURSERY_ID";
    public static final String STAFF_NAME = "STAFF_NAME";
    public static final String STAFF_PASSWORD = "PASSWORD";
    public static final String STAFF_ADMIN_AUTHORIZATION = "ADMIN_AUTHORIZATION";
    //PAYMENT RECORD
    public static final String PAYMENT_RECORD_TABLE = "PAYMENT_RECORD";
    public static final String PAYMENT_RECORD_ID = "PAYMENT_RECORD_ID";
    public static final String PR_STUDENT_ID = "STUDENT_ID";
    public static final String PR_NURSERY_ID = "NURSERY_ID";
    public static final String PR_AMOUNT_PAID = "PR_AMOUNT_PAID";
    public static final String PR_DAY = "PR_DAY";
    public static final String PR_MONTH = "PR_MONTH";
    public static final String PR_YEAR = "PR_YEAR";
    public static final String EXPENSES_TYPE = "EXPENSES_TYPE";
    public static final String PR_NUMBER_OF_HOURS = "Number_of_Hours";
    public static final String DATABASE_NAME = "Nursery_Management_System";
    public static final String SUPER_AUTHORISATION = "SUPER_AUTHORISATION";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME + ".db",null,1);
    }

    //Called on the first time the DB is accessed
    // CALL USING DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_Nursery_Statement = "CREATE TABLE " + NURSERY_TABLE +
                " (" + NURSERY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NURSERY_NAME + " TEXT COLLATE NOCASE, " +
                NURSERY_PRE_CHARGE + " INT, " +
                NURSERY_POST_CHARGE + " INT, " +
                NURSERY_MIN_HOURS + " INT);";

        String create_Nursery_Expenses_Statement = "CREATE TABLE " + NURSERY_EXPENSES_TABLE +
                " ( " + NURSERY_EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EXPENSES_NURSERY_ID + " INTEGER,  " +
                EXPENSES_AMOUNT_PAID + " INT, " +
                EXPENSES_MONTH + " INT," +
                EXPENSES_YEAR + " INT,"+
                EXPENSES_TYPE + " TEXT COLLATE NOCASE," +
                "FOREIGN KEY ("+EXPENSES_NURSERY_ID+") REFERENCES " + NURSERY_TABLE +"("+NURSERY_ID+"));";

        String create_Student_Table = "CREATE TABLE " + STUDENT_TABLE +
                " ( " +
                STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STUDENT_NURSERY_ID + " INT, " +
                STUDENT_NAME + " TEXT COLLATE NOCASE, " +
                STUDENT_SURNAME + " TEXT COLLATE NOCASE, " +
                STUDENT_PARENT_NUMBER + " TEXT, " +
                STUDENT_DOB + " TEXT," +
                STUDENT_EMAIL + " TEXT," +
                "FOREIGN KEY ("+STUDENT_NURSERY_ID+") REFERENCES " + NURSERY_TABLE +"("+NURSERY_ID+"));";

        String create_Staff_Table = "CREATE TABLE " + STAFF_TABLE +
                " ( " + STAFF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STAFF_NURSERY_ID + " INT, " +
                STAFF_NAME + " TEXT COLLATE NOCASE, " +
                STAFF_PASSWORD + " TEXT, " +
                STAFF_ADMIN_AUTHORIZATION + " BOOL ,"+
                SUPER_AUTHORISATION + " BOOL );";

        String create_Payment_record_Table = "CREATE TABLE " + PAYMENT_RECORD_TABLE +
                "( " + PAYMENT_RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PR_STUDENT_ID + " INT," +
                PR_NURSERY_ID + " INT, " +
                PR_AMOUNT_PAID + " INT, " +
                PR_NUMBER_OF_HOURS + " INT, " +
                PR_DAY + " INT, " +
                PR_MONTH + " INT, " +
                PR_YEAR + " INT,"+
                " FOREIGN KEY ("+PR_STUDENT_ID+") REFERENCES " +STUDENT_TABLE+"("+STUDENT_ID+ "), "+
                "FOREIGN KEY (" + PR_NURSERY_ID + ") REFERENCES " + NURSERY_TABLE +"("+NURSERY_ID+"));";

        sqLiteDatabase.execSQL(create_Nursery_Statement);
        sqLiteDatabase.execSQL(create_Nursery_Expenses_Statement);
        sqLiteDatabase.execSQL(create_Student_Table);
        sqLiteDatabase.execSQL(create_Staff_Table);
        sqLiteDatabase.execSQL(create_Payment_record_Table);

    }

    //Called when the DB is upgraded
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Inserts
    public boolean AddNursery(Nursery nurseryModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NURSERY_NAME,nurseryModel.getNurseryName());
        cv.put(NURSERY_POST_CHARGE,nurseryModel.getPostCharge());
        cv.put(NURSERY_PRE_CHARGE,nurseryModel.getPreCharge());
        cv.put(NURSERY_MIN_HOURS,nurseryModel.getMinHours());

        long insert = db.insert(NURSERY_TABLE,null,cv);
        return (insert!=-1);            //IF insert is negative aka an error return false
    }
    public boolean AddStudent(Student studentModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(STUDENT_NAME,studentModel.getName());
        cv.put(STUDENT_SURNAME,studentModel.getSurName());
        cv.put(STUDENT_DOB,studentModel.getDOB());
        cv.put(STUDENT_PARENT_NUMBER,studentModel.getParent_Number());
        cv.put(STUDENT_EMAIL,studentModel.getEmail());
        cv.put(STUDENT_NURSERY_ID,studentModel.getNurseryID());

        long insert = db.insert(STUDENT_TABLE, null, cv);
        return (insert!=-1);
    }
    public boolean AddStaff(Staff staffModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(STAFF_NAME,staffModel.getStaffName());
        cv.put(STAFF_PASSWORD,staffModel.getPassword());
        cv.put(STAFF_ADMIN_AUTHORIZATION,staffModel.isAdmin());
        cv.put(SUPER_AUTHORISATION,staffModel.isSuper_Auth());
        cv.put(STAFF_NURSERY_ID,staffModel.getNurseryID());

        long insert = db.insert(STAFF_TABLE, null, cv);
        return (insert!=-1);
    }
    public boolean AddExpenses(Expenses expenseModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EXPENSES_NURSERY_ID,expenseModel.getNurseryID());
        cv.put(EXPENSES_AMOUNT_PAID,expenseModel.getAmountPaid());
        cv.put(EXPENSES_MONTH,expenseModel.getMonth());
        cv.put(EXPENSES_YEAR,expenseModel.getYear());
        cv.put(EXPENSES_TYPE,expenseModel.getType());

        long insert = db.insert(NURSERY_EXPENSES_TABLE, null, cv);
        return (insert!=-1);
    }
    public boolean AddPaymentRecord(Payment_Record PRModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PR_DAY,PRModel.getDay());
        cv.put(PR_MONTH,PRModel.getMonth());
        cv.put(PR_YEAR,PRModel.getYear());
        cv.put(PR_AMOUNT_PAID,PRModel.getAmountPaid());
        cv.put(PR_NUMBER_OF_HOURS,PRModel.getNumberOfHours());
        cv.put(PR_NURSERY_ID,PRModel.getNurseryID());
        cv.put(PR_STUDENT_ID,PRModel.getStdID());

        long insert = db.insert(PAYMENT_RECORD_TABLE, null, cv);
        return (insert!=-1);
    }


    //Get ALL ROWS
    public ArrayList<Nursery> getAllNurseries(){
        ArrayList<Nursery> ListToReturn = new ArrayList<>();
        //get data from the database

        String query ="SELECT * FROM "+NURSERY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor Nurseries = db.rawQuery(query,null);
        if(Nurseries.moveToFirst()){
            do{
                int Nursery_ID = Nurseries.getInt(0);
                String Nursery_Name = Nurseries.getString(1);
                int MinimumCharge = Nurseries.getInt(2);
                int MaximumCharge = Nurseries.getInt(3);
                int MinimumHours = Nurseries.getInt(4);
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Nursery newNursery = new Nursery(Nursery_ID,Nursery_Name,MinimumCharge,MaximumCharge,MinimumHours);
                ListToReturn.add(newNursery);

            }while(Nurseries.moveToNext());
        }
        else {
            //Error Handling
        }

        Nurseries.close();
        db.close();

        return ListToReturn;
    }
    public ArrayList<Staff> getAllStaff(){
        ArrayList<Staff> ListToReturn = new ArrayList<>();
        //get data from the database

        String query ="SELECT * FROM "+STAFF_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor current_staff = db.rawQuery(query,null);
        if(current_staff.moveToFirst()){
            do{
                int staff_id = current_staff.getInt(0);
                int Nursery_ID = current_staff.getInt(1);
                String staff_name = current_staff.getString(2);
                String password = current_staff.getString(3);
                boolean isAdmin = current_staff.getInt(4)==1?true:false;
                boolean isSuper = current_staff.getInt(5)==1?true:false;
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Staff newStaff = new Staff(staff_id,Nursery_ID,staff_name,password,isAdmin,isSuper);
                ListToReturn.add(newStaff);

            }while(current_staff.moveToNext());
        }
        else {
            //Error Handling
        }

        current_staff.close();
        db.close();

        return ListToReturn;
    }
    public ArrayList<Student> getAllStudents(int NurseryID){
        ArrayList<Student> ListToReturn = new ArrayList<>();
        //get data from the database

        String query ="SELECT * FROM "+STUDENT_TABLE + " WHERE " + STUDENT_NURSERY_ID + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        String[] args = {""+NurseryID+""};

        Cursor Students = db.rawQuery(query,args);
        if(Students.moveToFirst()){
            do{
                int Student_ID = Students.getInt(0);
                int Nursery_ID = Students.getInt(1);
                String Student_Name = Students.getString(2);
                String Student_surname = Students.getString(3);
                String Parent_Number = Students.getString(4);
                String DOB = Students.getString(5);
                String Email = Students.getString(6);
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Student newStudent = new Student(Student_ID,Nursery_ID,Student_Name,Student_surname,Parent_Number,DOB,Email, R.drawable.user);
                ListToReturn.add(newStudent);

            }while(Students.moveToNext());
        }
        else {
            //Error Handling
        }
        Students.close();
        db.close();
        return ListToReturn;
    }
    public List<Expenses> getAllExpenses(int NurseryID){
        List<Expenses> ListToReturn = new ArrayList<>();
        //get data from the database

        String query ="SELECT * FROM "+NURSERY_EXPENSES_TABLE +
                " WHERE " + EXPENSES_NURSERY_ID + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        String[] args = {""+NurseryID+""};

        Cursor Expenses_Cursor = db.rawQuery(query,args);
        if(Expenses_Cursor.moveToFirst()){
            do{
                int Expenses_ID = Expenses_Cursor.getInt(0);
                int Nursery_ID = Expenses_Cursor.getInt(1);
                int Amount_Paid = Expenses_Cursor.getInt(2);
                int Month = Expenses_Cursor.getInt(3);
                int Year = Expenses_Cursor.getInt(4);
                String Type = Expenses_Cursor.getString(5);

                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Expenses newExpense = new Expenses(Expenses_ID,Nursery_ID,Amount_Paid,Month,Year,Type);
                ListToReturn.add(newExpense);

            }while(Expenses_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Expenses_Cursor.close();
        db.close();

        return ListToReturn;
    }
    public List<Payment_Record> getAllPR(int NurseryID){
        List<Payment_Record> ListToReturn = new ArrayList<>();
        //get data from the database

        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE " + EXPENSES_NURSERY_ID + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        String[] args = {""+NurseryID+""};

        Cursor Payment_Record_Cursor = db.rawQuery(query,args);
        if(Payment_Record_Cursor.moveToFirst()){
            do{
                int Payment_Record_ID = Payment_Record_Cursor.getInt(0);
                int Student_ID = Payment_Record_Cursor.getInt(1);
                int Nursery_ID = Payment_Record_Cursor.getInt(2);
                int Amount_Paid = Payment_Record_Cursor.getInt(3);
                int NumberOfHours = Payment_Record_Cursor.getInt(4);
                int Day = Payment_Record_Cursor.getInt(5);
                int Month = Payment_Record_Cursor.getInt(6);
                int Year = Payment_Record_Cursor.getInt(7);

                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Payment_Record newPaymentRecord = new Payment_Record(Payment_Record_ID,Student_ID,Nursery_ID,Amount_Paid,NumberOfHours,Day,Month,Year);
                ListToReturn.add(newPaymentRecord);

            }while(Payment_Record_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Record_Cursor.close();
        db.close();

        return ListToReturn;
    }

    //Filtering
    public Staff loginStaff(String name,String password){

        //get data from the database

        String query ="SELECT * FROM "+STAFF_TABLE +
                " WHERE "+STAFF_NAME+" = ? AND "+STAFF_PASSWORD+" = ?";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor Staff_Cursor = db.rawQuery(query,new String[]{name,password});
        if(Staff_Cursor.moveToFirst()){
                int Staff_ID = Staff_Cursor.getInt(0);
                int Nursery_ID = Staff_Cursor.getInt(1);
                String StaffName = Staff_Cursor.getString(2);
                String Pass = Staff_Cursor.getString(3);
                boolean isAdmin = Staff_Cursor.getInt(4)==1?true:false;
                boolean isSuper = Staff_Cursor.getInt(5)==1?true:false;
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Staff newStaff = new Staff(Staff_ID,Nursery_ID,StaffName,Pass,isAdmin,isSuper);
                Staff_Cursor.close();
                db.close();
                return newStaff;
        }
        Staff_Cursor.close();
        db.close();
        return new Staff(-1,-1,"error","error",false);
        //Need to handle this when you get it on the users end
    }
    public Nursery getCurrentNursery(int NurseryID){
        String query ="SELECT * FROM "+NURSERY_TABLE+
                " WHERE "+NURSERY_ID+" = ?";
        SQLiteDatabase db = this.getReadableDatabase();

        Nursery newNursery = new Nursery(-1,"Error",-1,-1,-1);

        Cursor Nurseries = db.rawQuery(query,new String[]{""+NurseryID+""});
        if(Nurseries.moveToFirst()){
                int Nursery_ID = Nurseries.getInt(0);
                String Nursery_Name = Nurseries.getString(1);
                int MinimumCharge = Nurseries.getInt(2);
                int MaximumCharge = Nurseries.getInt(3);
                int MinimumHours = Nurseries.getInt(4);
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;
                newNursery = new Nursery(Nursery_ID,Nursery_Name,MinimumCharge,MaximumCharge,MinimumHours);
        }

        Nurseries.close();
        db.close();
        return newNursery;
    }
    public List<Expenses> filterExpenses(int check_month,int check_year, int NurseryID){
        List<Expenses> ListToReturn = new ArrayList<>();
        //get data from the database

        String query ="SELECT * FROM "+NURSERY_EXPENSES_TABLE+
                " WHERE "+
                EXPENSES_NURSERY_ID+" = ? AND "+
                EXPENSES_MONTH+" = ? AND "+
                EXPENSES_YEAR+" = ? ";
        String[] ARGS = {""+NurseryID+"",""+check_month+"",""+check_year+""};
        SQLiteDatabase db = this.getReadableDatabase();

                Cursor Expenses_Cursor = db.rawQuery(query,ARGS);
        if(Expenses_Cursor.moveToFirst()){
            do{
                int Expenses_ID = Expenses_Cursor.getInt(0);
                int Nursery_ID = Expenses_Cursor.getInt(1);
                int Amount_Paid = Expenses_Cursor.getInt(2);
                int Month = Expenses_Cursor.getInt(3);
                int Year = Expenses_Cursor.getInt(4);
                String Type = Expenses_Cursor.getString(5);

                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Expenses newExpense = new Expenses(Expenses_ID,Nursery_ID,Amount_Paid,Month,Year,Type);
                ListToReturn.add(newExpense);

            }while(Expenses_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Expenses_Cursor.close();
        db.close();

        return ListToReturn;
    }
    public List<Expenses> filterExpenses(int check_year,int NurseryID){
        List<Expenses> ListToReturn = new ArrayList<>();
        //get data from the database

        String query ="SELECT * FROM "+NURSERY_EXPENSES_TABLE+
                " WHERE " +
                EXPENSES_NURSERY_ID+" = ? AND "+
        EXPENSES_YEAR+" = ? ";
        String[] ARGS = {""+NurseryID+"",""+check_year+""};
        SQLiteDatabase db = this.getReadableDatabase();



        Cursor Expenses_Cursor = db.rawQuery(query,ARGS);
        if(Expenses_Cursor.moveToFirst()){
            do{
                int Expenses_ID = Expenses_Cursor.getInt(0);
                int Nursery_ID = Expenses_Cursor.getInt(1);
                int Amount_Paid = Expenses_Cursor.getInt(2);
                int Month = Expenses_Cursor.getInt(3);
                int Year = Expenses_Cursor.getInt(4);
                String Type = Expenses_Cursor.getString(5);

                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Expenses newExpense = new Expenses(Expenses_ID,Nursery_ID,Amount_Paid,Month,Year,Type);
                ListToReturn.add(newExpense);

            }while(Expenses_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Expenses_Cursor.close();
        db.close();

        return ListToReturn;
    }
    public List<Payment_Record> filterPayments(int check_day,int check_month,int check_year,int NurseryID){
        List<Payment_Record> ListToReturn = new ArrayList<>();
        //get data from the database

        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE "+
                EXPENSES_NURSERY_ID+" = ? AND "+
                PR_DAY+" = ? AND "+
                PR_MONTH+" = ? AND "+
                PR_YEAR+" = ? ";

        String[] ARGS = {""+NurseryID+"",""+check_day+"",""+check_month+"",""+check_year+""};
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor Payment_Record_Cursor = db.rawQuery(query,ARGS);
        if(Payment_Record_Cursor.moveToFirst()){
            do{
                int Payment_Record_ID = Payment_Record_Cursor.getInt(0);
                int Student_ID = Payment_Record_Cursor.getInt(1);
                int Nursery_ID = Payment_Record_Cursor.getInt(2);
                int Amount_Paid = Payment_Record_Cursor.getInt(3);
                int NumberOfHours = Payment_Record_Cursor.getInt(4);
                int Day = Payment_Record_Cursor.getInt(5);
                int Month = Payment_Record_Cursor.getInt(6);
                int Year = Payment_Record_Cursor.getInt(7);

                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Payment_Record newPaymentRecord = new Payment_Record(Payment_Record_ID,Student_ID,Nursery_ID,Amount_Paid,NumberOfHours,Day,Month,Year);
                ListToReturn.add(newPaymentRecord);

            }while(Payment_Record_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Record_Cursor.close();
        db.close();

        return ListToReturn;
    }
    public List<Payment_Record> filterPayments(int check_month,int check_year,int NurseryID){
        List<Payment_Record> ListToReturn = new ArrayList<>();
        //get data from the database

        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE "+
                EXPENSES_NURSERY_ID+" = ? AND "+
                PR_MONTH+" = ? AND "+
                PR_YEAR+" = ? ";

        String[] ARGS = {""+NurseryID+"",""+check_month+"",""+check_year+""};
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor Payment_Record_Cursor = db.rawQuery(query,ARGS);
        if(Payment_Record_Cursor.moveToFirst()){
            do{
                int Payment_Record_ID = Payment_Record_Cursor.getInt(0);
                int Student_ID = Payment_Record_Cursor.getInt(1);
                int Nursery_ID = Payment_Record_Cursor.getInt(2);
                int Amount_Paid = Payment_Record_Cursor.getInt(3);
                int NumberOfHours = Payment_Record_Cursor.getInt(4);
                int Day = Payment_Record_Cursor.getInt(5);
                int Month = Payment_Record_Cursor.getInt(6);
                int Year = Payment_Record_Cursor.getInt(7);

                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Payment_Record newPaymentRecord = new Payment_Record(Payment_Record_ID,Student_ID,Nursery_ID,Amount_Paid,NumberOfHours,Day,Month,Year);
                ListToReturn.add(newPaymentRecord);

            }while(Payment_Record_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Record_Cursor.close();
        db.close();

        return ListToReturn;
    }
    public List<Payment_Record> filterPayments(int check_year,int NurseryID){
        List<Payment_Record> ListToReturn = new ArrayList<>();
        //get data from the database

        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE "+
                EXPENSES_NURSERY_ID+" = ? AND "+
                PR_YEAR+" = ? ";

        String[] ARGS = {""+NurseryID+"",""+check_year+""};
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor Payment_Record_Cursor = db.rawQuery(query,ARGS);
        if(Payment_Record_Cursor.moveToFirst()){
            do{
                int Payment_Record_ID = Payment_Record_Cursor.getInt(0);
                int Student_ID = Payment_Record_Cursor.getInt(1);
                int Nursery_ID = Payment_Record_Cursor.getInt(2);
                int Amount_Paid = Payment_Record_Cursor.getInt(3);
                int NumberOfHours = Payment_Record_Cursor.getInt(4);
                int Day = Payment_Record_Cursor.getInt(5);
                int Month = Payment_Record_Cursor.getInt(6);
                int Year = Payment_Record_Cursor.getInt(7);

                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Payment_Record newPaymentRecord = new Payment_Record(Payment_Record_ID,Student_ID,Nursery_ID,Amount_Paid,NumberOfHours,Day,Month,Year);
                ListToReturn.add(newPaymentRecord);

            }while(Payment_Record_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Record_Cursor.close();
        db.close();

        return ListToReturn;
    }
    public List<Payment_Record> filterStudentPayments(int StudentID,int check_day,int check_month,int check_year,int NurseryID){
        List<Payment_Record> ListToReturn = new ArrayList<>();
        //get data from the database

        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE "+
                EXPENSES_NURSERY_ID+" = ? AND "+
                PR_STUDENT_ID+" = ? AND "+
                PR_DAY+" = ? AND "+
                PR_MONTH+" = ? AND "+
                PR_YEAR+" = ? ";

        String[] ARGS = {""+NurseryID+"",""+StudentID+"",""+check_day+"",""+check_month+"",""+check_year+""};
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor Payment_Record_Cursor = db.rawQuery(query,ARGS);
        if(Payment_Record_Cursor.moveToFirst()){
            do{
                int Payment_Record_ID = Payment_Record_Cursor.getInt(0);
                int Student_ID = Payment_Record_Cursor.getInt(1);
                int Nursery_ID = Payment_Record_Cursor.getInt(2);
                int Amount_Paid = Payment_Record_Cursor.getInt(3);
                int NumberOfHours = Payment_Record_Cursor.getInt(4);
                int Day = Payment_Record_Cursor.getInt(5);
                int Month = Payment_Record_Cursor.getInt(6);
                int Year = Payment_Record_Cursor.getInt(7);

                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Payment_Record newPaymentRecord = new Payment_Record(Payment_Record_ID,Student_ID,Nursery_ID,Amount_Paid,NumberOfHours,Day,Month,Year);
                ListToReturn.add(newPaymentRecord);

            }while(Payment_Record_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Record_Cursor.close();
        db.close();

        return ListToReturn;
    }
    public List<Payment_Record> filterStudentPayments(int StudentID,int check_month,int check_year,int NurseryID){
        List<Payment_Record> ListToReturn = new ArrayList<>();
        //get data from the database

        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE "+
                EXPENSES_NURSERY_ID+" = ? AND "+
                PR_STUDENT_ID+" = ? AND "+
                PR_MONTH+" = ? AND "+
                PR_YEAR+" = ? ";

        String[] ARGS = {""+NurseryID+"",""+StudentID+"",""+check_month+"",""+check_year+""};
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor Payment_Record_Cursor = db.rawQuery(query,ARGS);
        if(Payment_Record_Cursor.moveToFirst()){
            do{
                int Payment_Record_ID = Payment_Record_Cursor.getInt(0);
                int Student_ID = Payment_Record_Cursor.getInt(1);
                int Nursery_ID = Payment_Record_Cursor.getInt(2);
                int Amount_Paid = Payment_Record_Cursor.getInt(3);
                int NumberOfHours = Payment_Record_Cursor.getInt(4);
                int Day = Payment_Record_Cursor.getInt(5);
                int Month = Payment_Record_Cursor.getInt(6);
                int Year = Payment_Record_Cursor.getInt(7);

                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Payment_Record newPaymentRecord = new Payment_Record(Payment_Record_ID,Student_ID,Nursery_ID,Amount_Paid,NumberOfHours,Day,Month,Year);
                ListToReturn.add(newPaymentRecord);

            }while(Payment_Record_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Record_Cursor.close();
        db.close();

        return ListToReturn;
    }
    public List<Payment_Record> filterStudentPayments(int StudentID,int check_year,int NurseryID){
        List<Payment_Record> ListToReturn = new ArrayList<>();
        //get data from the database

        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE "+
                EXPENSES_NURSERY_ID+" = ? AND "+
                PR_STUDENT_ID+" = ? AND "+
                PR_YEAR+" = ? ";

        String[] ARGS = {""+NurseryID+"",""+StudentID+"",""+check_year+""};
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor Payment_Record_Cursor = db.rawQuery(query,ARGS);
        if(Payment_Record_Cursor.moveToFirst()){
            do{
                int Payment_Record_ID = Payment_Record_Cursor.getInt(0);
                int Student_ID = Payment_Record_Cursor.getInt(1);
                int Nursery_ID = Payment_Record_Cursor.getInt(2);
                int Amount_Paid = Payment_Record_Cursor.getInt(3);
                int NumberOfHours = Payment_Record_Cursor.getInt(4);
                int Day = Payment_Record_Cursor.getInt(5);
                int Month = Payment_Record_Cursor.getInt(6);
                int Year = Payment_Record_Cursor.getInt(7);

                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Payment_Record newPaymentRecord = new Payment_Record(Payment_Record_ID,Student_ID,Nursery_ID,Amount_Paid,NumberOfHours,Day,Month,Year);
                ListToReturn.add(newPaymentRecord);

            }while(Payment_Record_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Record_Cursor.close();
        db.close();

        return ListToReturn;
    }
    public List<Payment_Record> filterStudentPayments(int StudentID,int NurseryID){
        List<Payment_Record> ListToReturn = new ArrayList<>();
        //get data from the database

        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE "+
                EXPENSES_NURSERY_ID+" = ? AND "+
                PR_STUDENT_ID+" = ? " ;

        String[] ARGS = {""+NurseryID+"",""+StudentID+""};
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor Payment_Record_Cursor = db.rawQuery(query,ARGS);
        if(Payment_Record_Cursor.moveToFirst()){
            do{
                int Payment_Record_ID = Payment_Record_Cursor.getInt(0);
                int Student_ID = Payment_Record_Cursor.getInt(1);
                int Nursery_ID = Payment_Record_Cursor.getInt(2);
                int Amount_Paid = Payment_Record_Cursor.getInt(3);
                int NumberOfHours = Payment_Record_Cursor.getInt(4);
                int Day = Payment_Record_Cursor.getInt(5);
                int Month = Payment_Record_Cursor.getInt(6);
                int Year = Payment_Record_Cursor.getInt(7);

                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;

                Payment_Record newPaymentRecord = new Payment_Record(Payment_Record_ID,Student_ID,Nursery_ID,Amount_Paid,NumberOfHours,Day,Month,Year);
                ListToReturn.add(newPaymentRecord);

            }while(Payment_Record_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Record_Cursor.close();
        db.close();

        return ListToReturn;
    }

    //Update Nursery
    public void updateNurseryDetails(int NurseryID,int PreCharge,int PostCharge,int NumHours){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NURSERY_MIN_HOURS,NumHours);
        cv.put(NURSERY_PRE_CHARGE,PreCharge);
        cv.put(NURSERY_POST_CHARGE,PostCharge);

        String[] ARGS = {""+NurseryID+""};

        db.update(NURSERY_TABLE,cv,""+NURSERY_ID+" = ?",ARGS);
        db.close();

    }
    public void resetPassword(int NurseryID,int StaffID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STAFF_PASSWORD,"0000");
        String[] ARGS = {""+NurseryID+"",""+StaffID+""};

        db.update(STAFF_TABLE,cv,""+STAFF_NURSERY_ID+" = ? AND "+STAFF_ID+" = ?" ,ARGS);
        db.close();

    }
    //Get Total Expenses
    public double getTotalExpenses(int NurseryID){
        String query ="SELECT * FROM "+ NURSERY_EXPENSES_TABLE + " WHERE " + EXPENSES_NURSERY_ID+" = ? ";
        SQLiteDatabase db = this.getReadableDatabase();
        String[] args = {""+NurseryID+""};

        double total_Expense = 0;

        Cursor Expenses_Cursor = db.rawQuery(query,args);
        if(Expenses_Cursor.moveToFirst()){
            do{
                int Amount_Paid = Expenses_Cursor.getInt(2);
                total_Expense += Amount_Paid;
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;
            }while(Expenses_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Expenses_Cursor.close();
        db.close();
        return total_Expense;
    }
    //Get Filtered Expenses
    public double getTotalExpenses(int check_day,int check_month,int check_year,int NurseryID){
        String query ="SELECT * FROM "+NURSERY_EXPENSES_TABLE+
                " WHERE "+
                EXPENSES_NURSERY_ID+" = ? AND "+
                EXPENSES_MONTH+" = ? AND "+
                EXPENSES_YEAR+" = ? ";
        SQLiteDatabase db = this.getReadableDatabase();

        String[] ARGS = {""+NurseryID+"",""+check_month+"",""+check_year+""};

        double total_Expense = 0;

        Cursor Expenses_Cursor = db.rawQuery(query,ARGS);
        if(Expenses_Cursor.moveToFirst()){
            do{
                int Amount_Paid = Expenses_Cursor.getInt(2);
                total_Expense += Amount_Paid;
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;
            }while(Expenses_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Expenses_Cursor.close();
        db.close();
        return total_Expense/30;
    }
    public double getTotalExpenses(int check_month,int check_year,int NurseryID){
        String query ="SELECT * FROM "+NURSERY_EXPENSES_TABLE+
                " WHERE "+
                EXPENSES_NURSERY_ID+" = ? AND "+
                EXPENSES_MONTH+" = ? AND "+
                EXPENSES_YEAR+" = ? ";
        SQLiteDatabase db = this.getReadableDatabase();

        String[] ARGS = {""+NurseryID+"",""+check_month+"",""+check_year+""};

        double total_Expense = 0;

        Cursor Expenses_Cursor = db.rawQuery(query,ARGS);
        if(Expenses_Cursor.moveToFirst()){
            do{
                int Amount_Paid = Expenses_Cursor.getInt(2);
                total_Expense += Amount_Paid;
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;
            }while(Expenses_Cursor.moveToNext());
        }
        else {

        }

        Expenses_Cursor.close();
        db.close();
        return total_Expense;
    }
    public double getTotalExpenses(int check_year,int NurseryID){
        String query ="SELECT * FROM "+NURSERY_EXPENSES_TABLE+
                " WHERE "+ EXPENSES_NURSERY_ID+" = ? AND " + EXPENSES_YEAR+" = ? ";
        SQLiteDatabase db = this.getReadableDatabase();

        String[] ARGS = {""+NurseryID+"",""+check_year+""};

        double total_Expense = 0;

        Cursor Expenses_Cursor = db.rawQuery(query,ARGS);
        if(Expenses_Cursor.moveToFirst()){
            do{
                int Amount_Paid = Expenses_Cursor.getInt(2);
                total_Expense += Amount_Paid;
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;
            }while(Expenses_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Expenses_Cursor.close();
        db.close();
        return total_Expense;
    }
    //Get Total Payments
    public double getTotalPayments(int NurseryID){
        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE + " WHERE " + PR_NURSERY_ID+" = ? ";
        SQLiteDatabase db = this.getReadableDatabase();
        String[] args = {""+NurseryID+""};

        double total_Expense = 0;

        Cursor Payment_Cursor = db.rawQuery(query,args);
        if(Payment_Cursor.moveToFirst()){
            do{
                int Amount_Paid = Payment_Cursor.getInt(3);
                total_Expense += Amount_Paid;
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;
            }while(Payment_Cursor.moveToNext());
            return total_Expense;
        }
        else {
            //Error Handling
        }

        Payment_Cursor.close();
        db.close();
        return total_Expense;
    }
    //Get Filtered Payments
    public double getTotalPayments(int check_day, int check_month,int check_year,int NurseryID){
        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE "+
                PR_NURSERY_ID+" = ? AND "+
                PR_DAY+" = ? AND "+
                PR_MONTH+" = ? AND "+
                PR_YEAR+" = ? ";
        SQLiteDatabase db = this.getReadableDatabase();

        String[] ARGS = {""+NurseryID+"",""+check_day+"",""+check_month+"",""+check_year+""};

        double total_Expense = 0;

        Cursor Payment_Cursor = db.rawQuery(query,ARGS);
        if(Payment_Cursor.moveToFirst()){
            do{
                int Amount_Paid = Payment_Cursor.getInt(3);
                total_Expense += Amount_Paid;
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;
            }while(Payment_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Cursor.close();
        db.close();
        return total_Expense;
    }
    public double getTotalPayments(int check_month,int check_year,int NurseryID){
        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE "+
                PR_NURSERY_ID+" = ? AND "+
                PR_MONTH+" = ? AND "+
                PR_YEAR+" = ? ";
        SQLiteDatabase db = this.getReadableDatabase();

        String[] ARGS = {""+NurseryID+"",""+check_month+"",""+check_year+""};

        double total_Expense = 0;

        Cursor Payment_Cursor = db.rawQuery(query,ARGS);
        if(Payment_Cursor.moveToFirst()){
            do{
                int Amount_Paid = Payment_Cursor.getInt(3);
                total_Expense += Amount_Paid;
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;
            }while(Payment_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Cursor.close();
        db.close();
        return total_Expense;
    }
    public double getTotalPayments(int check_year,int NurseryID){
        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE "+
                PR_NURSERY_ID+" = ? AND "+
                PR_YEAR+" = ? ";
        SQLiteDatabase db = this.getReadableDatabase();

        String[] ARGS = {""+NurseryID+"",""+check_year+""};

        double total_Expense = 0;

        Cursor Payment_Cursor = db.rawQuery(query,ARGS);
        if(Payment_Cursor.moveToFirst()){
            do{
                int Amount_Paid = Payment_Cursor.getInt(3);
                total_Expense += Amount_Paid;
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;
            }while(Payment_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Cursor.close();
        db.close();
        return total_Expense;
    }
    public double getTotalStudentPayments(int StudentID, int check_day, int check_month,int check_year,int NurseryID){
        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE "+
                PR_NURSERY_ID+" = ? AND "+
                PR_STUDENT_ID+" = ? AND "+
                PR_DAY+" = ? AND "+
                PR_MONTH+" = ? AND "+
                PR_YEAR+" = ? ";
        SQLiteDatabase db = this.getReadableDatabase();

        String[] ARGS = {""+NurseryID+"",""+StudentID+"",""+check_day+"",""+check_month+"",""+check_year+""};

        double total_Expense = 0;

        Cursor Payment_Cursor = db.rawQuery(query,ARGS);
        if(Payment_Cursor.moveToFirst()){
            do{
                int Amount_Paid = Payment_Cursor.getInt(3);
                total_Expense += Amount_Paid;
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;
            }while(Payment_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Cursor.close();
        db.close();
        return total_Expense;
    }
    public double getTotalStudentPayments(int StudentID, int check_month,int check_year,int NurseryID){
        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE "+
                PR_NURSERY_ID+" = ? AND "+
                PR_STUDENT_ID+" = ? AND "+
                PR_MONTH+" = ? AND "+
                PR_YEAR+" = ? ";
        SQLiteDatabase db = this.getReadableDatabase();

        String[] ARGS = {""+NurseryID+"",""+StudentID+"",""+check_month+"",""+check_year+""};

        double total_Expense = 0;

        Cursor Payment_Cursor = db.rawQuery(query,ARGS);
        if(Payment_Cursor.moveToFirst()){
            do{
                int Amount_Paid = Payment_Cursor.getInt(3);
                total_Expense += Amount_Paid;
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;
            }while(Payment_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Cursor.close();
        db.close();
        return total_Expense;
    }
    public double getTotalStudentPayments(int StudentID,int check_year,int NurseryID){
        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE "+
                PR_NURSERY_ID+" = ? AND "+
                PR_STUDENT_ID+" = ? AND "+
                PR_YEAR+" = ? ";
        SQLiteDatabase db = this.getReadableDatabase();

        String[] ARGS = {""+NurseryID+"",""+StudentID+"",""+check_year+""};

        double total_Expense = 0;

        Cursor Payment_Cursor = db.rawQuery(query,ARGS);
        if(Payment_Cursor.moveToFirst()){
            do{
                int Amount_Paid = Payment_Cursor.getInt(3);
                total_Expense += Amount_Paid;
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;
            }while(Payment_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Cursor.close();
        db.close();
        return total_Expense;
    }
    public double getTotalStudentPayments(int StudentID,int NurseryID){
        String query ="SELECT * FROM "+PAYMENT_RECORD_TABLE+
                " WHERE "+
                PR_NURSERY_ID+" = ? AND "+
                PR_STUDENT_ID+" = ? ";
        SQLiteDatabase db = this.getReadableDatabase();

        String[] ARGS = {""+NurseryID+"",""+StudentID+""};

        double total_Expense = 0;

        Cursor Payment_Cursor = db.rawQuery(query,ARGS);
        if(Payment_Cursor.moveToFirst()){
            do{
                int Amount_Paid = Payment_Cursor.getInt(3);
                total_Expense += Amount_Paid;
                //getting booleans : boolean bool = Nurseries.getInt(n)==1?true:false;
            }while(Payment_Cursor.moveToNext());
        }
        else {
            //Error Handling
        }

        Payment_Cursor.close();
        db.close();
        return total_Expense;
    }
    //Get Profits
    public double totalProfits(int NurseryID){
        double profit = 0;
        double TotalExpense = getTotalExpenses(NurseryID);
        double TotalIncome = getTotalPayments(NurseryID);
        profit = TotalIncome - TotalExpense;
        return profit;
    }
    public double totalProfits(int check_day, int check_month,int check_year,int NurseryID){
        double profit = 0;
        double TotalExpense = getTotalExpenses(check_day,check_month,check_year,NurseryID);
        double TotalIncome = getTotalPayments(check_day,check_month,check_year,NurseryID);
        profit = TotalIncome - TotalExpense;
        return profit;
    }
    public double totalProfits(int check_month,int check_year,int NurseryID){
        double profit = 0;
        double TotalExpense = getTotalExpenses(check_month,check_year,NurseryID);
        double TotalIncome = getTotalPayments(check_month,check_year,NurseryID);
        profit = TotalIncome - TotalExpense;
        return profit;
    }
    public double totalProfits(int check_year,int NurseryID){
        double profit = 0;
        double TotalExpense = getTotalExpenses(check_year,NurseryID);
        double TotalIncome = getTotalPayments(check_year,NurseryID);
        profit = TotalIncome - TotalExpense;
        return profit;
    }
    //toString
    public ArrayList<String> expensesToString(List<Expenses> e){

        ArrayList<String> exp = new ArrayList<>();

        for (int i = 0; i<e.size();i++){
            exp.add(e.get(i).toString());
        }

        return exp;
    }

    public ArrayList<String> PRToString(List<Payment_Record> p){

        ArrayList<String> pr = new ArrayList<>();

        for (int i = 0; i<p.size();i++){
            pr.add(p.get(i).toString());
        }

        return pr;
    }
}