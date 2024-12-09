package com.milvh.app.recycleviewassignment;

import static android.provider.DocumentsContract.Document.MIME_TYPE_DIR;
import static android.provider.DocumentsContract.Root.MIME_TYPE_ITEM;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBProvider extends ContentProvider {
    private static final String AUTHORITY = "com.milvh.app.goodsprovider";
    private static final String TABLE_NAME = "product";
    private static final int PRODUCT = 1;
    private static final int PRODUCT_ID = 2;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, TABLE_NAME, PRODUCT);
        uriMatcher.addURI(AUTHORITY, TABLE_NAME + "/#", PRODUCT_ID);
    }

    private DBHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PRODUCT_ID:
                selection = "ID = ?";
                selectionArgs = new String[]{uri.getLastPathSegment()};
                cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id;
        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                id = db.insert(TABLE_NAME, null, values);
                if (id > 0) {
                    Uri resultUri = ContentUris.withAppendedId(uri, id);
                    getContext().getContentResolver().notifyChange(uri, null);
                    return resultUri;
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
        throw new UnsupportedOperationException("Insert failed for URI: " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted;
        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                rowsDeleted = db.delete(TABLE_NAME, selection, selectionArgs);
                break;
            case PRODUCT_ID:
                selection = "ID = ?";
                selectionArgs = new String[]{uri.getLastPathSegment()};
                rowsDeleted = db.delete(TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
        if (rowsDeleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsUpdated;
        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                rowsUpdated = db.update(TABLE_NAME, values, selection, selectionArgs);
                break;
            case PRODUCT_ID:
                selection = "ID = ?";
                selectionArgs = new String[]{uri.getLastPathSegment()};
                rowsUpdated = db.update(TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
        if (rowsUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + TABLE_NAME;
            case PRODUCT_ID:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + TABLE_NAME;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
    }
}