package com.google.android.apps.photosgo.oneup.video;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;

/* compiled from: PG */
public final class OneUpVideoView extends dsz implements hbf {

    /* renamed from: a */
    private dsw f4899a;

    @Deprecated
    public OneUpVideoView(Context context) {
        super(context);
        m4841c();
    }

    public OneUpVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public OneUpVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public OneUpVideoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public OneUpVideoView(hbl hbl) {
        super(hbl);
        m4841c();
    }

    /* renamed from: c */
    private final void m4841c() {
        if (this.f4899a == null) {
            try {
                this.f4899a = ((dsx) mo2453b()).mo2523u();
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
        m4841c();
    }

    /* renamed from: a */
    public final dsw mo2635n() {
        dsw dsw = this.f4899a;
        if (dsw != null) {
            return dsw;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
