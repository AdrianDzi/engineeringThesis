package com.example.newtempapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class AboutApplication extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About application");
        TextView myCounterText = (TextView) findViewById(R.id.exampleText);
        String[] items = new String[]{"Select information", "about application", "about connection", "about author"};
        Spinner spinner = findViewById(R.id.spinnerAbout);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_dropdown_item, items);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                String text = spinner.getSelectedItem().toString();
                if(text.equals("about application")){
                    myCounterText.setText(R.string.aboutApp);
                }else if(text.equals("about connection")){
                    myCounterText.setText(R.string.aboutConnect);
                }else if(text.equals("about author")){
                    myCounterText.setText(R.string.aboutAuthor);
                }else if(text.equals("Select information")){
                    myCounterText.setText(R.string.emptyString);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.aboutTasks, R.layout.my_dropdown_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        String text = adapterView.getItemAtPosition(i).toString();
//        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
////        String text = spinner.getSelectedItem().toString();
////        if (text.equals("about application")) {
////            myCounterText.setText("about application");
////        }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
}
