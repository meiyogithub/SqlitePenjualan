package com.rfa.sqlitepenjualan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class HalamanPenjualan extends AppCompatActivity {
        public static HalamanPenjualan hpj;
        protected Cursor cursor;
        String[] daftar ;
        ListView listView01;
        Button btn;
        Menu menu;
        DataHelper dbCenter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_halaman_penjualan);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            btn = findViewById(R.id.button2);
            btn.setOnClickListener(view -> {
                Intent goToBuatPenjualan = new Intent(HalamanPenjualan.this, BuatPenjualan.class);
                startActivity(goToBuatPenjualan);
            });

            hpj = this;
            dbCenter = new DataHelper(this);
            RefreshList();
        }

        public void RefreshList() {
            SQLiteDatabase db = dbCenter.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM penjualan", null);
            daftar = new String[cursor.getCount()];
            cursor.moveToFirst();

            for (int cc = 0; cc < cursor.getCount(); cc++){
                cursor.moveToPosition(cc);
                daftar[cc] = cursor.getString(0).toString();
            }

            listView01 = findViewById(R.id.listView1);
            listView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
            listView01.setSelected(true);
            listView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                    final String selection = daftar[arg2];
                    final CharSequence[] dialogItem = { "Lihat Penjualan", "Update Penjualan", "Hapus Penjualan"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(HalamanPenjualan.this);
                    builder.setTitle("Pilihan");
                    builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item){
                                case 0:
                                    Intent i = new Intent(HalamanPenjualan.this, LihatPenjualan.class);
                                    i.putExtra("id_penjualan", selection);
                                    startActivity(i);
                                    break;

                                case 1:
                                    Intent in = new Intent(HalamanPenjualan.this, UpdatePenjualan.class);
                                    in.putExtra("id_penjualan", selection);
                                    Toast.makeText(getApplicationContext(), selection.toString(), Toast.LENGTH_SHORT).show();
                                    startActivity(in);
                                    break;

                                case 2:
                                    SQLiteDatabase db = dbCenter.getWritableDatabase();
                                    db.execSQL("DELETE FROM penjualan WHERE id_penjualan = '"+ selection + "'");
                                    RefreshList();
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                }
            });
            ((ArrayAdapter)listView01.getAdapter()).notifyDataSetInvalidated();
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