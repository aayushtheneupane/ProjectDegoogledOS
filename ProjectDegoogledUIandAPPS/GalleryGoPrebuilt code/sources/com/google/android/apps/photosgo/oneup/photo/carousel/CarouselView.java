package com.google.android.apps.photosgo.oneup.photo.carousel;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.View;

/* compiled from: PG */
public final class CarouselView extends dsd implements hbf {

    /* renamed from: a */
    private dry f4891a;

    @Deprecated
    public CarouselView(Context context) {
        super(context);
        m4830c();
    }

    public CarouselView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CarouselView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CarouselView(hbl hbl) {
        super(hbl);
        m4830c();
    }

    /* renamed from: c */
    private final void m4830c() {
        if (this.f4891a == null) {
            try {
                this.f4891a = ((dsa) mo2453b()).mo2522t();
                hok.m11838a(getContext()).f13171d = this;
                ihg.m13036a((View) this, drr.class, (hol) new drz(this.f4891a));
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
        m4830c();
    }

    /* renamed from: a */
    public final dry mo2635n() {
        dry dry = this.f4891a;
        if (dry != null) {
            return dry;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
