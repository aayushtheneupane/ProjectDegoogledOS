package com.google.android.apps.photosgo.oneup;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;

/* compiled from: PG */
public final class OneUpPagerView extends doo implements hbf {

    /* renamed from: g */
    private dog f4881g;

    @Deprecated
    public OneUpPagerView(Context context) {
        super(context);
        m4817c();
    }

    public OneUpPagerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public OneUpPagerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public OneUpPagerView(hbl hbl) {
        super(hbl);
        m4817c();
    }

    /* renamed from: c */
    private final void m4817c() {
        if (this.f4881g == null) {
            try {
                this.f4881g = ((doh) mo2453b()).mo2519q();
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
        m4817c();
    }

    /* renamed from: a */
    public final dog mo2635n() {
        dog dog = this.f4881g;
        if (dog != null) {
            return dog;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
