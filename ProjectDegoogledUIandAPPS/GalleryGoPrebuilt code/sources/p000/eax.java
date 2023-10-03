package p000;

import android.graphics.Rect;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;

/* renamed from: eax */
/* compiled from: PG */
final class eax extends C0607wg {

    /* renamed from: a */
    private final /* synthetic */ ebc f7809a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public eax(ebc ebc) {
        super(0);
        this.f7809a = ebc;
    }

    /* renamed from: a */
    public final boolean mo4640a(RecyclerView recyclerView, View view, Rect rect, boolean z) {
        return false;
    }

    /* renamed from: a */
    public final boolean mo4641a(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        return false;
    }

    /* renamed from: i */
    public final boolean mo2620i() {
        eav eav = this.f7809a.f7829e;
        if (eav != null) {
            return ((Boolean) eav.mo4634a().map(ear.f7795a).orElse(false)).booleanValue();
        }
        return false;
    }

    /* renamed from: a */
    public final void mo4531a(int i, int i2, C0664yj yjVar, C0646xs xsVar) {
        int signum = Integer.signum(i);
        if (signum != 0) {
            int i3 = m15975i(mo10582h(signum >= 0 ? mo10585r() - 1 : 0));
            int a = signum > 0 ? yjVar.mo10626a() - 1 : 0;
            for (int i4 = 0; i4 < this.f7809a.f7827c && i3 != a; i4++) {
                i3 += signum;
                xsVar.mo10410a(i3, Math.abs(i) + 1 + i4);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final int mo4642b(C0664yj yjVar) {
        int i = this.f7809a.f7827c;
        return i + i;
    }
}
