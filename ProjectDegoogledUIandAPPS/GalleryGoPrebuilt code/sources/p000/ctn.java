package p000;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.infosheet.InfoView;

/* renamed from: ctn */
/* compiled from: PG */
public class ctn extends ConstraintLayout implements ioe {

    /* renamed from: a */
    private ftv f5637a;

    public ctn(Context context) {
        super(context);
        mo3390c();
    }

    public ctn(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3390c();
    }

    public ctn(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3390c();
    }

    /* renamed from: c */
    private final void mo3390c() {
        InfoView infoView = (InfoView) this;
        ((ctd) mo2453b()).mo2478O();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f5637a == null) {
            this.f5637a = new ftv(this);
        }
        return this.f5637a.mo2453b();
    }
}
