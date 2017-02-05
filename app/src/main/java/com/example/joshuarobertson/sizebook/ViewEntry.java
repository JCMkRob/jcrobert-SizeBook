package com.example.joshuarobertson.sizebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by joshuarobertson on 2017-02-04.
 */
public class ViewEntry extends Entry {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ViewEntry", "Created...");
        super.onCreate(savedInstanceState);
        Log.d("ViewEntry", "Attempting to create window...");
        setContentView(R.layout.view_window);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.95),(int)(height*.95));

        DataEntry entry = new DataEntry(getIntent().getExtras());

        textView = (TextView) findViewById(R.id.name);
        textView.setText(entry.getName(), TextView.BufferType.EDITABLE);

        textView = (TextView)findViewById(R.id.year);
        textView.setText(entry.getYear(), TextView.BufferType.EDITABLE);

        textView = (TextView)findViewById(R.id.month);
        textView.setText(entry.getMonth(), TextView.BufferType.EDITABLE);

        textView = (TextView)findViewById(R.id.day);
        textView.setText(entry.getDay(), TextView.BufferType.EDITABLE);

        textView = (TextView)findViewById(R.id.neck);
        textView.setText(entry.getNeck(), TextView.BufferType.EDITABLE);

        textView = (TextView)findViewById(R.id.bust);
        textView.setText(entry.getBust(), TextView.BufferType.EDITABLE);

        textView = (TextView)findViewById(R.id.chest);
        textView.setText(entry.getChest(), TextView.BufferType.EDITABLE);

        textView = (TextView)findViewById(R.id.waist);
        textView.setText(entry.getWaist(), TextView.BufferType.EDITABLE);

        textView = (TextView)findViewById(R.id.hip);
        textView.setText(entry.getHip(), TextView.BufferType.EDITABLE);

        textView = (TextView)findViewById(R.id.inseam);
        textView.setText(entry.getInseam(), TextView.BufferType.EDITABLE);

        textView = (TextView)findViewById(R.id.comment);
        textView.setText(entry.getComment(), TextView.BufferType.EDITABLE);

        Button edit = (Button) findViewById(R.id.button_right);
        Button delete = (Button) findViewById(R.id.button_left);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent response = new Intent();
                response.putExtra("position", (int)getIntent().getExtras().get("position"));
                setResult(0,response);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent response = new Intent();
                response.putExtra("position", (int)getIntent().getExtras().get("position"));
                setResult(1, response);
                finish();
            }
        });

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.view_layout);
        rlayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setResult(2);
                finish();
            }
        });
    }
}
