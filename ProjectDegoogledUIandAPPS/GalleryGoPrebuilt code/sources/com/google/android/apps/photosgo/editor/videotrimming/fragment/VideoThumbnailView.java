package com.google.android.apps.photosgo.editor.videotrimming.fragment;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* compiled from: PG */
public final class VideoThumbnailView extends cch implements hbf {

    /* renamed from: a */
    private cdn f4849a;

    @Deprecated
    public VideoThumbnailView(Context context) {
        super(context);
        m4779c();
    }

    public VideoThumbnailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VideoThumbnailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public VideoThumbnailView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public VideoThumbnailView(hbl hbl) {
        super(hbl);
        m4779c();
    }

    /* renamed from: c */
    private final void m4779c() {
        if (this.f4849a == null) {
            try {
                this.f4849a = ((cdo) mo2453b()).mo2512j();
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
        m4779c();
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        m4779c();
        cdn cdn = this.f4849a;
        hlp a = cdn.f4123c.mo7577a("VideoThumbnailsOnLayout");
        try {
            int width = cdn.f4122b.getWidth();
            int i5 = (width / cdn.f4124d) + 1;
            int i6 = cdn.f4128h;
            cdn.f4128h = i5;
            if (i6 != i5) {
                cdm cdm = cdn.f4129i;
                if (cdm != null) {
                    ccy ccy = ((ccz) cdm).f4090a;
                    gus gus = ((ccz) cdm).f4091b;
                    ccy.f4083g = i5;
                    gus.mo7099b(ife.m12820a((Object) null), ccy.f4081e);
                }
            }
            new Object[1][0] = Integer.valueOf(width);
            new Object[1][0] = Integer.valueOf(cdn.f4124d);
            new Object[1][0] = Integer.valueOf(i5);
            int a2 = cdn.mo3050a();
            int i7 = cdn.f4128h;
            if (i7 > a2) {
                int i8 = i7 - a2;
                for (int i9 = 0; i9 < i8; i9++) {
                    C0533tn tnVar = new C0533tn(cdn.f4121a);
                    tnVar.setBackgroundColor(cdn.f4126f);
                    int i10 = cdn.f4124d;
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i10, i10);
                    int i11 = cdn.f4125e;
                    layoutParams.setMargins(i11, 0, i11, 0);
                    cdn.f4122b.addViewInLayout(tnVar, -1, layoutParams, true);
                }
            } else if (i7 < a2) {
                cdn.f4122b.removeViewsInLayout(i7, a2 - i7);
            }
            if (cdn.f4128h != a2 && cdn.f4127g.size() == cdn.f4128h) {
                cdn.mo3051b();
            }
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final cdn mo2635n() {
        cdn cdn = this.f4849a;
        if (cdn != null) {
            return cdn;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
