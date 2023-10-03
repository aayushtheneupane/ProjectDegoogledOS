package p000;

import java.io.IOException;

/* renamed from: apr */
/* compiled from: PG */
public final class apr {

    /* renamed from: a */
    public final aps f1368a;

    /* renamed from: b */
    public final boolean[] f1369b;

    /* renamed from: c */
    public boolean f1370c;

    /* renamed from: d */
    public final /* synthetic */ apu f1371d;

    public /* synthetic */ apr(apu apu, aps aps) {
        this.f1371d = apu;
        this.f1368a = aps;
        this.f1369b = !aps.f1375d ? new boolean[apu.f1383d] : null;
    }

    /* renamed from: a */
    public final void mo1456a() {
        this.f1371d.mo1463a(this, false);
    }

    /* renamed from: b */
    public final void mo1457b() {
        if (!this.f1370c) {
            try {
                mo1456a();
            } catch (IOException e) {
            }
        }
    }
}
