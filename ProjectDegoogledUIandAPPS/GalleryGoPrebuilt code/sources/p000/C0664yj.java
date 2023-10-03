package p000;

/* renamed from: yj */
/* compiled from: PG */
public final class C0664yj {

    /* renamed from: a */
    public int f16358a = -1;

    /* renamed from: b */
    public int f16359b = 0;

    /* renamed from: c */
    public int f16360c = 0;

    /* renamed from: d */
    public int f16361d = 1;

    /* renamed from: e */
    public int f16362e = 0;

    /* renamed from: f */
    public boolean f16363f = false;

    /* renamed from: g */
    public boolean f16364g = false;

    /* renamed from: h */
    public boolean f16365h = false;

    /* renamed from: i */
    public boolean f16366i = false;

    /* renamed from: j */
    public boolean f16367j = false;

    /* renamed from: k */
    public boolean f16368k = false;

    /* renamed from: l */
    public int f16369l;

    /* renamed from: m */
    public long f16370m;

    /* renamed from: n */
    public int f16371n;

    /* renamed from: o */
    public int f16372o;

    /* renamed from: p */
    public int f16373p;

    /* renamed from: a */
    public final int mo10626a() {
        return this.f16364g ? this.f16359b - this.f16360c : this.f16362e;
    }

    /* renamed from: a */
    public final void mo10627a(int i) {
        if ((this.f16361d & i) == 0) {
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i) + " but it is " + Integer.toBinaryString(this.f16361d));
        }
    }

    public final String toString() {
        return "State{mTargetPosition=" + this.f16358a + ", mData=" + null + ", mItemCount=" + this.f16362e + ", mIsMeasuring=" + this.f16366i + ", mPreviousLayoutItemCount=" + this.f16359b + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f16360c + ", mStructureChanged=" + this.f16363f + ", mInPreLayout=" + this.f16364g + ", mRunSimpleAnimations=" + this.f16367j + ", mRunPredictiveAnimations=" + this.f16368k + '}';
    }
}
