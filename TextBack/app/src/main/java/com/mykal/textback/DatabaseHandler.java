package com.mykal.textback;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
    SQLiteDatabase db;

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
        values.put(KEY_NAME, AddReply.saveName().toString());
        values.put(KEY_MESSAGE, AddReply.saveMessage());

        db.insert(TABLE_REPLIES, null, values);
        System.out.println("Hello from DB");
        db.close();
    }

    Replies getReply(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_REPLIES, new String[] {KEY_ID, KEY_NAME, KEY_MESSAGE}, KEY_ID
                + "=?", new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Replies reply = new Replies(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        return reply;
    }

    List<Replies> getAllReplies() {
        List<Replies> repliesList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_REPLIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Replies reply = new Replies();
                reply.setId(Integer.parseInt(cursor.getString(0)));
                reply.setName(cursor.getString(1));
                reply.setMessage(cursor.getString(2));
                repliesList.add(reply);
            } while (cursor.moveToNext());
        }

        return repliesList;
    }

    int updateReply(Replies reply) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, AddReply.saveName());
        values.put(KEY_MESSAGE, AddReply.saveMessage());

        return db.update(TABLE_REPLIES, values, KEY_ID + " = ?", new String[] {String.valueOf(reply.getId())});
    }

    void deleteReply(Replies reply) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_REPLIES, KEY_ID + " = ?", new String[] {String.valueOf(reply.getId())});
        db.close();
    }
}
