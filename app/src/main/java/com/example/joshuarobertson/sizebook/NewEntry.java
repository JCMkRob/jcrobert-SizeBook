package com.example.joshuarobertson.sizebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by joshuarobertson on 2017-02-04.
 */
public class NewEntry extends Activity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_window);



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.95),(int)(height*.95));

        Button create = (Button) findViewById(R.id.button_right);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Log.d("Check","sending");
                editText = (EditText) findViewById(R.id.name);
                intent.putExtra("name", editText.getText().toString());

                editText = (EditText) findViewById(R.id.day);
                intent.putExtra("day", editText.getText().toString());

                editText = (EditText) findViewById(R.id.month);
                intent.putExtra("month", editText.getText().toString());

                editText = (EditText) findViewById(R.id.year);
                intent.putExtra("year", editText.getText().toString());

                editText = (EditText) findViewById(R.id.neck);
                intent.putExtra("neck", editText.getText().toString());

                editText = (EditText) findViewById(R.id.bust);
                intent.putExtra("bust", editText.getText().toString());

                editText = (EditText) findViewById(R.id.chest);
                intent.putExtra("chest", editText.getText().toString());

                editText = (EditText) findViewById(R.id.waist);
                intent.putExtra("waist", editText.getText().toString());

                editText = (EditText) findViewById(R.id.hip);
                intent.putExtra("hip", editText.getText().toString());

                editText = (EditText) findViewById(R.id.inseam);
                intent.putExtra("inseam", editText.getText().toString());

                editText = (EditText) findViewById(R.id.comment);
                intent.putExtra("comment", editText.getText().toString());
                Log.d("Check","Sent");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
