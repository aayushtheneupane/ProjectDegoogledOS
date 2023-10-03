package com.google.android.apps.photosgo.editor.presets;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public final class PresetItemView extends cbt implements hbf {

    /* renamed from: a */
    private cbi f4838a;

    @Deprecated
    public PresetItemView(Context context) {
        super(context);
        m4773c();
    }

    public PresetItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PresetItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PresetItemView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public PresetItemView(hbl hbl) {
        super(hbl);
        m4773c();
    }

    /* renamed from: c */
    private final void m4773c() {
        if (this.f4838a == null) {
            try {
                this.f4838a = ((cbk) mo2453b()).mo2510h();
                hos a = hok.m11838a(getContext());
                a.f13171d = this;
                a.mo7632a(a.f13171d.findViewById(R.id.preset_item), (View.OnClickListener) new cbj(this.f4838a));
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
    public final void onFinishInflate() {
        super.onFinishInflate();
        m4773c();
    }

    /* renamed from: a */
    public final cbi mo2635n() {
        cbi cbi = this.f4838a;
        if (cbi != null) {
            return cbi;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
