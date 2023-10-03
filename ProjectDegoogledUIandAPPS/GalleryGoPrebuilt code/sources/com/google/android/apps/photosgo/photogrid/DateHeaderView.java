package com.google.android.apps.photosgo.photogrid;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;

/* compiled from: PG */
public final class DateHeaderView extends dxz implements hbf {

    /* renamed from: b */
    private dvt f4902b;

    @Deprecated
    public DateHeaderView(Context context) {
        super(context);
        m4850c();
    }

    public DateHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DateHeaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DateHeaderView(hbl hbl) {
        super(hbl);
        m4850c();
    }

    /* renamed from: c */
    private final void m4850c() {
        if (this.f4902b == null) {
            try {
                this.f4902b = ((dvu) mo2453b()).mo2527y();
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
        m4850c();
    }

    /* renamed from: a */
    public final dvt mo2635n() {
        dvt dvt = this.f4902b;
        if (dvt != null) {
            return dvt;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
