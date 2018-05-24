package com.uottawa.a3125lab1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class tipSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_setting);
        SharedPreferences shared = getSharedPreferences("SP", Context.MODE_PRIVATE);
        String unit=shared.getString("unit","Dollar");
        String cuTip=shared.getString("tip","10");
        int id = 0;
        switch (unit) {
            case "Dollar":
                id=R.id.btn_Dollar;
                break;
            case "Euro":
                id=R.id.btn_Euro;
                break;
            case "Pound":
                id=R.id.btn_Pound;
                break;
        }
        RadioButton choise=(RadioButton) findViewById(id);
        choise.toggle();
        TextView defTIp=(TextView)findViewById(R.id.tv_default_tip);
        defTIp.setHint(cuTip);
    }
    public void SetBack(View view) {
        Intent returnIntent = new Intent();
        TextView retip=(TextView)findViewById(R.id.tv_default_tip);

        String back_Tip;
        if(!retip.getHint().toString().isEmpty()) {
            if (!retip.getText().toString().isEmpty()) {
                back_Tip=retip.getText().toString();
            }else{
                back_Tip=retip.getHint().toString();
            }
        }else{
            if (!retip.getText().toString().isEmpty()) {
                back_Tip=retip.getText().toString();
            }else {
                Toast.makeText(tipSetting.this, "Wrong Tip Input", Toast.LENGTH_SHORT).show();
                return;

            }
        }

        SharedPreferences sharedPref = getSharedPreferences("SP", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed =sharedPref.edit();

        ed.putString("tip",back_Tip);
        RadioGroup gUnit = (RadioGroup) findViewById(R.id.unit_group);
        int id=gUnit.getCheckedRadioButtonId();
        RadioButton choise=(RadioButton) findViewById(id);
        ed.putString("unit",choise.getText().toString());


        ed.commit();
        setResult(RESULT_OK, returnIntent);

        finish();
    }
}
