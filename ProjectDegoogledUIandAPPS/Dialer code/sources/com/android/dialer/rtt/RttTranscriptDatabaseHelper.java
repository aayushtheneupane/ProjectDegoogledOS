package com.android.dialer.rtt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import com.android.dialer.common.LogUtil;

final class RttTranscriptDatabaseHelper extends SQLiteOpenHelper {
    RttTranscriptDatabaseHelper(Context context) {
        super(context, "rtt_transcript.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        LogUtil.enterBlock("RttTranscriptDatabaseHelper.onCreate");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        sQLiteDatabase.execSQL("create table if not exists rtt_transcript (rtt_transcript_id integer primary key, transcript_data blob not null);");
        LogUtil.m9i("RttTranscriptDatabaseHelper.onCreate", "took: %dms", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
