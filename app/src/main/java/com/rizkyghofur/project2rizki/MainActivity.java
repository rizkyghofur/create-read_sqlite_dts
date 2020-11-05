package com.rizkyghofur.project2rizki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Data> itemList = new ArrayList<Data>();
    Adapter adapter;
    DatabaseHelper SQLite = new DatabaseHelper(this);

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_GENDER = "gender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLite = new DatabaseHelper(getApplicationContext());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.list_view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Tambah.class);
                startActivity(intent);
            }
        });

        adapter = new Adapter(MainActivity.this, itemList);
        listView.setAdapter(adapter);
        getAllData();

    }

    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.ambildata();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String poster = row.get(i).get(TAG_NAME);
            String title = row.get(i).get(TAG_GENDER);

            Data data = new Data();

            data.setId(id);
            data.setName(poster);
            data.setGender(title);

            itemList.add(data);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}