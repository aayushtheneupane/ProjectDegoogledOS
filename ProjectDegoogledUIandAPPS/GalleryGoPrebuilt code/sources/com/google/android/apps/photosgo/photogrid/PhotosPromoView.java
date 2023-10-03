package com.google.android.apps.photosgo.photogrid;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public final class PhotosPromoView extends dyd implements hbf {

    /* renamed from: a */
    private dxk f4904a;

    @Deprecated
    public PhotosPromoView(Context context) {
        super(context);
        m4857d();
    }

    public PhotosPromoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PhotosPromoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PhotosPromoView(hbl hbl) {
        super(hbl);
        m4857d();
    }

    /* renamed from: d */
    private final void m4857d() {
        if (this.f4904a == null) {
            try {
                this.f4904a = ((dxm) mo2453b()).mo2465B();
                hos a = hok.m11838a(getContext());
                a.f13171d = this;
                a.mo7632a(a.f13171d.findViewById(R.id.link_text), (View.OnClickListener) new dxl(this.f4904a));
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
        m4857d();
    }

    /* renamed from: c */
    public final dxk mo2635n() {
        dxk dxk = this.f4904a;
        if (dxk != null) {
            return dxk;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
