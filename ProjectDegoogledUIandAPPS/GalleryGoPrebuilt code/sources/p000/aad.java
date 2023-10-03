package p000;

import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: aad */
/* compiled from: PG */
public final class aad {

    /* renamed from: a */
    public boolean f8a = false;

    /* renamed from: b */
    public final CopyOnWriteArrayList f9b = new CopyOnWriteArrayList();

    /* renamed from: c */
    public final /* synthetic */ C0171gd f10c;

    public aad(C0171gd gdVar) {
        this.f10c = gdVar;
    }

    public aad() {
    }

    /* renamed from: a */
    public final void mo12a(C0705zx zxVar) {
        this.f9b.add(zxVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo13b(C0705zx zxVar) {
        this.f9b.remove(zxVar);
    }
}
