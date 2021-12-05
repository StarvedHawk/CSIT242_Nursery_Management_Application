package com.example.assignment_1;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment_1.Model.DatabaseHelper;
import com.example.assignment_1.Model.Nursery;
import com.example.assignment_1.Model.Payment_Record;

import java.util.Objects;

public class ChargeStudentActivity extends AppCompatActivity {

    Spinner day;
    Spinner month;
    Spinner year;
    Spinner hours;
    Spinner discount;
    Button makeCharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charge_student_layout);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));
        getSupportActionBar().setTitle("Charge Student");

        day = findViewById(R.id.day_spin);
        month = findViewById(R.id.month_spin);
        year = findViewById(R.id.year_spin);
        hours = findViewById(R.id.hours_spinner);
        discount = findViewById(R.id.discount_spinner);
        makeCharge = findViewById(R.id.make_charge_id);

        Intent i1 = getIntent();
        int NurseryID = i1.getIntExtra("NurseryID",0);
        int StudentID = i1.getIntExtra("StudentID",0);

        makeCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler ChargeHandler;

                try{
                    int dayData = Integer.parseInt(day.getSelectedItem().toString());
                    int monthData = Integer.parseInt(month.getSelectedItem().toString());
                    int yearData = Integer.parseInt(year.getSelectedItem().toString());
                    int hoursData = Integer.parseInt(hours.getSelectedItem().toString());
                    int discountData = Integer.parseInt(discount.getSelectedItem().toString());

                    if (dayData==0 || monthData==0 || yearData==0){
                        throw new Exception("No Null Values");
                    }

                    ChargeHandler = new Handler(){
                        @Override
                        public void handleMessage(Message msg){
                            boolean success = msg.arg1==1;
                            if(success){
                                Toast.makeText(getApplicationContext(), "Successfully Charged",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Failed to Charge!\nERROR: Database access failed!",Toast.LENGTH_LONG).show();
                            }
                        }
                    };

                    Thread ChargeThread;
                    ChargeThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            boolean success = ChargeStudent(NurseryID,StudentID,hoursData,dayData,monthData,yearData,discountData);
                            Message msg = ChargeHandler.obtainMessage();
                            msg.arg1 = success?1:0;
                            ChargeHandler.sendMessage(msg);

                        }
                    });
                    ChargeThread.start();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Failed to Charge!\nERROR: " + e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public boolean ChargeStudent(int NurseryID, int StudentID, int checkHours, int checkDay, int checkMonth, int checkYear, int checkDiscount){

        DatabaseHelper db = new DatabaseHelper(ChargeStudentActivity.this);
        Nursery tempNursery = db.getCurrentNursery(NurseryID);

        double amount = tempNursery.amountToPay(checkHours);
        double finalAmount = (100 - checkDiscount) * amount / 100;

        Payment_Record p_r = new Payment_Record(-1,StudentID,NurseryID,finalAmount,checkHours,checkDay,checkMonth,checkYear);
        db.AddPaymentRecord(p_r);

    return true;
    }
}
