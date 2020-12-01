package com.rfa.sqlitepenjualan;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuatPelanggan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    EditText ned1, ned2, ned3;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pelanggan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dbHelper = new DataHelper(this);
        ned1 = (EditText) findViewById(R.id.et_kodePelanggan);
        ned2 = (EditText) findViewById(R.id.et_namaPelanggan);
        ned3 = (EditText) findViewById(R.id.et_telp);
        btn1 = (Button) findViewById(R.id.button);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pelanggan WHERE nama_pelanggan = '" +
                getIntent().getStringExtra("namapelanggan") + "'" , null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            ned1.setText(cursor.getString(0).toString());
            ned2.setText(cursor.getString(1).toString());
            ned3.setText(cursor.getString(2).toString());
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into pelanggan(kd_pelanggan, nama_pelanggan, telp) values('" +
                        ned1.getText().toString() + "','" +
                        ned2.getText().toString() + "','" +
                        ned3.getText().toString() + "')");

                Toast.makeText(getApplicationContext(), "Berhasil" ,
                        Toast.LENGTH_LONG).show();
                HalamanPelanggan.hp.RefreshList();
                finish();
            }
        } );
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}