package com.google.android.apps.photosgo.sharing;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;

/* compiled from: PG */
public final class SharingAppGridView extends edq implements hbf {

    /* renamed from: a */
    private ect f4921a;

    @Deprecated
    public SharingAppGridView(Context context) {
        super(context);
        m4881c();
    }

    public SharingAppGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SharingAppGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SharingAppGridView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public SharingAppGridView(hbl hbl) {
        super(hbl);
        m4881c();
    }

    /* renamed from: c */
    private final void m4881c() {
        if (this.f4921a == null) {
            try {
                this.f4921a = ((ecu) mo2453b()).mo2466C();
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
        m4881c();
    }

    /* renamed from: a */
    public final ect mo2635n() {
        ect ect = this.f4921a;
        if (ect != null) {
            return ect;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
