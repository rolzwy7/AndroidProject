package com.example.bartnowproj;

import com.example.bartnowproj.figury.Figura;
import com.example.bartnowproj.figury.Kolo;
import com.example.bartnowproj.figury.Kwadrat;
import com.example.bartnowproj.figury.Trojkat;

import com.example.bartnowproj.preferences.Preference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private LinearLayout scroll_linear;
    private Preference preference;

    protected void pushToScroll(LinearLayout scroll_linear, Figura figura) {
        // Inflate scroll item
        LinearLayout scroll_item = (LinearLayout) getLayoutInflater().inflate(R.layout.scroll_item, null);

        // Image
        ImageView img = (ImageView) scroll_item.findViewById(R.id.scroll_item__image);
        img.setImageResource(figura.getIcon());

        // Text 1 (pole)
        TextView text_pole = (TextView) scroll_item.findViewById(R.id.scroll_item__text_pole);
        text_pole.setText(figura.getAreaNameFormatted());

        // Text 2 (atrybut)
        TextView text_atrybut = (TextView) scroll_item.findViewById(R.id.scroll_item__text_atrybut);
        text_atrybut.setText(figura.getFeatureNameFormatted());


        // Add to scroll
        scroll_linear.addView(scroll_item);

    }

    protected Figura[] genFigures(int N, float val_min, float val_max, boolean force_create) {
        Random generator = new Random();
        Figura[] figures = new Figura[N];
        for (int idx = 0; idx < N; ++idx) {
            figures[idx] = this.preference.get_or_create_figure(idx, force_create, val_min, val_max);
        }
        return figures;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_statistics_id:
                Log.d("BartNow", "Menu Item: Statistics clicked");
                Intent intent_statistics = new Intent(this, StatisticsActivity.class);
                intent_statistics.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent_statistics);
                return true;
            case R.id.menu_settings_id:
                Log.d("BartNow", "Menu Item: Settings clicked");
                Intent intent_settings = new Intent(this, SettingsActivity.class);
                intent_settings.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent_settings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("BartNow", "onCreate");

        this.preference = new Preference(getApplicationContext(), "BartNowData");
        scroll_linear = (LinearLayout) findViewById(R.id.scroll_linear);
    }

    public void onPause(){
        super.onPause();
        Log.d("BartNow", "onPause - MainActivity");
    }

    public void onResume(){
        super.onResume();
        Log.d("BartNow", "onResume - MainActivity");
        boolean force_create = this.preference.getForceFiguresReload();
        float val_min = this.preference.getMinFigValRange();
        float val_max = this.preference.getMaxFigValRange();
        Figura[] figures = this.genFigures(this.preference.getNumGenFigures(), val_min, val_max, force_create);
        scroll_linear.removeAllViews();
        TextView figura_label = (TextView) findViewById(R.id.figura_label);
        figura_label.setText(String.format("Figura (%d)", this.preference.getNumGenFigures()));
        for (Figura figure : figures) {
            pushToScroll(scroll_linear, figure);
        }
        this.preference.setForceFiguresReload(false);
        this.preference.logDebug();
    }

}

