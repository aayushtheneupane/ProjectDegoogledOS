package com.android.messaging.p041ui.conversationlist;

import android.content.Intent;
import android.net.Uri;
import com.android.messaging.datamodel.data.C0909V;
import com.android.messaging.util.C1474sa;

/* renamed from: com.android.messaging.ui.conversationlist.x */
class C1235x extends C0909V {
    final /* synthetic */ C1236y this$1;

    C1235x(C1236y yVar) {
        this.this$1 = yVar;
    }

    /* renamed from: Mb */
    public Intent mo6127Mb() {
        return null;
    }

    public long getContactId() {
        return -1;
    }

    public String getDetails() {
        String name = this.this$1.mData.getName();
        String Ia = C1474sa.getDefault().mo8220Ia(this.this$1.mData.mo6504Tf());
        if (Ia == null || Ia.equals(name)) {
            return null;
        }
        return Ia;
    }

    public String getDisplayName() {
        return this.this$1.mData.getName();
    }

    /* renamed from: m */
    public String mo6131m() {
        return null;
    }

    /* renamed from: rf */
    public Uri mo6132rf() {
        if (this.this$1.mData.getIcon() == null) {
            return null;
        }
        return Uri.parse(this.this$1.mData.getIcon());
    }

    /* renamed from: sf */
    public String mo6133sf() {
        return null;
    }
}
