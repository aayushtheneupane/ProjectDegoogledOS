package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
public abstract class BasePendingResult extends ekx {

    /* renamed from: a */
    private static final ThreadLocal f4981a = new elv();

    /* renamed from: g */
    public static /* synthetic */ int f4982g;

    /* renamed from: b */
    private final elw f4983b;

    /* renamed from: c */
    public final Object f4984c;

    /* renamed from: d */
    public final WeakReference f4985d;

    /* renamed from: e */
    public ela f4986e;

    /* renamed from: f */
    public boolean f4987f;

    /* renamed from: h */
    private final CountDownLatch f4988h;

    /* renamed from: i */
    private final ArrayList f4989i;

    /* renamed from: j */
    private elb f4990j;

    /* renamed from: k */
    private final AtomicReference f4991k;

    /* renamed from: l */
    private Status f4992l;

    /* renamed from: m */
    private volatile boolean f4993m;
    public elx mResultGuardian;

    /* renamed from: n */
    private boolean f4994n;

    /* renamed from: o */
    private boolean f4995o;

    /* renamed from: p */
    private volatile ele f4996p;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract ela mo3504a(Status status);

    @Deprecated
    BasePendingResult() {
        this.f4984c = new Object();
        this.f4988h = new CountDownLatch(1);
        this.f4989i = new ArrayList();
        this.f4991k = new AtomicReference();
        this.f4987f = false;
        this.f4983b = new elw(Looper.getMainLooper());
        this.f4985d = new WeakReference((Object) null);
    }

    @Deprecated
    protected BasePendingResult(Looper looper) {
        this.f4984c = new Object();
        this.f4988h = new CountDownLatch(1);
        this.f4989i = new ArrayList();
        this.f4991k = new AtomicReference();
        this.f4987f = false;
        this.f4983b = new elw(looper);
        this.f4985d = new WeakReference((Object) null);
    }

    protected BasePendingResult(ekv ekv) {
        this.f4984c = new Object();
        this.f4988h = new CountDownLatch(1);
        this.f4989i = new ArrayList();
        this.f4991k = new AtomicReference();
        this.f4987f = false;
        this.f4983b = new elw(ekv.mo4949b());
        this.f4985d = new WeakReference(ekv);
    }

    /* renamed from: a */
    public final void mo3506a(ekw ekw) {
        abj.m117b(true, (Object) "Callback cannot be null.");
        synchronized (this.f4984c) {
            if (!m4946d()) {
                this.f4989i.add(ekw);
            } else {
                ekw.mo4951a(this.f4992l);
            }
        }
    }

