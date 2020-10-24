package com.example.bartnowproj.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.example.bartnowproj.figury.Figura;
import com.example.bartnowproj.figury.Kolo;
import com.example.bartnowproj.figury.Kwadrat;
import com.example.bartnowproj.figury.Trojkat;

import java.util.Random;

public class Preference {

    private SharedPreferences shared_preferences;
    private Context context;

    public void logDebug() {
        Log.d("BartNow",
                String.format("NumGenFigures : %d", this.getNumGenFigures())
        );
    }

    public Preference(Context context, String pref_name) {
        this.shared_preferences = context.getSharedPreferences(pref_name, context.MODE_PRIVATE);
        this.context = context;
    }

    public int getNumGenFigures() {
        return this.shared_preferences.getInt("num_of_gen_fig", 3);
    }

    public void setNumGenFigures(int num_of_gen_fig) {
        SharedPreferences.Editor editor = this.shared_preferences.edit();
        editor.putInt("num_of_gen_fig", num_of_gen_fig);
        editor.apply();
    }

    public boolean getForceFiguresReload() {
        return this.shared_preferences.getBoolean("FORCE_FIG_RELOAD", false);
    }

    public void setForceFiguresReload(boolean force_figures_reload) {
        SharedPreferences.Editor editor = this.shared_preferences.edit();
        editor.putBoolean("FORCE_FIG_RELOAD", force_figures_reload);
        editor.apply();
    }

    public Float getMinFigValRange() {
        return this.shared_preferences.getFloat("fig_val_min", 0);
    }
    public void setMinFigValRange(float fig_val_min) {
        SharedPreferences.Editor editor = this.shared_preferences.edit();
        editor.putFloat("fig_val_min", fig_val_min);
        editor.apply();
    }

    public Float getMaxFigValRange() {
        return this.shared_preferences.getFloat("fig_val_max", 1);
    }
    public void setMaxFigValRange(float fig_val_max) {
        SharedPreferences.Editor editor = this.shared_preferences.edit();
        editor.putFloat("fig_val_max", fig_val_max);
        editor.apply();
    }

    public Float getFigureValByIdx(int idx) {
        String figure_val_key = String.format("f%d_val", idx);
        return this.shared_preferences.getFloat(figure_val_key, -1);
    }

    public int getFigureTypeByIdx(int idx) {
        String figure_type_key = String.format("f%d_type", idx);
        return this.shared_preferences.getInt(figure_type_key, -1);
    }

    public Figura get_or_create_figure(int idx, boolean force_create, float val_min, float val_max) {
        Log.d("BartNow", String.format("Trying to get figure(idx=%d)", idx));

        String figure_val_key = String.format("f%d_val", idx);
        String figure_type_key = String.format("f%d_type", idx);

        Float figure_val = this.shared_preferences.getFloat(figure_val_key, -1);
        int figure_type = this.shared_preferences.getInt(figure_type_key, -1);

        Log.d("BartNow", figure_val_key);
        Log.d("BartNow", String.format("Got type %d with val %.3f", figure_type, figure_val));

        // Zadecyduj czy figura ma byc
        boolean created = false;
        if(figure_type < 0 || force_create) { // TODO: Stworz nowa figure
            created = true;
            Log.d("BartNow", "No saved figure -> creating new");
            Random generator = new Random();
            figure_type = generator.nextInt(3);
            figure_val = val_min + generator.nextFloat() * (val_max - val_min);
        } else {  // TODO: Wykorzystaj stara
            Log.d("BartNow", "Figure already saved -> using old");
        }

        Figura figura = new Kolo(1); // TODO: Żeby Java nie krzyczała
        switch (figure_type) {
            case 0:
                figura = new Kwadrat(figure_val);
                break;
            case 1:
                figura = new Kolo(figure_val);
                break;
            case 2:
                figura = new Trojkat(figure_val);
                break;
        }

        if(created) { // Zapisz figure
            SharedPreferences.Editor editor = this.shared_preferences.edit();
            editor.putFloat(figure_val_key, figure_val);
            editor.putInt(figure_type_key, figure_type);
            editor.apply();
            Log.d("BartNow", "New figure saved");
        }

        return figura;
    }

}


