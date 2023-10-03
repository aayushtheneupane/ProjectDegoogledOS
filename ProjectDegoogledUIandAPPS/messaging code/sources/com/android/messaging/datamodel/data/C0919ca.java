package com.android.messaging.datamodel.data;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.data.ca */
public class C0919ca {
    private final Context mContext;

    /* renamed from: qC */
    private final List f1225qC = new ArrayList();

    /* renamed from: rC */
    private C0917ba f1226rC;

    public C0919ca(Context context) {
        this.mContext = context;
    }

    /* renamed from: Eh */
    public List mo6418Eh() {
        return this.f1225qC;
    }

    /* renamed from: d */
    public C0917ba mo6419d(String str, boolean z) {
        C0917ba baVar = this.f1226rC;
        if (baVar == null || !TextUtils.equals(baVar.f1214lC, str)) {
            for (C0917ba baVar2 : this.f1225qC) {
                if (TextUtils.equals(baVar2.f1214lC, str)) {
                    return baVar2;
                }
            }
            return null;
        } else if (z) {
            return null;
        } else {
            return this.f1226rC;
        }
    }

    public boolean hasData() {
        return !this.f1225qC.isEmpty() || this.f1226rC != null;
    }

    /* renamed from: j */
    public void mo6421j(List list) {
        this.f1225qC.clear();
        this.f1226rC = null;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ParticipantData participantData = (ParticipantData) it.next();
            C0917ba a = C0917ba.m1908a(participantData, this.mContext);
            if (!participantData.mo6361yh()) {
                this.f1225qC.add(a);
            } else {
                this.f1226rC = a;
            }
        }
    }
}
