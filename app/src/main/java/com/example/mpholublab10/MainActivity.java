package com.example.mpholublab10;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText user_name_field, user_number_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_name_field = findViewById(R.id.user_name_field);
        user_number_group = findViewById(R.id.user_number_group);
    }

    public void saveData(View view) {
        String userName = user_name_field.getText().toString();
        String userGroup = user_number_group.getText().toString();

        try {
            FileOutputStream fileOutput = openFileOutput("user_data.txt", MODE_PRIVATE);
            fileOutput.write((userName + "\n" + userGroup).getBytes());
            fileOutput.close();

            user_name_field.setText("");
            user_number_group.setText("");
            Toast.makeText(this, getString(R.string.data_saved), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getData(View view) {
        try {
            FileInputStream fileInput = openFileInput("user_data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInput));

            String userName = reader.readLine();
            String userGroup = reader.readLine();
            reader.close();

            user_name_field.setText(userName);
            user_number_group.setText(userGroup);
            Toast.makeText(this, getString(R.string.data_restored), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, getString(R.string.no_saved_data), Toast.LENGTH_SHORT).show();
        }
    }
}
