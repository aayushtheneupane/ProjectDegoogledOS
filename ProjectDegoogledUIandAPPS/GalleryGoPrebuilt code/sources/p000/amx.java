package p000;

import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: amx */
/* compiled from: PG */
public final class amx implements ieh {

    /* renamed from: a */
    public static final boolean f799a = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));

    /* renamed from: b */
    public static final amo f800b;

    /* renamed from: f */
    private static final Logger f801f;

    /* renamed from: g */
    private static final Object f802g = new Object();

    /* renamed from: c */
    public volatile Object f803c;

    /* renamed from: d */
    public volatile ams f804d;

    /* renamed from: e */
    public volatile amw f805e;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: amt} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: amv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: amt} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: amt} */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            java.lang.String r0 = "c"
            java.lang.Class<amx> r1 = p000.amx.class
            java.lang.String r2 = "guava.concurrent.generate_cancellation_cause"
            java.lang.String r3 = "false"
            java.lang.String r2 = java.lang.System.getProperty(r2, r3)
            boolean r2 = java.lang.Boolean.parseBoolean(r2)
            f799a = r2
            java.lang.String r2 = r1.getName()
            java.util.logging.Logger r2 = java.util.logging.Logger.getLogger(r2)
            f801f = r2
            amt r2 = new amt     // Catch:{ all -> 0x004c }
            java.lang.Class<amw> r3 = p000.amw.class
            java.lang.Class<java.lang.Thread> r4 = java.lang.Thread.class
            java.lang.String r5 = "b"
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r3, r4, r5)     // Catch:{ all -> 0x004c }
            java.lang.Class<amw> r3 = p000.amw.class
            java.lang.Class<amw> r5 = p000.amw.class
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r3, r5, r0)     // Catch:{ all -> 0x004c }
            java.lang.Class<amw> r3 = p000.amw.class
            java.lang.String r6 = "e"
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r6 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r1, r3, r6)     // Catch:{ all -> 0x004c }
            java.lang.Class<ams> r3 = p000.ams.class
            java.lang.String r7 = "d"
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r1, r3, r7)     // Catch:{ all -> 0x004c }
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r8 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r1, r3, r0)     // Catch:{ all -> 0x004c }
            r3 = r2
            r3.<init>(r4, r5, r6, r7, r8)     // Catch:{ all -> 0x004c }
            r0 = 0
            goto L_0x0052
        L_0x004c:
            r0 = move-exception
            amv r2 = new amv
            r2.<init>()
        L_0x0052:
            f800b = r2
            if (r0 == 0) goto L_0x005f
            java.util.logging.Logger r1 = f801f
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "SafeAtomicHelper is broken!"
            r1.log(r2, r3, r0)
        L_0x005f:
            java.lang.Object r0 = new java.lang.Object
            r0.<init>()
            f802g = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.amx.<clinit>():void");
    }

    protected amx() {
    }

    /* renamed from: a */
    private final void m788a(StringBuilder sb) {
        try {
            Object a = m785a((Future) this);
            sb.append("SUCCESS, result=[");
            sb.append(m790c(a));
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
    public final void mo53a(Runnable runnable, Executor executor) {
        m784a((Object) runnable);
        m784a((Object) executor);
        ams ams = this.f804d;
        if (ams != ams.f785a) {
            ams ams2 = new ams(runnable, executor);
            do {
                ams2.f788d = ams;
                if (!f800b.mo651a(this, ams, ams2)) {
                    ams = this.f804d;
                } else {
                    return;
                }
            } while (ams != ams.f785a);
        }
        m789b(runnable, executor);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: ieh} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: amx} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean cancel(boolean r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.f803c
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0008
            r3 = 1
            goto L_0x0009
        L_0x0008:
            r3 = 0
        L_0x0009:
            boolean r4 = r0 instanceof p000.amu
            r3 = r3 | r4
            if (r3 == 0) goto L_0x0064
            boolean r3 = f799a
            if (r3 == 0) goto L_0x001f
            amp r3 = new amp
            java.util.concurrent.CancellationException r4 = new java.util.concurrent.CancellationException
            java.lang.String r5 = "Future.cancel() was called."
            r4.<init>(r5)
            r3.<init>(r8, r4)
            goto L_0x0026
        L_0x001f:
            if (r8 == 0) goto L_0x0024
            amp r3 = p000.amp.f779a
            goto L_0x0026
        L_0x0024:
            amp r3 = p000.amp.f780b
        L_0x0026:
            r5 = 0
            r4 = r7
        L_0x0028:
            amo r6 = f800b
            boolean r6 = r6.mo653a((p000.amx) r4, (java.lang.Object) r0, (java.lang.Object) r3)
            if (r6 == 0) goto L_0x005c
            m787a((p000.amx) r4)
            boolean r4 = r0 instanceof p000.amu
            if (r4 == 0) goto L_0x0058
            amu r0 = (p000.amu) r0
            ieh r0 = r0.f795b
            boolean r4 = r0 instanceof p000.amx
            if (r4 == 0) goto L_0x0052
            r4 = r0
            amx r4 = (p000.amx) r4
            java.lang.Object r0 = r4.f803c
            if (r0 != 0) goto L_0x0048
            r5 = 1
            goto L_0x004a
        L_0x0048:
            r5 = 0
        L_0x004a:
            boolean r6 = r0 instanceof p000.amu
            r5 = r5 | r6
            if (r5 != 0) goto L_0x0050
            goto L_0x005a
        L_0x0050:
            r5 = 1
            goto L_0x0028
        L_0x0052:
            r0.cancel(r8)
            r1 = 1
            goto L_0x0066
        L_0x0058:
        L_0x005a:
            r1 = 1
            goto L_0x0066
        L_0x005c:
            java.lang.Object r0 = r4.f803c
            boolean r6 = r0 instanceof p000.amu
            if (r6 != 0) goto L_0x0028
            r1 = r5
            goto L_0x0066
        L_0x0064:
        L_0x0066:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.amx.cancel(boolean):boolean");
    }

    /* renamed from: a */
    static Object m784a(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw null;
    }

    /* renamed from: a */
    static void m787a(amx amx) {
        ams ams;
        ams ams2 = null;
        while (true) {
            amw amw = amx.f805e;
            if (f800b.mo652a(amx, amw, amw.f796a)) {
                while (amw != null) {
                    Thread thread = amw.f797b;
                    if (thread != null) {
                        amw.f797b = null;
                        LockSupport.unpark(thread);
                    }
                    amw = amw.f798c;
                }
                do {
                    ams = amx.f804d;
                } while (!f800b.mo651a(amx, ams, ams.f785a));
                ams ams3 = ams2;
                ams ams4 = ams;
                while (ams4 != null) {
                    ams ams5 = ams4.f788d;
                    ams4.f788d = ams3;
                    ams3 = ams4;
                    ams4 = ams5;
                }
                while (ams3 != null) {
                    ams2 = ams3.f788d;
                    Runnable runnable = ams3.f786b;
                    if (runnable instanceof amu) {
                        amu amu = (amu) runnable;
                        amx = amu.f794a;
                        if (amx.f803c == amu) {
                            if (f800b.mo653a(amx, (Object) amu, m783a(amu.f795b))) {
                            }
                        } else {
                            continue;
                        }
                    } else {
                        m789b(runnable, ams3.f787c);
                    }
                    ams3 = ams2;
                }
                return;
            }
        }
    }

    /* renamed from: b */
    private static void m789b(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = f801f;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
        }
    }

    public final Object get() {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.f803c;
            if ((obj2 != null) && (!(obj2 instanceof amu))) {
                return m791d(obj2);
            }
            amw amw = this.f805e;
            if (amw != amw.f796a) {
                amw amw2 = new amw();
                do {
                    amw2.mo656a(amw);
                    if (f800b.mo652a(this, amw, amw2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.f803c;
                            } else {
                                m786a(amw2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof amu))));
                        return m791d(obj);
                    }
                    amw = this.f805e;
                } while (amw != amw.f796a);
            }
            return m791d(this.f803c);
        }
        throw new InterruptedException();
    }

    public final Object get(long j, TimeUnit timeUnit) {
        boolean z;
        long j2;
        boolean z2;
        long j3 = j;
        TimeUnit timeUnit2 = timeUnit;
        long nanos = timeUnit2.toNanos(j3);
        if (!Thread.interrupted()) {
            Object obj = this.f803c;
            boolean z3 = true;
            if (obj != null) {
                z = true;
            } else {
                z = false;
            }
            if (z && (!(obj instanceof amu))) {
                return m791d(obj);
            }
            if (nanos > 0) {
                j2 = System.nanoTime() + nanos;
            } else {
                j2 = 0;
            }
            if (nanos >= 1000) {
                amw amw = this.f805e;
                if (amw != amw.f796a) {
                    amw amw2 = new amw();
                    do {
                        amw2.mo656a(amw);
                        if (!f800b.mo652a(this, amw, amw2)) {
                            amw = this.f805e;
                        } else {
                            do {
                                LockSupport.parkNanos(this, nanos);
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.f803c;
                                    if ((obj2 != null) && (!(obj2 instanceof amu))) {
                                        return m791d(obj2);
                                    }
                                    nanos = j2 - System.nanoTime();
                                } else {
                                    m786a(amw2);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            m786a(amw2);
                        }
                    } while (amw != amw.f796a);
                }
                return m791d(this.f803c);
            }
            while (nanos > 0) {
                Object obj3 = this.f803c;
                if (obj3 != null) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2 && (!(obj3 instanceof amu))) {
                    return m791d(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = j2 - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String amx = toString();
            String lowerCase = timeUnit.toString().toLowerCase(Locale.ROOT);
            String str = "Waited " + j3 + " " + timeUnit.toString().toLowerCase(Locale.ROOT);
            if (nanos + 1000 < 0) {
                String str2 = str + " (plus ";
                long j4 = -nanos;
                long convert = timeUnit2.convert(j4, TimeUnit.NANOSECONDS);
                long nanos2 = j4 - timeUnit2.toNanos(convert);
                if (convert != 0 && nanos2 <= 1000) {
                    z3 = false;
                }
                if (convert > 0) {
                    String str3 = str2 + convert + " " + lowerCase;
                    if (z3) {
                        str3 = str3 + ",";
                    }
                    str2 = str3 + " ";
                }
                if (z3) {
                    str2 = str2 + nanos2 + " nanoseconds ";
                }
                str = str2 + "delay)";
            }
            if (isDone()) {
                throw new TimeoutException(str + " but future completed as timeout expired");
            }
            throw new TimeoutException(str + " for " + amx);
        }
        throw new InterruptedException();
    }

    /* renamed from: d */
    private static final Object m791d(Object obj) {
        if (obj instanceof amp) {
            Throwable th = ((amp) obj).f782d;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        } else if (obj instanceof amr) {
            throw new ExecutionException(((amr) obj).f784b);
        } else if (obj == f802g) {
            return null;
        } else {
            return obj;
        }
    }

    /* renamed from: a */
    static Object m783a(ieh ieh) {
        if (ieh instanceof amx) {
            Object obj = ((amx) ieh).f803c;
            if (!(obj instanceof amp)) {
                return obj;
            }
            amp amp = (amp) obj;
            if (!amp.f781c) {
                return obj;
            }
            Throwable th = amp.f782d;
            return th != null ? new amp(false, th) : amp.f780b;
        }
        boolean isCancelled = ieh.isCancelled();
        if ((!f799a) && isCancelled) {
            return amp.f780b;
        }
        try {
            Object a = m785a((Future) ieh);
            return a == null ? f802g : a;
        } catch (ExecutionException e) {
            return new amr(e.getCause());
        } catch (CancellationException e2) {
            if (isCancelled) {
                return new amp(false, e2);
            }
            return new amr(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + ieh, e2));
        } catch (Throwable th2) {
            return new amr(th2);
        }
    }

    /* renamed from: a */
    private static Object m785a(Future future) {
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

    public final boolean isCancelled() {
        return this.f803c instanceof amp;
    }

    public final boolean isDone() {
        Object obj = this.f803c;
        return (!(obj instanceof amu)) & (obj != null);
    }

    /* renamed from: a */
    private final void m786a(amw amw) {
        amw.f797b = null;
        while (true) {
            amw amw2 = this.f805e;
            if (amw2 != amw.f796a) {
                amw amw3 = null;
                while (amw2 != null) {
                    amw amw4 = amw2.f798c;
                    if (amw2.f797b != null) {
                        amw3 = amw2;
                    } else if (amw3 != null) {
                        amw3.f798c = amw4;
                        if (amw3.f797b == null) {
                        }
                    } else if (!f800b.mo652a(this, amw2, amw4)) {
                    }
                    amw2 = amw4;
                }
                return;
            }
            return;
        }
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (isCancelled()) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            m788a(sb);
        } else {
            try {
                Object obj = this.f803c;
                if (obj instanceof amu) {
                    str = "setFuture=[" + m790c(((amu) obj).f795b) + "]";
                } else {
                    str = null;
                }
            } catch (RuntimeException e) {
                str = "Exception thrown from implementation: " + e.getClass();
            }
            if (str != null && !str.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(str);
                sb.append("]");
            } else if (!isDone()) {
                sb.append("PENDING");
            } else {
                m788a(sb);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* renamed from: c */
    private final String m790c(Object obj) {
        return obj == this ? "this future" : String.valueOf(obj);
    }

    private amx(byte[] bArr) {
    }

    /* renamed from: a */
    public static amx m782a() {
        return new amx((byte[]) null);
    }

    /* renamed from: b */
    public final void mo659b(Object obj) {
        if (obj == null) {
            obj = f802g;
        }
        if (f800b.mo653a(this, (Object) null, obj)) {
            m787a(this);
        }
    }

    /* renamed from: a */
    public final void mo657a(Throwable th) {
        if (f800b.mo653a(this, (Object) null, (Object) new amr((Throwable) m784a((Object) th)))) {
            m787a(this);
        }
    }

    /* renamed from: b */
    public final void mo658b(ieh ieh) {
        amu amu;
        amr amr;
        m784a((Object) ieh);
        Object obj = this.f803c;
        if (obj == null) {
            if (ieh.isDone()) {
                if (f800b.mo653a(this, (Object) null, m783a(ieh))) {
                    m787a(this);
                    return;
                }
                return;
            }
            amu = new amu(this, ieh);
            if (!f800b.mo653a(this, (Object) null, (Object) amu)) {
                obj = this.f803c;
            } else {
                try {
                    ieh.mo53a(amu, amy.f806a);
                    return;
                } catch (Throwable th) {
                    amr = amr.f783a;
                }
            }
        }
        if (obj instanceof amp) {
            ieh.cancel(((amp) obj).f781c);
            return;
        }
        return;
        f800b.mo653a(this, (Object) amu, (Object) amr);
    }
}
