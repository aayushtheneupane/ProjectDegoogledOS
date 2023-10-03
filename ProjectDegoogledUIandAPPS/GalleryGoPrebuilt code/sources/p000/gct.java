package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* renamed from: gct */
/* compiled from: PG */
public class gct extends abj {

    /* renamed from: a */
    private gcu f10968a;

    /* renamed from: b */
    private int f10969b = 0;

    public gct() {
    }

    /* renamed from: c */
    public final int mo6403c() {
        gcu gcu = this.f10968a;
        if (gcu == null) {
            return 0;
        }
        return gcu.f10973d;
    }

    public gct(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo6401c(CoordinatorLayout coordinatorLayout, View view, int i) {
        coordinatorLayout.mo1123b(view, i);
    }

    /* renamed from: a */
    public boolean mo88a(CoordinatorLayout coordinatorLayout, View view, int i) {
        mo6401c(coordinatorLayout, view, i);
        if (this.f10968a == null) {
            this.f10968a = new gcu(view);
        }
        gcu gcu = this.f10968a;
        gcu.f10971b = gcu.f10970a.getTop();
        gcu.f10972c = gcu.f10970a.getLeft();
        this.f10968a.mo6405a();
        int i2 = this.f10969b;
        if (i2 == 0) {
            return true;
        }
        this.f10968a.mo6406a(i2);
        this.f10969b = 0;
        return true;
    }

    /* renamed from: c */
    public final boolean mo6404c(int i) {
        gcu gcu = this.f10968a;
        if (gcu != null) {
            return gcu.mo6406a(i);
        }
        this.f10969b = i;
        return false;
    }
}
