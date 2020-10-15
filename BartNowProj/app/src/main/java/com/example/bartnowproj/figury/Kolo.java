package com.example.bartnowproj.figury;

import static java.lang.Math.*;

import com.example.bartnowproj.R;

public class Kolo extends Figura {


    public Kolo(float wymiarLiniowy) {
        super(wymiarLiniowy, "Koło", "Średnica", R.drawable.ic_circle);
    }

    @Override
    public double pole() {
        return PI * this.getWymiarLiniowy() * this.getWymiarLiniowy();
    }


}