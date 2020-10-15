package com.example.bartnowproj.figury;

public abstract class Figura {

    private float wymiarLiniowy;
    private String feature_name;
    private String figure_name;
    private int icon;

    public Figura(float wymiarLiniowy, String figure_name, String feature_name, int icon) {
        this.wymiarLiniowy = wymiarLiniowy;
        this.figure_name = figure_name;
        this.feature_name = feature_name;
        this.icon = icon;
    }

    // Abstracts
    public abstract double pole();

    public int getIcon() {
        return this.icon;
    }


    // Printing
    public String getFeatureNameFormatted() {
        return String.format("%s\n%.3f", this.feature_name, this.getWymiarLiniowy());
    }

    public String getAreaNameFormatted() {
        return String.format("%s\npole = %.3f", this.figure_name, this.pole());
    }

    // Wymiar liniowy
    public float getWymiarLiniowy() {
        return this.wymiarLiniowy;
    }

    public void setWymiarLiniowy(float wymiarLiniowy) {
        this.wymiarLiniowy = wymiarLiniowy;
    }

    // Figure Name
    public String getFigureName() {
        return this.figure_name;
    }

    public void setFigureName(String figure_name) {
        this.figure_name = figure_name;
    }


    // Feature Name
    public String getFeatureName() {
        return this.feature_name;
    }


    public void setFeatureName(String feature_name) {
        this.feature_name = feature_name;
    }


}
