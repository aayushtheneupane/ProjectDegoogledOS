package com.android.messaging.datamodel.data;

import android.text.TextUtils;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.sms.C1024t;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1474sa;
import com.android.messaging.util.C1478ua;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.data.w */
public class C0940w extends C1478ua {

    /* renamed from: Gd */
    private final boolean f1338Gd;

    /* renamed from: Hd */
    private final int f1339Hd;

    /* renamed from: Id */
    private final List f1340Id;

    /* renamed from: Jd */
    private int f1341Jd = 0;

    /* renamed from: gc */
    private final String f1342gc;
    private final C0941x mCallback;
    final /* synthetic */ C0889A this$0;

    public C0940w(C0889A a, boolean z, int i, C0941x xVar, C0783c cVar) {
        this.this$0 = a;
        this.f1338Gd = z;
        this.f1339Hd = i;
        this.mCallback = xVar;
        this.f1342gc = cVar.getBindingId();
        this.f1340Id = new ArrayList(a.f1141Kz);
        C0940w unused = a.f1146Pz = this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Object mo6323a(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        int i = this.f1341Jd;
        if (i != 0) {
            return Integer.valueOf(i);
        }
        if (this.f1338Gd) {
            C1424b.m3584Gj();
            int size = this.f1340Id.size();
            int unused = this.this$0.m1685io();
            boolean z = true;
            if (size <= 10) {
                long j = 0;
                for (MessagePartData bh : this.f1340Id) {
                    j += bh.mo6296bh();
                }
                if (j <= ((long) C1024t.get(this.f1339Hd).getMaxMessageSize())) {
                    z = false;
                }
            }
            if (z) {
                return 3;
            }
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        C0940w unused = this.this$0.f1146Pz = null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        Integer num = (Integer) obj;
        C0940w unused = this.this$0.f1146Pz = null;
        if (!this.this$0.mo5926W(this.f1342gc) || isCancelled()) {
            if (!this.this$0.mo5926W(this.f1342gc)) {
                C1430e.m3630w("MessagingApp", "Message can't be sent: draft not bound");
            }
            if (isCancelled()) {
                C1430e.m3630w("MessagingApp", "Message can't be sent: draft is cancelled");
                return;
            }
            return;
        }
        this.mCallback.mo6581b(this.this$0, num.intValue());
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        if (this.this$0.mo6202nf()) {
            this.f1341Jd = 1;
            return;
        }
        if (this.this$0.mo6188ef()) {
            try {
                if (TextUtils.isEmpty(C1474sa.get(this.f1339Hd).mo8218ma(true))) {
                    this.f1341Jd = 2;
                    return;
                }
            } catch (IllegalStateException unused) {
                this.f1341Jd = 5;
                return;
            }
        }
        if (C0889A.m1679d(this.this$0) > 1) {
            this.f1341Jd = 4;
        }
    }
}
