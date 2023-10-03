package androidx.recyclerview.widget;

import androidx.core.util.Pools;

/* renamed from: androidx.recyclerview.widget.Fa */
class C0532Fa {
    static Pools.Pool sPool = new Pools.SimplePool(20);
    int flags;
    C0548V postInfo;
    C0548V preInfo;

    private C0532Fa() {
    }

    /* renamed from: a */
    static void m575a(C0532Fa fa) {
        fa.flags = 0;
        fa.preInfo = null;
        fa.postInfo = null;
        sPool.release(fa);
    }

    static C0532Fa obtain() {
        C0532Fa fa = (C0532Fa) sPool.acquire();
        return fa == null ? new C0532Fa() : fa;
    }
}
