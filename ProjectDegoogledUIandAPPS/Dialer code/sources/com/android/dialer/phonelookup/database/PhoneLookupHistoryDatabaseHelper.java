package com.android.dialer.phonelookup.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import com.android.dialer.common.LogUtil;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.concurrent.Callable;

public class PhoneLookupHistoryDatabaseHelper extends SQLiteOpenHelper {
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutor;

    PhoneLookupHistoryDatabaseHelper(Context context, ListeningExecutorService listeningExecutorService) {
        super(context, "phone_lookup_history.db", (SQLiteDatabase.CursorFactory) null, 1);
        this.appContext = context;
        this.backgroundExecutor = listeningExecutorService;
    }

    public ListenableFuture<Void> delete() {
        return this.backgroundExecutor.submit(new Callable() {
            public final Object call() {
                return PhoneLookupHistoryDatabaseHelper.this.lambda$delete$0$PhoneLookupHistoryDatabaseHelper();
            }
        });
    }

    public /* synthetic */ Void lambda$delete$0$PhoneLookupHistoryDatabaseHelper() throws Exception {
        close();
        this.appContext.deleteDatabase("phone_lookup_history.db");
        return null;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        LogUtil.enterBlock("PhoneLookupHistoryDatabaseHelper.onCreate");
        long uptimeMillis = SystemClock.uptimeMillis();
        sQLiteDatabase.execSQL("create table if not exists PhoneLookupHistory (normalized_number text primary key not null, phone_lookup_info blob not null, last_modified long not null);");
        sQLiteDatabase.execSQL("create index last_modified_index on PhoneLookupHistory (last_modified);");
        LogUtil.m9i("PhoneLookupHistoryDatabaseHelper.onCreate", "took: %dms", Long.valueOf(SystemClock.uptimeMillis() - uptimeMillis));
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
