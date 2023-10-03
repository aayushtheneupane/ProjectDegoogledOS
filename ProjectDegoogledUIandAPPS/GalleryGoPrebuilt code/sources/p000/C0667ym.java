package p000;

import android.support.p002v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: ym */
/* compiled from: PG */
public class C0667ym {

    /* renamed from: p */
    private static final List f16381p = Collections.emptyList();

    /* renamed from: a */
    public final View f16382a;

    /* renamed from: b */
    public WeakReference f16383b;

    /* renamed from: c */
    public int f16384c = -1;

    /* renamed from: d */
    public int f16385d = -1;

    /* renamed from: e */
    public long f16386e = -1;

    /* renamed from: f */
    public int f16387f = -1;

    /* renamed from: g */
    public int f16388g = -1;

    /* renamed from: h */
    public C0667ym f16389h = null;

    /* renamed from: i */
    public C0667ym f16390i = null;

    /* renamed from: j */
    public int f16391j;

    /* renamed from: k */
    public C0656yb f16392k = null;

    /* renamed from: l */
    public boolean f16393l = false;

    /* renamed from: m */
    public int f16394m = 0;

    /* renamed from: n */
    public int f16395n = -1;

    /* renamed from: o */
    public RecyclerView f16396o;

    /* renamed from: q */
    private List f16397q = null;

    /* renamed from: r */
    private List f16398r = null;

    /* renamed from: s */
    private int f16399s = 0;

    /* renamed from: a */
    public final boolean mo10640a(int i) {
        return (i & this.f16391j) != 0;
    }

    /* renamed from: b */
    public final boolean mo10642b() {
        return (this.f16391j & 128) != 0;
    }

