package com.example.bartnowproj.figury;

import static java.lang.Math.*;

import com.example.bartnowproj.R;

public class Trojkat extends Figura {

    public Trojkat(float wymiarLiniowy) {
        super(wymiarLiniowy, "Trójkąt", "Wysokość", R.drawable.ic_triangle);
    }

    @Override
    public double pole() {
        return (this.getWymiarLiniowy() * this.getWymiarLiniowy() * sqrt(3)) / 4.0;
    }

}
