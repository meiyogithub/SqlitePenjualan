package com.rfa.sqlitepenjualan;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class LihatBarang extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    TextView ned1, ned2, ned3, ned4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_barang);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dbHelper = new DataHelper(this);
        ned1 = (TextView) findViewById(R.id.et_kodeBarang);
        ned2 = (TextView) findViewById(R.id.et_namaBarang);
        ned3 = (TextView) findViewById(R.id.et_satuan);
        ned4 = (TextView) findViewById(R.id.et_harga);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang WHERE nama_barang = '" +
                getIntent().getStringExtra("nama") + "'" , null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            ned1.setText(cursor.getString(0).toString());
            ned2.setText(cursor.getString(1).toString());
            ned3.setText(cursor.getString(2).toString());
            ned4.setText(cursor.getString(3).toString());
        }
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