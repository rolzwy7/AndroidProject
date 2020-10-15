package com.example.bartnowproj;

import com.example.bartnowproj.figury.Figura;
import com.example.bartnowproj.figury.Kolo;
import com.example.bartnowproj.figury.Kwadrat;
import com.example.bartnowproj.figury.Trojkat;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Bundle;

import org.w3c.dom.Text;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private LinearLayout scroll_linear;

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

    protected Figura[] genRandomFigures(int N) {
        Random generator = new Random();
        Figura[] figures = new Figura[N];
        for (int idx = 0; idx < N; ++idx) {
            int choice = generator.nextInt(3);
            float val = generator.nextFloat();
            switch (choice) {
                case 0:
                    figures[idx] = new Kwadrat(val);
                    break;
                case 1:
                    figures[idx] = new Kolo(val);
                    break;
                case 2:
                    figures[idx] = new Trojkat(val);
                    break;
            }
        }
        return figures;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("BartNow", "Started");
        scroll_linear = (LinearLayout) findViewById(R.id.scroll_linear);

        Figura[] figures = this.genRandomFigures(10);
        for(Figura figure: figures) {
            pushToScroll(scroll_linear, figure);
        }

    }
}
