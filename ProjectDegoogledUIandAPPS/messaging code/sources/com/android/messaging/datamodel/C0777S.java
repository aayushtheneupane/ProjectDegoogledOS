package com.android.messaging.datamodel;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import com.android.messaging.datamodel.action.SyncMessagesAction;
import com.android.messaging.util.C1430e;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.S */
class C0777S extends ContentObserver {
    final /* synthetic */ C0779U this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0777S(C0779U u) {
        super((Handler) null);
        this.this$0 = u;
    }

    public void onChange(boolean z) {
        if (Log.isLoggable("MessagingApp", 2)) {
            StringBuilder Pa = C0632a.m1011Pa("SyncManager: Sms/Mms DB changed @");
            Pa.append(System.currentTimeMillis());
            Pa.append(" for ");
            Pa.append("<unk>");
            Pa.append(" ");
            Pa.append(this.this$0.f1046Ly);
            Pa.append("/");
            Pa.append(this.this$0.f1047My);
            C1430e.m3628v("MessagingApp", Pa.toString());
        }
        if (this.this$0.f1046Ly) {
            SyncMessagesAction.m1448Ke();
        }
        boolean unused = this.this$0.f1047My;
    }

    public void onChange(boolean z, Uri uri) {
        String str;
        if (Log.isLoggable("MessagingApp", 2)) {
            StringBuilder Pa = C0632a.m1011Pa("SyncManager: Sms/Mms DB changed @");
            Pa.append(System.currentTimeMillis());
            Pa.append(" for ");
            if (uri == null) {
                str = "<unk>";
            } else {
                str = uri.toString();
            }
            Pa.append(str);
            Pa.append(" ");
            Pa.append(this.this$0.f1046Ly);
            Pa.append("/");
            Pa.append(this.this$0.f1047My);
            C1430e.m3628v("MessagingApp", Pa.toString());
        }
        if (this.this$0.f1046Ly) {
            SyncMessagesAction.m1448Ke();
        }
        boolean unused = this.this$0.f1047My;
    }
}
