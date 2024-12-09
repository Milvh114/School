package com.milvh.app.recycleviewassignment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "goods.db";
    private static final int DATABASE_VERSION = 1;

    // Câu lệnh tạo bảng product
    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE product (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "ProductName TEXT," +
            "ProductDescription TEXT," +
            "ProductPrice INTEGER" +
            ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS product");
        onCreate(db);
    }

    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("product", null, null, null, null, null, null);
    }
}
