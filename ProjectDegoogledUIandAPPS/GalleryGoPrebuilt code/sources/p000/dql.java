package p000;

import java.util.HashSet;
import java.util.Set;

/* renamed from: dql */
/* compiled from: PG */
public final class dql {

    /* renamed from: a */
    public final Set f7120a = new HashSet();

    /* renamed from: b */
    public boolean f7121b;

    /* renamed from: a */
    public final void mo4342a(boolean z) {
        Object[] objArr = {Boolean.valueOf(z), this};
        boolean z2 = this.f7121b;
        if (z2 == z) {
            Object[] objArr2 = new Object[2];
            objArr2[0] = Boolean.valueOf(z2 == z);
            objArr2[1] = false;
            return;
        }
        this.f7121b = z;
        for (dqk a : this.f7120a) {
            a.mo4340a(z);
        }
    }

    public final String toString() {
        return String.format("FullScreenHandler {inFullScreen=%s, allowFullScreen=%s}", new Object[]{Boolean.valueOf(this.f7121b), true});
    }

    /* renamed from: a */
    public final void mo4341a() {
        mo4342a(!this.f7121b);
    }
}
