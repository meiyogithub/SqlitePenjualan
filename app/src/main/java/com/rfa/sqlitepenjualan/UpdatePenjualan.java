package com.rfa.sqlitepenjualan;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePenjualan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn;
    EditText et1, et2, et3, et4, et5;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_penjualan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        dbHelper = new DataHelper(this);


        btn = findViewById(R.id.button);

        et1 = findViewById(R.id.et_idPenjualan);
        et2 = findViewById(R.id.et_tglPenjualan);
        et3 = findViewById(R.id.et_kdPelanggan);
        et4 = findViewById(R.id.et_kdBarang);
        et5= findViewById(R.id.et_qty);

        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT id_penjualan, tgl_penjualan, J.kd_pelanggan, J.kd_barang, qty FROM penjualan as J JOIN barang as B ON J.kd_barang = B.kd_barang JOIN pelanggan as P ON J.kd_pelanggan = P.kd_pelanggan WHERE id_penjualan ='" + getIntent().getStringExtra("id_penjualan") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            et1.setText(cursor.getString(0));
            et2.setText(cursor.getString(1));
            et3.setText(cursor.getString(2));
            et4.setText(cursor.getString(3));
            et5.setText(cursor.getString(4));

        }

        btn.setOnClickListener(view -> {
            db = dbHelper.getWritableDatabase();

            db.execSQL("UPDATE penjualan set tgl_penjualan='" +
                    et2.getText().toString() + "', kd_pelanggan='" +
                    et3.getText().toString() + "', kd_barang='" +
                    et4.getText().toString() + "', qty='" +
                    et5.getText().toString() + "'" + "where id_penjualan ='" +
                    et1.getText().toString() + "'");

            Toast.makeText(UpdatePenjualan.this.getApplicationContext(), "Berhasil",
                    Toast.LENGTH_LONG).show();

           HalamanPenjualan.hpj.RefreshList();
            UpdatePenjualan.this.finish();
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