package com.example.shubham.contact_map_1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String[][] word = new String[5][4];
    int count =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);

        TextView tv = (TextView) findViewById(R.id.text); //Find the view by its id
        TextView tvp1 = (TextView) findViewById(R.id.p1);
        TextView tvp2 = (TextView) findViewById(R.id.p2);


        File sdcard = Environment.getExternalStorageDirectory(); //Find the directory for the SD Card using the API

        File file = new File(sdcard, "contacts.txt");   //Get the text file

        if (file.exists()) {
            StringBuilder text = new StringBuilder();   //Read text from file

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {

                    word[count]= line.split("\\s");
                    text.append(line);
                    text.append('\n');

                    Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
                    contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                     contactIntent
                            .putExtra(ContactsContract.Intents.Insert.NAME,word[count][0])
                            .putExtra(ContactsContract.Intents.Insert.EMAIL, word[count][1])
                            .putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE,word[count][2])
                            .putExtra(ContactsContract.Intents.Insert.PHONE, word[count][3]);
                            startActivityForResult(contactIntent, 1);
                    count=count+1;
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Error reading file", Toast.LENGTH_SHORT).show();
            }
            tv.setText(word[0][2]);   //Set the text
            tvp1.setText(word[0][0]);
            tvp2.setText(word[0][1]);
        } else {
            tv.setText("Sorry file doesn't exist");
        }
    }
}

