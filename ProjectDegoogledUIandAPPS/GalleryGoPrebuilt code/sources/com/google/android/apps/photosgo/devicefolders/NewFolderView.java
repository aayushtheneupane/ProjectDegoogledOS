package com.google.android.apps.photosgo.devicefolders;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* compiled from: PG */
public final class NewFolderView extends bst implements hbf {

    /* renamed from: a */
    private gbz f4815a;

    @Deprecated
    public NewFolderView(Context context) {
        super(context);
        m4749c();
    }

    public NewFolderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NewFolderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public NewFolderView(hbl hbl) {
        super(hbl);
        m4749c();
    }

    /* renamed from: c */
    private final void m4749c() {
        if (this.f4815a == null) {
            try {
                this.f4815a = ((bsi) mo2453b()).mo2503am();
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
        m4749c();
    }

    /* renamed from: n */
    public final /* bridge */ /* synthetic */ Object mo2635n() {
        gbz gbz = this.f4815a;
        if (gbz != null) {
            return gbz;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
