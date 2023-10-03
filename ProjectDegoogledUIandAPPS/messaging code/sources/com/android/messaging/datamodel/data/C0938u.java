package com.android.messaging.datamodel.data;

import android.database.Cursor;
import com.android.messaging.util.C1424b;
import java.util.ArrayList;
import java.util.Iterator;
import p000a.p005b.C0027n;

/* renamed from: com.android.messaging.datamodel.data.u */
public class C0938u implements Iterable {
    /* access modifiers changed from: private */

    /* renamed from: FB */
    public final C0027n f1336FB = new C0027n();

    /* renamed from: GB */
    private int f1337GB = 0;

    /* access modifiers changed from: package-private */
    /* renamed from: Jg */
    public ArrayList mo6571Jg() {
        ArrayList arrayList = new ArrayList(this.f1336FB.size());
        for (int i = 0; i < this.f1336FB.size(); i++) {
            ParticipantData participantData = (ParticipantData) this.f1336FB.valueAt(i);
            if (!participantData.mo6362zh()) {
                arrayList.add(participantData);
            }
        }
        return arrayList;
    }

    /* renamed from: Xe */
    public int mo6572Xe() {
        return this.f1337GB;
    }

    /* renamed from: Ye */
    public ParticipantData mo6573Ye() {
        if (this.f1337GB != 1) {
            return null;
        }
        for (int i = 0; i < this.f1336FB.size(); i++) {
            ParticipantData participantData = (ParticipantData) this.f1336FB.valueAt(i);
            if (!participantData.mo6362zh()) {
                return participantData;
            }
        }
        C1424b.fail("Could not find other participant");
        return null;
    }

    /* renamed from: c */
    public void mo6574c(Cursor cursor) {
        this.f1336FB.clear();
        this.f1337GB = 0;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ParticipantData k = ParticipantData.m1839k(cursor);
                if (!k.mo6362zh()) {
                    this.f1337GB++;
                }
                this.f1336FB.put(k.getId(), k);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ParticipantData getParticipantById(String str) {
        return (ParticipantData) this.f1336FB.get(str);
    }

    public boolean isLoaded() {
        return !this.f1336FB.isEmpty();
    }

    public Iterator iterator() {
        return new C0937t(this);
    }
}