    /* renamed from: a */
    public final void mo3510a(TimeUnit timeUnit) {
        abj.m108a(!this.f4993m, (Object) "Result has already been consumed.");
        abj.m108a(true, (Object) "Cannot await if then() has been called.");
        try {
            if (!this.f4988h.await(0, timeUnit)) {
                mo3513c(Status.f4975d);
            }
        } catch (InterruptedException e) {
            mo3513c(Status.f4973b);
        }
        abj.m108a(m4946d(), (Object) "Result is not ready.");
        m4947e();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo3505a() {
        /*
            r2 = this;
            java.lang.Object r0 = r2.f4984c
            monitor-enter(r0)
            boolean r1 = r2.f4994n     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x001e
            boolean r1 = r2.f4993m     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x001e
            ela r1 = r2.f4986e     // Catch:{ all -> 0x0020 }
            m4944b(r1)     // Catch:{ all -> 0x0020 }
            r1 = 1
            r2.f4994n = r1     // Catch:{ all -> 0x0020 }
            com.google.android.gms.common.api.Status r1 = com.google.android.gms.common.api.Status.f4976e     // Catch:{ all -> 0x0020 }
            ela r1 = r2.mo3504a((com.google.android.gms.common.api.Status) r1)     // Catch:{ all -> 0x0020 }
            r2.m4945c((p000.ela) r1)     // Catch:{ all -> 0x0020 }
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            return
        L_0x001e:
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            return
        L_0x0020:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.BasePendingResult.mo3505a():void");
    }

    /* renamed from: c */
    public final void mo3513c(Status status) {
        synchronized (this.f4984c) {
            if (!m4946d()) {
                mo3507a(mo3504a(status));
                this.f4995o = true;
            }
        }
    }

    /* renamed from: e */
    private final ela m4947e() {
        ela ela;
        synchronized (this.f4984c) {
            abj.m108a(!this.f4993m, (Object) "Result has already been consumed.");
            abj.m108a(m4946d(), (Object) "Result is not ready.");
            ela = this.f4986e;
            this.f4986e = null;
            this.f4990j = null;
            this.f4993m = true;
        }
        eos eos = (eos) this.f4991k.getAndSet((Object) null);
        if (eos != null) {
            eos.mo5092a(this);
        }
        return ela;
    }

    /* renamed from: b */
    public final boolean mo3511b() {
        boolean z;
        synchronized (this.f4984c) {
            z = this.f4994n;
        }
        return z;
    }

    /* renamed from: d */
    private final boolean m4946d() {
        return this.f4988h.getCount() == 0;
    }

    /* renamed from: c */
    public final void mo3512c() {
        boolean z = true;
        if (!this.f4987f && !((Boolean) f4981a.get()).booleanValue()) {
            z = false;
        }
        this.f4987f = z;
    }

    /* renamed from: b */
    public static void m4944b(ela ela) {
        if (ela instanceof eky) {
            try {
                ((eky) ela).mo4952a();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(ela);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
                sb.append("Unable to release ");
                sb.append(valueOf);
                Log.w("BasePendingResult", sb.toString(), e);
            }
        }
    }

    /* renamed from: a */
    public final void mo3507a(ela ela) {
        synchronized (this.f4984c) {
            if (this.f4995o || this.f4994n) {
                m4944b(ela);
                return;
            }
            m4946d();
            abj.m108a(!m4946d(), (Object) "Results have already been set");
            abj.m108a(!this.f4993m, (Object) "Result has already been consumed");
            m4945c(ela);
        }
    }

    /* renamed from: c */
    private final void m4945c(ela ela) {
        this.f4986e = ela;
        this.f4988h.countDown();
        this.f4992l = this.f4986e.mo3498a();
        if (this.f4994n) {
            this.f4990j = null;
        } else if (this.f4990j != null) {
            this.f4983b.removeMessages(2);
            this.f4983b.mo4989a(this.f4990j, m4947e());
        } else if (this.f4986e instanceof eky) {
            this.mResultGuardian = new elx(this);
        }
        ArrayList arrayList = this.f4989i;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((ekw) arrayList.get(i)).mo4951a(this.f4992l);
        }
        this.f4989i.clear();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo3508a(p000.elb r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.f4984c
            monitor-enter(r0)
            boolean r1 = r4.f4993m     // Catch:{ all -> 0x002d }
            r2 = 1
            r1 = r1 ^ r2
            java.lang.String r3 = "Result has already been consumed."
            p000.abj.m108a((boolean) r1, (java.lang.Object) r3)     // Catch:{ all -> 0x002d }
            java.lang.String r1 = "Cannot set callbacks if then() has been called."
            p000.abj.m108a((boolean) r2, (java.lang.Object) r1)     // Catch:{ all -> 0x002d }
            boolean r1 = r4.mo3511b()     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x002b
            boolean r1 = r4.m4946d()     // Catch:{ all -> 0x002d }
            if (r1 == 0) goto L_0x0027
            elw r1 = r4.f4983b     // Catch:{ all -> 0x002d }
            ela r2 = r4.m4947e()     // Catch:{ all -> 0x002d }
            r1.mo4989a(r5, r2)     // Catch:{ all -> 0x002d }
            goto L_0x0029
        L_0x0027:
            r4.f4990j = r5     // Catch:{ all -> 0x002d }
        L_0x0029:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.BasePendingResult.mo3508a(elb):void");
    }

    /* renamed from: a */
    public final void mo3509a(eos eos) {
        this.f4991k.set(eos);
    }
}
