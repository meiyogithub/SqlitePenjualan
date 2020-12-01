package com.rfa.sqlitepenjualan;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class UpdateBarang extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn1;
    EditText ned1, ned2, ned3, ned4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_barang);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dbHelper = new DataHelper(this);
        ned1 = (EditText) findViewById(R.id.et_kodeBarang);
        ned2 = (EditText) findViewById(R.id.et_namaBarang);
        ned3 = (EditText) findViewById(R.id.et_satuan);
        ned4 = (EditText) findViewById(R.id.et_harga);
        btn1 = (Button) findViewById(R.id.button5);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang WHERE nama_barang= '" +
                getIntent().getStringExtra("nama") + "'" , null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            ned1.setText(cursor.getString(0).toString());
            ned2.setText(cursor.getString(1).toString());
            ned3.setText(cursor.getString(2).toString());
            ned4.setText(cursor.getString(3).toString());
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update barang set nama_barang='" +
                        ned2.getText().toString() + "', satuan='" +
                        ned3.getText().toString() + "', harga='" +
                        ned4.getText().toString() + "'where kd_barang='" +
                        ned1.getText().toString() + "'");
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