package com.rizkyghofur.project2rizki;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Tambah extends AppCompatActivity {

    EditText txt_id, txt_name;
    Spinner select_gender;
    Button btn_submit, btn_cancel;
    DatabaseHelper SQLite = new DatabaseHelper(this);
    String id, name, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        txt_id = (EditText) findViewById(R.id.txt_id);
        txt_name = (EditText) findViewById(R.id.txt_name);
        select_gender = (Spinner) findViewById(R.id.select_gender);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        name = getIntent().getStringExtra(MainActivity.TAG_NAME);
        address = getIntent().getStringExtra(MainActivity.TAG_GENDER);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txt_id.getText().toString().equals("")) {
                        save();
                    }
                } catch (Exception e){
                    Log.e("Submit", e.toString());
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void blank() {
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_name.setText(null);
    }

    private void save() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(select_gender.getSelectedItem()).equals(null) || String.valueOf(select_gender.getSelectedItem()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Tolong masukkan nama beserta gender!", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.tambahdata(txt_name.getText().toString().trim(), select_gender.getSelectedItem().toString().trim());
            blank();
            finish();
        }
    }
}