package com.example.bartnowproj.figury;

import static java.lang.Math.*;

import com.example.bartnowproj.R;

public class Kwadrat extends Figura {

    public Kwadrat(float wymiarLiniowy) {
        super(wymiarLiniowy, "Kwadrat", "Przekątna", R.drawable.ic_square);
    }

    @Override
    public double pole() {
        double a = this.getWymiarLiniowy() / sqrt(2);
        return a * a;
    }
}
