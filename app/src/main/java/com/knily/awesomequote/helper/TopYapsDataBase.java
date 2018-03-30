package com.knily.awesomequote.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TopYapsDataBase extends SQLiteOpenHelper {
    private static final String TAG = TopYapsDataBase.class.getSimpleName();

    public TopYapsDataBase(Context context) {
        super(context, Constants.DATABASE.DB_NAME, null, Constants.DATABASE.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY_POSTJSON);
            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY_AUTHOR);
            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY_CONTENT);
            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY_EXCERPT);
            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY_GUEST_AUTHOR50);
            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY_MEDIA_DETAILS);
            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY_REPLY);
            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY_TITLE);
            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY_MEDIUM_THUMB);
            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY_BETTER_FEATURED_IMAGE);
        }catch (SQLiteException e){
            Log.d(TAG, e.getMessage());
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DATABASE.DROP_QUERY_POSTJSON);
        db.execSQL(Constants.DATABASE.DROP_QUERY_TITLE);
        db.execSQL(Constants.DATABASE.DROP_QUERY_CONTENT);
        db.execSQL(Constants.DATABASE.DROP_QUERY_EXCERPT);
        db.execSQL(Constants.DATABASE.DROP_QUERY_BETTER_FEATURED_IMAGE);
        db.execSQL(Constants.DATABASE.DROP_QUERY_MEDIA_DETAILS);
        db.execSQL(Constants.DATABASE.DROP_QUERY_MEDIUM_THUMB);
        db.execSQL(Constants.DATABASE.DROP_QUERY_GUEST_AUTHOR50);
        db.execSQL(Constants.DATABASE.DROP_QUERY_AUTHOR);
        db.execSQL(Constants.DATABASE.DROP_QUERY_REPLY);
        this.onCreate(db);
    }
}