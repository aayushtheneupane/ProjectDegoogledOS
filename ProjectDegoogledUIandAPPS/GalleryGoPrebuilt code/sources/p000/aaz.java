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

/* renamed from: aaz */
/* compiled from: PG */
public abstract class aaz implements ieh {

    /* renamed from: a */
    public static final boolean f58a = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));

    /* renamed from: b */
    public static final Logger f59b;

    /* renamed from: c */
    public static final aaq f60c;

    /* renamed from: d */
    public static final Object f61d = new Object();
    public volatile aau listeners;
    public volatile Object value;
    public volatile aay waiters;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: aav} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: aax} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: aav} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: aav} */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            java.lang.Class<aaz> r0 = p000.aaz.class
            java.lang.String r1 = "guava.concurrent.generate_cancellation_cause"
            java.lang.String r2 = "false"
            java.lang.String r1 = java.lang.System.getProperty(r1, r2)
            boolean r1 = java.lang.Boolean.parseBoolean(r1)
            f58a = r1
            java.lang.String r1 = r0.getName()
            java.util.logging.Logger r1 = java.util.logging.Logger.getLogger(r1)
            f59b = r1
            aav r1 = new aav     // Catch:{ all -> 0x004e }
            java.lang.Class<aay> r2 = p000.aay.class
            java.lang.Class<java.lang.Thread> r3 = java.lang.Thread.class
            java.lang.String r4 = "thread"
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r2, r3, r4)     // Catch:{ all -> 0x004e }
            java.lang.Class<aay> r2 = p000.aay.class
            java.lang.Class<aay> r4 = p000.aay.class
            java.lang.String r5 = "next"
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r2, r4, r5)     // Catch:{ all -> 0x004e }
            java.lang.Class<aay> r2 = p000.aay.class
            java.lang.String r5 = "waiters"
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r0, r2, r5)     // Catch:{ all -> 0x004e }
            java.lang.Class<aau> r2 = p000.aau.class
            java.lang.String r6 = "listeners"
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r6 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r0, r2, r6)     // Catch:{ all -> 0x004e }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            java.lang.String r7 = "value"
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r0, r2, r7)     // Catch:{ all -> 0x004e }
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x004e }
            r0 = 0
            goto L_0x0054
        L_0x004e:
            r0 = move-exception
            aax r1 = new aax
            r1.<init>()
        L_0x0054:
            f60c = r1
            if (r0 == 0) goto L_0x0061
            java.util.logging.Logger r1 = f59b
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "SafeAtomicHelper is broken!"
            r1.log(r2, r3, r0)
        L_0x0061:
            java.lang.Object r0 = new java.lang.Object
            r0.<init>()
            f61d = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.aaz.<clinit>():void");
    }

    protected aaz() {
    }

    /* renamed from: a */
    private final void m51a(StringBuilder sb) {
        try {
            Object a = m48a((Future) this);
            sb.append("SUCCESS, result=[");
            sb.append(m54c(a));
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
        m52b(runnable);
        m52b(executor);
        aau aau = this.listeners;
        if (aau != aau.f47a) {
            aau aau2 = new aau(runnable, executor);
            do {
                aau2.next = aau;
                if (!f60c.mo46a(this, aau, aau2)) {
                    aau = this.listeners;
                } else {
                    return;
                }
            } while (aau != aau.f47a);
        }
        m53b(runnable, executor);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: ieh} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: aaz} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean cancel(boolean r8) {
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
            boolean r4 = r0 instanceof p000.aaw
            r3 = r3 | r4
            if (r3 == 0) goto L_0x0064
            boolean r3 = f58a
            if (r3 == 0) goto L_0x001f
            aar r3 = new aar
            java.util.concurrent.CancellationException r4 = new java.util.concurrent.CancellationException
            java.lang.String r5 = "Future.cancel() was called."
            r4.<init>(r5)
            r3.<init>(r8, r4)
            goto L_0x0026
        L_0x001f:
            if (r8 == 0) goto L_0x0024
            aar r3 = p000.aar.f41a
            goto L_0x0026
        L_0x0024:
            aar r3 = p000.aar.f42b
        L_0x0026:
            r5 = 0
            r4 = r7
        L_0x0028:
            aaq r6 = f60c
            boolean r6 = r6.mo48a((p000.aaz) r4, (java.lang.Object) r0, (java.lang.Object) r3)
            if (r6 == 0) goto L_0x005c
            m50a((p000.aaz) r4)
            boolean r4 = r0 instanceof p000.aaw
            if (r4 == 0) goto L_0x0058
            aaw r0 = (p000.aaw) r0
            ieh r0 = r0.f56b
            boolean r4 = r0 instanceof p000.aaz
            if (r4 == 0) goto L_0x0052
            r4 = r0
            aaz r4 = (p000.aaz) r4
            java.lang.Object r0 = r4.value
            if (r0 != 0) goto L_0x0048
            r5 = 1
            goto L_0x004a
        L_0x0048:
            r5 = 0
        L_0x004a:
            boolean r6 = r0 instanceof p000.aaw
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
            java.lang.Object r0 = r4.value
            boolean r6 = r0 instanceof p000.aaw
            if (r6 != 0) goto L_0x0028
            r1 = r5
            goto L_0x0066
        L_0x0064:
        L_0x0066:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.aaz.cancel(boolean):boolean");
    }

    /* renamed from: b */
    static Object m52b(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw null;
    }

    /* renamed from: a */
    static void m50a(aaz aaz) {
        aau aau;
        aau aau2 = null;
        while (true) {
            aay aay = aaz.waiters;
            if (f60c.mo47a(aaz, aay, aay.f57a)) {
                while (aay != null) {
                    Thread thread = aay.thread;
                    if (thread != null) {
                        aay.thread = null;
                        LockSupport.unpark(thread);
                    }
                    aay = aay.next;
                }
                do {
                    aau = aaz.listeners;
                } while (!f60c.mo46a(aaz, aau, aau.f47a));
                aau aau3 = aau2;
                aau aau4 = aau;
                while (aau4 != null) {
                    aau aau5 = aau4.next;
                    aau4.next = aau3;
                    aau3 = aau4;
                    aau4 = aau5;
                }
                while (aau3 != null) {
                    aau2 = aau3.next;
                    Runnable runnable = aau3.f48b;
                    if (runnable instanceof aaw) {
                        aaw aaw = (aaw) runnable;
                        aaz = aaw.f55a;
                        if (aaz.value == aaw) {
                            if (f60c.mo48a(aaz, (Object) aaw, m47a(aaw.f56b))) {
                            }
                        } else {
                            continue;
                        }
                    } else {
                        m53b(runnable, aau3.f49c);
                    }
                    aau3 = aau2;
                }
                return;
            }
        }
    }

    /* renamed from: b */
    private static void m53b(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = f59b;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
        }
    }

    public final Object get() {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) && (!(obj2 instanceof aaw))) {
                return m55d(obj2);
            }
            aay aay = this.waiters;
            if (aay != aay.f57a) {
                aay aay2 = new aay();
                do {
                    aay2.mo51a(aay);
                    if (f60c.mo47a(this, aay, aay2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                m49a(aay2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof aaw))));
                        return m55d(obj);
                    }
                    aay = this.waiters;
                } while (aay != aay.f57a);
            }
            return m55d(this.value);
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
            Object obj = this.value;
            boolean z3 = true;
            if (obj != null) {
                z = true;
            } else {
                z = false;
            }
            if (z && (!(obj instanceof aaw))) {
                return m55d(obj);
            }
            if (nanos > 0) {
                j2 = System.nanoTime() + nanos;
            } else {
                j2 = 0;
            }
            if (nanos >= 1000) {
                aay aay = this.waiters;
                if (aay != aay.f57a) {
                    aay aay2 = new aay();
                    do {
                        aay2.mo51a(aay);
                        if (!f60c.mo47a(this, aay, aay2)) {
                            aay = this.waiters;
                        } else {
                            do {
                                LockSupport.parkNanos(this, nanos);
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.value;
                                    if ((obj2 != null) && (!(obj2 instanceof aaw))) {
                                        return m55d(obj2);
                                    }
                                    nanos = j2 - System.nanoTime();
                                } else {
                                    m49a(aay2);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            m49a(aay2);
                        }
                    } while (aay != aay.f57a);
                }
                return m55d(this.value);
            }
            while (nanos > 0) {
                Object obj3 = this.value;
                if (obj3 != null) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2 && (!(obj3 instanceof aaw))) {
                    return m55d(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = j2 - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String aaz = toString();
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
            throw new TimeoutException(str + " for " + aaz);
        }
        throw new InterruptedException();
    }

    /* renamed from: d */
    private static final Object m55d(Object obj) {
        if (obj instanceof aar) {
            Throwable th = ((aar) obj).f44d;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        } else if (obj instanceof aat) {
            throw new ExecutionException(((aat) obj).f46b);
        } else if (obj == f61d) {
            return null;
        } else {
            return obj;
        }
    }

    /* renamed from: a */
    private static Object m47a(ieh ieh) {
        if (ieh instanceof aaz) {
            Object obj = ((aaz) ieh).value;
            if (!(obj instanceof aar)) {
                return obj;
            }
            aar aar = (aar) obj;
            if (!aar.f43c) {
                return obj;
            }
            Throwable th = aar.f44d;
            return th != null ? new aar(false, th) : aar.f42b;
        }
        boolean isCancelled = ieh.isCancelled();
        if ((!f58a) && isCancelled) {
            return aar.f42b;
        }
        try {
            Object a = m48a((Future) ieh);
            return a == null ? f61d : a;
        } catch (ExecutionException e) {
            return new aat(e.getCause());
        } catch (CancellationException e2) {
            if (isCancelled) {
                return new aar(false, e2);
            }
            return new aat(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + ieh, e2));
        } catch (Throwable th2) {
            return new aat(th2);
        }
    }

    /* renamed from: a */
    private static Object m48a(Future future) {
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
        return this.value instanceof aar;
    }

    public final boolean isDone() {
        Object obj = this.value;
        return (!(obj instanceof aaw)) & (obj != null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo52a() {
        Object obj = this.value;
        if (obj instanceof aaw) {
            return "setFuture=[" + m54c(((aaw) obj).f56b) + "]";
        } else if (!(this instanceof ScheduledFuture)) {
            return null;
        } else {
            return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
        }
    }

    /* renamed from: a */
    private final void m49a(aay aay) {
        aay.thread = null;
        while (true) {
            aay aay2 = this.waiters;
            if (aay2 != aay.f57a) {
                aay aay3 = null;
                while (aay2 != null) {
                    aay aay4 = aay2.next;
                    if (aay2.thread != null) {
                        aay3 = aay2;
                    } else if (aay3 != null) {
                        aay3.next = aay4;
                        if (aay3.thread == null) {
                        }
                    } else if (!f60c.mo47a(this, aay2, aay4)) {
                    }
                    aay2 = aay4;
                }
                return;
            }
            return;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo54a(Object obj) {
        if (obj == null) {
            obj = f61d;
        }
        if (!f60c.mo48a(this, (Object) null, obj)) {
            return false;
        }
        m50a(this);
        return true;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (isCancelled()) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            m51a(sb);
        } else {
            try {
                str = mo52a();
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
                m51a(sb);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* renamed from: c */
    private final String m54c(Object obj) {
        return obj == this ? "this future" : String.valueOf(obj);
    }
}
