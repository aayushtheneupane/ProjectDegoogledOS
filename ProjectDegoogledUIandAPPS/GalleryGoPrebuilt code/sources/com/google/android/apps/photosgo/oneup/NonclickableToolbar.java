package com.google.android.apps.photosgo.oneup;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/* compiled from: PG */
public final class NonclickableToolbar extends dom implements hbf {

    /* renamed from: w */
    private dvg f4876w;

    @Deprecated
    public NonclickableToolbar(Context context) {
        super(context);
        m4814k();
    }

    public NonclickableToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NonclickableToolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public NonclickableToolbar(hbl hbl) {
        super(hbl);
        m4814k();
    }

    /* renamed from: k */
    private final void m4814k() {
        if (this.f4876w == null) {
            try {
                this.f4876w = ((dlz) mo2453b()).mo2502al();
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
        return m930j();
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return m920a(layoutParams);
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        m4814k();
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        m4814k();
        return false;
    }

    /* renamed from: n */
    public final /* bridge */ /* synthetic */ Object mo2635n() {
        dvg dvg = this.f4876w;
        if (dvg != null) {
            return dvg;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
