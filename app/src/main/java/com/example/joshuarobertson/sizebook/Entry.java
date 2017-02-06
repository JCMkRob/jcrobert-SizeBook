package com.example.joshuarobertson.sizebook;

import android.app.Activity;
import android.content.Intent;

import android.text.InputFilter;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by joshuarobertson on 2017-02-04.
 *
 * What this class does currently is enable the getting and setting of data fields.
 * This allows the user to: create, edit and view entries. The three subclasses
 * provide separation of responsibilities. Currently (if passed the right view)
 * I could do everything through Entry, but that limits my flexibility.
 *
 * This needs some major refactoring.
 *
 * What I would like to do is make the subclasses EditEntry and NewEntry the same.
 * For both you would call with an intent; the subclass would then check if there
 * was passed data and fill the fields as applicable.
 *
 * Currently NewEntry is not passed data, and EditEntry is.
 *
 * dataToIntent breaks if called by subclass ViewEntry. (I'm assuming).
 * I would like to change the view_window.xml and ViewEntry class so
 * that all use editText fields- the ViewEntry fields will just be
 * set to uneditable.
 *
 * I did not do this yet as ViewEntry never needs dataToIntent().
 *
 * I would also like to refactor viewPreviousValues() and loadPreviousValues()
 * to behave more like getters and setters. In order to do this every Entry
 * subclass would have to enforce that it is always given an Intent with
 * DataEntry values loaded; or I could declare a private DataEntry in Entry.
 *
 * Then viewPreviousValues() and loadPreviousValues() would simply get from
 * the current fields or set the current view fields. dataToIntent() would then
 * always work as well.
 *
 * As a last note I would like to see if I can wrap the sections below in loops.
 * because of the method call nature of things like "R.id.name" I wasn't sure,
 * but if I could it would allow for much cleaner code, and more stability.
 * (As I could just declare the field names in a string array once, hopefully globally)
 *
 * @see NewEntry
 * @see ViewEntry
 * @see EditEntry
 * @see DataEntry
 *
 * @version 1.0
 * @author  jcrobert
 */
public class Entry extends Activity {
    //TODO: Extract these message flags to global area. (How?)
    public static final int CREATE = 10;
    public static final int VIEW = 20;
    public static final int EDIT = 30;
    public static final int DELETE = 40;

    /**
     * Eventually I was going to use this to make the windows bordered and
     * translucent, and any additional stylistic changes.
     */
    public void setWindowStyle() {
        //TODO: Custom window style.
    }

    /**
     * Data to intent intent.
     *
     * Saves the current form field data to an intent, so that the returned intent
     * may be passed back to the main activity.
     *
     * In the future it may be a good idea to pass the intent as a parameter so that
     * data can be appended to an existing intent, or at least create two methods.
     *
     * @return the intent
     */
//TODO: This can break if called by wrong subclass.
    public Intent dataToIntent() {
        EditText editText;
        Intent intent = new Intent();

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

    /**
     * View previous values.
     *
     * Acts as a way to push sent DataEntry values to the view fields.
     */
//TODO: This can break if called by wrong subclass.
    public void viewPreviousValues() {
        TextView textView;
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
    }

    /**
     * Load previous values.
     *
     * Acts as a way to push sent DataEntry values to the edit fields.
     */
//TODO: This can break if called by wrong subclass.
    public void loadPreviousValues() {
        EditText editView;
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
    }

    /**
     * Sets boundaries.
     *
     * This is where I was trying to localize all of the boundary etc. checks
     * for user input. Currently it's not very good.
     *
     * I would change all of the entry methods to scroll wheels if I could,
     * but I wasn't sure how.
     *
     * I would also like to check for valid dates using the Date/Calender class.
     */
    public void setBoundaries() {
        //TODO: Better boundary checks, protect the users from themselves. (Drop down?)
        EditText editText = (EditText) findViewById(R.id.day);
        editText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "31")});
        editText = (EditText) findViewById(R.id.month);
        editText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "12")});
        editText = (EditText) findViewById(R.id.year);
        editText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "2100")});

        editText = (EditText) findViewById(R.id.neck);
        editText.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(3,1)});
        editText = (EditText) findViewById(R.id.bust);
        editText.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(3,1)});
        editText = (EditText) findViewById(R.id.chest);
        editText.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(3,1)});
        editText = (EditText) findViewById(R.id.waist);
        editText.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(3,1)});
        editText = (EditText) findViewById(R.id.hip);
        editText.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(3,1)});
        editText = (EditText) findViewById(R.id.inseam);
        editText.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(3,1)});
    }
}
