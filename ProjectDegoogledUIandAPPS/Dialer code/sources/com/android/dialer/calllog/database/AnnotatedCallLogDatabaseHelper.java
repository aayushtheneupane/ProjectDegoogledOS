package com.android.dialer.calllog.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.android.dialer.common.LogUtil;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.Locale;
import java.util.concurrent.Callable;

public class AnnotatedCallLogDatabaseHelper extends SQLiteOpenHelper {
    static final int VERSION = 4;
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutor;
    private final int maxRows;

    public AnnotatedCallLogDatabaseHelper(Context context, int i, ListeningExecutorService listeningExecutorService) {
        super(context, "annotated_call_log.db", (SQLiteDatabase.CursorFactory) null, VERSION);
        this.appContext = context;
        this.maxRows = i;
        this.backgroundExecutor = listeningExecutorService;
    }

    public ListenableFuture<Void> delete() {
        return this.backgroundExecutor.submit(new Callable() {
            public final Object call() {
                return AnnotatedCallLogDatabaseHelper.this.lambda$delete$0$AnnotatedCallLogDatabaseHelper();
            }
        });
    }

    public /* synthetic */ Void lambda$delete$0$AnnotatedCallLogDatabaseHelper() throws Exception {
        close();
        this.appContext.deleteDatabase("annotated_call_log.db");
        return null;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        LogUtil.enterBlock("AnnotatedCallLogDatabaseHelper.onCreate");
        long currentTimeMillis = System.currentTimeMillis();
        sQLiteDatabase.execSQL("create table if not exists AnnotatedCallLog (_id integer primary key, timestamp integer, number blob, formatted_number text, presentation integer, duration integer, data_usage integer, is_read integer, new integer, geocoded_location text, phone_account_component_name text, phone_account_id text, features integer, transcription integer, voicemail_uri text, call_type integer, number_attributes blob, is_voicemail_call integer, voicemail_call_tag text, transcription_state integer, call_mapping_id text);");
        sQLiteDatabase.execSQL(String.format(Locale.US, "create trigger delete_old_rows after insert on AnnotatedCallLog when (select count(*) from AnnotatedCallLog where call_type != 4) > %d begin delete from AnnotatedCallLog where _id in (select _id from AnnotatedCallLog where call_type != 4 order by timestamp limit (select count(*)-%d from AnnotatedCallLog where call_type != 4)); end;", new Object[]{Integer.valueOf(this.maxRows), Integer.valueOf(this.maxRows)}));
        sQLiteDatabase.execSQL("create index call_type_index on AnnotatedCallLog (call_type);");
        sQLiteDatabase.execSQL("create index number_index on AnnotatedCallLog (number);");
        LogUtil.m9i("AnnotatedCallLogDatabaseHelper.onCreate", "took: %dms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 2) {
            sQLiteDatabase.execSQL("alter table AnnotatedCallLog add column call_mapping_id text;");
            sQLiteDatabase.execSQL("update AnnotatedCallLog set call_mapping_id = timestamp");
        }
        if (i < VERSION) {
            sQLiteDatabase.execSQL("update AnnotatedCallLog set is_voicemail_call = 0 where is_voicemail_call is null");
        }
    }
}
