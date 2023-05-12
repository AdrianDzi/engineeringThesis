package com.example.newtempapplication;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LineChart extends AppCompatActivity {
//    EditText xValue, yValue;
//    Button btn_insert;
    com.github.mikephil.charting.charts.LineChart lineChart;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;

    XAxis xAxis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);
        setTitle("Temperatures");
//        xValue = findViewById(R.id.x_value);
//        yValue = findViewById(R.id.y_value);
//        btn_insert = findViewById(R.id.btn_insert);
        lineChart = findViewById(R.id.linechartView);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("Chartvalues");
        lineChart.setNoDataText("Data Loading...");

        xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                SimpleDateFormat dateFormatting = new SimpleDateFormat("yy.MM.dd HH:mm");
                String date = dateFormatting.format(value);
                return date;
            }
        });
//        insertData();
        retrieveData();
    }
//    private void insertData() {
//        btn_insert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String id = myRef.push().getKey();
//                float x = Integer.parseInt(xValue.getText().toString());
//                int y = Integer.parseInt(yValue.getText().toString());
//                DataPoint dataPoint = new DataPoint(y,x);
//                myRef.child(id).setValue(dataPoint);
//                retrieveData();
//            }
//        });
//    }

    private void retrieveData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Entry> dataVals = new ArrayList<Entry>();

                if(snapshot.hasChildren()){
                    for(DataSnapshot myDataSnapshot : snapshot.getChildren()){
                        DataPoint dataPoint = myDataSnapshot.getValue(DataPoint.class);
//                        xAxis.setAvoidFirstLastClipping(true);
//                        xAxis.setLabelCount(dataVals.size(), true);
//                        xAxis.setDrawGridLines(false);


                        xAxis.setLabelCount(dataVals.size(), true);
                        float secondsToMillis = dataPoint.getxValue() * 1000;
//                        dataVals.add(new Entry(dataPoint.getxValue(), dataPoint.getyValue()));
                        dataVals.add(new Entry(secondsToMillis, dataPoint.getyValue()));
                    }
                    showChart(dataVals);
                }else {
                    lineChart.clear();
                    lineChart.invalidate();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void showChart(ArrayList<Entry> dataVals) {
        //customization chart
        lineDataSet.setValues(dataVals);
        lineDataSet.setLabel("Temperature");
        lineChart.getDescription().setEnabled(false);
        //setting legend on top left side
        Legend l = lineChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        //paddings in linechart
        //------------
        ;

        lineDataSet.setColor(Color.WHITE);
        lineChart.setTouchEnabled(true);
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        lineData = new LineData(iLineDataSets);
        lineChart.clear();
        lineChart.setData(lineData);
        lineChart.invalidate();
        xAxis.setLabelRotationAngle(90);
        lineChart.animateXY(1500, 2000, Easing.EaseInOutBounce, Easing.EaseInExpo);
        lineChart.getAxisLeft().setTextColor(Color.WHITE);
        lineChart.getXAxis().setTextColor(Color.WHITE);
        lineChart.getLegend().setTextColor(Color.WHITE);
        lineChart.getDescription().setTextColor(Color.WHITE);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setTextSize(12);

        lineDataSet.setLineWidth(3);
        lineDataSet.setColor(Color.WHITE);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setCircleColor(Color.MAGENTA);
        lineDataSet.setCircleHoleColor(Color.BLACK);
        lineDataSet.setCircleRadius(5);
        lineDataSet.setValueTextSize(15);
        lineDataSet.setValueTextColor(Color.WHITE);

        //setting gradient for high temperature
        lineDataSet.setDrawFilled(true);
        if (Utils.getSDKInt() >= 25) {
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
            lineDataSet.setFillDrawable(drawable);
        }
        else {
            lineDataSet.setFillColor(Color.BLACK);
        }

    }


}
