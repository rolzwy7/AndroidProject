package com.example.bartnowproj;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bartnowproj.preferences.Preference;

import org.w3c.dom.Text;

public class EditActivity extends AppCompatActivity {
    private Preference preference;
    private int edited_item;

    protected void showToast(String str) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        Log.d("BartNow", "Edit activity started");
        this.preference = new Preference(getApplicationContext(), "BartNowData");

        this.edited_item = this.preference.getCurrentlyEditedItem();

        // Edit label
        TextView edit_label = (TextView) findViewById(R.id.edit_label);
        edit_label.setText(String.format("Edytujesz figure o indeksie %d", this.edited_item));

        // Feature edit text
        EditText feature_edit = (EditText) findViewById(R.id.feature_edit_text);
        feature_edit.setText(String.format("%.3f", this.preference.getFigureValByIdx(this.edited_item)));

        // Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner_id);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.figures_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setSelection(this.preference.getFigureTypeByIdx(this.edited_item));
    }

    public void saveAndFinish(View view) {
        // Feature
        EditText feature_edit = (EditText) findViewById(R.id.feature_edit_text);
        float figure_value;
        try {
            figure_value = Float.parseFloat(feature_edit.getText().toString());
            Log.d("BartNow", "Min fig val range  - OK");
        } catch (NumberFormatException e) {
            this.showToast("Wprowadź poprawną liczbę zmiennoprzecinkową jako wartość cechy");
            return;
        }

        if (figure_value < 0) {
            this.showToast("Cecha nie może być ujemna");
            return;
        }

        if (figure_value > this.preference.getMaxFigValRange() || figure_value < this.preference.getMinFigValRange()) {
            this.showToast(String.format("Cecha musi mieć wartość pomiędzy %.3f i %.3f", this.preference.getMinFigValRange(), this.preference.getMaxFigValRange()));
            return;
        }

        // Set value
        this.preference.setFigureValByIdx(this.edited_item, figure_value);

        // Set from spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner_id);
        Log.d("BartNow", String.format("spinner item pos %d", spinner.getSelectedItemPosition()));

        this.preference.setFigureTypeByIdx(this.edited_item, spinner.getSelectedItemPosition());

        finish();
    }

    public void goBack(View view) {
        finish();
    }

}

