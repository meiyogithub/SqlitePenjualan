package com.rfa.sqlitepenjualan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbpenjualan.db" ;
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pelanggan(kd_pelanggan integer primary key, nama_pelanggan text, telp text)");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1, 'Dinny Susilowati', '08577110779')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (2, 'Afif Mahar Widodo', '08988981908')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (3, 'Didik Setya Budi', '083830082299')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (4, 'Fahri Saputra', '085156298710')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (5, 'Galumbang Christo Marbun', '081263816274')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (6, 'Hendika Saputro', '081385745468')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (7, 'Kunto Wibisono', '081387690506')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (8, 'Nur Zain Pradana', '082125652279')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (9, 'Rizqi Fawaz Al-Fahri', '081295120669')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (10, 'Rizki Wahyudi', '085211345481')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (11, 'Ravi Budi Rizkiaji', '083869147788')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (12, 'Lailatul Zahro', '081388438315')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (13, 'Rico Irmansyah', '081804847455')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (14, 'Ariq Andrean', '081285459750')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (15, 'Dede Khairuddin', '085714266056')");

        db.execSQL("create table barang(kd_barang integer primary key, nama_barang text, satuan text, harga integer)");
        db.execSQL("INSERT INTO barang (kd_barang, nama_barang, satuan, harga) VALUES (1, 'Lemari Es Toshiba 2 Pintu', 'Unit', 2700000)");
        db.execSQL("INSERT INTO barang (kd_barang, nama_barang, satuan, harga) VALUES (2, 'TV Xiomi Android 32 inc', 'Unit', 2000000)");
        db.execSQL("INSERT INTO barang (kd_barang, nama_barang, satuan, harga) VALUES (3, 'Kabel Antena ', 'Roll', 750000)");

        db.execSQL("create table penjualan(id_penjualan integer primary key, tgl_penjualan date, kd_pelanggan integer, kd_barang integer, qty integer)");
        db.execSQL("INSERT INTO penjualan (id_penjualan, tgl_penjualan, kd_pelanggan, kd_barang, qty) VALUES (1001, '2020-11-26', 1, 1, 1)");
        db.execSQL("INSERT INTO penjualan (id_penjualan, tgl_penjualan, kd_pelanggan, kd_barang, qty) VALUES (1002, '2020-11-26', 2, 2, 2)");
        db.execSQL("INSERT INTO penjualan (id_penjualan, tgl_penjualan, kd_pelanggan, kd_barang, qty) VALUES (1003, '2020-11-26', 3, 3, 5)");
        db.execSQL("INSERT INTO penjualan (id_penjualan, tgl_penjualan, kd_pelanggan, kd_barang, qty) VALUES (1004, '2020-11-26', 9, 2, 2)");
        db.execSQL("INSERT INTO penjualan (id_penjualan, tgl_penjualan, kd_pelanggan, kd_barang, qty) VALUES (1005, '2020-11-26', 8, 1, 2)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE pelanggan");
        db.execSQL("DROP TABLE barang");
        db.execSQL("DROP TABLE penjualan");
        onCreate(db);
    }
}