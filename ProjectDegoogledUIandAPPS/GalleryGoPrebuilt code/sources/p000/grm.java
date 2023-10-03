package p000;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.Executor;

/* renamed from: grm */
/* compiled from: PG */
public final class grm implements Executor {

    /* renamed from: a */
    public final Object f11904a = new Object();

    /* renamed from: b */
    public final Deque f11905b = new ArrayDeque();

    /* renamed from: c */
    public boolean f11906c = false;

    /* renamed from: d */
    public boolean f11907d;

    /* renamed from: e */
    private final boolean f11908e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public Runnable f11909f;

    /* renamed from: g */
    private final Thread f11910g;

    /* renamed from: h */
    private final grl f11911h = new grl(this);

    private grm(Thread thread, gtf gtf) {
        ife.m12898e((Object) gtf);
        this.f11908e = true;
        this.f11910g = thread;
    }

    /* renamed from: a */
    public static grm m10663a(gtf gtf) {
        return new grm(fxk.m9838d().getLooper().getThread(), gtf);
    }

    /* renamed from: b */
    public final Queue mo6961b() {
        ArrayDeque arrayDeque;
        synchronized (this.f11904a) {
            ife.m12876b(this.f11907d, (Object) "Executor may only be drained when it is suspended.");
            arrayDeque = new ArrayDeque(this.f11905b);
            this.f11905b.clear();
        }
        return arrayDeque;
    }

    public final void execute(Runnable runnable) {
        synchronized (this.f11904a) {
            this.f11905b.add(runnable);
        }
        m10665d();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004b, code lost:
        if (r2 != 3) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004d, code lost:
        p000.ife.m12896d(r0);
        r8.f11911h.run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0055, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0056, code lost:
        if (r2 != 2) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0058, code lost:
        if (r6 == null) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005a, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005e, code lost:
        p000.ife.m12876b(r3, (java.lang.Object) "Worker to be scheduled was not set correctly in mutexed block.");
        p000.fxk.m9824a(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m10665d() {
        /*
            r8 = this;
            java.lang.Thread r0 = r8.f11910g
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            boolean r0 = r0.equals(r1)
            java.lang.Object r1 = r8.f11904a
            monitor-enter(r1)
            boolean r2 = r8.f11906c     // Catch:{ all -> 0x0069 }
            if (r2 != 0) goto L_0x0067
            boolean r2 = r8.f11907d     // Catch:{ all -> 0x0069 }
            r3 = 0
            r4 = 2
            r5 = 3
            r6 = 0
            r7 = 1
            if (r2 == 0) goto L_0x001c
        L_0x001a:
            r2 = 1
            goto L_0x004a
        L_0x001c:
            if (r0 == 0) goto L_0x002c
            boolean r2 = r8.f11908e     // Catch:{ all -> 0x0069 }
            if (r2 == 0) goto L_0x002c
            java.lang.Runnable r2 = r8.f11909f     // Catch:{ all -> 0x0069 }
            p000.fxk.m9832b((java.lang.Runnable) r2)     // Catch:{ all -> 0x0069 }
            r8.f11909f = r6     // Catch:{ all -> 0x0069 }
            r2 = 3
            goto L_0x004a
        L_0x002c:
            java.lang.Runnable r2 = r8.f11909f     // Catch:{ all -> 0x0069 }
            if (r2 != 0) goto L_0x001a
            if (r0 == 0) goto L_0x003a
            boolean r2 = r8.f11908e     // Catch:{ all -> 0x0069 }
            if (r2 != 0) goto L_0x0037
            goto L_0x003a
        L_0x0037:
            r2 = 0
            goto L_0x003b
        L_0x003a:
            r2 = 1
        L_0x003b:
            java.lang.String r6 = "This processQueue decided to schedule a worker and should have picked something else"
            p000.ife.m12876b((boolean) r2, (java.lang.Object) r6)     // Catch:{ all -> 0x0069 }
            grl r2 = r8.f11911h     // Catch:{ all -> 0x0069 }
            java.lang.Runnable r6 = p000.hmq.m11748a((java.lang.Runnable) r2)     // Catch:{ all -> 0x0069 }
            r8.f11909f = r6     // Catch:{ all -> 0x0069 }
            r2 = 2
        L_0x004a:
            monitor-exit(r1)     // Catch:{ all -> 0x0069 }
            if (r2 != r5) goto L_0x0056
            p000.ife.m12896d((boolean) r0)
            grl r0 = r8.f11911h
            r0.run()
            return
        L_0x0056:
            if (r2 != r4) goto L_0x0066
            if (r6 == 0) goto L_0x005c
            r3 = 1
            goto L_0x005e
        L_0x005c:
        L_0x005e:
            java.lang.String r0 = "Worker to be scheduled was not set correctly in mutexed block."
            p000.ife.m12876b((boolean) r3, (java.lang.Object) r0)
            p000.fxk.m9824a((java.lang.Runnable) r6)
        L_0x0066:
            return
        L_0x0067:
            monitor-exit(r1)     // Catch:{ all -> 0x0069 }
            return
        L_0x0069:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0069 }
            goto L_0x006d
        L_0x006c:
            throw r0
        L_0x006d:
            goto L_0x006c
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.grm.m10665d():void");
    }

    /* renamed from: c */
    public final void mo6962c() {
        fxk.m9830b();
        synchronized (this.f11904a) {
            this.f11907d = false;
        }
        m10665d();
    }

    /* renamed from: a */
    public final void mo6960a() {
        fxk.m9830b();
        synchronized (this.f11904a) {
            this.f11907d = true;
        }
    }
}
