
package com.example.bartnowproj;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.bartnowproj.figury.Kolo;
import com.example.bartnowproj.figury.Kwadrat;
import com.example.bartnowproj.figury.Trojkat;

import com.example.bartnowproj.figury.Kolo;
import com.example.bartnowproj.figury.Kwadrat;
import com.example.bartnowproj.figury.Trojkat;
import com.example.bartnowproj.preferences.Preference;

public class StatisticsActivity extends AppCompatActivity {

    private Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        this.preference = new Preference(getApplicationContext(), "BartNowData");

        if (getSupportActionBar() != null) {
            // Wymus na uzytkowniku zapisanie zmian poprzez button
            // lub powrot do starej aktywnosci bez zapisu zmian
            // (poprzez fizyczny przycisk na telefonie)
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        TextView circles_stats = (TextView) findViewById(R.id.circles_stats);
        TextView squares_stats = (TextView) findViewById(R.id.squares_stats);
        TextView triangles_stats = (TextView) findViewById(R.id.triangles_stats);

        int circles_num = 0;
        int squares_num = 0;
        int triangles_num = 0;

        double circles_area_sum = 0f;
        double squares_area_sum = 0f;
        double triangles_area_sum = 0f;

        double circles_feature_sum = 0f;
        double squares_feature_sum = 0f;
        double triangles_feature_sum = 0f;

        // 0 - Kwadrat
        // 1 - Kolo
        // 2 - Trojakt
        int numOfCurrDisplayedFigs = this.preference.getNumGenFigures();
        for (int idx = 0; idx < numOfCurrDisplayedFigs; ++idx) {
            int fig_type_val = this.preference.getFigureTypeByIdx(idx);
            float fig_feature_val = this.preference.getFigureValByIdx(idx);
            switch (fig_type_val) {
                case 0:
                    squares_num += 1;
                    squares_area_sum += new Kwadrat(fig_feature_val).pole();
                    squares_feature_sum += fig_feature_val;
                    break;
                case 1:
                    circles_num += 1;
                    circles_area_sum += new Kolo(fig_feature_val).pole();
                    circles_feature_sum += fig_feature_val;
                    break;
                case 2:
                    triangles_num += 1;
                    triangles_area_sum += new Trojkat(fig_feature_val).pole();
                    triangles_feature_sum += fig_feature_val;
                    break;
            }
        }

        circles_stats.setText(String.format("Jest %d kół\no łącznj sumie pól %.3f\ni łacznej sumie cech %.2f", circles_num, circles_area_sum, circles_feature_sum));
        squares_stats.setText(String.format("Jest %d kwadratów\no łącznj sumie pól %.3f\ni łacznej sumie cech %.2f", squares_num, squares_area_sum, squares_feature_sum));
        triangles_stats.setText(String.format("Jest %d trójkątów\no łącznj sumie pól %.3f\ni łacznej sumie cech %.2f", triangles_num, triangles_area_sum, triangles_feature_sum));
    }

    public void goBack(View view) {
        finish();
    }
}
