package com.example.joshuarobertson.sizebook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by joshuarobertson on 2017-02-04.
 *
 *  *
 * The class is very simple, look at the Entry documentation
 * for more details.
 *
 * @see Entry
 */
public class NewEntry extends Entry {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_window);
        setWindowStyle();
        setBoundaries();

        Button create = (Button) findViewById(R.id.button_right);
        Button cancel = (Button) findViewById(R.id.button_left);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userName = (EditText) findViewById(R.id.name);
                if( userName.getText().toString().trim().equals("")){
                    userName.setError( "Name Required!" );
                }else{
                    setResult(RESULT_OK, dataToIntent());
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
