package p000;

import android.content.Context;
import android.support.p002v7.widget.Toolbar;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.oneup.NonclickableToolbar;

/* renamed from: dom */
/* compiled from: PG */
public class dom extends Toolbar implements ioe {

    /* renamed from: w */
    private ftv f6944w;

    public dom(Context context) {
        super(context);
        m6408k();
    }

    public dom(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m6408k();
    }

    public dom(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m6408k();
    }

    /* renamed from: k */
    private final void m6408k() {
        NonclickableToolbar nonclickableToolbar = (NonclickableToolbar) this;
        ((dma) mo2453b()).mo2481R();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f6944w == null) {
            this.f6944w = new ftv(this);
        }
        return this.f6944w.mo2453b();
    }
}
