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
public class HalamanBarang extends AppCompatActivity {
    public static HalamanBarang hb;
    protected Cursor cursor;
    String[] daftar;
    ListView ListView01;
    Button btn;
    Menu menu;
    DataHelper dbcenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_barang);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn = (Button)findViewById(R.id.button2);
        btn.setOnClickListener(view -> {
            Intent inte = new Intent(HalamanBarang.this, BuatBarang.class);
            startActivity(inte);
        });

        hb = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }
    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang" ,null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = { "Lihat Barang" , "Update Barang" ,
                        "Hapus Barang"
                } ;
                AlertDialog.Builder builder = new AlertDialog.Builder(HalamanBarang.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item) {
                            case 0 :
                                Intent i = new Intent(getApplicationContext(), LihatBarang.class);
                                i.putExtra("nama" , selection);
                                startActivity(i);
                                break;
                            case 1 :
                                Intent in = new Intent(getApplicationContext(), UpdateBarang.class);
                                in.putExtra("nama" , selection);
                                startActivity(in);
                                break;
                            case 2 :
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from barang where nama_barang = '" +selection+ "'");
                                RefreshList();
                                break;
                        }
                    }
                } );
                builder.create().show();
            }
        } );
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
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