package p000;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

/* renamed from: ieu */
/* compiled from: PG */
final class ieu implements Executor {

    /* renamed from: a */
    public static final Logger f13969a = Logger.getLogger(ieu.class.getName());

    /* renamed from: b */
    public final Deque f13970b = new ArrayDeque();

    /* renamed from: c */
    public long f13971c = 0;

    /* renamed from: d */
    public int f13972d = 1;

    /* renamed from: e */
    private final Executor f13973e;

    /* renamed from: f */
    private final iet f13974f = new iet(this);

    public ieu(Executor executor) {
        this.f13973e = (Executor) ife.m12898e((Object) executor);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r7.f13973e.execute(r7.f13974f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        if (r7.f13972d != 2) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        r0 = r7.f13970b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        if (r7.f13971c != r3) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
        if (r7.f13972d != 2) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
        r7.f13972d = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0039, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0044, code lost:
        monitor-enter(r7.f13970b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r3 = r7.f13972d;
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0049, code lost:
        if (r3 == 1) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x004c, code lost:
        if (r3 != 2) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0055, code lost:
        if (r7.f13970b.removeLastOccurrence(r1) != false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0057, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x005c, code lost:
        if ((r0 instanceof java.util.concurrent.RejectedExecutionException) != false) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0061, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0062, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void execute(java.lang.Runnable r8) {
        /*
            r7 = this;
            p000.ife.m12898e((java.lang.Object) r8)
            java.util.Deque r0 = r7.f13970b
            monitor-enter(r0)
            int r1 = r7.f13972d     // Catch:{ all -> 0x006d }
            r2 = 4
            if (r1 != r2) goto L_0x000c
            goto L_0x0066
        L_0x000c:
            r2 = 3
            if (r1 == r2) goto L_0x0066
            long r3 = r7.f13971c     // Catch:{ all -> 0x006d }
            ies r1 = new ies     // Catch:{ all -> 0x006d }
            r1.<init>(r8)     // Catch:{ all -> 0x006d }
            java.util.Deque r8 = r7.f13970b     // Catch:{ all -> 0x006d }
            r8.add(r1)     // Catch:{ all -> 0x006d }
            r8 = 2
            r7.f13972d = r8     // Catch:{ all -> 0x006d }
            monitor-exit(r0)     // Catch:{ all -> 0x006d }
            java.util.concurrent.Executor r0 = r7.f13973e     // Catch:{ RuntimeException -> 0x0041, Error -> 0x003f }
            iet r5 = r7.f13974f     // Catch:{ RuntimeException -> 0x0041, Error -> 0x003f }
            r0.execute(r5)     // Catch:{ RuntimeException -> 0x0041, Error -> 0x003f }
            int r0 = r7.f13972d
            if (r0 != r8) goto L_0x003e
            java.util.Deque r0 = r7.f13970b
            monitor-enter(r0)
            long r5 = r7.f13971c     // Catch:{ all -> 0x003b }
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x0039
            int r1 = r7.f13972d     // Catch:{ all -> 0x003b }
            if (r1 != r8) goto L_0x0039
            r7.f13972d = r2     // Catch:{ all -> 0x003b }
        L_0x0039:
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x003b:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            throw r8
        L_0x003e:
            return
        L_0x003f:
            r0 = move-exception
            goto L_0x0042
        L_0x0041:
            r0 = move-exception
        L_0x0042:
            java.util.Deque r2 = r7.f13970b
            monitor-enter(r2)
            int r3 = r7.f13972d     // Catch:{ all -> 0x0063 }
            r4 = 0
            r5 = 1
            if (r3 != r5) goto L_0x004c
            goto L_0x004f
        L_0x004c:
            if (r3 == r8) goto L_0x004f
        L_0x004e:
            goto L_0x005a
        L_0x004f:
            java.util.Deque r8 = r7.f13970b     // Catch:{ all -> 0x0063 }
            boolean r8 = r8.removeLastOccurrence(r1)     // Catch:{ all -> 0x0063 }
            if (r8 == 0) goto L_0x0059
            r4 = 1
            goto L_0x005a
        L_0x0059:
            goto L_0x004e
        L_0x005a:
            boolean r8 = r0 instanceof java.util.concurrent.RejectedExecutionException     // Catch:{ all -> 0x0063 }
            if (r8 == 0) goto L_0x0062
            if (r4 != 0) goto L_0x0062
            monitor-exit(r2)     // Catch:{ all -> 0x0063 }
            return
        L_0x0062:
            throw r0     // Catch:{ all -> 0x0063 }
        L_0x0063:
            r8 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0063 }
            throw r8
        L_0x0066:
            java.util.Deque r1 = r7.f13970b     // Catch:{ all -> 0x006d }
            r1.add(r8)     // Catch:{ all -> 0x006d }
            monitor-exit(r0)     // Catch:{ all -> 0x006d }
            return
        L_0x006d:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x006d }
            goto L_0x0071
        L_0x0070:
            throw r8
        L_0x0071:
            goto L_0x0070
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ieu.execute(java.lang.Runnable):void");
    }
}
