package com.android.messaging.p041ui.mediapicker;

import android.net.Uri;
import android.util.Log;
import com.android.messaging.datamodel.data.PendingAttachmentData;
import com.android.messaging.util.C1416U;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1478ua;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.mediapicker.J */
class C1282J extends C1478ua {

    /* renamed from: Pd */
    final /* synthetic */ Uri f2028Pd;
    final /* synthetic */ C1284L this$0;

    C1282J(C1284L l, Uri uri) {
        this.this$0 = l;
        this.f2028Pd = uri;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Object mo6323a(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        if (!C1430e.m3627u(this.f2028Pd)) {
            return C1416U.m3564a(C0632a.m1012Pk(), this.f2028Pd);
        }
        if (Log.isLoggable("MessagingApp", 6)) {
            StringBuilder Pa = C0632a.m1011Pa("Aborting attach of private app data (");
            Pa.append(this.f2028Pd);
            Pa.append(")");
            C1430e.m3622e("MessagingApp", Pa.toString());
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        String str = (String) obj;
        if (str != null) {
            PendingAttachmentData a = PendingAttachmentData.m1870a(str, this.f2028Pd);
            C1294W w = (C1294W) this.this$0.mListener;
            if (w.this$0.f2119nF.isBound()) {
                w.this$0.f2118Dj.mo7901c(a);
            }
        }
    }
}
