package com.example.assignment_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.assignment_1.Model.DatabaseHelper;

import java.util.ArrayList;
import java.util.Objects;

public class ProfitMarginActivity extends AppCompatActivity {

    Spinner daySpinner;
    Spinner monthSpinner;
    Spinner yearSpinner;
    Button b1;
    TextView profitMarginResult;
    LinearLayout profitMarginHidden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profit_margin_layout);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black_grey)));
        getSupportActionBar().setTitle("Profit Margins");

        daySpinner = findViewById(R.id.day_spinner_id);
        monthSpinner = findViewById(R.id.month_spinner_id);
        yearSpinner = findViewById(R.id.year_spinner_id);
        b1 = findViewById(R.id.gen_profit_id);
        profitMarginResult = findViewById(R.id.r_pm_id);
        profitMarginHidden = findViewById(R.id.p_m_id);

        DatabaseHelper db = new DatabaseHelper(ProfitMarginActivity.this);

        Intent i1 = getIntent();
        int NurseryID = i1.getIntExtra("NurseryID",0);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Handler ProfitHandler;


                ProfitHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg){
                        AlertDialog.Builder builder = new AlertDialog.Builder(ProfitMarginActivity.this);

                        builder.setCancelable(true);
                        builder.setTitle("Profit Margin");
                        builder.setMessage("Successfully Generated!");

                        final double finalTotal = (double)msg.obj;

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                profitMarginHidden.setVisibility(View.VISIBLE);
                                if (finalTotal < 0)
                                {
                                    profitMarginResult.setText(Double.toString(finalTotal));
                                    profitMarginResult.setTextColor(Color.parseColor("#DC143C"));
                                }
                                else if (finalTotal > 200)
                                {
                                    profitMarginResult.setText(Double.toString(finalTotal));
                                    profitMarginResult.setTextColor(Color.parseColor("#14DC3C"));
                                }
                                else
                                {
                                    profitMarginResult.setText(Double.toString(finalTotal));
                                    profitMarginResult.setTextColor(Color.parseColor("#143CDC"));
                                }
                            }
                        });
                        builder.show();
                    }
                };

                Thread profitThread;
                int day = Integer.parseInt(daySpinner.getSelectedItem().toString());
                int month = Integer.parseInt(monthSpinner.getSelectedItem().toString());
                int year = Integer.parseInt(yearSpinner.getSelectedItem().toString());

                if(year!=0 && month!=0 && day!=0)
                {

                    profitThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<String> profitMargin;
                            double total = 0;
                            profitMargin = db.PRToString(db.filterPayments(day,month,year,NurseryID));
                            total = db.totalProfits(day,month,year,NurseryID);
                            Message msg = ProfitHandler.obtainMessage();
                            msg.obj = total;
                            ProfitHandler.sendMessage(msg);

                        }
                    });
                    profitThread.start();



                }
                else if(year!=0 && month!=0 && day==0)
                {
                    profitThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<String> profitMargin;
                            double total = 0;
                            profitMargin = db.PRToString(db.filterPayments(month,year,NurseryID));
                            total = db.totalProfits(month,year,NurseryID);
                            Message msg = ProfitHandler.obtainMessage();
                            msg.obj = total;
                            ProfitHandler.sendMessage(msg);
                        }
                    });
                    profitThread.start();

                }
                else if(year!=0 && month==0 && day==0)
                {
                    profitThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<String> profitMargin;
                            double total = 0;
                            profitMargin = db.PRToString(db.filterPayments(year,NurseryID));
                            total = db.totalProfits(year,NurseryID);
                            Message msg = ProfitHandler.obtainMessage();
                            msg.obj = total;
                            ProfitHandler.sendMessage(msg);
                        }
                    });
                    profitThread.start();

                }else
                {
                    profitThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<String> profitMargin;
                            double total = 0;
                            profitMargin = db.PRToString(db.getAllPR(NurseryID));
                            total = db.totalProfits(NurseryID);
                            Message msg = ProfitHandler.obtainMessage();
                            msg.obj = total;
                            ProfitHandler.sendMessage(msg);
                        }
                    });
                    profitThread.start();

                }

            }
        });
    }
}