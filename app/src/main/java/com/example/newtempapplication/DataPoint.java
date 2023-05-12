package com.example.newtempapplication;

public class DataPoint {
    float yValue;
    float xValue;

    public DataPoint(float yValue, float xValue) {
        this.yValue = yValue;
        this.xValue = xValue;
    }
    public DataPoint(){

    }

    public float getyValue() {
        return yValue;
    }

    public float getxValue() {
        return xValue;
    }
}
