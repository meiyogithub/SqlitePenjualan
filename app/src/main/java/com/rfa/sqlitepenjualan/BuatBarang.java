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

public class BuatBarang extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    EditText ned1, ned2, ned3, ned4;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_barang);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dbHelper = new DataHelper(this);
        ned1 = (EditText) findViewById(R.id.et_kodeBarang);
        ned2 = (EditText) findViewById(R.id.et_namaBarang);
        ned3 = (EditText) findViewById(R.id.et_satuan);
        ned4 = (EditText) findViewById(R.id.et_harga);
        btn =  (Button) findViewById(R.id.button3);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang WHERE nama_barang = '" +
                getIntent().getStringExtra("namabarang") + "'" , null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            ned1.setText(cursor.getString(0).toString());
            ned2.setText(cursor.getString(1).toString());
            ned3.setText(cursor.getString(2).toString());
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into barang(kd_barang, nama_barang, satuan, harga) values('" +
                        ned1.getText().toString() + "','" +
                        ned2.getText().toString() + "','" +
                        ned3.getText().toString() + "','" +
                        ned4.getText().toString() + "')");

                Toast.makeText(getApplicationContext(), "Berhasil" ,
                        Toast.LENGTH_LONG).show();
                HalamanBarang.hb.RefreshList();
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