package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: aop */
/* compiled from: PG */
public final class aop extends aon {
    public aop() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final int mo1355d() {
        return -2147475470;
    }

    /* renamed from: k */
    public final boolean mo1380k() {
        return (this.f1282a & 768) > 0;
    }

    public aop(int i) {
        super(i);
    }

    /* renamed from: c */
    public final void mo1361c(int i) {
        if ((i & 256) > 0 && (i & 512) > 0) {
            throw new ang("IsStruct and IsArray options are mutually exclusive", 103);
        } else if ((i & 2) > 0 && (i & 768) > 0) {
            throw new ang("Structs and arrays can't have \"value\" options", 103);
        }
    }

    /* renamed from: c */
    public final boolean mo1372c() {
        return mo1359a(64);
    }

    /* renamed from: f */
    public final boolean mo1375f() {
        return mo1359a(512);
    }

    /* renamed from: i */
    public final boolean mo1378i() {
        return mo1359a(4096);
    }

    /* renamed from: h */
    public final boolean mo1377h() {
        return mo1359a(2048);
    }

    /* renamed from: g */
    public final boolean mo1376g() {
        return mo1359a(1024);
    }

    /* renamed from: b */
    public final boolean mo1370b() {
        return mo1359a(32);
    }

    /* renamed from: j */
    public final boolean mo1379j() {
        return mo1359a(RecyclerView.UNDEFINED_DURATION);
    }

    /* renamed from: e */
    public final boolean mo1374e() {
        return mo1359a(256);
    }

    /* renamed from: a */
    public final boolean mo1368a() {
        return mo1359a(2);
    }

    /* renamed from: a */
    public final void mo1366a(aop aop) {
        if (aop != null) {
            mo1360b(aop.f1282a | this.f1282a);
        }
    }

    /* renamed from: l */
    public final void mo1381l() {
        mo1358a(512, true);
    }

    /* renamed from: m */
    public final void mo1382m() {
        mo1358a(4096, true);
    }

    /* renamed from: n */
    public final void mo1383n() {
        mo1358a(2048, true);
    }

    /* renamed from: o */
    public final void mo1384o() {
        mo1358a(1024, true);
    }

    /* renamed from: a */
    public final void mo1367a(boolean z) {
        mo1358a(64, z);
    }

    /* renamed from: b */
    public final void mo1369b(boolean z) {
        mo1358a(16, z);
    }

    /* renamed from: c */
    public final void mo1371c(boolean z) {
        mo1358a(128, z);
    }

    /* renamed from: d */
    public final void mo1373d(boolean z) {
        mo1358a(256, z);
    }
}
