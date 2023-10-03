package com.android.dialer.preferredsim.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.android.dialer.common.LogUtil;

public class PreferredSimDatabaseHelper extends SQLiteOpenHelper {
    PreferredSimDatabaseHelper(Context context) {
        super(context, "preferred_sim.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        LogUtil.enterBlock("PreferredSimDatabaseHelper.onCreate");
        long currentTimeMillis = System.currentTimeMillis();
        sQLiteDatabase.execSQL("create table if not exists preferred_sim (data_id integer primary key, preferred_phone_account_component_name text, preferred_phone_account_id text);");
        LogUtil.m9i("PreferredSimDatabaseHelper.onCreate", "took: %dms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
