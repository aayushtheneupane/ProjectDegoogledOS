package com.google.android.apps.photosgo.category;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.util.AttributeSet;

/* compiled from: PG */
public final class CategoryListView extends boq implements hbf {

    /* renamed from: a */
    private bnu f4811a;

    @Deprecated
    public CategoryListView(Context context) {
        super(context);
        m4736c();
    }

    public CategoryListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CategoryListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CategoryListView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public CategoryListView(hbl hbl) {
        super(hbl);
        m4736c();
    }

    /* renamed from: c */
    private final void m4736c() {
        if (this.f4811a == null) {
            try {
                this.f4811a = ((bnv) mo2453b()).mo2504b();
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

    /* renamed from: d */
    private final bnu m4737d() {
        m4736c();
        return this.f4811a;
    }

    /* access modifiers changed from: protected */
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        bnu d = m4737d();
        d.f3229c.setLayoutManager(new bnr(d.f3227a));
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        m4736c();
    }

    /* access modifiers changed from: protected */
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        bnu d = m4737d();
        if (d.f3229c.canScrollHorizontally(1) || d.f3229c.canScrollHorizontally(-1)) {
            d.f3229c.setOverScrollMode(0);
        } else {
            d.f3229c.setOverScrollMode(2);
        }
    }

    /* renamed from: a */
    public final bnu mo2635n() {
        bnu bnu = this.f4811a;
        if (bnu != null) {
            return bnu;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
