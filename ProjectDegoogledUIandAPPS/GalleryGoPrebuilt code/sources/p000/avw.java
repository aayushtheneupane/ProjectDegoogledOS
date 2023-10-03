package p000;

import android.text.TextUtils;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: avw */
/* compiled from: PG */
public final class avw {

    /* renamed from: a */
    public String f1779a;

    /* renamed from: b */
    private final boolean f1780b;

    /* renamed from: c */
    private int f1781c;

    /* renamed from: d */
    private int f1782d;

    public avw(boolean z) {
        this.f1780b = z;
    }

    /* renamed from: a */
    public final avz mo1676a() {
        if (!TextUtils.isEmpty(this.f1779a)) {
            return new avz(new ThreadPoolExecutor(this.f1781c, this.f1782d, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new avy(this.f1779a, this.f1780b)));
        }
        String valueOf = String.valueOf(this.f1779a);
        throw new IllegalArgumentException(valueOf.length() == 0 ? new String("Name must be non-null and non-empty, but given: ") : "Name must be non-null and non-empty, but given: ".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo1677a(int i) {
        this.f1781c = i;
        this.f1782d = i;
    }
}
