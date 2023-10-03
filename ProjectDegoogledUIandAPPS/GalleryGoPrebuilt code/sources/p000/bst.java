package p000;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.devicefolders.NewFolderView;

/* renamed from: bst */
/* compiled from: PG */
public class bst extends ConstraintLayout implements ioe {

    /* renamed from: a */
    private ftv f3500a;

    public bst(Context context) {
        super(context);
        m3536c();
    }

    public bst(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m3536c();
    }

    public bst(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m3536c();
    }

    /* renamed from: c */
    private final void m3536c() {
        NewFolderView newFolderView = (NewFolderView) this;
        ((bsj) mo2453b()).mo2480Q();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f3500a == null) {
            this.f3500a = new ftv(this);
        }
        return this.f3500a.mo2453b();
    }
}
