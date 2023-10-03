package com.android.messaging.datamodel.data;

import android.database.sqlite.SQLiteFullException;
import com.android.messaging.C0967f;
import com.android.messaging.util.C1430e;
import java.util.ArrayList;
import p026b.p027a.p028a.p029a.C0631a;

/* renamed from: com.android.messaging.datamodel.data.f */
class C0923f implements Runnable {

    /* renamed from: cA */
    final /* synthetic */ ArrayList f1232cA;

    /* renamed from: dA */
    final /* synthetic */ ArrayList f1233dA;

    C0923f(C0931n nVar, ArrayList arrayList, ArrayList arrayList2) {
        this.f1232cA = arrayList;
        this.f1233dA = arrayList2;
    }

    public void run() {
        C0967f.get().getApplicationContext();
        new C0631a();
        try {
            boolean isEmpty = this.f1232cA.isEmpty();
            boolean isEmpty2 = this.f1233dA.isEmpty();
        } catch (SQLiteFullException e) {
            C1430e.m3631w("bugle_datamodel", "Unable to update contact", e);
        }
    }
}
