package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: aoj */
/* compiled from: PG */
public final class aoj {

    /* renamed from: a */
    private final List f1277a = new ArrayList(5);

    /* renamed from: a */
    public final void mo1348a(aok aok) {
        this.f1277a.add(aok);
    }

    /* renamed from: a */
    public final aok mo1347a(int i) {
        return (aok) this.f1277a.get(i);
    }

    /* renamed from: a */
    public final int mo1346a() {
        return this.f1277a.size();
    }

    public final String toString() {
        int i;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 1; i2 < mo1346a(); i2++) {
            stringBuffer.append(mo1347a(i2));
            if (i2 < mo1346a() - 1 && ((i = mo1347a(i2 + 1).f1279b) == 1 || i == 2)) {
                stringBuffer.append('/');
            }
        }
        return stringBuffer.toString();
    }
}
