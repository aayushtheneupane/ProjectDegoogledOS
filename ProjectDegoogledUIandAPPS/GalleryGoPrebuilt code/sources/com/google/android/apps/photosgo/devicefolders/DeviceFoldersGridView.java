package com.google.android.apps.photosgo.devicefolders;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;

/* compiled from: PG */
public final class DeviceFoldersGridView extends bsq implements hbf {

    /* renamed from: a */
    private bru f4813a;

    @Deprecated
    public DeviceFoldersGridView(Context context) {
        super(context);
        m4743c();
    }

    public DeviceFoldersGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DeviceFoldersGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DeviceFoldersGridView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public DeviceFoldersGridView(hbl hbl) {
        super(hbl);
        m4743c();
    }

    /* renamed from: c */
    private final void m4743c() {
        if (this.f4813a == null) {
            try {
                this.f4813a = ((brv) mo2453b()).mo2506d();
                Context context = getContext();
                while ((context instanceof ContextWrapper) && !(context instanceof ioe) && !(context instanceof fts) && !(context instanceof hcf)) {
                    context = ((ContextWrapper) context).getBaseContext();
                }
                if (!(context instanceof hbs)) {
                    String cls = getClass().toString();
                    StringBuilder sb = new StringBuilder(String.valueOf(cls).length() + 57);
                    sb.append("TikTok View ");
                    sb.append(cls);
                    sb.append(", cannot be attached to a non-TikTok Fragment");
                    throw new IllegalStateException(sb.toString());
                }
            } catch (ClassCastException e) {
                throw new IllegalStateException("Missing entry point. If you're in a test with explicit entry points specified in your @TestRoot, check that you're not missing the one for this class.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        m4743c();
    }

    /* renamed from: a */
    public final bru mo2635n() {
        bru bru = this.f4813a;
        if (bru != null) {
            return bru;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
