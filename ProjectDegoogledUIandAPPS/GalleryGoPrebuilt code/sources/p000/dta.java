package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.oneup.video.VideoControlsView;

/* renamed from: dta */
/* compiled from: PG */
public class dta extends FrameLayout implements ioe {

    /* renamed from: a */
    private ftv f7311a;

    public dta(Context context) {
        super(context);
        mo3418a();
    }

    public dta(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3418a();
    }

    public dta(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3418a();
    }

    public dta(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo3418a();
    }

    /* renamed from: a */
    private final void mo3418a() {
        VideoControlsView videoControlsView = (VideoControlsView) this;
        ((dtt) mo2453b()).mo2498ah();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f7311a == null) {
            this.f7311a = new ftv(this);
        }
        return this.f7311a.mo2453b();
    }
}
