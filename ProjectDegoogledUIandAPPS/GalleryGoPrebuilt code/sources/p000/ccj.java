package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.editor.videotrimming.fragment.VideoTrimView;

/* renamed from: ccj */
/* compiled from: PG */
public class ccj extends FrameLayout implements ioe {

    /* renamed from: a */
    private ftv f4056a;

    public ccj(Context context) {
        super(context);
        mo3367a();
    }

    public ccj(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3367a();
    }

    public ccj(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3367a();
    }

    public ccj(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo3367a();
    }

    /* renamed from: a */
    private final void mo3367a() {
        VideoTrimView videoTrimView = (VideoTrimView) this;
        ((ceh) mo2453b()).mo2500aj();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f4056a == null) {
            this.f4056a = new ftv(this);
        }
        return this.f4056a.mo2453b();
    }
}
