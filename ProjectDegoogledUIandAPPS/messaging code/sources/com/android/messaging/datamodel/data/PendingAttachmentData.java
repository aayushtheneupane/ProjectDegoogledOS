package com.android.messaging.datamodel.data;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1481w;

public class PendingAttachmentData extends MessagePartData {
    public static final Parcelable.Creator CREATOR = new C0904P();
    /* access modifiers changed from: private */

    /* renamed from: Ps */
    public int f1205Ps;

    protected PendingAttachmentData(String str, String str2, Uri uri, int i, int i2, boolean z) {
        super(str, str2, uri, i, i2, z);
        this.f1205Ps = 0;
    }

    public int getCurrentState() {
        return this.f1205Ps;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f1205Ps);
    }

    /* renamed from: a */
    public static PendingAttachmentData m1870a(String str, Uri uri) {
        C1424b.m3592ia(C1481w.m3827Ba(str));
        return new PendingAttachmentData((String) null, str, uri, -1, -1, false);
    }

    protected PendingAttachmentData(Parcel parcel) {
        super(parcel);
        this.f1205Ps = parcel.readInt();
    }

    /* renamed from: a */
    public void mo6363a(C0889A a, String str) {
        if (this.f1205Ps == 0) {
            this.f1205Ps = 1;
            new C0903O(this, 60000, true, a, str).mo8233b(new Void[0]);
        }
    }
}
