package com.uottawa.a3125lab1;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = getSharedPreferences("SP",Context.MODE_PRIVATE);
        String pretip=sharedPref.getString("tip","10");


        TextView tip= (TextView)findViewById(R.id.tvTip);
        tip.setText(pretip);

        String unit=sharedPref.getString("unit","Dollar");
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
        TextView billUnit= (TextView)findViewById(R.id.tv_unit);
        billUnit.setText(unMark);

    }
    public void OnSetSummaryButton(View view) {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        TextView bill= (TextView)findViewById(R.id.tvBill);
        TextView tip= (TextView)findViewById(R.id.tvTip);
        TextView people= (TextView)findViewById(R.id.tvPeople);


        if(bill.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this,"Wrong Bill Input",Toast.LENGTH_SHORT).show();
            return;
        }
        if(tip.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this,"Wrong Tip Input",Toast.LENGTH_SHORT).show();
            return;
        }
        if(people.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this,"Wrong People Number Input",Toast.LENGTH_SHORT).show();
            return;
        }
        if(people.getText().toString().equals("0")){
            Toast.makeText(MainActivity.this,"Wrong People Number Input",Toast.LENGTH_SHORT).show();
            return;
        }




        intent.putExtra("bills",bill.getText().toString());
        intent.putExtra("tipPercent",tip.getText().toString());
        intent.putExtra("peopleNum",people.getText().toString());
        startActivityForResult(intent, 0);
    }
    public void OnSetSettingButton(View view) {
        Intent intent = new Intent(getApplicationContext(),tipSetting.class);
        startActivityForResult(intent, 1);
    }
    public void OnSetSuggestionButton(View view) {
        Intent intent = new Intent(getApplicationContext(), suggestTip.class);
        startActivityForResult(intent, 2);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) return;
        else if(resultCode == 0) return;
        else if(requestCode == 1) {
            SharedPreferences sharedPref = getSharedPreferences("SP",Context.MODE_PRIVATE);
            String pretip=sharedPref.getString("tip","10");
            TextView tip = (TextView)findViewById(R.id.tvTip);
            tip.setText(pretip);
            String unit=sharedPref.getString("unit","Dollar");
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
            TextView billUnit= (TextView)findViewById(R.id.tv_unit);
            billUnit.setText(unMark);
            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode == 2) {
            TextView tip = (TextView)findViewById(R.id.tvTip);
            String suggestionTip=data.getExtras().getString("SuggestTip");
            tip.setText(suggestionTip);
        }
    }
}
