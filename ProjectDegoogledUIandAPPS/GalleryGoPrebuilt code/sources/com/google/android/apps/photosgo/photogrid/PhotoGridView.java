package com.google.android.apps.photosgo.photogrid;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.util.AttributeSet;

/* compiled from: PG */
public final class PhotoGridView extends dyc implements hbf {

    /* renamed from: a */
    private dxg f4903a;

    @Deprecated
    public PhotoGridView(Context context) {
        super(context);
        m4853c();
    }

    public PhotoGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PhotoGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PhotoGridView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public PhotoGridView(hbl hbl) {
        super(hbl);
        m4853c();
    }

    /* renamed from: c */
    private final void m4853c() {
        if (this.f4903a == null) {
            try {
                this.f4903a = ((dxh) mo2453b()).mo2464A();
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
    private final dxg m4854d() {
        m4853c();
        return this.f4903a;
    }

    /* access modifiers changed from: protected */
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        dxg d = m4854d();
        int a = d.mo4539a();
        d.f7561f = a;
        d.f7558c.mo10425a(a);
        d.f7559d.f7554a = d.f7561f;
        d.f7557b.invalidateItemDecorations();
        d.f7557b.requestLayout();
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        m4853c();
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        dxg d = m4854d();
        if (d.f7557b.canScrollVertically(1) || d.f7557b.canScrollVertically(-1)) {
            d.f7557b.setOverScrollMode(0);
        } else {
            d.f7557b.setOverScrollMode(2);
        }
    }

    /* renamed from: a */
    public final dxg mo2635n() {
        dxg dxg = this.f4903a;
        if (dxg != null) {
            return dxg;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
