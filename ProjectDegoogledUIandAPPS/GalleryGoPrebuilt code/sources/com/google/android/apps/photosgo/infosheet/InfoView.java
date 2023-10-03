package com.google.android.apps.photosgo.infosheet;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* compiled from: PG */
public final class InfoView extends ctn implements hbf {

    /* renamed from: a */
    private ctb f4868a;

    @Deprecated
    public InfoView(Context context) {
        super(context);
        m4803d();
    }

    public InfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public InfoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public InfoView(hbl hbl) {
        super(hbl);
        m4803d();
    }

    /* renamed from: d */
    private final void m4803d() {
        if (this.f4868a == null) {
            try {
                this.f4868a = ((ctc) mo2453b()).mo2516n();
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
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return m802a();
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        m4803d();
    }

    /* renamed from: c */
    public final ctb mo2635n() {
        ctb ctb = this.f4868a;
        if (ctb != null) {
            return ctb;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
