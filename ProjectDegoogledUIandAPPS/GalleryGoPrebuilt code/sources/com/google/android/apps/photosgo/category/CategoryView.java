package com.google.android.apps.photosgo.category;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;

/* compiled from: PG */
public final class CategoryView extends bos implements hbf {

    /* renamed from: a */
    private bon f4812a;

    @Deprecated
    public CategoryView(Context context) {
        super(context);
        m4740c();
    }

    public CategoryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CategoryView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CategoryView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public CategoryView(hbl hbl) {
        super(hbl);
        m4740c();
    }

    /* renamed from: c */
    private final void m4740c() {
        if (this.f4812a == null) {
            try {
                this.f4812a = ((boo) mo2453b()).mo2505c();
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
        m4740c();
    }

    /* renamed from: a */
    public final bon mo2635n() {
        bon bon = this.f4812a;
        if (bon != null) {
            return bon;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
