package p000;

import java.util.logging.Level;

/* renamed from: hvt */
/* compiled from: PG */
public abstract class hvt {

    /* renamed from: a */
    public final hxa f13492a;

    protected hvt(hxa hxa) {
        this.f13492a = (hxa) ife.m12827a((Object) hxa, "backend");
    }

    /* renamed from: a */
    public abstract hwm mo8179a(Level level);

    /* renamed from: a */
    public final hwm mo8178a() {
        return mo8179a(Level.SEVERE);
    }

    /* renamed from: b */
    public final hwm mo8180b() {
        return mo8179a(Level.WARNING);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final boolean mo8181b(Level level) {
        return this.f13492a.mo7301a(level);
    }
}
