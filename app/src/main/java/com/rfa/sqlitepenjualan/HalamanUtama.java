package com.rfa.sqlitepenjualan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HalamanUtama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public void movePelanggan(View view) {
        Intent move1 = new Intent(HalamanUtama.this, HalamanPelanggan.class );
        startActivity(move1);
    }

    public void moveBarang(View view) {
        Intent move2 = new Intent(HalamanUtama.this, HalamanBarang.class );
        startActivity(move2);
    }

    public void movePenjualan(View view) {
        Intent move3 = new Intent(HalamanUtama.this, HalamanPenjualan.class );
        startActivity(move3);
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