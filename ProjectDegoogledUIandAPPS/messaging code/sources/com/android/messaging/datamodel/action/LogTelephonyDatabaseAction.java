package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.C0967f;
import com.android.messaging.util.C1410N;
import com.android.messaging.util.C1430e;

public class LogTelephonyDatabaseAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0830t();

    /* renamed from: Sy */
    private static final String[] f1062Sy = {"_id", "date", "message_count", "recipient_ids", "snippet", "snippet_cs", "read", "error", "has_attachment"};

    private LogTelephonyDatabaseAction() {
    }

    /* renamed from: Fe */
    public static void m1381Fe() {
        new LogTelephonyDatabaseAction().start();
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        C0967f.get().getApplicationContext();
        C1410N.m3547Nj();
        C1430e.m3622e("MessagingApp", "Can't log telephony database unless debugging is enabled");
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ LogTelephonyDatabaseAction(Parcel parcel, C0830t tVar) {
        super(parcel);
    }
}
