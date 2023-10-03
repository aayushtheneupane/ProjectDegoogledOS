package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.oneup.photo.OneUpPhotoView;

/* renamed from: drm */
/* compiled from: PG */
public class drm extends FrameLayout implements ioe {

    /* renamed from: a */
    private ftv f7235a;

    public drm(Context context) {
        super(context);
        mo3399a();
    }

    public drm(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3399a();
    }

    public drm(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3399a();
    }

    public drm(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo3399a();
    }

    /* renamed from: a */
    private final void mo3399a() {
        OneUpPhotoView oneUpPhotoView = (OneUpPhotoView) this;
        ((drj) mo2453b()).mo2484U();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f7235a == null) {
            this.f7235a = new ftv(this);
        }
        return this.f7235a.mo2453b();
    }
}
