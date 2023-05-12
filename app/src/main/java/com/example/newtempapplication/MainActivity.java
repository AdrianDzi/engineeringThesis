package com.example.newtempapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonLineChart;
    private Button buttonAbout;

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAbout = findViewById(R.id.btn_about);
        buttonLineChart = findViewById(R.id.btn_alltemp);
        buttonLineChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linechartActivityLayout();
            }
        });
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutActivityLayout();
            }
        });

    }

    private void linechartActivityLayout() {
        Intent intent = new Intent(MainActivity.this, LineChart.class);
        startActivity(intent);
    }

    private void aboutActivityLayout() {
        Intent intent = new Intent(MainActivity.this, AboutApplication.class);
        startActivity(intent);
    }
}




