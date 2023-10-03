package com.android.messaging.datamodel.action;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.sms.C1017m;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.datamodel.action.L */
class C0798L implements C0797K {

    /* renamed from: ha */
    private final C0955p f1061ha;
    private Cursor mCursor;

    C0798L(C0955p pVar, String str) {
        this.f1061ha = pVar;
        try {
            if (Log.isLoggable("MessagingApp", 2)) {
                C1430e.m3628v("MessagingApp", "SyncCursorPair: Querying for local messages; selection = " + str);
            }
            this.mCursor = this.f1061ha.query("messages", C0799M.f1063Wu, str, (String[]) null, (String) null, (String) null, "received_timestamp DESC");
        } catch (SQLiteException e) {
            C1430e.m3623e("MessagingApp", "SyncCursorPair: failed to query local sms/mms", e);
            throw e;
        }
    }

    public void close() {
        Cursor cursor = this.mCursor;
        if (cursor != null) {
            cursor.close();
            this.mCursor = null;
        }
    }

    public int getCount() {
        Cursor cursor = this.mCursor;
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }

    public int getPosition() {
        Cursor cursor = this.mCursor;
        if (cursor == null) {
            return 0;
        }
        return cursor.getPosition();
    }

    public C1017m next() {
        Cursor cursor = this.mCursor;
        if (cursor == null || !cursor.moveToNext()) {
            return null;
        }
        return C0801O.m1390i(this.mCursor);
    }
}
