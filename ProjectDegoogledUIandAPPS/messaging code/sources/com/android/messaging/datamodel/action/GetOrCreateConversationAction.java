package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.data.C0892D;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1430e;
import java.util.ArrayList;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

public class GetOrCreateConversationAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0825o();

    private GetOrCreateConversationAction(ArrayList arrayList, String str) {
        super(str);
        this.f1057Oy.putParcelableArrayList("participants_list", arrayList);
    }

    /* renamed from: a */
    public static C0827q m1370a(ArrayList arrayList, Object obj, C0826p pVar) {
        C0827q qVar = new C0827q(obj, pVar);
        new GetOrCreateConversationAction(arrayList, qVar.mo6033Ne()).mo5947a((C0813c) qVar);
        return qVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        C0955p database = C0947h.get().getDatabase();
        ArrayList parcelableArrayList = this.f1057Oy.getParcelableArrayList("participants_list");
        C0887c.m1672h(parcelableArrayList);
        ArrayList g = C0887c.m1669g(parcelableArrayList);
        long a = C1029y.m2417a(C0967f.get().getApplicationContext(), (List) g);
        if (a >= 0) {
            return C0887c.m1637a(database, a, false, parcelableArrayList);
        }
        StringBuilder Pa = C0632a.m1011Pa("Couldn't create a threadId in SMS db for numbers : ");
        Pa.append(C1430e.m3633xa(g.toString()));
        C1430e.m3630w("MessagingApp", Pa.toString());
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ GetOrCreateConversationAction(Parcel parcel, C0825o oVar) {
        super(parcel);
    }

    /* renamed from: a */
    public static C0827q m1371a(String[] strArr, Object obj, C0892D d) {
        ArrayList arrayList = new ArrayList();
        for (String trim : strArr) {
            String trim2 = trim.trim();
            if (!TextUtils.isEmpty(trim2)) {
                arrayList.add(ParticipantData.m1837ia(trim2));
            } else {
                C1430e.m3630w("MessagingApp", "getOrCreateConversation hit empty recipient");
            }
        }
        return m1370a(arrayList, obj, (C0826p) d);
    }
}
