package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.infosheet.InfoSheetListView;

/* renamed from: ctm */
/* compiled from: PG */
public class ctm extends FrameLayout implements ioe {

    /* renamed from: a */
    private ftv f5636a;

    public ctm(Context context) {
        super(context);
        mo3387a();
    }

    public ctm(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3387a();
    }

    public ctm(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3387a();
    }

    public ctm(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo3387a();
    }

    /* renamed from: a */
    private final void mo3387a() {
        InfoSheetListView infoSheetListView = (InfoSheetListView) this;
        ((cta) mo2453b()).mo2477N();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f5636a == null) {
            this.f5636a = new ftv(this);
        }
        return this.f5636a.mo2453b();
    }
}
