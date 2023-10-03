package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: androidx.lifecycle.w */
public abstract class C0466w {

    /* renamed from: aq */
    private final Map f453aq = new HashMap();

    /* access modifiers changed from: protected */
    /* renamed from: Dc */
    public void mo247Dc() {
    }

    /* access modifiers changed from: package-private */
    public final void clear() {
        Map map = this.f453aq;
        if (map != null) {
            synchronized (map) {
                for (Object next : this.f453aq.values()) {
                    if (next instanceof Closeable) {
                        try {
                            ((Closeable) next).close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        mo247Dc();
    }
}