    /* renamed from: c */
    public final int mo10643c() {
        int i = this.f16388g;
        return i == -1 ? this.f16384c : i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final boolean mo10645e() {
        return this.f16392k != null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final boolean mo10647g() {
        return (this.f16391j & 32) != 0;
    }

    /* renamed from: j */
    public final boolean mo10650j() {
        return (this.f16391j & 4) != 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public final boolean mo10651k() {
        return (this.f16391j & 2) != 0;
    }

    /* renamed from: l */
    public final boolean mo10652l() {
        return (this.f16391j & 1) != 0;
    }

    /* renamed from: m */
    public final boolean mo10653m() {
        return (this.f16391j & 8) != 0;
    }

    /* renamed from: n */
    public final boolean mo10654n() {
        return (this.f16391j & 256) != 0;
    }

    /* renamed from: t */
    public final boolean mo10660t() {
        return (this.f16391j & 2) != 0;
    }

    public C0667ym(View view) {
        if (view != null) {
            this.f16382a = view;
            return;
        }
        throw new IllegalArgumentException("itemView may not be null");
    }

    /* renamed from: a */
    public final void mo10637a(Object obj) {
        if (obj == null) {
            mo10641b(1024);
        } else if ((1024 & this.f16391j) == 0) {
            if (this.f16397q == null) {
                ArrayList arrayList = new ArrayList();
                this.f16397q = arrayList;
                this.f16398r = Collections.unmodifiableList(arrayList);
            }
            this.f16397q.add(obj);
        }
    }

    /* renamed from: b */
    public final void mo10641b(int i) {
        this.f16391j = i | this.f16391j;
    }

    /* renamed from: a */
    public final void mo10634a() {
        this.f16385d = -1;
        this.f16388g = -1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: p */
    public final void mo10656p() {
        List list = this.f16397q;
        if (list != null) {
            list.clear();
        }
        this.f16391j &= -1025;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public final void mo10648h() {
        this.f16391j &= -33;
    }

    /* renamed from: i */
    public final void mo10649i() {
        this.f16391j &= -257;
    }

    /* renamed from: d */
    public final int mo10644d() {
        RecyclerView recyclerView = this.f16396o;
        if (recyclerView == null) {
            return -1;
        }
        return recyclerView.getAdapterPositionFor(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r1.f16397q;
     */
    /* renamed from: q */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List mo10657q() {
        /*
            r1 = this;
            int r0 = r1.f16391j
            r0 = r0 & 1024(0x400, float:1.435E-42)
            if (r0 != 0) goto L_0x0013
            java.util.List r0 = r1.f16397q
            if (r0 == 0) goto L_0x0013
            int r0 = r0.size()
            if (r0 == 0) goto L_0x0013
            java.util.List r0 = r1.f16398r
            return r0
        L_0x0013:
            java.util.List r0 = f16381p
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0667ym.mo10657q():java.util.List");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: o */
    public final boolean mo10655o() {
        return (this.f16382a.getParent() == null || this.f16382a.getParent() == this.f16396o) ? false : true;
    }

    /* renamed from: s */
    public final boolean mo10659s() {
        return (this.f16391j & 16) == 0 && !C0340mj.m14709c(this.f16382a);
    }

    /* renamed from: a */
    public final void mo10636a(int i, boolean z) {
        if (this.f16385d == -1) {
            this.f16385d = this.f16384c;
        }
        int i2 = this.f16388g;
        if (i2 == -1) {
            i2 = this.f16384c;
            this.f16388g = i2;
        }
        if (z) {
            this.f16388g = i2 + i;
        }
        this.f16384c += i;
        if (this.f16382a.getLayoutParams() != null) {
            ((C0648xu) this.f16382a.getLayoutParams()).f16314e = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: r */
    public final void mo10658r() {
        this.f16391j = 0;
        this.f16384c = -1;
        this.f16385d = -1;
        this.f16386e = -1;
        this.f16388g = -1;
        this.f16399s = 0;
        this.f16389h = null;
        this.f16390i = null;
        mo10656p();
        this.f16394m = 0;
        this.f16395n = -1;
        RecyclerView.clearNestedRecyclerViewIfNotNested(this);
    }

    /* renamed from: a */
    public final void mo10635a(int i, int i2) {
        this.f16391j = (i & i2) | (this.f16391j & (i2 ^ -1));
    }

    /* renamed from: a */
    public final void mo10639a(boolean z) {
        int i = z ? this.f16399s - 1 : this.f16399s + 1;
        this.f16399s = i;
        if (i < 0) {
            this.f16399s = 0;
            Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
        } else if (!z && i == 1) {
            this.f16391j |= 16;
        } else if (z && i == 0) {
            this.f16391j &= -17;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10638a(C0656yb ybVar, boolean z) {
        this.f16392k = ybVar;
        this.f16393l = z;
    }

    public final String toString() {
        String str;
        String str2;
        if (!getClass().isAnonymousClass()) {
            str = getClass().getSimpleName();
        } else {
            str = "ViewHolder";
        }
        StringBuilder sb = new StringBuilder(str + "{" + Integer.toHexString(hashCode()) + " position=" + this.f16384c + " id=" + this.f16386e + ", oldPos=" + this.f16385d + ", pLpos:" + this.f16388g);
        if (mo10645e()) {
            sb.append(" scrap ");
            if (!this.f16393l) {
                str2 = "[attachedScrap]";
            } else {
                str2 = "[changeScrap]";
            }
            sb.append(str2);
        }
        if (mo10650j()) {
            sb.append(" invalid");
        }
        if (!mo10652l()) {
            sb.append(" unbound");
        }
        if (mo10651k()) {
            sb.append(" update");
        }
        if (mo10653m()) {
            sb.append(" removed");
        }
        if (mo10642b()) {
            sb.append(" ignored");
        }
        if (mo10654n()) {
            sb.append(" tmpDetached");
        }
        if (!mo10659s()) {
            sb.append(" not recyclable(" + this.f16399s + ")");
        }
        if ((this.f16391j & 512) != 0 || mo10650j()) {
            sb.append(" undefined adapter position");
        }
        if (this.f16382a.getParent() == null) {
            sb.append(" no parent");
        }
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final void mo10646f() {
        this.f16392k.mo10612b(this);
    }
}
