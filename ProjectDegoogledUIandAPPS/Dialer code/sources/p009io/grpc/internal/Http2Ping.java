package p009io.grpc.internal;

import com.google.common.base.Stopwatch;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import p009io.grpc.internal.ClientTransport;
import p009io.grpc.internal.KeepAliveManager;

/* renamed from: io.grpc.internal.Http2Ping */
public class Http2Ping {
    private static final Logger log = Logger.getLogger(Http2Ping.class.getName());
    private Map<ClientTransport.PingCallback, Executor> callbacks = new LinkedHashMap();
    private boolean completed;
    private final long data;
    private Throwable failureCause;
    private long roundTripTimeNanos;
    private final Stopwatch stopwatch;

    public Http2Ping(long j, Stopwatch stopwatch2) {
        this.data = j;
        this.stopwatch = stopwatch2;
    }

    private static void doExecute(Executor executor, Runnable runnable) {
        try {
            executor.execute(runnable);
        } catch (Throwable th) {
            log.log(Level.SEVERE, "Failed to execute PingCallback", th);
        }
    }

    public static void notifyFailed(final ClientTransport.PingCallback pingCallback, Executor executor, final Throwable th) {
        doExecute(executor, new Runnable() {
            public void run() {
                ((KeepAliveManager.KeepAlivePingCallback) ClientTransport.PingCallback.this).onFailure(r4);
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        doExecute(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addCallback(final p009io.grpc.internal.ClientTransport.PingCallback r4, java.util.concurrent.Executor r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.completed     // Catch:{ all -> 0x0025 }
            if (r0 != 0) goto L_0x000c
            java.util.Map<io.grpc.internal.ClientTransport$PingCallback, java.util.concurrent.Executor> r0 = r3.callbacks     // Catch:{ all -> 0x0025 }
            r0.put(r4, r5)     // Catch:{ all -> 0x0025 }
            monitor-exit(r3)     // Catch:{ all -> 0x0025 }
            return
        L_0x000c:
            java.lang.Throwable r0 = r3.failureCause     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x0018
            java.lang.Throwable r0 = r3.failureCause     // Catch:{ all -> 0x0025 }
            io.grpc.internal.Http2Ping$2 r1 = new io.grpc.internal.Http2Ping$2     // Catch:{ all -> 0x0025 }
            r1.<init>(r0)     // Catch:{ all -> 0x0025 }
            goto L_0x0020
        L_0x0018:
            long r0 = r3.roundTripTimeNanos     // Catch:{ all -> 0x0025 }
            io.grpc.internal.Http2Ping$1 r2 = new io.grpc.internal.Http2Ping$1     // Catch:{ all -> 0x0025 }
            r2.<init>(r0)     // Catch:{ all -> 0x0025 }
            r1 = r2
        L_0x0020:
            monitor-exit(r3)     // Catch:{ all -> 0x0025 }
            doExecute(r5, r1)
            return
        L_0x0025:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0025 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p009io.grpc.internal.Http2Ping.addCallback(io.grpc.internal.ClientTransport$PingCallback, java.util.concurrent.Executor):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        if (r3.hasNext() == false) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        r0 = r3.next();
        r0 = (p009io.grpc.internal.ClientTransport.PingCallback) r0.getKey();
        doExecute((java.util.concurrent.Executor) r0.getValue(), new p009io.grpc.internal.Http2Ping.C09432());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r3 = r0.entrySet().iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void failed(final java.lang.Throwable r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.completed     // Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r3)     // Catch:{ all -> 0x003c }
            return
        L_0x0007:
            r0 = 1
            r3.completed = r0     // Catch:{ all -> 0x003c }
            r3.failureCause = r4     // Catch:{ all -> 0x003c }
            java.util.Map<io.grpc.internal.ClientTransport$PingCallback, java.util.concurrent.Executor> r0 = r3.callbacks     // Catch:{ all -> 0x003c }
            r1 = 0
            r3.callbacks = r1     // Catch:{ all -> 0x003c }
            monitor-exit(r3)     // Catch:{ all -> 0x003c }
            java.util.Set r3 = r0.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x001a:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x003b
            java.lang.Object r0 = r3.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getValue()
            java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1
            java.lang.Object r0 = r0.getKey()
            io.grpc.internal.ClientTransport$PingCallback r0 = (p009io.grpc.internal.ClientTransport.PingCallback) r0
            io.grpc.internal.Http2Ping$2 r2 = new io.grpc.internal.Http2Ping$2
            r2.<init>(r4)
            doExecute(r1, r2)
            goto L_0x001a
        L_0x003b:
            return
        L_0x003c:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x003c }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p009io.grpc.internal.Http2Ping.failed(java.lang.Throwable):void");
    }
}
