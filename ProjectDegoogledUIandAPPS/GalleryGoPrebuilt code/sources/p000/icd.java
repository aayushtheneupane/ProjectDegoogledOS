package p000;

import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: icd */
/* compiled from: PG */
abstract class icd extends ibn {

    /* renamed from: a */
    private static final Logger f13873a;

    /* renamed from: f */
    public static final ica f13874f;
    public volatile int remaining;
    public volatile Set seenExceptions = null;

    static {
        Throwable th;
        ica ica;
        Class<icd> cls = icd.class;
        f13873a = Logger.getLogger(cls.getName());
        try {
            ica = new icb(AtomicReferenceFieldUpdater.newUpdater(cls, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(cls, "remaining"));
            th = null;
        } catch (Throwable th2) {
            ica = new icc((byte[]) null);
            th = th2;
        }
        f13874f = ica;
        if (th != null) {
            f13873a.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFutureState", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
    }

    /* renamed from: a */
    public abstract void mo8367a(Set set);

    public icd(int i) {
        this.remaining = i;
    }
}
