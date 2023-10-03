package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.List;

/* renamed from: xg */
/* compiled from: PG */
public abstract class C0634xg {

    /* renamed from: a */
    public final C0635xh f16287a = new C0635xh();

    /* renamed from: b */
    public boolean f16288b = false;

    /* renamed from: a */
    public abstract int mo220a();

    /* renamed from: a */
    public int mo221a(int i) {
        return 0;
    }

    /* renamed from: a */
    public abstract C0667ym mo222a(ViewGroup viewGroup, int i);

    /* renamed from: a */
    public void mo4519a(RecyclerView recyclerView) {
    }

    /* renamed from: a */
    public void mo4520a(C0667ym ymVar) {
    }

    /* renamed from: a */
    public abstract void mo223a(C0667ym ymVar, int i);

    /* renamed from: b */
    public long mo224b(int i) {
        return -1;
    }

    /* renamed from: d */
    public final void mo10540d() {
        this.f16287a.mo10546b();
    }

    /* renamed from: c */
    public final void mo10538c(int i) {
        this.f16287a.mo10543a(i, 1);
    }

    /* renamed from: a */
    public final void mo10535a(int i, Object obj) {
        this.f16287a.mo10544a(i, 1, obj);
    }

    /* renamed from: d */
    public final void mo10541d(int i) {
        this.f16287a.mo10547b(i, 1);
    }

    /* renamed from: a */
    public final void mo10534a(int i, int i2) {
        this.f16287a.mo10549d(i, i2);
    }

    /* renamed from: b */
    public final void mo10537b(int i, int i2) {
        this.f16287a.mo10547b(i, i2);
    }

    /* renamed from: c */
    public final void mo10539c(int i, int i2) {
        this.f16287a.mo10548c(i, i2);
    }

    /* renamed from: e */
    public final void mo10542e(int i) {
        this.f16287a.mo10548c(i, 1);
    }

    /* renamed from: a */
    public void mo4521a(C0667ym ymVar, int i, List list) {
        mo223a(ymVar, i);
    }

    /* renamed from: a */
    public final void mo10536a(boolean z) {
        if (!this.f16287a.mo10545a()) {
            this.f16288b = z;
            return;
        }
        throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
    }
}
