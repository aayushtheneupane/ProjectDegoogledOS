package com.google.android.apps.photosgo.foldermanagement.creation;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* compiled from: PG */
public final class SingleVolumeChooserView extends cme implements hbf {

    /* renamed from: a */
    private clz f4855a;

    @Deprecated
    public SingleVolumeChooserView(Context context) {
        super(context);
        m4786d();
    }

    public SingleVolumeChooserView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SingleVolumeChooserView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SingleVolumeChooserView(hbl hbl) {
        super(hbl);
        m4786d();
    }

    /* renamed from: d */
    private final void m4786d() {
        if (this.f4855a == null) {
            try {
                this.f4855a = ((cma) mo2453b()).mo2514l();
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
        m4786d();
    }

    /* renamed from: c */
    public final clz mo2635n() {
        clz clz = this.f4855a;
        if (clz != null) {
            return clz;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
