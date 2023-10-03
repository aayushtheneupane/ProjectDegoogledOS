package com.android.dialer.callrecord;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class CallRecordingDataStore {
    private SQLiteDatabase mDatabase = null;
    private SQLiteOpenHelper mOpenHelper = null;

    static class CallRecordingSQLiteOpenHelper extends SQLiteOpenHelper {
        public CallRecordingSQLiteOpenHelper(Context context) {
            super(context, "callrecordings.db", (SQLiteDatabase.CursorFactory) null, 1);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE call_recordings (_id INTEGER PRIMARY KEY AUTOINCREMENT,phone_number TEXT,call_date LONG,recording_filename TEXT, creation_date LONG);");
            sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS phone_number_call_date_index ON call_recordings (phone_number, call_date);");
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    public void close() {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
        SQLiteOpenHelper sQLiteOpenHelper = this.mOpenHelper;
        if (sQLiteOpenHelper != null) {
            sQLiteOpenHelper.close();
        }
        this.mDatabase = null;
        this.mOpenHelper = null;
    }

    public List<CallRecording> getRecordings(String str, long j) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor rawQuery = this.mDatabase.rawQuery("SELECT recording_filename,creation_date FROM call_recordings WHERE phone_number = ? AND call_date = ? ORDER BY creation_date", new String[]{str, String.valueOf(j)});
            while (rawQuery.moveToNext()) {
                CallRecording callRecording = new CallRecording(str, j, rawQuery.getString(0), rawQuery.getLong(1));
                if (callRecording.getFile().exists()) {
                    arrayList.add(callRecording);
                }
            }
            rawQuery.close();
        } catch (SQLiteException e) {
            Log.w("CallRecordingStore", "Failed to fetch recordings for number " + str + ", date " + j, e);
        }
        return arrayList;
    }

    public void open(Context context) {
        if (this.mDatabase == null) {
            this.mOpenHelper = new CallRecordingSQLiteOpenHelper(context);
            this.mDatabase = this.mOpenHelper.getWritableDatabase();
        }
    }

    public void putRecording(CallRecording callRecording) {
        try {
            SQLiteStatement compileStatement = this.mDatabase.compileStatement("INSERT INTO call_recordings (phone_number, call_date, recording_filename, creation_date)  VALUES (?, ?, ?, ?)");
            compileStatement.bindString(1, callRecording.phoneNumber);
            compileStatement.bindLong(2, callRecording.creationTime);
            compileStatement.bindString(3, callRecording.fileName);
            compileStatement.bindLong(4, System.currentTimeMillis());
            long executeInsert = compileStatement.executeInsert();
            Log.i("CallRecordingStore", "Saved recording " + callRecording + " with id " + executeInsert);
        } catch (SQLiteException e) {
            Log.w("CallRecordingStore", "Failed to save recording " + callRecording, e);
        }
    }
}
