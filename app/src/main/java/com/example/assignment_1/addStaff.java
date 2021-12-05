package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.assignment_1.Model.DatabaseHelper;
import com.example.assignment_1.Model.Staff;

import java.util.Objects;

public class addStaff extends AppCompatActivity {

    EditText ed1;
    EditText ed2;
    Switch isAdminSwitch;
    Switch isSuperSwitch;
    Button hireStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_black)));
        getSupportActionBar().setTitle("Add Staff");

        ed1 = findViewById(R.id.hire_Username);
        ed2 = findViewById(R.id.hire_password);
        isAdminSwitch = findViewById(R.id.hire_Admin);
        isSuperSwitch = findViewById(R.id.hire_super);
        hireStaff = findViewById(R.id.hire_b_id);

        Intent i1 = getIntent();
        int NurseryID = i1.getIntExtra("NurseryID",0);
        DatabaseHelper db = new DatabaseHelper(addStaff.this);


        hireStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String username = ed1.getText().toString();
                    String password = ed2.getText().toString();
                    boolean isAdmin = isAdminSwitch.isChecked();
                    boolean isSuper = isSuperSwitch.isChecked();

                    Staff newStaff = new Staff(-1,NurseryID,username,password,isAdmin,isSuper);

                    if(!username.matches("") && !password.matches("")){
                        if(username.matches("^([a-zA-Z\\s]*)$"))
                            if(password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$")) {
                                db.AddStaff(newStaff);
                                Toast.makeText(getApplicationContext(), "Successfully Added",Toast.LENGTH_LONG).show();
                            }
                            else
                                Toast.makeText(getApplicationContext(),"Use 8 characters with non-Alpha-numeric,Upper-case,Lower-case,Numbers",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(),"Pick a better first name,We don't accept Elon's naming sense",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Successfully FAILED!",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){}
                ed1.getText().clear();
                ed2.getText().clear();
                isSuperSwitch.setChecked(false);
                isAdminSwitch.setChecked(false);
            }
        });
    }
}