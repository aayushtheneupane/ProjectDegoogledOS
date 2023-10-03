package com.android.messaging.datamodel;

import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.E */
class C0764E {

    /* renamed from: Ka */
    final String f987Ka;

    /* renamed from: Wx */
    final boolean f988Wx;

    /* renamed from: Xx */
    final String f989Xx;

    /* renamed from: Yx */
    final boolean f990Yx;

    /* renamed from: Zx */
    final long f991Zx;

    /* renamed from: _x */
    final String f992_x;

    /* renamed from: ay */
    final List f993ay = new ArrayList();

    /* renamed from: by */
    int f994by = 0;

    /* renamed from: cy */
    final Uri f995cy;

    /* renamed from: dy */
    final int f996dy;
    final Uri mAvatarUri;
    final int mSubId;

    public C0764E(String str, boolean z, String str2, boolean z2, long j, String str3, Uri uri, Uri uri2, int i, int i2) {
        this.f987Ka = str;
        this.f988Wx = z;
        this.f989Xx = str2;
        this.f990Yx = z2;
        this.f991Zx = j;
        this.f992_x = str3;
        this.mAvatarUri = uri;
        this.f995cy = uri2;
        this.mSubId = i;
        this.f996dy = i2;
    }

    /* renamed from: bo */
    private C0765F m1230bo() {
        if (this.f993ay.size() <= 0 || !(this.f993ay.get(0) instanceof C0765F)) {
            return null;
        }
        return (C0765F) this.f993ay.get(0);
    }

    /* renamed from: fe */
    public boolean mo5868fe() {
        C0765F bo = m1230bo();
        if (bo == null) {
            return false;
        }
        return bo.f1001iy;
    }

    /* renamed from: ge */
    public String mo5869ge() {
        C0765F bo = m1230bo();
        if (bo == null) {
            return null;
        }
        return bo.f1002jy;
    }
}
