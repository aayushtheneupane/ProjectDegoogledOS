package com.google.android.apps.photosgo.oneup.photo.carousel;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;

/* compiled from: PG */
public final class CarouselItemView extends dsc implements hbf {

    /* renamed from: a */
    private drt f4890a;

    @Deprecated
    public CarouselItemView(Context context) {
        super(context);
        m4827c();
    }

    public CarouselItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CarouselItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CarouselItemView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public CarouselItemView(hbl hbl) {
        super(hbl);
        m4827c();
    }

    /* renamed from: c */
    private final void m4827c() {
        if (this.f4890a == null) {
            try {
                this.f4890a = ((dru) mo2453b()).mo2521s();
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
        m4827c();
    }

    /* renamed from: a */
    public final drt mo2635n() {
        drt drt = this.f4890a;
        if (drt != null) {
            return drt;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
