package com.mykal.replyer;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 4/18/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Replies.db";
    private static final int DATABASE_VERSION = 1;
    public static final String REPLY_TABLE_NAME = "reply";
    public static final String REPLY_COLUMN_ID = "_id";
    public static final String REPLY_COLUMN_NAME = "name";
    public static final String REPLY_COLUMN_MESSAGE = "message";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + REPLY_TABLE_NAME + "(" +
                REPLY_COLUMN_ID + " INTEGER PRIMARY KEY, "
                + REPLY_COLUMN_NAME + " TEXT UNIQUE, " + REPLY_COLUMN_MESSAGE
                + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + REPLY_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertReply(String name, String message) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(REPLY_COLUMN_NAME, name);
        contentValues.put(REPLY_COLUMN_MESSAGE, message);
        db.insert(REPLY_TABLE_NAME, null, contentValues);

        return true;
    }

    public boolean updateReply(String name, String message) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(REPLY_COLUMN_NAME, name);
        contentValues.put(REPLY_COLUMN_MESSAGE, message);

        db.update(REPLY_TABLE_NAME, contentValues, REPLY_COLUMN_NAME + " = ? ",
                new String[] {name});

        return true;
    }

    public Cursor getReply(String name) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + REPLY_TABLE_NAME  +
        " WHERE " + REPLY_COLUMN_NAME + "=?", new String[] {name});

        return res;
    }

    public Cursor getAllReplies() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + REPLY_TABLE_NAME, null);

        return res;
    }

    public Integer deleteReply(String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(REPLY_TABLE_NAME, REPLY_COLUMN_NAME + "=?",
                new String[] {name});
    }
}
