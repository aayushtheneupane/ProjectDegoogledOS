package com.google.android.apps.photosgo.oneup.photo;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;

/* compiled from: PG */
public final class OneUpPhotoView extends drm implements hbf {

    /* renamed from: a */
    private drg f4882a;

    @Deprecated
    public OneUpPhotoView(Context context) {
        super(context);
        m4820c();
    }

    public OneUpPhotoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public OneUpPhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public OneUpPhotoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public OneUpPhotoView(hbl hbl) {
        super(hbl);
        m4820c();
    }

    /* renamed from: c */
    private final void m4820c() {
        if (this.f4882a == null) {
            try {
                this.f4882a = ((dri) mo2453b()).mo2520r();
                hok.m11838a(getContext()).f13171d = this;
                ihg.m13036a((View) this, drr.class, (hol) new drh(this.f4882a));
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
        m4820c();
        drg drg = this.f4882a;
        if (drg.f7219k.mo3175a() && drg.f7214f.isPresent() && flw.m9194a((cxi) drg.f7214f.get())) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) drg.f7220l.getLayoutParams();
            marginLayoutParams.bottomMargin = windowInsets.getSystemWindowInsetBottom() + drg.f7221m;
            marginLayoutParams.leftMargin = windowInsets.getSystemWindowInsetLeft();
            marginLayoutParams.rightMargin = windowInsets.getSystemWindowInsetRight();
        }
        return windowInsets;
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        m4820c();
    }

    /* renamed from: a */
    public final drg mo2635n() {
        drg drg = this.f4882a;
        if (drg != null) {
            return drg;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
