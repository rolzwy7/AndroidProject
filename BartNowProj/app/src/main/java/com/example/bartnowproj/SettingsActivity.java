
package com.example.bartnowproj;

import com.example.bartnowproj.figury.Figura;
import com.example.bartnowproj.figury.Kolo;
import com.example.bartnowproj.figury.Kwadrat;
import com.example.bartnowproj.figury.Trojkat;

import com.example.bartnowproj.preferences.Preference;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.*;

public class SettingsActivity extends AppCompatActivity {

    private Preference preference;

    protected void showToast(String str) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.preference = new Preference(getApplicationContext(), "BartNowData");
        // TODO: Set values of settings here from preferences
        EditText num_of_gen_fig = (EditText) findViewById(R.id.num_of_gen_fig);
        num_of_gen_fig.setText(String.format("%d", this.preference.getNumGenFigures()));

        EditText fig_val_min = (EditText) findViewById(R.id.fig_val_min);
        fig_val_min.setText(String.format("%.3f", this.preference.getMinFigValRange()));

        EditText fig_val_max = (EditText) findViewById(R.id.fig_val_max);
        fig_val_max.setText(String.format("%.3f", this.preference.getMaxFigValRange()));

        if (getSupportActionBar() != null) {
            // Wymus na uzytkowniku zapisanie zmian poprzez button
            // lub powrot do starej aktywnosci bez zapisu zmian
            // (poprzez fizyczny przycisk na telefonie)
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

    }

    public void saveSettings(View view) {
        Log.d("BartNow", "Saving settings ...");
        // Ilosc generowanych figur
        EditText num_of_gen_fig = (EditText) findViewById(R.id.num_of_gen_fig);
        int num_of_gen_fig_val;
        try {
            num_of_gen_fig_val = Integer.parseInt(num_of_gen_fig.getText().toString());
            Log.d("BartNow", "Number of generated figures - OK");
        } catch (NumberFormatException e) {
            this.showToast("Wprowadź poprawną wartość dla liczby generowanych figur");
            return;
        }
        if (num_of_gen_fig_val < 0) {
            this.showToast("Nie możesz wygenerować ujemnej liczby figur");
            return;
        }
        if (num_of_gen_fig_val > 100) {
            this.showToast("Aby wygenerować więcej niż 100 figur wykup plan Premium");
            return;
        }
        // Zakres MIN
        EditText fig_val_min = (EditText) findViewById(R.id.fig_val_min);
        float fig_val_min_val;
        try {
            fig_val_min_val = Float.parseFloat(fig_val_min.getText().toString());
            Log.d("BartNow", "Min fig val range  - OK");
        } catch (NumberFormatException e) {
            this.showToast("Wprowadź poprawną wartość dla dolnej granicy losowania (MIN) wartości cechy figury");
            return;
        }
        // Zakres MAX
        EditText fig_val_max = (EditText) findViewById(R.id.fig_val_max);
        float fig_val_max_val;
        try {
            fig_val_max_val = Float.parseFloat(fig_val_max.getText().toString());
            Log.d("BartNow", "Max fig val range  - OK");
        } catch (NumberFormatException e) {
            this.showToast("Wprowadź poprawną wartość dla górnej granicy losowania (MAX) wartości cechy figury");
            return;
        }

        if (fig_val_max_val < fig_val_min_val) {
            this.showToast("Górna granica losowania (MAX) nie może być mniejsza od dolnej granicy losowania (MIN)");
            return;
        }
        if (fig_val_max_val < 0) {
            this.showToast("Górna granica nie może być ujemna");
            return;
        }
        if (fig_val_min_val < 0) {
            this.showToast("Dolna granica nie może być ujemna");
            return;
        }

        this.preference.setNumGenFigures(num_of_gen_fig_val);
        this.preference.setMinFigValRange(fig_val_min_val);
        this.preference.setMaxFigValRange(fig_val_max_val);
        this.preference.setForceFiguresReload(true);
        Log.d("BartNow", "Settings saved ...");
        this.showToast("Zapisano zmiany");
        finish();
    }

    public void goBack(View view) {
        this.showToast("Nie zapisano zmian");
        finish();
    }
}