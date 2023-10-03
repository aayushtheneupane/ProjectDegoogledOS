package com.android.messaging.datamodel.data;

import android.net.Uri;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1478ua;
import com.android.messaging.util.C1488za;

/* renamed from: com.android.messaging.datamodel.data.O */
class C0903O extends C1478ua {

    /* renamed from: Kd */
    final /* synthetic */ C0889A f1189Kd;

    /* renamed from: Ld */
    final /* synthetic */ String f1190Ld;
    final /* synthetic */ PendingAttachmentData this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0903O(PendingAttachmentData pendingAttachmentData, long j, boolean z, C0889A a, String str) {
        super(j, z);
        this.this$0 = pendingAttachmentData;
        this.f1189Kd = a;
        this.f1190Ld = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Object mo6323a(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        Uri D = C1488za.m3864D(this.this$0.getContentUri());
        if (D != null) {
            return MessagePartData.m1807a(this.this$0.getText(), this.this$0.getContentType(), D, this.this$0.getWidth(), this.this$0.getHeight());
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        C1430e.m3630w("MessagingApp", "Timeout while retrieving media");
        int unused = this.this$0.f1205Ps = 3;
        if (this.f1189Kd.mo5926W(this.f1190Ld)) {
            this.f1189Kd.mo6187e(this.this$0);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        MessagePartData messagePartData = (MessagePartData) obj;
        if (messagePartData != null) {
            int unused = this.this$0.f1205Ps = 2;
            if (this.f1189Kd.mo5926W(this.f1190Ld)) {
                this.f1189Kd.mo6174a(messagePartData, this.this$0);
            } else {
                messagePartData.mo6294_g();
            }
        } else {
            int unused2 = this.this$0.f1205Ps = 3;
            if (this.f1189Kd.mo5926W(this.f1190Ld)) {
                this.f1189Kd.mo6185d(this.this$0);
                this.f1189Kd.mo6187e(this.this$0);
            }
        }
    }
}
