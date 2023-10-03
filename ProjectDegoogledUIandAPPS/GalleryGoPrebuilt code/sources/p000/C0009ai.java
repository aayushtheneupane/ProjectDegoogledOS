package p000;

import android.os.Looper;
import java.util.List;

/* renamed from: ai */
/* compiled from: PG */
public class C0009ai {

    /* renamed from: b */
    public static final Object f519b = new Object();

    /* renamed from: a */
    public final Object f520a = new Object();

    /* renamed from: c */
    public int f521c = 0;

    /* renamed from: d */
    public volatile Object f522d = f519b;

    /* renamed from: e */
    public volatile Object f523e = f519b;

    /* renamed from: f */
    public int f524f = -1;

    /* renamed from: g */
    public final Runnable f525g = new C0006af(this);

    /* renamed from: h */
    private final C0303l f526h = new C0303l();

    /* renamed from: i */
    private boolean f527i;

    /* renamed from: j */
    private boolean f528j;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo509a() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo512a(Object obj) {
        throw null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo514b() {
    }

    /* renamed from: a */
    static void m522a(String str) {
        C0029b.m2002a();
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
        }
    }

    /* renamed from: b */
    private final void m523b(C0008ah ahVar) {
        if (!ahVar.f467b) {
            return;
        }
        if (!ahVar.mo340a()) {
            ahVar.mo456a(false);
            return;
        }
        int i = ahVar.f468c;
        int i2 = this.f524f;
        if (i < i2) {
            ahVar.f468c = i2;
            C0011ak akVar = ahVar.f466a;
            Object obj = this.f522d;
            if (C0210ho.m11828a(2)) {
                "  onLoadFinished in " + ((C0207hl) akVar).f12953a + ": " + C0224ib.m12595b(obj);
            }
            C0207hl hlVar = (C0207hl) akVar;
            hlVar.f12955c = true;
            fsw fsw = (fsw) hlVar.f12954b;
            fsw.f10549a.clear();
            fsw.f10549a.addAll((List) obj);
            fsw.f10549a.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo510a(C0008ah ahVar) {
        if (!this.f527i) {
            this.f527i = true;
            do {
                this.f528j = false;
                if (ahVar == null) {
                    C0222i a = this.f526h.mo9306a();
                    while (a.hasNext()) {
                        m523b((C0008ah) ((C0195h) a.next()).f12389b);
                        if (this.f528j) {
                            break;
                        }
                    }
                } else {
                    m523b(ahVar);
                    ahVar = null;
                }
            } while (this.f528j);
            this.f527i = false;
            return;
        }
        this.f528j = true;
    }

    /* renamed from: a */
    public final void mo513a(C0681z zVar, C0011ak akVar) {
        Object obj;
        m522a("observe");
        if (zVar.mo5ad().mo61a() != C0573v.DESTROYED) {
            C0007ag agVar = new C0007ag(this, zVar, akVar);
            C0303l lVar = this.f526h;
            C0195h a = lVar.mo4622a(akVar);
            if (a == null) {
                lVar.mo9305a(akVar, agVar);
                obj = null;
            } else {
                obj = a.f12389b;
            }
            C0008ah ahVar = (C0008ah) obj;
            if (ahVar != null && !ahVar.mo341a(zVar)) {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            } else if (ahVar == null) {
                zVar.mo5ad().mo64a(agVar);
            }
        }
    }

    /* renamed from: a */
    public void mo511a(C0011ak akVar) {
        m522a("removeObserver");
        C0008ah ahVar = (C0008ah) this.f526h.mo4623b(akVar);
        if (ahVar != null) {
            ahVar.mo342b();
            ahVar.mo456a(false);
        }
    }
}
