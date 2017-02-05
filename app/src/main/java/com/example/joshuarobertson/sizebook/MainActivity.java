package com.example.joshuarobertson.sizebook;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    private static final String FILENAME = "file.sav";
    private ListView ListOfPeople;

    private ArrayList<DataEntry> DataList; /* CHANGE */
    private ArrayAdapter<DataEntry> adapter; /* CHANGE */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataList = new ArrayList<DataEntry>();

        Button create = (Button) findViewById(R.id.button_right);


        ListOfPeople = (ListView) findViewById(R.id.listOfPeople);
        //adapter.notifyDataSetChanged();


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Button","Create");
                startActivityForResult(new Intent(MainActivity.this, NewEntry.class), 1); //1 is request code
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1) : {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    Bundle bundle = data.getExtras();

                    DataEntry entry = new DataEntry(bundle.getString("name"));

                    entry.setDate(bundle.getString("year"),bundle.getString("month"),bundle.getString("day"));
                    entry.setNeck(bundle.getString("neck"));
                    entry.setBust(bundle.getString("bust"));
                    entry.setChest(bundle.getString("chest"));
                    entry.setWaist(bundle.getString("waist"));
                    entry.setHip(bundle.getString("hip"));
                    entry.setInseam(bundle.getString("inseam"));
                    entry.setComment(bundle.getString("comment"));

                    DataList.add(entry);
                    adapter.notifyDataSetChanged();

                    Log.d("AppendToDataList",DataList.toString());
                }
                break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


        //loadFromFile();

        adapter = new ArrayAdapter<DataEntry>(this, R.layout.list_view_people, DataList);
        ListOfPeople.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        startActivity(new Intent(MainActivity.this,NewEntry.class));

        int id = item.getItemId();

        //displayPopupWindow();

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.entry_window);
        dialog.setTitle("Help");
        dialog.setCancelable(true);
        dialog.show();
        return true;

        /*
        if (id == R.id.action_settings) {
            return true;
        }
        */
        //return super.onOptionsItemSelected(item);
    }

    private void displayPopupWindow() {
        PopupWindow popup = new PopupWindow(this);
        View layout = getLayoutInflater().inflate(R.layout.entry_window, null);
        popup.setContentView(layout);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }
}
