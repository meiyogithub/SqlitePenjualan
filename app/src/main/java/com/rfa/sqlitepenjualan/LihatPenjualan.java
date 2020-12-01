package com.rfa.sqlitepenjualan;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LihatPenjualan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    TextView tv1, tv2, tv3, tv4, tv5;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_penjualan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dbHelper = new DataHelper(this);

        tv1 = findViewById(R.id.et_idPenjualan);
        tv2 = findViewById(R.id.et_tglPenjualan);
        tv3= findViewById(R.id.et_kdPelanggan);
        tv4 = findViewById(R.id.et_kdBarang);
        tv5 = findViewById(R.id.et_qty);


        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT id_penjualan, tgl_penjualan, J.kd_pelanggan, J.kd_barang, qty FROM penjualan as J JOIN barang as B ON J.kd_barang = B.kd_barang JOIN pelanggan as P ON J.kd_pelanggan = P.kd_pelanggan WHERE id_penjualan ='" + getIntent().getStringExtra("id_penjualan") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            tv1.setText(cursor.getString(0));
            tv2.setText(cursor.getString(1));
            tv3.setText(cursor.getString(2));
            tv4.setText(cursor.getString(3));
            tv5.setText(cursor.getString(4));
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
