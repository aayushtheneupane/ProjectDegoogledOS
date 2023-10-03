package com.google.android.apps.photosgo.photogrid;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.View;
import java.util.Stack;

/* compiled from: PG */
public final class SinglePhotoView extends dye implements hbf {

    /* renamed from: a */
    public dxu f4906a;

    @Deprecated
    public SinglePhotoView(Context context) {
        super(context);
        m4861c();
    }

    public SinglePhotoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SinglePhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SinglePhotoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public SinglePhotoView(hbl hbl) {
        super(hbl);
        m4861c();
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: c */
    private final void m4861c() {
        if (this.f4906a == null) {
            try {
                dxv dxv = (dxv) mo2453b();
                dxq dxq = new dxq(this);
                ((Stack) hci.f12472a.get()).push((hch) ife.m12898e((Object) dxq));
                try {
                    dxu ak = dxv.mo2501ak();
                    this.f4906a = ak;
                    if (ak == null) {
                        hci.m11204a(dxq);
                    }
                    this.f4906a.f7586a = this;
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
                } catch (Throwable th) {
                    if (this.f4906a == null) {
                        hci.m11204a(dxq);
                    }
                    throw th;
                }
            } catch (ClassCastException e) {
                throw new IllegalStateException("Missing entry point. If you're in a test with explicit entry points specified in your @TestRoot, check that you're not missing the one for this class.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        m4861c();
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        m4861c();
        dxu dxu = this.f4906a;
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(dxu.f7596k, 1073741824));
    }

    /* renamed from: a */
    public final dxu mo2635n() {
        dxu dxu = this.f4906a;
        if (dxu != null) {
            return dxu;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
