package com.example.joshuarobertson.sizebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * The type Main activity.
 *
 * This activity can only do two things: Launch an NewEntry activity (button)
 * or a ViewEntry activity (clicking a data entry).
 *
 * However, this activity is responsible for launching all Entry activities,
 * and managing data, and displaying the number of current entries.
 *
 * This activity is responsible for creating, updating, deleting,
 * saving, and loading data entries.
 *
 * entries are stored in DataList. Whenever an entry is created/updated/deleted
 * all entries are saved.
 *
 * When the app starts [ onStart() ] the entries are loaded.
 *
 * On return of an activity, action is taken based on the return value.
 *
 * NewEntry: create a new DataEntry or pass.
 * ViewEntry: Launch an EditEntry, delete the DataEntry, or pass.
 * EditEntry: update an existing DataEntry or pass.
 *
 * The main requirements are DataEntry and Entry. (Some Renaming might be in order)
 *
 * @see Entry
 * @see DataEntry
 *
 * @version 1.0
 * @author  jcrobert
 *
 */
public class MainActivity extends Activity {
    private TextView textView;

    private static final String FILENAME = "file.sav";
    private ListView ListOfPeople;

    private ArrayList<DataEntry> DataList;
    private ArrayAdapter<DataEntry> adapter;

    //TODO: Extract these message flags to global area. (How?)
    public static final int CREATE = 10;
    public static final int VIEW = 20;
    public static final int EDIT = 30;
    public static final int DELETE = 40;


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
                startActivityForResult(new Intent(MainActivity.this, NewEntry.class), CREATE);
            }
        });

        ListOfPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                DataEntry entry = (DataEntry) parent.getAdapter().getItem(position);
                Intent intent = new Intent(MainActivity.this, ViewEntry.class);
                intent = entry.AppendToIntent(intent);
                intent.putExtra("position", position);
                startActivityForResult(intent, VIEW);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (CREATE) : {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    Bundle bundle = data.getExtras();
                    DataList.add(new DataEntry(bundle));
                    adapter.notifyDataSetChanged();
                    updateEntryCount();
                    saveInFile();
                    Log.d("AppendToDataList",DataList.toString());
                }
                break;
            }

            case (VIEW) : {
                if (resultCode == EDIT) {
                    Bundle bundle = data.getExtras();
                    DataEntry entry = DataList.get((int)bundle.get("position"));
                    Intent intent = new Intent(MainActivity.this, EditEntry.class);

                    intent = entry.AppendToIntent(intent);

                    intent.putExtra("position", (int)bundle.get("position"));
                    startActivityForResult(intent, EDIT);

                }
                if (resultCode == DELETE) {
                    Bundle bundle = data.getExtras();
                    DataList.remove((int)bundle.get("position"));
                    adapter.notifyDataSetChanged();
                    updateEntryCount();
                    saveInFile();
                }
                break;
            }

            case (EDIT) : {
                if (resultCode == CREATE) {
                    Bundle bundle = data.getExtras();
                    DataList.set((int)bundle.get("position"), new DataEntry(bundle));
                    adapter.notifyDataSetChanged();
                    updateEntryCount();
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

        textView = (TextView) findViewById(R.id.entry_count);
        updateEntryCount();
    }

    private void loadFromFile() {
        try {
            DataList = new ArrayList<DataEntry>();

            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //TA took the solution from StackOverFlow
            Type listType = new TypeToken<ArrayList<DataEntry>>(){}.getType();
            DataList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            //TODO: Handle Exception
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
            // TODO: Handle the Exception later.
            throw new RuntimeException();
        } catch (IOException e) {
            //TODO: Handle this Exception.
            throw new RuntimeException();
        }
    }

    private void updateEntryCount() {
        String s = "entries: "+ DataList.size();
        textView.setText(s, TextView.BufferType.EDITABLE);
    }
}
