package com.google.android.apps.photosgo.editor.presets;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.View;

/* compiled from: PG */
public final class PresetSelectionView extends cbu implements hbf {

    /* renamed from: a */
    private cbp f4839a;

    @Deprecated
    public PresetSelectionView(Context context) {
        super(context);
        m4776c();
    }

    public PresetSelectionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PresetSelectionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PresetSelectionView(hbl hbl) {
        super(hbl);
        m4776c();
    }

    /* renamed from: c */
    private final void m4776c() {
        if (this.f4839a == null) {
            try {
                this.f4839a = ((cbr) mo2453b()).mo2511i();
                hok.m11838a(getContext()).f13171d = this;
                ihg.m13036a((View) this, cba.class, (hol) new cbq(this.f4839a));
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
        m4776c();
    }

    /* renamed from: a */
    public final cbp mo2635n() {
        cbp cbp = this.f4839a;
        if (cbp != null) {
            return cbp;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
