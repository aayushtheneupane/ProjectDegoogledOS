package p000;

import android.support.p002v7.widget.RecyclerView;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: hng */
/* compiled from: PG */
final class hng extends AtomicInteger {

    /* renamed from: a */
    public final hng f13086a;

    /* renamed from: b */
    public final String f13087b;

    /* renamed from: c */
    public final int f13088c;

    /* renamed from: d */
    public final hln f13089d;

    /* renamed from: e */
    public int f13090e;

    /* renamed from: f */
    public hng f13091f;

    /* renamed from: g */
    public volatile int f13092g;

    public hng(hng hng, String str, int i, hln hln) {
        this.f13092g = 0;
        this.f13086a = hng;
        this.f13087b = str;
        this.f13090e = -1;
        this.f13088c = i;
        this.f13089d = hln;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo7605a() {
        return this.f13092g != 0;
    }

    public hng(String str, hln hln) {
        this.f13092g = 0;
        this.f13086a = null;
        this.f13087b = str;
        this.f13090e = 0;
        this.f13088c = 0;
        this.f13089d = hln;
        this.f13092g = RecyclerView.UNDEFINED_DURATION;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo7604a(int i, hng hng) {
        this.f13090e = i;
        this.f13091f = hng;
    }
}
