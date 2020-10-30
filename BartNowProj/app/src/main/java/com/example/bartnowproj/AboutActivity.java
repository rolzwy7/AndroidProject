package com.example.bartnowproj;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Log.d("BartNow", "onCreate - AboutActivity");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Log.d("BartNow", String.format("onItemSelected pos = %d", pos));
    }

    public void goBack(View view) {
        finish();
    }

}
