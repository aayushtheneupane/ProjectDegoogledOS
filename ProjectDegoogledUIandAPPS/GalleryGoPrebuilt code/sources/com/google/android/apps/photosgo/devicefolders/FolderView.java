package com.google.android.apps.photosgo.devicefolders;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* compiled from: PG */
public final class FolderView extends bsr implements hbf {

    /* renamed from: a */
    private bry f4814a;

    @Deprecated
    public FolderView(Context context) {
        super(context);
        m4746d();
    }

    public FolderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FolderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public FolderView(hbl hbl) {
        super(hbl);
        m4746d();
    }

    /* renamed from: d */
    private final void m4746d() {
        if (this.f4814a == null) {
            try {
                this.f4814a = ((brz) mo2453b()).mo2507e();
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
        m4746d();
    }

    /* renamed from: c */
    public final bry mo2635n() {
        bry bry = this.f4814a;
        if (bry != null) {
            return bry;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
