package com.google.android.apps.photosgo.peoplegrid;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.util.AttributeSet;

/* compiled from: PG */
public final class PeopleGridView extends dvc implements hbf {

    /* renamed from: a */
    private duw f4901a;

    @Deprecated
    public PeopleGridView(Context context) {
        super(context);
        m4847c();
    }

    public PeopleGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PeopleGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PeopleGridView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public PeopleGridView(hbl hbl) {
        super(hbl);
        m4847c();
    }

    /* renamed from: c */
    private final void m4847c() {
        if (this.f4901a == null) {
            try {
                this.f4901a = ((dux) mo2453b()).mo2526x();
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
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m4847c();
        duw duw = this.f4901a;
        int a = duw.mo4463a();
        duw.f7433c = a;
        duw.f7431a.mo10425a(a);
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        m4847c();
    }

    /* renamed from: a */
    public final duw mo2635n() {
        duw duw = this.f4901a;
        if (duw != null) {
            return duw;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
