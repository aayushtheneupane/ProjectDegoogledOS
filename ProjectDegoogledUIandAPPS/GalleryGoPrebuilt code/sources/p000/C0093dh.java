package p000;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: dh */
/* compiled from: PG */
final class C0093dh implements Serializable {
    public static final long serialVersionUID = 1;

    /* renamed from: a */
    public boolean f6529a = false;

    /* renamed from: b */
    public final List f6530b = new ArrayList();

    private C0093dh() {
    }

    public /* synthetic */ C0093dh(byte[] bArr) {
    }

    /* renamed from: a */
    public final void mo4130a(C0092dg dgVar) {
        String str = dgVar.f6484a;
        List list = this.f6530b;
        int size = list.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            if (str.equals(((C0092dg) list.get(i)).f6484a)) {
                String valueOf = String.valueOf(str);
                throw new IllegalArgumentException(valueOf.length() == 0 ? new String("Duplicate keyword: ") : "Duplicate keyword: ".concat(valueOf));
            }
            i = i2;
        }
        this.f6530b.add(dgVar);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        List list = this.f6530b;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            C0092dg dgVar = (C0092dg) list.get(i);
            if (sb.length() != 0) {
                sb.append(";  ");
            }
            sb.append(dgVar);
        }
        return sb.toString();
    }
}
