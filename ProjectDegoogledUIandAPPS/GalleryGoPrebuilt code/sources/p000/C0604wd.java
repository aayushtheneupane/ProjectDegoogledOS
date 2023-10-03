package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import java.util.List;

/* renamed from: wd */
/* compiled from: PG */
final class C0604wd {

    /* renamed from: a */
    public boolean f16205a = true;

    /* renamed from: b */
    public int f16206b;

    /* renamed from: c */
    public int f16207c;

    /* renamed from: d */
    public int f16208d;

    /* renamed from: e */
    public int f16209e;

    /* renamed from: f */
    public int f16210f;

    /* renamed from: g */
    public int f16211g;

    /* renamed from: h */
    public int f16212h = 0;

    /* renamed from: i */
    public int f16213i = 0;

    /* renamed from: j */
    public boolean f16214j = false;

    /* renamed from: k */
    public int f16215k;

    /* renamed from: l */
    public List f16216l = null;

    /* renamed from: m */
    public boolean f16217m;

    /* renamed from: a */
    public final void mo10454a() {
        m15693a((View) null);
    }

    /* renamed from: a */
    private final void m15693a(View view) {
        int c;
        int size = this.f16216l.size();
        View view2 = null;
        int i = Integer.MAX_VALUE;
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            View view3 = ((C0667ym) this.f16216l.get(i2)).f16382a;
            C0648xu xuVar = (C0648xu) view3.getLayoutParams();
            if (view3 != view && !xuVar.mo10594a() && (c = (xuVar.mo10596c() - this.f16208d) * this.f16209e) >= 0 && c < i) {
                if (c == 0) {
                    view2 = view3;
                    break;
                } else {
                    view2 = view3;
                    i = c;
                }
            }
            i2++;
        }
        if (view2 != null) {
            this.f16208d = ((C0648xu) view2.getLayoutParams()).mo10596c();
        } else {
            this.f16208d = -1;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo10455a(C0664yj yjVar) {
        int i = this.f16208d;
        return i >= 0 && i < yjVar.mo10626a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final View mo10453a(C0656yb ybVar) {
        List list = this.f16216l;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = ((C0667ym) this.f16216l.get(i)).f16382a;
                C0648xu xuVar = (C0648xu) view.getLayoutParams();
                if (!xuVar.mo10594a() && this.f16208d == xuVar.mo10596c()) {
                    m15693a(view);
                    return view;
                }
            }
            return null;
        }
        View view2 = ybVar.mo10604a(this.f16208d, (long) RecyclerView.FOREVER_NS).f16382a;
        this.f16208d += this.f16209e;
        return view2;
    }
}
