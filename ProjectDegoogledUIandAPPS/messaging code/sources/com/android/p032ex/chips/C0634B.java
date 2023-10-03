package com.android.p032ex.chips;

import android.content.res.Resources;
import android.net.Uri;

/* renamed from: com.android.ex.chips.B */
abstract class C0634B {

    /* renamed from: jv */
    private final Uri f748jv;

    /* renamed from: kv */
    private final Uri f749kv;
    private final String[] mProjection;

    public C0634B(String[] strArr, Uri uri, Uri uri2) {
        this.mProjection = strArr;
        this.f748jv = uri;
        this.f749kv = uri2;
    }

    public Uri getContentUri() {
        return this.f749kv;
    }

    public String[] getProjection() {
        return this.mProjection;
    }

    public abstract CharSequence getTypeLabel(Resources resources, int i, CharSequence charSequence);

    /* renamed from: sd */
    public Uri mo5440sd() {
        return this.f748jv;
    }
}
