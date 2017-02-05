package com.example.joshuarobertson.sizebook;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import static android.provider.AlarmClock.EXTRA_MESSAGE;

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

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Button","Create");
                startActivityForResult(new Intent(MainActivity.this, NewEntry.class), 1); //1 is request code
            }
        });

        ListOfPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                DataEntry entry = (DataEntry) parent.getAdapter().getItem(position);
                Log.d("OnItemClick", "Made new DataEntry from List: "+entry.getName());
                Intent intent = new Intent(MainActivity.this, ViewEntry.class);
                Log.d("OnItemClick", "Made new Intent.");
                intent = entry.AppendToIntent(intent);
                Log.d("OnItemClick", "Appended DataEntry to intent.");
                Log.d("OnItemClick", "Starting activity...");
                intent.putExtra("position", position);
                startActivityForResult(intent, 2);
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
                    DataList.add(new DataEntry(bundle));
                    adapter.notifyDataSetChanged();
                    saveInFile();
                    Log.d("AppendToDataList",DataList.toString());
                }
                break;
            }

            case (2) : {
                if (resultCode == 0) {
                    //edit
                    Bundle bundle = data.getExtras();
                    DataEntry entry = DataList.get((int)bundle.get("position"));
                    Intent intent = new Intent(MainActivity.this, EditEntry.class);

                    intent = entry.AppendToIntent(intent);

                    intent.putExtra("position", (int)bundle.get("position"));
                    startActivityForResult(intent, 3);

                }
                if (resultCode == 1) {
                    //delete
                    Bundle bundle = data.getExtras();
                    DataList.remove((int)bundle.get("position"));
                    adapter.notifyDataSetChanged();
                    saveInFile();

                }
                if (resultCode == 2) {
                    //ignore
                }
                break;
            }

            case (3) : {
                if (resultCode == 0) {
                    //cancel


                }
                if (resultCode == 1) {
                    //commit
                    Bundle bundle = data.getExtras();
                    DataList.set((int)bundle.get("position"), new DataEntry(bundle));
                    adapter.notifyDataSetChanged();
                    saveInFile();
                }
                break;
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadFromFile();

        adapter = new ArrayAdapter<DataEntry>(this, R.layout.list_view_people, DataList);
        ListOfPeople.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //TA took the solution from stackoverflow
            Type listType = new TypeToken<ArrayList<DataEntry>>(){}.getType();
            DataList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            DataList = new ArrayList<DataEntry>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(DataList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO: Handle the Exception later
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
