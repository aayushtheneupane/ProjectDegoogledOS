package com.android.messaging.datamodel.action;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.provider.Telephony;
import android.support.p016v4.media.session.C0107q;
import android.util.Log;
import com.android.messaging.C0967f;
import com.android.messaging.sms.C1017m;
import com.android.messaging.sms.DatabaseMessages$MmsMessage;
import com.android.messaging.sms.DatabaseMessages$SmsMessage;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.datamodel.action.N */
class C0800N implements C0797K {

    /* renamed from: _y */
    private Cursor f1064_y = null;

    /* renamed from: az */
    private Cursor f1065az = null;

    /* renamed from: bz */
    private C1017m f1066bz;

    /* renamed from: cz */
    private C1017m f1067cz;

    C0800N(String str, String str2) {
        try {
            Context applicationContext = C0967f.get().getApplicationContext();
            if (Log.isLoggable("MessagingApp", 2)) {
                C1430e.m3628v("MessagingApp", "SyncCursorPair: Querying for remote SMS; selection = " + str);
            }
            this.f1064_y = C0107q.query(applicationContext.getContentResolver(), Telephony.Sms.CONTENT_URI, DatabaseMessages$SmsMessage.getProjection(), str, (String[]) null, "date DESC");
            if (this.f1064_y != null) {
                if (Log.isLoggable("MessagingApp", 2)) {
                    C1430e.m3628v("MessagingApp", "SyncCursorPair: Querying for remote MMS; selection = " + str2);
                }
                this.f1065az = C0107q.query(applicationContext.getContentResolver(), Telephony.Mms.CONTENT_URI, DatabaseMessages$MmsMessage.getProjection(), str2, (String[]) null, "date DESC");
                if (this.f1065az != null) {
                    this.f1066bz = m1387fo();
                    this.f1067cz = m1386eo();
                    return;
                }
                C1430e.m3630w("MessagingApp", "SyncCursorPair: Remote MMS query returned null cursor; need to cancel sync");
                throw new RuntimeException("Null cursor from remote MMS query");
            }
            C1430e.m3630w("MessagingApp", "SyncCursorPair: Remote SMS query returned null cursor; need to cancel sync");
            throw new RuntimeException("Null cursor from remote SMS query");
        } catch (SQLiteException e) {
            C1430e.m3623e("MessagingApp", "SyncCursorPair: failed to query remote messages", e);
            throw e;
        }
    }

    /* renamed from: eo */
    private C1017m m1386eo() {
        Cursor cursor = this.f1065az;
        if (cursor == null || !cursor.moveToNext()) {
            return null;
        }
        return DatabaseMessages$MmsMessage.m2335l(this.f1065az);
    }

    /* renamed from: fo */
    private C1017m m1387fo() {
        Cursor cursor = this.f1064_y;
        if (cursor == null || !cursor.moveToNext()) {
            return null;
        }
        return DatabaseMessages$SmsMessage.m2342l(this.f1064_y);
    }

    public void close() {
        Cursor cursor = this.f1064_y;
        if (cursor != null) {
            cursor.close();
            this.f1064_y = null;
        }
        Cursor cursor2 = this.f1065az;
        if (cursor2 != null) {
            cursor2.close();
            this.f1065az = null;
        }
    }

    public int getCount() {
        Cursor cursor = this.f1064_y;
        int i = 0;
        int count = cursor == null ? 0 : cursor.getCount();
        Cursor cursor2 = this.f1065az;
        if (cursor2 != null) {
            i = cursor2.getCount();
        }
        return count + i;
    }

    public int getPosition() {
        Cursor cursor = this.f1064_y;
        int i = 0;
        int position = cursor == null ? 0 : cursor.getPosition();
        Cursor cursor2 = this.f1065az;
        if (cursor2 != null) {
            i = cursor2.getPosition();
        }
        return (position + i) - 1;
    }

    public C1017m next() {
        C1017m mVar = this.f1066bz;
        if (mVar == null || this.f1067cz == null) {
            C1017m mVar2 = this.f1066bz;
            if (mVar2 != null) {
                this.f1066bz = m1387fo();
                return mVar2;
            }
            C1017m mVar3 = this.f1067cz;
            this.f1067cz = m1386eo();
            return mVar3;
        } else if (mVar.mo6791hi() >= this.f1067cz.mo6791hi()) {
            C1017m mVar4 = this.f1066bz;
            this.f1066bz = m1387fo();
            return mVar4;
        } else {
            C1017m mVar5 = this.f1067cz;
            this.f1067cz = m1386eo();
            return mVar5;
        }
    }
}
