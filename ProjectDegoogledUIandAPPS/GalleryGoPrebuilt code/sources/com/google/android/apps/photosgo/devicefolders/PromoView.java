package com.google.android.apps.photosgo.devicefolders;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public final class PromoView extends bsu implements hbf {

    /* renamed from: a */
    private bsl f4816a;

    @Deprecated
    public PromoView(Context context) {
        super(context);
        m4751d();
    }

    public PromoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PromoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PromoView(hbl hbl) {
        super(hbl);
        m4751d();
    }

    /* renamed from: d */
    private final void m4751d() {
        if (this.f4816a == null) {
            try {
                this.f4816a = ((bsn) mo2453b()).mo2509g();
                hos a = hok.m11838a(getContext());
                a.f13171d = this;
                a.mo7632a(a.f13171d.findViewById(R.id.link_text), (View.OnClickListener) new bsm(this.f4816a));
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
        m4751d();
    }

    /* renamed from: c */
    public final bsl mo2635n() {
        bsl bsl = this.f4816a;
        if (bsl != null) {
            return bsl;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
