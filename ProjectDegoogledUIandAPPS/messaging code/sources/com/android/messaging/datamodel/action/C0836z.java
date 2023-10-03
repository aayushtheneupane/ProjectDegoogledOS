package com.android.messaging.datamodel.action;

import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1465o;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.action.z */
class C0836z implements C1465o {

    /* renamed from: Yy */
    final /* synthetic */ int f1087Yy;

    C0836z(int i) {
        this.f1087Yy = i;
    }

    /* renamed from: b */
    public void mo6075b(int i) {
        if (i == 0) {
            StringBuilder Pa = C0632a.m1011Pa("ProcessPendingMessagesAction: Now connected for subId ");
            Pa.append(this.f1087Yy);
            Pa.append(", starting action");
            C1430e.m3625i("MessagingAppDataModel", Pa.toString());
            ProcessPendingMessagesAction.m1416Cb(this.f1087Yy);
            ProcessPendingMessagesAction processPendingMessagesAction = new ProcessPendingMessagesAction((C0835y) null);
            processPendingMessagesAction.f1057Oy.putInt("sub_id", this.f1087Yy);
            processPendingMessagesAction.start();
        }
    }
}
