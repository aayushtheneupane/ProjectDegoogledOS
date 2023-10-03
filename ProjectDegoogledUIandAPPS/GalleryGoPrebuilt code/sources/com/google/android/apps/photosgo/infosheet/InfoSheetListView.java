package com.google.android.apps.photosgo.infosheet;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;

/* compiled from: PG */
public final class InfoSheetListView extends ctm implements hbf {

    /* renamed from: a */
    private csy f4867a;

    @Deprecated
    public InfoSheetListView(Context context) {
        super(context);
        m4800c();
    }

    public InfoSheetListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public InfoSheetListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public InfoSheetListView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public InfoSheetListView(hbl hbl) {
        super(hbl);
        m4800c();
    }

    /* renamed from: c */
    private final void m4800c() {
        if (this.f4867a == null) {
            try {
                this.f4867a = ((csz) mo2453b()).mo2515m();
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
        m4800c();
    }

    /* renamed from: a */
    public final csy mo2635n() {
        csy csy = this.f4867a;
        if (csy != null) {
            return csy;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
