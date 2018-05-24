package com.uottawa.a3125lab1;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.LinearLayout;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SharedPreferences sharedPref = getSharedPreferences("SP", Context.MODE_PRIVATE);
        String unit=sharedPref.getString("unit","Dollar");

        Intent recive=getIntent();
        Bundle extra =recive.getExtras();
        float bill = Float.parseFloat(extra.getString("bills"));
        float tippercent = Float.parseFloat(extra.getString("tipPercent"));
        float people = Float.parseFloat(extra.getString("peopleNum"));
        TextView sumbill = (TextView) findViewById(R.id.tv_sum_bill);
        TextView sumtip = (TextView) findViewById(R.id.tv_sum_tip);
        TextView tipper = (TextView) findViewById(R.id.tv_tip_per_person);
        TextView total = (TextView) findViewById(R.id.tv_amount);
        TextView eachPeople = (TextView) findViewById(R.id.tv_aveage);


        float tip = bill*tippercent/100;
        String unMark = null;
        switch (unit){
            case "Dollar":
                unMark="$";
                break;
            case "Euro":
                unMark="€";
            case "Pound":
                unMark="£";
        }
        sumbill.setText(unMark+String.valueOf(bill));
        sumtip.setText(unMark+String.valueOf(tip));
        tipper.setText(unMark+String.valueOf(tip/people));
        total.setText(unMark+String.valueOf(bill+tip));
        eachPeople.setText(unMark+String.valueOf((bill+tip)/people));
        if(people==1){
            LinearLayout lytip=(LinearLayout)findViewById(R.id.ly_tip);
            LinearLayout lypeo=(LinearLayout)findViewById(R.id.ly_people);
            lytip.setVisibility(View.INVISIBLE);
            lypeo.setVisibility(View.INVISIBLE);
        }
    }
    public void SetBack(View view) {

        setResult(RESULT_OK, null);
        finish();
    }
}
