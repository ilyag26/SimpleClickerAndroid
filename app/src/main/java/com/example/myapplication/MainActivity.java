package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;
    TextView tView;
    TextView Buy;
    TextView resultBuy;
    TextView CountBought;
    String s = "res";
    String s2 = "res2";
    String s1 = "0";
    int count;
    int update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Buy = findViewById(R.id.ResultBuy);
        resultBuy = findViewById(R.id.resultBuy);
        CountBought = findViewById(R.id.CountBought);
        tView = findViewById(R.id.textView);
        tView.setText("0");
        init();
    }

    public void Buy(View view) {
        if (count >= 40) {
            count = count - 40;
            update++;
            CountBought.setText(String.valueOf(update));
            tView.setText(String.valueOf(count));
            resultBuy.setVisibility(View.GONE);
        } else {
            resultBuy.setVisibility(View.VISIBLE);
        }
    }

    public void clicker(View view) {
        count++;
        tView.setText(String.valueOf(count));
    }

    public void saving() {
        int a = count;
        int b = update;
        SharedPreferences.Editor ed = pref.edit();
        ed.putString(s, String.valueOf(a));
        ed.putString(s2, String.valueOf(b));
        ed.apply();
    }
    public void delete(View view) {
        SharedPreferences.Editor ed = pref.edit();
        ed.remove(s);
        ed.remove(s2);
        ed.apply();
        CountPrint();
    }

    public void CountPrint() {
        count = Integer.parseInt(pref.getString(s, s1));
        update = Integer.parseInt(pref.getString(s2, s1));
        CountBought.setText(pref.getString(s2, s1));
        tView.setText(pref.getString(s, s1));
    }
protected void onPause() {
saving();
    super.onPause();
}
    public void init() {
        pref = getSharedPreferences("Test", MODE_PRIVATE);
        CountPrint();
    }
}