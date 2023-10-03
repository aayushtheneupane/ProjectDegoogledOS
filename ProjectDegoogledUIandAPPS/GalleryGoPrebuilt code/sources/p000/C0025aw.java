package p000;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;

/* renamed from: aw */
/* compiled from: PG */
public final class C0025aw {

    /* renamed from: a */
    public final HashMap f1790a = new HashMap();

    /* renamed from: a */
    public final void mo1694a() {
        for (C0020ar arVar : this.f1790a.values()) {
            arVar.f1475b = true;
            synchronized (arVar.f1474a) {
                for (Object next : arVar.f1474a.values()) {
                    if (next instanceof Closeable) {
                        try {
                            ((Closeable) next).close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            arVar.mo1506a();
        }
        this.f1790a.clear();
    }
}
