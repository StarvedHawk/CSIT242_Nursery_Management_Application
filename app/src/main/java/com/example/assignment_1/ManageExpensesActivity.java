package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignment_1.Model.DatabaseHelper;
import com.example.assignment_1.Model.Expenses;

import java.util.Objects;

public class ManageExpensesActivity extends AppCompatActivity {

    EditText amt;
    Spinner type;
    Spinner month;
    Spinner year;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_expenses);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black_grey)));
        getSupportActionBar().setTitle("Manage Expenses");

       //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i1 = getIntent();
        int NurseryID = i1.getIntExtra("NurseryID",0);

        amt = findViewById(R.id.amt_id);
        type = findViewById(R.id.type_spinner);
        month = findViewById(R.id.month_spinner);
        year = findViewById(R.id.year_spinner);
        b1 = findViewById(R.id.gen_all_id);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    int amtData = Integer.parseInt(amt.getText().toString());
                    String typeData = type.getSelectedItem().toString();
                    int monthData = Integer.parseInt(month.getSelectedItem().toString());
                    int yearData = Integer.parseInt(year.getSelectedItem().toString());

                    DatabaseHelper dataBase = new DatabaseHelper(ManageExpensesActivity.this);
                    Expenses exp = new Expenses(-1, NurseryID, amtData, monthData, yearData, typeData);
                    dataBase.AddExpenses(exp);
                    Toast.makeText(getApplicationContext(), "Successfully Added",Toast.LENGTH_SHORT).show();

                }catch (Exception e){}
            }
        });

    }
}