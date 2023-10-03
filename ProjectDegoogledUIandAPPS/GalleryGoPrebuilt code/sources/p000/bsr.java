package p000;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.devicefolders.FolderView;

/* renamed from: bsr */
/* compiled from: PG */
public class bsr extends ConstraintLayout implements ioe {

    /* renamed from: a */
    private ftv f3498a;

    public bsr(Context context) {
        super(context);
        mo3340c();
    }

    public bsr(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3340c();
    }

    public bsr(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3340c();
    }

    /* renamed from: c */
    private final void mo3340c() {
        FolderView folderView = (FolderView) this;
        ((bsa) mo2453b()).mo2474K();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f3498a == null) {
            this.f3498a = new ftv(this);
        }
        return this.f3498a.mo2453b();
    }
}
