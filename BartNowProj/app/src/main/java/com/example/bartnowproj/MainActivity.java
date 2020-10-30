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
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
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
    static View currentCtxView;

    protected void showToast(String str) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_LONG);
        toast.show();
    }

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
        registerForContextMenu(scroll_item);
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        currentCtxView = v;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int idx = scroll_linear.indexOfChild(currentCtxView);

        switch (item.getItemId()) {
            case R.id.ctx_menu_duplicate:
                Log.d("BartNow", String.format("Ctx menu duplicate %d", idx));
                if(this.preference.addDuplicateFigure(idx)) {
                    this.showToast("Dodano duplikat figury na końcu listy");
                    this.onResume();
                } else {
                    this.showToast("Nie można wygenerować więcej niż 100 figur. Wykup plan Premium");
                }
                return true;
            case R.id.ctx_menu_remove:
                this.preference.removeFigure(idx);
                this.onResume();
                this.showToast("Usunięto figurę");
                return true;
            case R.id.ctx_menu_edit:
                Log.d("BartNow", "Ctx Item intent edit");
                this.preference.setCurrentlyEditedItem(idx);
                Intent intent_edit = new Intent(this, EditActivity.class);
                intent_edit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent_edit);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

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
            case R.id.menu_add_random_figure_id:
                Log.d("BartNow", "Menu Item: Add random figure clicked");
                if (this.preference.addRandomFigure()) {
                    this.showToast("Dodano losową figurę");
                    this.onResume();
                } else {
                    this.showToast("Nie można wygenerować więcej niż 100 figur. Wykup plan Premium");
                }
                return true;
            case R.id.menu_remove_and_regenerate_id:
                Log.d("BartNow", "Menu Item: Regenerate figures");
                this.preference.regenerateFigures();
                this.onResume();
                this.showToast("Figury zostały wylosowane ponownie");
                return true;
            case R.id.menu_about_id:
                Log.d("BartNow", "Menu Item: About clicked");
                Intent intent_about = new Intent(this, AboutActivity.class);
                intent_about.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent_about);
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

        //registerForContextMenu(scroll_linear);
    }

    public void onPause() {
        super.onPause();
        Log.d("BartNow", "onPause - MainActivity");
    }

    public void onResume() {
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

