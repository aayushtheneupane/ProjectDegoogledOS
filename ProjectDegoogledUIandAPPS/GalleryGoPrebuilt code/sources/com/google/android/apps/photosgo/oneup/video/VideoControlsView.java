package com.google.android.apps.photosgo.oneup.video;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.WindowInsets;

/* compiled from: PG */
public final class VideoControlsView extends dta implements hbf {

    /* renamed from: a */
    private dtr f4900a;

    @Deprecated
    public VideoControlsView(Context context) {
        super(context);
        m4844c();
    }

    public VideoControlsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VideoControlsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public VideoControlsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public VideoControlsView(hbl hbl) {
        super(hbl);
        m4844c();
    }

    /* renamed from: c */
    private final void m4844c() {
        if (this.f4900a == null) {
            try {
                this.f4900a = ((dts) mo2453b()).mo2524v();
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

    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        super.onApplyWindowInsets(windowInsets);
        m4844c();
        dtr dtr = this.f4900a;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) dtr.f7360d.getLayoutParams();
        marginLayoutParams.bottomMargin = windowInsets.getSystemWindowInsetBottom() + dtr.f7362f;
        marginLayoutParams.leftMargin = windowInsets.getSystemWindowInsetLeft();
        marginLayoutParams.rightMargin = windowInsets.getSystemWindowInsetRight();
        return windowInsets;
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        m4844c();
    }

    /* renamed from: a */
    public final dtr mo2635n() {
        dtr dtr = this.f4900a;
        if (dtr != null) {
            return dtr;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
