package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.google.android.apps.photosgo.editor.videotrimming.fragment.VideoThumbnailView;

/* renamed from: cch */
/* compiled from: PG */
public class cch extends LinearLayout implements ioe {

    /* renamed from: a */
    private ftv f4052a;

    public cch(Context context) {
        super(context);
        mo3363a();
    }

    public cch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3363a();
    }

    public cch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3363a();
    }

    public cch(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo3363a();
    }

    /* renamed from: a */
    private final void mo3363a() {
        VideoThumbnailView videoThumbnailView = (VideoThumbnailView) this;
        ((cdp) mo2453b()).mo2499ai();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f4052a == null) {
            this.f4052a = new ftv(this);
        }
        return this.f4052a.mo2453b();
    }
}
