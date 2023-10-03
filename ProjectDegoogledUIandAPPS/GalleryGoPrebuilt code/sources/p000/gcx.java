package p000;

import android.view.View;
import android.view.ViewParent;
import com.google.android.material.behavior.SwipeDismissBehavior;

/* renamed from: gcx */
/* compiled from: PG */
public final class gcx extends C0379nv {

    /* renamed from: a */
    private int f10976a;

    /* renamed from: b */
    private int f10977b = -1;

    /* renamed from: c */
    private final /* synthetic */ SwipeDismissBehavior f10978c;

    public gcx(SwipeDismissBehavior swipeDismissBehavior) {
        this.f10978c = swipeDismissBehavior;
    }

    /* renamed from: c */
    public final int mo6414c(View view, int i) {
        int i2;
        int i3;
        int f = C0340mj.m14714f(view);
        int i4 = this.f10978c.f5150c;
        if (i4 != 0) {
            if (i4 != 1) {
                i3 = this.f10976a - view.getWidth();
                i2 = this.f10976a + view.getWidth();
            } else if (f != 1) {
                i3 = this.f10976a - view.getWidth();
                i2 = this.f10976a;
            } else {
                i3 = this.f10976a;
                i2 = i3 + view.getWidth();
            }
        } else if (f == 1) {
            i3 = this.f10976a - view.getWidth();
            i2 = this.f10976a;
        } else {
            i3 = this.f10976a;
            i2 = i3 + view.getWidth();
        }
        return Math.min(Math.max(i3, i), i2);
    }

    /* renamed from: d */
    public final int mo6415d(View view, int i) {
        return view.getTop();
    }

    /* renamed from: a */
    public final int mo6408a(View view) {
        return view.getWidth();
    }

    /* renamed from: a */
    public final void mo6411a(View view, int i) {
        this.f10977b = i;
        this.f10976a = view.getLeft();
        ViewParent parent = view.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    /* renamed from: a */
    public final void mo6409a(int i) {
        gcz gcz = this.f10978c.f5149b;
        if (gcz == null) {
            return;
        }
        if (i != 0) {
            gir.m10379a().mo6723a(((gid) gcz).f11402a.f11427n);
        } else {
            gir.m10379a().mo6727b(((gid) gcz).f11402a.f11427n);
        }
    }

    /* renamed from: a */
    public final void mo6412a(View view, int i, int i2) {
        float width = ((float) this.f10976a) + (((float) view.getWidth()) * this.f10978c.f5152e);
        float width2 = ((float) this.f10976a) + (((float) view.getWidth()) * this.f10978c.f5153f);
        float f = (float) i;
        if (f <= width) {
            view.setAlpha(1.0f);
        } else if (f < width2) {
            view.setAlpha(SwipeDismissBehavior.m5035a(1.0f - ((f - width) / (width2 - width))));
        } else {
            view.setAlpha(0.0f);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004d, code lost:
        if (java.lang.Math.abs(r6.getLeft() - r5.f10976a) >= java.lang.Math.round(((float) r6.getWidth()) * r5.f10978c.f5151d)) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005b, code lost:
        r1 = r5.f10976a;
        r0 = false;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo6410a(android.view.View r6, float r7, float r8) {
        /*
            r5 = this;
            r8 = -1
            r5.f10977b = r8
            int r8 = r6.getWidth()
            r0 = 1
            r1 = 0
            int r2 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r2 == 0) goto L_0x0033
            int r2 = p000.C0340mj.m14714f(r6)
            com.google.android.material.behavior.SwipeDismissBehavior r3 = r5.f10978c
            int r3 = r3.f5150c
            r4 = 2
            if (r3 != r4) goto L_0x0019
            goto L_0x004f
        L_0x0019:
            if (r3 != 0) goto L_0x0027
            if (r2 != r0) goto L_0x0022
            int r7 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r7 < 0) goto L_0x004f
            goto L_0x005b
        L_0x0022:
            int r7 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r7 <= 0) goto L_0x005b
            goto L_0x004f
        L_0x0027:
            if (r2 != r0) goto L_0x002e
            int r7 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r7 <= 0) goto L_0x005b
            goto L_0x004f
        L_0x002e:
            int r7 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r7 < 0) goto L_0x004f
            goto L_0x005b
        L_0x0033:
            int r7 = r6.getLeft()
            int r1 = r5.f10976a
            int r2 = r6.getWidth()
            com.google.android.material.behavior.SwipeDismissBehavior r3 = r5.f10978c
            float r3 = r3.f5151d
            float r2 = (float) r2
            float r2 = r2 * r3
            int r2 = java.lang.Math.round(r2)
            int r7 = r7 - r1
            int r7 = java.lang.Math.abs(r7)
            if (r7 < r2) goto L_0x005b
        L_0x004f:
            int r7 = r6.getLeft()
            int r1 = r5.f10976a
            if (r7 < r1) goto L_0x0059
            int r1 = r1 + r8
            goto L_0x005a
        L_0x0059:
            int r1 = r1 - r8
        L_0x005a:
            goto L_0x0060
        L_0x005b:
            int r1 = r5.f10976a
            r0 = 0
        L_0x0060:
            com.google.android.material.behavior.SwipeDismissBehavior r7 = r5.f10978c
            nw r7 = r7.f5148a
            int r8 = r6.getTop()
            boolean r7 = r7.mo9480a((int) r1, (int) r8)
            if (r7 == 0) goto L_0x0079
            gda r7 = new gda
            com.google.android.material.behavior.SwipeDismissBehavior r8 = r5.f10978c
            r7.<init>(r8, r6, r0)
            p000.C0340mj.m14695a((android.view.View) r6, (java.lang.Runnable) r7)
            return
        L_0x0079:
            if (r0 == 0) goto L_0x0084
            com.google.android.material.behavior.SwipeDismissBehavior r7 = r5.f10978c
            gcz r7 = r7.f5149b
            if (r7 == 0) goto L_0x0084
            r7.mo6417a(r6)
        L_0x0084:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.gcx.mo6410a(android.view.View, float, float):void");
    }

    /* renamed from: b */
    public final boolean mo6413b(View view, int i) {
        int i2 = this.f10977b;
        return (i2 == -1 || i2 == i) && this.f10978c.mo3609e(view);
    }
}
