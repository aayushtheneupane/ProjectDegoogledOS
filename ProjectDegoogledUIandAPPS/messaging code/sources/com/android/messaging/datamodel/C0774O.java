package com.android.messaging.datamodel;

import android.database.ContentObserver;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import com.android.messaging.util.C1430e;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.O */
public class C0774O extends ContentObserver {

    /* renamed from: jc */
    private volatile boolean f1030jc = false;

    public C0774O() {
        super((Handler) null);
    }

    public void initialize() {
        C0632a.m1012Pk().registerContentObserver(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, true, this);
        this.f1030jc = true;
    }

    /* renamed from: lb */
    public boolean mo5901lb() {
        return this.f1030jc;
    }

    /* renamed from: mb */
    public void mo5902mb() {
        this.f1030jc = false;
    }

    public void onChange(boolean z) {
        super.onChange(z);
        if (Log.isLoggable("MessagingAppDataModel", 2)) {
            C1430e.m3628v("MessagingAppDataModel", "Contacts changed");
        }
        this.f1030jc = true;
    }
}
