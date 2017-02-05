package com.example.joshuarobertson.sizebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.EditText;

/**
 * Created by joshuarobertson on 2017-02-05.
 */
public class EditEntry extends Entry {

    private EditText editView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("EditEntry", "Created...");
        super.onCreate(savedInstanceState);
        Log.d("EditEntry", "Attempting to create window...");
        setContentView(R.layout.entry_window);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.95),(int)(height*.95));

        DataEntry entry = new DataEntry(getIntent().getExtras());

        editView = (EditText) findViewById(R.id.name);
        editView.setText(entry.getName(), EditText.BufferType.EDITABLE);

        editView = (EditText)findViewById(R.id.year);
        editView.setText(entry.getYear(), EditText.BufferType.EDITABLE);

        editView = (EditText)findViewById(R.id.month);
        editView.setText(entry.getMonth(), EditText.BufferType.EDITABLE);

        editView = (EditText)findViewById(R.id.day);
        editView.setText(entry.getDay(), EditText.BufferType.EDITABLE);

        editView = (EditText)findViewById(R.id.neck);
        editView.setText(entry.getNeck(), EditText.BufferType.EDITABLE);

        editView = (EditText)findViewById(R.id.bust);
        editView.setText(entry.getBust(), EditText.BufferType.EDITABLE);

        editView = (EditText)findViewById(R.id.chest);
        editView.setText(entry.getChest(), EditText.BufferType.EDITABLE);

        editView = (EditText)findViewById(R.id.waist);
        editView.setText(entry.getWaist(), EditText.BufferType.EDITABLE);

        editView = (EditText)findViewById(R.id.hip);
        editView.setText(entry.getHip(), EditText.BufferType.EDITABLE);

        editView = (EditText)findViewById(R.id.inseam);
        editView.setText(entry.getInseam(), EditText.BufferType.EDITABLE);

        editView = (EditText)findViewById(R.id.comment);
        editView.setText(entry.getComment(), EditText.BufferType.EDITABLE);

        Button cancel = (Button) findViewById(R.id.button_left);
        Button finish = (Button) findViewById(R.id.button_right);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0);
                finish();
            }
        });


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = dataToIntent();
                intent.putExtra("position", (int)getIntent().getExtras().get("position"));
                setResult(1,intent);
                finish();
            }
        });
    }
}

