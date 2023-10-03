package p000;

import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: ibr */
/* compiled from: PG */
public abstract class ibr extends iff implements ieh {

    /* renamed from: b */
    public static final boolean f13852b = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));

    /* renamed from: c */
    public static final Logger f13853c;

    /* renamed from: d */
    public static final ibe f13854d;

    /* renamed from: e */
    public static final Object f13855e = new Object();
    public volatile ibi listeners;
    public volatile Object value;
    public volatile ibq waiters;

    static {
        Throwable th;
        Throwable th2;
        ibe ibe;
        Class<ibr> cls = ibr.class;
        f13853c = Logger.getLogger(cls.getName());
        try {
            ibe = new ibp((byte[]) null);
            th2 = null;
            th = null;
        } catch (Throwable th3) {
            th = th3;
            th2 = th;
            ibe = new ibl((byte[]) null);
        }
        f13854d = ibe;
        if (th != null) {
            f13853c.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "UnsafeAtomicHelper is broken!", th2);
            f13853c.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo6947b() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo8347c() {
    }

    protected ibr() {
    }

    /* renamed from: b */
    private final void m12639b(StringBuilder sb) {
        try {
            Object b = m12637b((Future) this);
            sb.append("SUCCESS, result=[");
            m12635a(sb, b);
            sb.append("]");
        } catch (ExecutionException e) {
            sb.append("FAILURE, cause=[");
            sb.append(e.getCause());
            sb.append("]");
        } catch (CancellationException e2) {
            sb.append("CANCELLED");
        } catch (RuntimeException e3) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e3.getClass());
            sb.append(" thrown from get()]");
        }
    }

    /* renamed from: a */
    public void mo53a(Runnable runnable, Executor executor) {
        ibi ibi;
        ife.m12869b((Object) runnable, (Object) "Runnable was null.");
        ife.m12869b((Object) executor, (Object) "Executor was null.");
        if (isDone() || (ibi = this.listeners) == ibi.f13835a) {
            m12638b(runnable, executor);
        }
        ibi ibi2 = new ibi(runnable, executor);
        do {
            ibi2.next = ibi;
            if (!f13854d.mo8338a(this, ibi, ibi2)) {
                ibi = this.listeners;
            } else {
                return;
            }
        } while (ibi != ibi.f13835a);
        m12638b(runnable, executor);
    }

    /* renamed from: a */
    private final void m12634a(StringBuilder sb) {
        String str;
        int length = sb.length();
        sb.append("PENDING");
        Object obj = this.value;
        if (obj instanceof ibk) {
            sb.append(", setFuture=[");
            m12635a(sb, (Object) ((ibk) obj).f13844b);
            sb.append("]");
        } else {
            try {
                str = mo6386a();
                if (hpz.m11899a(str)) {
                    str = null;
                }
            } catch (RuntimeException | StackOverflowError e) {
                String valueOf = String.valueOf(e.getClass());
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 38);
                sb2.append("Exception thrown from implementation: ");
                sb2.append(valueOf);
                str = sb2.toString();
            }
            if (str != null) {
                sb.append(", info=[");
                sb.append(str);
                sb.append("]");
            }
        }
        if (isDone()) {
            sb.delete(length, sb.length());
            m12639b(sb);
        }
    }

    /* renamed from: a */
    private final void m12635a(StringBuilder sb, Object obj) {
        if (obj == this) {
            try {
                sb.append("this future");
            } catch (RuntimeException | StackOverflowError e) {
                sb.append("Exception thrown from implementation: ");
                sb.append(e.getClass());
            }
        } else {
            sb.append(obj);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: ieh} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: ibr} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean cancel(boolean r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.value
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0008
            r3 = 1
            goto L_0x0009
        L_0x0008:
            r3 = 0
        L_0x0009:
            boolean r4 = r0 instanceof p000.ibk
            r3 = r3 | r4
            if (r3 == 0) goto L_0x006a
            boolean r3 = f13852b
            if (r3 == 0) goto L_0x001f
            ibf r3 = new ibf
            java.util.concurrent.CancellationException r4 = new java.util.concurrent.CancellationException
            java.lang.String r5 = "Future.cancel() was called."
            r4.<init>(r5)
            r3.<init>(r8, r4)
            goto L_0x0026
        L_0x001f:
            if (r8 == 0) goto L_0x0024
            ibf r3 = p000.ibf.f13829a
            goto L_0x0026
        L_0x0024:
            ibf r3 = p000.ibf.f13830b
        L_0x0026:
            r5 = 0
            r4 = r7
        L_0x0028:
            ibe r6 = f13854d
            boolean r6 = r6.mo8340a((p000.ibr) r4, (java.lang.Object) r0, (java.lang.Object) r3)
            if (r6 != 0) goto L_0x0038
            java.lang.Object r0 = r4.value
            boolean r6 = r0 instanceof p000.ibk
            if (r6 != 0) goto L_0x0028
            r1 = r5
            goto L_0x006c
        L_0x0038:
            if (r8 != 0) goto L_0x003b
            goto L_0x003e
        L_0x003b:
            r4.mo8347c()
        L_0x003e:
            m12633a((p000.ibr) r4)
            boolean r4 = r0 instanceof p000.ibk
            if (r4 == 0) goto L_0x0067
            ibk r0 = (p000.ibk) r0
            ieh r0 = r0.f13844b
            boolean r4 = r0 instanceof p000.ibm
            if (r4 == 0) goto L_0x0061
            r4 = r0
            ibr r4 = (p000.ibr) r4
            java.lang.Object r0 = r4.value
            if (r0 != 0) goto L_0x0056
            r5 = 1
            goto L_0x0058
        L_0x0056:
            r5 = 0
        L_0x0058:
            boolean r6 = r0 instanceof p000.ibk
            r5 = r5 | r6
            if (r5 != 0) goto L_0x005e
            goto L_0x0068
        L_0x005e:
            r5 = 1
            goto L_0x0028
        L_0x0061:
            r0.cancel(r8)
            r1 = 1
            goto L_0x006c
        L_0x0067:
        L_0x0068:
            r1 = 1
            goto L_0x006c
        L_0x006a:
        L_0x006c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ibr.cancel(boolean):boolean");
    }

    /* renamed from: a */
    public static void m12633a(ibr ibr) {
        ibi ibi;
        ibi ibi2 = null;
        while (true) {
            ibq ibq = ibr.waiters;
            if (f13854d.mo8339a(ibr, ibq, ibq.f13851a)) {
                while (ibq != null) {
                    Thread thread = ibq.thread;
                    if (thread != null) {
                        ibq.thread = null;
                        LockSupport.unpark(thread);
                    }
                    ibq = ibq.next;
                }
                ibr.mo6947b();
                do {
                    ibi = ibr.listeners;
                } while (!f13854d.mo8338a(ibr, ibi, ibi.f13835a));
                ibi ibi3 = ibi2;
                ibi ibi4 = ibi;
                while (ibi4 != null) {
                    ibi ibi5 = ibi4.next;
                    ibi4.next = ibi3;
                    ibi3 = ibi4;
                    ibi4 = ibi5;
                }
                while (ibi3 != null) {
                    ibi2 = ibi3.next;
                    Runnable runnable = ibi3.f13836b;
                    if (runnable instanceof ibk) {
                        ibk ibk = (ibk) runnable;
                        ibr = ibk.f13843a;
                        if (ibr.value == ibk) {
                            if (f13854d.mo8340a(ibr, (Object) ibk, m12636b(ibk.f13844b))) {
                            }
                        } else {
                            continue;
                        }
                    } else {
                        m12638b(runnable, ibi3.f13837c);
                    }
                    ibi3 = ibi2;
                }
                return;
            }
        }
    }

    /* renamed from: b */
    private static void m12638b(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = f13853c;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(runnable);
            String valueOf2 = String.valueOf(executor);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 57 + String.valueOf(valueOf2).length());
            sb.append("RuntimeException while executing runnable ");
            sb.append(valueOf);
            sb.append(" with executor ");
            sb.append(valueOf2);
            logger.logp(level, "com.google.common.util.concurrent.AbstractFuture", "executeListener", sb.toString(), e);
        }
    }

    public Object get() {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) && (!(obj2 instanceof ibk))) {
                return mo8334a(obj2);
            }
            ibq ibq = this.waiters;
            if (ibq != ibq.f13851a) {
                ibq ibq2 = new ibq();
                do {
                    ibq2.mo8344a(ibq);
                    if (f13854d.mo8339a(this, ibq, ibq2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                m12632a(ibq2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof ibk))));
                        return mo8334a(obj);
                    }
                    ibq = this.waiters;
                } while (ibq != ibq.f13851a);
            }
            return mo8334a(this.value);
        }
        throw new InterruptedException();
    }

    public Object get(long j, TimeUnit timeUnit) {
        boolean z;
        long j2;
        boolean z2;
        long j3 = j;
        TimeUnit timeUnit2 = timeUnit;
        long nanos = timeUnit2.toNanos(j3);
        if (!Thread.interrupted()) {
            Object obj = this.value;
            boolean z3 = true;
            if (obj != null) {
                z = true;
            } else {
                z = false;
            }
            if (z && (!(obj instanceof ibk))) {
                return mo8334a(obj);
            }
            if (nanos > 0) {
                j2 = System.nanoTime() + nanos;
            } else {
                j2 = 0;
            }
            if (nanos >= 1000) {
                ibq ibq = this.waiters;
                if (ibq != ibq.f13851a) {
                    ibq ibq2 = new ibq();
                    do {
                        ibq2.mo8344a(ibq);
                        if (!f13854d.mo8339a(this, ibq, ibq2)) {
                            ibq = this.waiters;
                        } else {
                            do {
                                LockSupport.parkNanos(this, nanos);
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.value;
                                    if ((obj2 != null) && (!(obj2 instanceof ibk))) {
                                        return mo8334a(obj2);
                                    }
                                    nanos = j2 - System.nanoTime();
                                } else {
                                    m12632a(ibq2);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            m12632a(ibq2);
                        }
                    } while (ibq != ibq.f13851a);
                }
                return mo8334a(this.value);
            }
            while (nanos > 0) {
                Object obj3 = this.value;
                if (obj3 != null) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2 && (!(obj3 instanceof ibk))) {
                    return mo8334a(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = j2 - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String ibr = toString();
            String lowerCase = timeUnit.toString().toLowerCase(Locale.ROOT);
            String lowerCase2 = timeUnit.toString().toLowerCase(Locale.ROOT);
            StringBuilder sb = new StringBuilder(String.valueOf(lowerCase2).length() + 28);
            sb.append("Waited ");
            sb.append(j3);
            sb.append(" ");
            sb.append(lowerCase2);
            String sb2 = sb.toString();
            if (nanos + 1000 < 0) {
                String concat = String.valueOf(sb2).concat(" (plus ");
                long j4 = -nanos;
                long convert = timeUnit2.convert(j4, TimeUnit.NANOSECONDS);
                long nanos2 = j4 - timeUnit2.toNanos(convert);
                if (convert != 0 && nanos2 <= 1000) {
                    z3 = false;
                }
                if (convert > 0) {
                    String valueOf = String.valueOf(concat);
                    StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 21 + String.valueOf(lowerCase).length());
                    sb3.append(valueOf);
                    sb3.append(convert);
                    sb3.append(" ");
                    sb3.append(lowerCase);
                    String sb4 = sb3.toString();
                    if (z3) {
                        sb4 = String.valueOf(sb4).concat(",");
                    }
                    concat = String.valueOf(sb4).concat(" ");
                }
                if (z3) {
                    String valueOf2 = String.valueOf(concat);
                    StringBuilder sb5 = new StringBuilder(String.valueOf(valueOf2).length() + 33);
                    sb5.append(valueOf2);
                    sb5.append(nanos2);
                    sb5.append(" nanoseconds ");
                    concat = sb5.toString();
                }
                sb2 = String.valueOf(concat).concat("delay)");
            }
            if (isDone()) {
                throw new TimeoutException(String.valueOf(sb2).concat(" but future completed as timeout expired"));
            }
            StringBuilder sb6 = new StringBuilder(String.valueOf(sb2).length() + 5 + String.valueOf(ibr).length());
            sb6.append(sb2);
            sb6.append(" for ");
            sb6.append(ibr);
            throw new TimeoutException(sb6.toString());
        }
        throw new InterruptedException();
    }

    /* renamed from: a */
    private static final Object mo8334a(Object obj) {
        if (obj instanceof ibf) {
            Throwable th = ((ibf) obj).f13832d;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        } else if (obj instanceof ibh) {
            throw new ExecutionException(((ibh) obj).f13834b);
        } else if (obj == f13855e) {
            return null;
        } else {
            return obj;
        }
    }

    /* renamed from: b */
    public static Object m12636b(ieh ieh) {
        Throwable e;
        if (ieh instanceof ibm) {
            Object obj = ((ibr) ieh).value;
            if (!(obj instanceof ibf)) {
                return obj;
            }
            ibf ibf = (ibf) obj;
            if (!ibf.f13831c) {
                return obj;
            }
            Throwable th = ibf.f13832d;
            return th != null ? new ibf(false, th) : ibf.f13830b;
        } else if ((ieh instanceof iff) && (e = ((iff) ieh).mo8349e()) != null) {
            return new ibh(e);
        } else {
            boolean isCancelled = ieh.isCancelled();
            if ((!f13852b) && isCancelled) {
                return ibf.f13830b;
            }
            try {
                Object b = m12637b((Future) ieh);
                if (!isCancelled) {
                    return b == null ? f13855e : b;
                }
                String valueOf = String.valueOf(ieh);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 84);
                sb.append("get() did not throw CancellationException, despite reporting isCancelled() == true: ");
                sb.append(valueOf);
                return new ibf(false, new IllegalArgumentException(sb.toString()));
            } catch (ExecutionException e2) {
                if (!isCancelled) {
                    return new ibh(e2.getCause());
                }
                String valueOf2 = String.valueOf(ieh);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 84);
                sb2.append("get() did not throw CancellationException, despite reporting isCancelled() == true: ");
                sb2.append(valueOf2);
                return new ibf(false, new IllegalArgumentException(sb2.toString(), e2));
            } catch (CancellationException e3) {
                if (isCancelled) {
                    return new ibf(false, e3);
                }
                String valueOf3 = String.valueOf(ieh);
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 77);
                sb3.append("get() threw CancellationException, despite reporting isCancelled() == false: ");
                sb3.append(valueOf3);
                return new ibh(new IllegalArgumentException(sb3.toString(), e3));
            } catch (Throwable th2) {
                return new ibh(th2);
            }
        }
    }

    /* renamed from: b */
    private static Object m12637b(Future future) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException e) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public boolean isCancelled() {
        return this.value instanceof ibf;
    }

    public boolean isDone() {
        Object obj = this.value;
        return (!(obj instanceof ibk)) & (obj != null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8345a(Future future) {
        if ((future != null) && isCancelled()) {
            future.cancel(mo8348d());
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo6386a() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        long delay = ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS);
        StringBuilder sb = new StringBuilder(41);
        sb.append("remaining delay=[");
        sb.append(delay);
        sb.append(" ms]");
        return sb.toString();
    }

    /* renamed from: a */
    private final void m12632a(ibq ibq) {
        ibq.thread = null;
        while (true) {
            ibq ibq2 = this.waiters;
            if (ibq2 != ibq.f13851a) {
                ibq ibq3 = null;
                while (ibq2 != null) {
                    ibq ibq4 = ibq2.next;
                    if (ibq2.thread != null) {
                        ibq3 = ibq2;
                    } else if (ibq3 != null) {
                        ibq3.next = ibq4;
                        if (ibq3.thread == null) {
                        }
                    } else if (!f13854d.mo8339a(this, ibq2, ibq4)) {
                    }
                    ibq2 = ibq4;
                }
                return;
            }
            return;
        }
    }

    /* renamed from: b */
    public boolean mo8346b(Object obj) {
        if (obj == null) {
            obj = f13855e;
        }
        if (!f13854d.mo8340a(this, (Object) null, obj)) {
            return false;
        }
        m12633a(this);
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo6952a(Throwable th) {
        if (!f13854d.mo8340a(this, (Object) null, (Object) new ibh((Throwable) ife.m12898e((Object) th)))) {
            return false;
        }
        m12633a(this);
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo6946a(ieh ieh) {
        ibk ibk;
        ibh ibh;
        ife.m12898e((Object) ieh);
        Object obj = this.value;
        if (obj == null) {
            if (ieh.isDone()) {
                if (!f13854d.mo8340a(this, (Object) null, m12636b(ieh))) {
                    return false;
                }
                m12633a(this);
                return true;
            }
            ibk = new ibk(this, ieh);
            if (!f13854d.mo8340a(this, (Object) null, (Object) ibk)) {
                obj = this.value;
            } else {
                try {
                    ieh.mo53a(ibk, idh.f13918a);
                } catch (Throwable th) {
                    ibh = ibh.f13833a;
                }
                return true;
            }
        }
        if (obj instanceof ibf) {
            ieh.cancel(((ibf) obj).f13831c);
        }
        return false;
        f13854d.mo8340a(this, (Object) ibk, (Object) ibh);
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (isCancelled()) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            m12639b(sb);
        } else {
            m12634a(sb);
        }
        sb.append("]");
        return sb.toString();
    }

    /* renamed from: e */
    public final Throwable mo8349e() {
        if (!(this instanceof ibm)) {
            return null;
        }
        Object obj = this.value;
        if (obj instanceof ibh) {
            return ((ibh) obj).f13834b;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final boolean mo8348d() {
        Object obj = this.value;
        return (obj instanceof ibf) && ((ibf) obj).f13831c;
    }
}
