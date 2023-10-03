package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.peoplegrid.PeopleGridView;

/* renamed from: dvc */
/* compiled from: PG */
public class dvc extends FrameLayout implements ioe {

    /* renamed from: a */
    private ftv f7447a;

    public dvc(Context context) {
        super(context);
        mo3422a();
    }

    public dvc(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3422a();
    }

    public dvc(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3422a();
    }

    public dvc(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo3422a();
    }

    /* renamed from: a */
    private final void mo3422a() {
        PeopleGridView peopleGridView = (PeopleGridView) this;
        ((duy) mo2453b()).mo2487X();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f7447a == null) {
            this.f7447a = new ftv(this);
        }
        return this.f7447a.mo2453b();
    }
}
