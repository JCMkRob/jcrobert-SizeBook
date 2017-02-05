package com.example.joshuarobertson.sizebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import static com.example.joshuarobertson.sizebook.R.id.editText;

/**
 * Created by joshuarobertson on 2017-02-04.
 */

public class Entry extends Activity {

    private EditText editText;

    public Intent dataToIntent() {
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

        return intent;
    }


}
