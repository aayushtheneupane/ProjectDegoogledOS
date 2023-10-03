package com.google.android.apps.photosgo.sharing;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* compiled from: PG */
public final class SingleAppView extends eds implements hbf {

    /* renamed from: a */
    private edn f4922a;

    @Deprecated
    public SingleAppView(Context context) {
        super(context);
        m4884d();
    }

    public SingleAppView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SingleAppView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SingleAppView(hbl hbl) {
        super(hbl);
        m4884d();
    }

    /* renamed from: d */
    private final void m4884d() {
        if (this.f4922a == null) {
            try {
                this.f4922a = ((edo) mo2453b()).mo2467D();
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
        m4884d();
    }

    /* renamed from: c */
    public final edn mo2635n() {
        edn edn = this.f4922a;
        if (edn != null) {
            return edn;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
