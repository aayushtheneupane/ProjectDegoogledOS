package com.google.common.base;

/* renamed from: com.google.common.base.M */
public final class C1516M {
    /* access modifiers changed from: private */

    /* renamed from: ZM */
    public final C1545t f2392ZM;
    /* access modifiers changed from: private */

    /* renamed from: _M */
    public final boolean f2393_M;

    /* renamed from: aN */
    private final C1513J f2394aN;
    /* access modifiers changed from: private */
    public final int limit;

    private C1516M(C1513J j, boolean z, C1545t tVar, int i) {
        this.f2394aN = j;
        this.f2393_M = z;
        this.f2392ZM = tVar;
        this.limit = i;
    }

    /* renamed from: Ua */
    public static C1516M m3974Ua(String str) {
        C1508E.checkArgument(str.length() != 0, "The separator may not be the empty string.");
        return new C1516M(new C1513J(str), false, C1545t.NONE, Integer.MAX_VALUE);
    }

    /* renamed from: Wk */
    public C1516M mo8531Wk() {
        return new C1516M(this.f2394aN, true, this.f2392ZM, this.limit);
    }

    public Iterable split(CharSequence charSequence) {
        if (charSequence != null) {
            return new C1514K(this, charSequence);
        }
        throw new NullPointerException();
    }
}
