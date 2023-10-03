package p000;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: fsd */
/* compiled from: PG */
public final class fsd {

    /* renamed from: a */
    public final fsm[] f10477a = new fsm[5];

    /* renamed from: b */
    public byte[] f10478b;

    /* renamed from: c */
    public final ArrayList f10479c = new ArrayList();

    /* renamed from: d */
    public final ByteOrder f10480d;

    /* renamed from: e */
    public byte[] f10481e;

    public fsd(ByteOrder byteOrder) {
        this.f10480d = byteOrder;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo6119a() {
        return this.f10478b != null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6117a(fsm fsm) {
        this.f10477a[fsm.f10532a] = fsm;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6116a(fsl fsl) {
        if (fsl != null) {
            int i = fsl.f10528e;
            if (fsl.m9546a(i)) {
                fsm fsm = this.f10477a[i];
                if (fsm == null) {
                    fsm = new fsm(i);
                    this.f10477a[i] = fsm;
                }
                fsm.mo6148a(fsl);
            }
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof fsd)) {
            fsd fsd = (fsd) obj;
            if (fsd.f10480d == this.f10480d && fsd.f10479c.size() == this.f10479c.size() && Arrays.equals(fsd.f10478b, this.f10478b)) {
                for (int i = 0; i < this.f10479c.size(); i++) {
                    if (!Arrays.equals((byte[]) fsd.f10479c.get(i), (byte[]) this.f10479c.get(i))) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < 5; i2++) {
                    fsm b = fsd.mo6122b(i2);
                    fsm b2 = mo6122b(i2);
                    if (b != b2 && b != null && !b.equals(b2)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final List mo6124d() {
        fsl[] a;
        ArrayList arrayList = new ArrayList();
        for (fsm fsm : this.f10477a) {
            if (!(fsm == null || (a = fsm.mo6149a()) == null)) {
                Collections.addAll(arrayList, a);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final fsm mo6122b(int i) {
        if (fsl.m9546a(i)) {
            return this.f10477a[i];
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final byte[] mo6120a(int i) {
        return (byte[]) this.f10479c.get(i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final int mo6121b() {
        return this.f10479c.size();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final boolean mo6123c() {
        return !this.f10479c.isEmpty();
    }

    public final int hashCode() {
        return ((((((this.f10480d.hashCode() + 527) * 31) + this.f10479c.hashCode()) * 31) + Arrays.hashCode(this.f10478b)) * 31) + Arrays.hashCode(this.f10477a);
    }

    /* renamed from: a */
    public final void mo6118a(short s, int i) {
        fsm fsm = this.f10477a[i];
        if (fsm != null) {
            fsm.mo6151b(s);
        }
    }
}
