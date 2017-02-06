package com.example.joshuarobertson.sizebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by joshuarobertson on 2017-02-04.
 *
 *  *
 * The class is very simple, look at the Entry documentation
 * for more details.
 *
 * @see Entry
 */
public class ViewEntry extends Entry {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_window);

        setWindowStyle();
        viewPreviousValues();

        Button edit = (Button) findViewById(R.id.button_right);
        Button delete = (Button) findViewById(R.id.button_left);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent response = new Intent();
                response.putExtra("position", (int)getIntent().getExtras().get("position"));
                setResult(EDIT,response);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent response = new Intent();
                response.putExtra("position", (int)getIntent().getExtras().get("position"));
                setResult(DELETE, response);
                finish();
            }
        });

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.view_layout);
        rlayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
