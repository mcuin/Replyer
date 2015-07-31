package com.mykal.textback;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mykal on 7/31/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "repliesManager";
    private static final String TABLE_REPLIES = "replies";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MESSAGE = "message";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REPLIES_TABLE = "CREATE TABLE " + TABLE_REPLIES + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME +
                " TEXT," + KEY_MESSAGE + " TEXT" + ")";
        db.execSQL(CREATE_REPLIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPLIES);

        onCreate(db);
    }

    void addReplies() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, AddReply.getName());
        values.put(KEY_MESSAGE, AddReply.getMessage());

        db.insert(TABLE_REPLIES, null, values);
        System.out.println("Hello from DB");
        db.close();
    }

}
