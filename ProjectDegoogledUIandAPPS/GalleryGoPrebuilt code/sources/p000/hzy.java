package p000;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

/* renamed from: hzy */
/* compiled from: PG */
public final class hzy implements Closeable {

    /* renamed from: a */
    public static final hzx f13703a = (hzw.f13702b != null ? hzw.f13701a : hzv.f13700a);

    /* renamed from: b */
    public final Deque f13704b = new ArrayDeque(4);

    /* renamed from: c */
    public Throwable f13705c;

    /* renamed from: d */
    private final hzx f13706d;

    public hzy(hzx hzx) {
        this.f13706d = (hzx) ife.m12898e((Object) hzx);
    }

    public final void close() {
        Throwable th = this.f13705c;
        while (!this.f13704b.isEmpty()) {
            Closeable closeable = (Closeable) this.f13704b.removeFirst();
            try {
                closeable.close();
            } catch (Throwable th2) {
                if (th != null) {
                    this.f13706d.mo8310a(closeable, th, th2);
                } else {
                    th = th2;
                }
            }
        }
        if (this.f13705c == null && th != null) {
            hqo.m11926a(th, IOException.class);
            throw new AssertionError(th);
        }
    }
}
