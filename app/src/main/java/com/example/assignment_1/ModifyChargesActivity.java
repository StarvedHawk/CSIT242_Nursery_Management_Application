package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_1.Model.DatabaseHelper;
import com.example.assignment_1.Model.Nursery;

import java.util.Objects;

public class ModifyChargesActivity extends AppCompatActivity {

    EditText minAmt;
    EditText maxAmt;
    Spinner hours;
    Button b1;

    TextView name;
    TextView initCharge;
    TextView afterCharge;
    TextView hourText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_charges);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black_grey)));
        getSupportActionBar().setTitle("Modify Charges");

        minAmt = findViewById(R.id.min_id);
        maxAmt = findViewById(R.id.max_id);
        hours = findViewById(R.id.hour_spinner);
        b1 = findViewById(R.id.make_change);

        name = findViewById(R.id.n_name_id);
        initCharge = findViewById(R.id.n_min_id);
        afterCharge = findViewById(R.id.n_max_id);
        hourText = findViewById(R.id.n_hours_id);

        Intent i1 = getIntent();
        int NurseryID = i1.getIntExtra("NurseryID",0);

        DatabaseHelper db = new DatabaseHelper(ModifyChargesActivity.this);

        Nursery nus = db.getCurrentNursery(NurseryID);
        name.setText(nus.getNurseryName());
        initCharge.setText(Integer.toString(nus.getPreCharge()));
        afterCharge.setText(Integer.toString(nus.getPostCharge()));
        hourText.setText(Integer.toString(nus.getMinHours()));

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    int minAmtData = Integer.parseInt(minAmt.getText().toString());
                    int maxAmtData = Integer.parseInt(maxAmt.getText().toString());
                    int hourData = Integer.parseInt(hours.getSelectedItem().toString());

                    db.updateNurseryDetails(NurseryID, minAmtData, maxAmtData, hourData);

                    Nursery nus_update = db.getCurrentNursery(NurseryID);
                    name.setText(nus_update.getNurseryName());
                    initCharge.setText(Integer.toString(nus_update.getPreCharge()));
                    afterCharge.setText(Integer.toString(nus_update.getPostCharge()));
                    hourText.setText(Integer.toString(nus_update.getMinHours()));

                    Toast.makeText(getApplicationContext(), "Successfully Modified", Toast.LENGTH_SHORT).show();
                }catch (Exception e){}

            }
        });
    }
}