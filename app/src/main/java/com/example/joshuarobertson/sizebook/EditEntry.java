package com.example.joshuarobertson.sizebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by joshuarobertson on 2017-02-05.
 *
 * The class is very simple, look at the Entry documentation
 * for more details.
 *
 * @see Entry
 */
public class EditEntry extends Entry {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_window);
        setWindowStyle();
        loadPreviousValues();
        setBoundaries();

        Button cancel = (Button) findViewById(R.id.button_left);
        Button finish = (Button) findViewById(R.id.button_right);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = dataToIntent();
                intent.putExtra("position", (int)getIntent().getExtras().get("position"));
                setResult(CREATE,intent);
                finish();
            }
        });
    }
}

