package com.uottawa.a3125lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class suggestTip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_tip);
        final RatingBar star=(RatingBar) findViewById(R.id.ratingBar);
        star.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float i =  star.getRating();
                TextView tip_rate=(TextView) findViewById(R.id.tv_rate);
                tip_rate.setText("TipPercentage="+(i*2+10)+"%");
            }
        });

    }

    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromTouch) {
        final int numStars = ratingBar.getNumStars();
    }
    public void SetBack(View view) {
        Intent returnIntent = new Intent();
        TextView tip_rate=(TextView) findViewById(R.id.tv_rate);
        String stext= tip_rate.getText().toString();
        String suggestTip=stext.substring(14,stext.length()-1);
        returnIntent.putExtra("SuggestTip",suggestTip);

        setResult(RESULT_OK, returnIntent);


        finish();
    }
}
