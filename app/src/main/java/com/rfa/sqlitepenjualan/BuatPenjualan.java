package com.rfa.sqlitepenjualan;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuatPenjualan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn;
    EditText et1, et2, et3, et4, et5;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_penjualan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dbHelper = new DataHelper(this);

        btn = findViewById(R.id.button);

        et1 = findViewById(R.id.et_idPenjualan);
        et2 = findViewById(R.id.et_tglPenjualan);
        et3 = findViewById(R.id.et_kdPelanggan);
        et4 = findViewById(R.id.et_kdBarang);
        et5 = findViewById(R.id.et_qty);

        btn.setOnClickListener(view -> {
            db = dbHelper.getWritableDatabase();
            db.execSQL("insert into penjualan(id_penjualan, tgl_penjualan, kd_pelanggan, kd_barang, qty) values('" +
                    et1.getText().toString() + "','" +
                    et2.getText().toString() + "','" +
                    et3.getText().toString() + "','" +
                    et4.getText().toString() + "','" +
                    et5.getText().toString() + "')");
            Toast.makeText(getApplicationContext(), "Berhasil" ,
                    Toast.LENGTH_LONG).show();
            HalamanPenjualan.hpj.RefreshList();
            finish();
        });
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