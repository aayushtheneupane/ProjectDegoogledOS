package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.devicefolders.DeviceFoldersGridView;

/* renamed from: bsq */
/* compiled from: PG */
public class bsq extends FrameLayout implements ioe {

    /* renamed from: a */
    private ftv f3497a;

    public bsq(Context context) {
        super(context);
        mo3338a();
    }

    public bsq(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3338a();
    }

    public bsq(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3338a();
    }

    public bsq(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo3338a();
    }

    /* renamed from: a */
    private final void mo3338a() {
        DeviceFoldersGridView deviceFoldersGridView = (DeviceFoldersGridView) this;
        ((brw) mo2453b()).mo2473J();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f3497a == null) {
            this.f3497a = new ftv(this);
        }
        return this.f3497a.mo2453b();
    }
}
