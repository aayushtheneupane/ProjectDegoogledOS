package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.photogrid.PhotoGridView;

/* renamed from: dyc */
/* compiled from: PG */
public class dyc extends FrameLayout implements ioe {

    /* renamed from: a */
    private ftv f7638a;

    public dyc(Context context) {
        super(context);
        mo3428a();
    }

    public dyc(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3428a();
    }

    public dyc(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3428a();
    }

    public dyc(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo3428a();
    }

    /* renamed from: a */
    private final void mo3428a() {
        PhotoGridView photoGridView = (PhotoGridView) this;
        ((dxi) mo2453b()).mo2488Y();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f7638a == null) {
            this.f7638a = new ftv(this);
        }
        return this.f7638a.mo2453b();
    }
}
