package com.android.messaging.datamodel.data;

import android.database.Cursor;
import com.android.messaging.util.C1464na;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p000a.p005b.C0015b;

/* renamed from: com.android.messaging.datamodel.data.X */
public class C0911X {

    /* renamed from: iC */
    private final C0015b f1209iC = new C0015b();

    /* renamed from: J */
    public int mo6383J(boolean z) {
        int i = 0;
        for (ParticipantData participantData : this.f1209iC.values()) {
            if (!participantData.mo6361yh() && (!z || participantData.mo6358wh())) {
                i++;
            }
        }
        return i;
    }

    /* renamed from: Q */
    public List mo6384Q(boolean z) {
        ArrayList arrayList = new ArrayList();
        for (ParticipantData participantData : this.f1209iC.values()) {
            if (!z || participantData.mo6358wh()) {
                arrayList.add(participantData);
            }
        }
        Collections.sort(arrayList, new C0910W(this));
        return arrayList;
    }

    /* renamed from: Ve */
    public ParticipantData mo6385Ve() {
        for (ParticipantData participantData : this.f1209iC.values()) {
            if (participantData.mo6361yh()) {
                return participantData;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Y */
    public ParticipantData mo6386Y(String str) {
        return (ParticipantData) this.f1209iC.get(str);
    }

    /* renamed from: c */
    public void mo6387c(Cursor cursor) {
        this.f1209iC.clear();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ParticipantData k = ParticipantData.m1839k(cursor);
                this.f1209iC.put(k.getId(), k);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pa */
    public boolean mo6388pa(String str) {
        if (!C1464na.m3759Zj()) {
            return true;
        }
        ParticipantData Y = mo6386Y(str);
        if (Y != null && Y.getSubId() == -1) {
            return true;
        }
        return false;
    }
}
