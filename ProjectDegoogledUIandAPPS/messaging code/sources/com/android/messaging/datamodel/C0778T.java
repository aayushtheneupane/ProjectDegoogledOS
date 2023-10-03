package com.android.messaging.datamodel;

import android.util.Log;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import p000a.p005b.C0019f;

/* renamed from: com.android.messaging.datamodel.T */
public class C0778T {

    /* renamed from: Cy */
    private final C0019f f1037Cy = new C0019f();

    /* renamed from: Dy */
    private final C0019f f1038Dy = new C0019f();

    /* renamed from: Ey */
    private final HashSet f1039Ey = new HashSet();

    /* renamed from: O */
    public synchronized boolean mo5907O(String str) {
        return this.f1039Ey.contains(str);
    }

    /* renamed from: a */
    public synchronized String mo5908a(C0955p pVar, long j, int i, C0776Q q) {
        String str;
        C1424b.m3592ia(pVar.getDatabase().inTransaction());
        String str2 = (String) this.f1037Cy.get(j, (Object) null);
        if (str2 != null) {
            return str2;
        }
        ArrayList a = C0887c.m1641a(mo5909c(j), i);
        if (q != null) {
            str = C0887c.m1637a(pVar, j, q.mo5904qe(), a);
            if (q.mo5904qe()) {
                this.f1039Ey.add(str);
            }
        } else {
            str = C0887c.m1637a(pVar, j, false, a);
        }
        if (str == null) {
            return null;
        }
        this.f1037Cy.put(j, str);
        return str;
    }

    /* renamed from: c */
    public synchronized List mo5909c(long j) {
        List list;
        list = (List) this.f1038Dy.get(j, (Object) null);
        if (list == null && (list = C1029y.m2400A(j)) != null && list.size() > 0) {
            this.f1038Dy.put(j, list);
        }
        if (list == null || list.isEmpty()) {
            C1430e.m3630w("MessagingApp", "SyncManager : using unknown sender since thread " + j + " couldn't find any recipients.");
            list = new ArrayList();
            ParticipantData.m1842vh();
            list.add("ʼUNKNOWN_SENDER!ʼ");
        }
        return list;
    }

    public synchronized void clear() {
        if (Log.isLoggable("MessagingApp", 3)) {
            C1430e.m3620d("MessagingApp", "SyncManager: Cleared ThreadInfoCache");
        }
        this.f1037Cy.clear();
        this.f1038Dy.clear();
        this.f1039Ey.clear();
    }
}
