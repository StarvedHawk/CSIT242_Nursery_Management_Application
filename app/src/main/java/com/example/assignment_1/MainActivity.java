package com.example.assignment_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.example.assignment_1.Model.DatabaseHelper;
import com.example.assignment_1.Model.Expenses;
import com.example.assignment_1.Model.Nursery;
import com.example.assignment_1.Model.Payment_Record;
import com.example.assignment_1.Model.Staff;
import com.example.assignment_1.Model.Student;

import java.io.File;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    TextView dbCheck;
    Button b1;
    ImageButton b2;
    Switch s1;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));
        getSupportActionBar().setTitle("Nursery Management Application");

        DatabaseHelper database = new DatabaseHelper(MainActivity.this);
        dbCheck = findViewById(R.id.database_check);

        Nursery nursery = new Nursery(-1,"Little Hope", 40,30,4);
        Staff staff = new Staff(-1,1,"Angela","staff",false);
        Staff admin = new Staff(-1,1,"John","admin",true);
        Staff super_staff = new Staff(-1,1,"Tamas","super",true,true);

        Expenses expenses1 = new Expenses(-1,1,1500,3,2019,"Rent");
        Expenses expenses2 = new Expenses(-1,1,1000,1,2020,"Utility");
        Expenses expenses3 = new Expenses(-1,1,900,2,2019,"Salary");

        Student student1 = new Student(-1,1,"Jack","Jons","0509761288","21-03-2017","jack@gmail.com");
        Student student2 = new Student(-1,1,"Adnaan","Butler","0509761288","21-03-2017","adnaan@gmail.com");
        Student student3 = new Student(-1,1,"Faisal","Jackson","0509761288","21-03-2017","faisal@gmail.com");
        Student student4 = new Student(-1,1,"Ahmed","Boomer","0509761288","21-03-2017","ahmed@gmail.com");

        Payment_Record p_r1 = new Payment_Record(-1,1,1,80,2,20,3,2019);
        Payment_Record p_r2 = new Payment_Record(-1,2,1,1000,10,15,1,2020);
        Payment_Record p_r3 = new Payment_Record(-1,4,1,1500,20,10,2,2019);

        if(!doesDatabaseExist(MainActivity.this,DatabaseHelper.DATABASE_NAME+".db")){
            database.AddNursery(nursery);
            database.AddStaff(staff);
            database.AddStaff(admin);
            database.AddStaff(super_staff);
            database.AddStudent(student1);
            database.AddStudent(student2);
            database.AddStudent(student3);
            database.AddStudent(student4);
            database.AddExpenses(expenses1);
            database.AddExpenses(expenses2);
            database.AddExpenses(expenses3);
            database.AddPaymentRecord(p_r1);
            database.AddPaymentRecord(p_r2);
            database.AddPaymentRecord(p_r3);
            dbCheck.setText("Database Initialised");
        } //Populating DB

        username = findViewById(R.id.user_name_id);
        password = findViewById(R.id.password_id);
        b1 = findViewById(R.id.login_button_id);
        b2 = findViewById(R.id.helper_id);
        s1 = findViewById(R.id.Switch_Toggle);
        logo = findViewById(R.id.logo_id);

        logo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                s1.setVisibility(View.VISIBLE);

                return true;
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = username.getText().toString();
                String Password = password.getText().toString();
                boolean checkSuper = s1.isChecked();
                System.out.print(MainActivity.this.getDatabasePath(DatabaseHelper.DATABASE_NAME));

                Staff staff;
                try {
                    staff = database.loginStaff(Username,Password);

                    if (staff.getStaffID() == -1)
                    {
                        Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                    }
                    else if(staff.isSuper_Auth()&&checkSuper){
                        Toast.makeText(getApplicationContext(),"Redirecting to Super",Toast.LENGTH_SHORT).show();
                        openSuperActivity(staff);
                    }
                    else if(staff.isAdmin())
                    {
                        Toast.makeText(getApplicationContext(), "Redirecting to Admin",Toast.LENGTH_SHORT).show();
                        openAdminActivity(staff);
                    }
                    else if (!staff.isAdmin())
                    {
                        Toast.makeText(getApplicationContext(), "Redirecting to Staff",Toast.LENGTH_SHORT).show();
                        openStaffActivity(staff);
                    }
                }catch (Exception e){ System.out.print("Database Access Failed");}
                username.getText().clear();
                password.getText().clear();
                s1.setChecked(false);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setCancelable(true);
                builder.setTitle("Help");
                builder.setMessage("1. Admin's should enter their respective Username and Password \n\n" +
                        "2. Staff should enter their respective Username and Password \n\n" +
                        "3. Super Admins will be required to hold the logo to access the toggle");
                builder.setPositiveButton("OK",null);
                builder.show();
            }
        });
}


public void openAdminActivity(Staff staff_admin){
        //manifest file name mentioned
        Intent a = new Intent(getApplicationContext(),MainAdminActivity.class);
        a.putExtra("NurseryID",staff_admin.getNurseryID());
        a.putExtra("StaffID",staff_admin.getStaffID());
        startActivity(a);
}

    public void openStaffActivity(Staff staff){
        //manifest file name mentioned
        Intent s = new Intent(getApplicationContext(),MainStaffActivity.class);
        s.putExtra("NurseryID",staff.getNurseryID());
        s.putExtra("StaffID",staff.getStaffID());
        startActivity(s);
    }

    public void openSuperActivity(Staff staff){
        //manifest file name mentioned
        Intent s = new Intent(getApplicationContext(),MainSuperActivity.class);
        s.putExtra("NurseryID",staff.getNurseryID());
        s.putExtra("StaffID",staff.getStaffID());
        startActivity(s);
    }

    public boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
}

//TODO
//TODO *2: Implement Back button!!!
//TODO *3: Figure good colors!!!
//TODO *3: green for true flase red reset

//TODO *4: Button CIRCLES
//TODO *5: POP UP for RESET

//TODO *6: 3 taps for logo to show toggle
//TODO *: Use THREADS!!! --CHARGESTUDENTACTIVITY, PROFITMARGIN, DONE
//TODO *4: CLEAR TEXT BUTTON DONE
//TODO : FIX FAISALS CODE TOGETHER!!!! FIX THE SUPER LOGIN DONE
//TODO 2: Super class to add staff members DONE
//TODO 3: Information button (All Pages) DONE
//TODO 4: Refine UI DONE
//TODO 7: Make a dashboard DONE
//TODO 8: Handle errors on ENROLL PAGE DONE


// STaff - PINK, bck-whitepink, gradientdark to light pink button redish
//ADMIN - black grey, bakcg grey, gradient - dark grey, button-lightgrey
//Super - red black, backg- lighter version, gradeitn - maroon , button white, text red

