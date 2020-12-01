package com.rfa.sqlitepenjualan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText etUser;
    EditText etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUser = (EditText) findViewById(R.id.et_username);
        etPass = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.bt_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUser.getText().toString().equals("admin") &&
                        etPass.getText().toString().equals("admin")) {
                    Intent move = new Intent(MainActivity.this, HalamanUtama.class);
                    startActivity(move);
                    Toast.makeText(getApplicationContext(), "Berhasil Login",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Gagal Login",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}