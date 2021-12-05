package com.example.assignment_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.assignment_1.Model.DatabaseHelper;

import java.util.ArrayList;
import java.util.Objects;

public class CurrentExpensesActivity extends AppCompatActivity {

    Button manage_expenses;
    Button modify_charges;
    Button refresh_page;
    ImageButton info;

    private ListView listview;
    private EntryAdapter myAdapter;
    Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_expenses_activity);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black_grey)));
        getSupportActionBar().setTitle("Nursery Expenses");

        myContext = this;
        manage_expenses = findViewById(R.id.manage_exp_id);
        modify_charges = findViewById(R.id.modify_charges_id);
        refresh_page = findViewById(R.id.refresh_b_id);
        info = findViewById(R.id.helper_id);

        Intent i1 = getIntent();
        int NurseryID = i1.getIntExtra("NurseryID",0);

        DatabaseHelper database = new DatabaseHelper(CurrentExpensesActivity.this);

        listview = (ListView) findViewById(R.id.ExpensesList);
        ArrayList<String> expensesList = database.expensesToString(database.getAllExpenses(NurseryID));

        myAdapter = new EntryAdapter(myContext,expensesList);
        listview.setAdapter(myAdapter);

        manage_expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(getApplicationContext(),ManageExpensesActivity.class);
                m.putExtra("NurseryID",NurseryID);
                startActivity(m);
            }
        });

        modify_charges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m_c = new Intent(getApplicationContext(),ModifyChargesActivity.class);
                m_c.putExtra("NurseryID",NurseryID);
                startActivity(m_c);
            }
        });

        refresh_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> expensesList = database.expensesToString(database.getAllExpenses(NurseryID));

                myAdapter = new EntryAdapter(myContext,expensesList);
                listview.setAdapter(myAdapter);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CurrentExpensesActivity.this);

                builder.setCancelable(true);
                builder.setTitle("Help");
                builder.setMessage("Refresh: When tapped refreshes any changes made from the below options \n\n" +
                        "Manage Expenses: Allows the user to add an expense \n\n" +
                        "Modify Charges: Allows user to change the charge rates of the nursery");
                builder.setPositiveButton("OK",null);
                builder.show();
            }
        });
    }
}