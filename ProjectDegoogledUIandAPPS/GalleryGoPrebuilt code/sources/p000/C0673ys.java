package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: ys */
/* compiled from: PG */
public final class C0673ys {

    /* renamed from: a */
    public int f16412a = 0;

    /* renamed from: b */
    public int f16413b = 0;

    /* renamed from: c */
    public int f16414c = RecyclerView.UNDEFINED_DURATION;

    /* renamed from: d */
    public int f16415d = RecyclerView.UNDEFINED_DURATION;

    /* renamed from: e */
    public int f16416e = 0;

    /* renamed from: f */
    public int f16417f = 0;

    /* renamed from: g */
    public boolean f16418g = false;

    /* renamed from: h */
    public boolean f16419h = false;

    /* renamed from: a */
    public final void mo10712a(int i, int i2) {
        this.f16414c = i;
        this.f16415d = i2;
        this.f16419h = true;
        if (!this.f16418g) {
            if (i != Integer.MIN_VALUE) {
                this.f16412a = i;
            }
            if (i2 != Integer.MIN_VALUE) {
                this.f16413b = i2;
                return;
            }
            return;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f16412a = i2;
        }
        if (i != Integer.MIN_VALUE) {
            this.f16413b = i;
        }
    }
}
