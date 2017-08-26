package com.example.shubham.contact_map_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    EditText et_text;
    Button b_read;
    TextView tv_text;
    String fileName = "contacts.txt"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_text = (EditText) findViewById(R.id.et_text);
        b_read = (Button) findViewById(R.id.b_read);
        tv_text = (TextView) findViewById(R.id.tv_text);

        b_read.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                tv_text.setText(readFile(fileName)));
            }
        }

    }

    public String readFile(String file){
        String text ="";

        try{
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte(size);
            fis.read(buffer);
            fis.close();
            text = new String (buffer);
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Error reading file", Toast.LENGTH_SHORT).show();
        }

        return text;
    }

}
