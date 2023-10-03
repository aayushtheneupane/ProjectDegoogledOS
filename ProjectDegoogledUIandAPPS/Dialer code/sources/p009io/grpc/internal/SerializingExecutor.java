package p009io.grpc.internal;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.MoreObjects;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

/* renamed from: io.grpc.internal.SerializingExecutor */
public final class SerializingExecutor implements Executor {
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(SerializingExecutor.class.getName());
    private final Executor executor;
    /* access modifiers changed from: private */
    public final Object internalLock = new Object(this) {
        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("SerializingExecutor lock: ");
            outline13.append(super.toString());
            return outline13.toString();
        }
    };
    /* access modifiers changed from: private */
    public boolean isThreadScheduled = false;
    private final TaskRunner taskRunner = new TaskRunner((C09491) null);
    /* access modifiers changed from: private */
    public final Queue<Runnable> waitQueue = new ArrayDeque(4);

    /* renamed from: io.grpc.internal.SerializingExecutor$TaskRunner */
    private class TaskRunner implements Runnable {
        /* synthetic */ TaskRunner(C09491 r2) {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            r3.run();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r8 = this;
            L_0x0000:
                r0 = 0
                r1 = 1
                io.grpc.internal.SerializingExecutor r2 = p009io.grpc.internal.SerializingExecutor.this     // Catch:{ all -> 0x004f }
                java.lang.Object r2 = r2.internalLock     // Catch:{ all -> 0x004f }
                monitor-enter(r2)     // Catch:{ all -> 0x004f }
                io.grpc.internal.SerializingExecutor r3 = p009io.grpc.internal.SerializingExecutor.this     // Catch:{ all -> 0x004c }
                boolean r3 = r3.isThreadScheduled     // Catch:{ all -> 0x004c }
                com.google.common.base.MoreObjects.checkState(r3)     // Catch:{ all -> 0x004c }
                io.grpc.internal.SerializingExecutor r3 = p009io.grpc.internal.SerializingExecutor.this     // Catch:{ all -> 0x004c }
                java.util.Queue r3 = r3.waitQueue     // Catch:{ all -> 0x004c }
                java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x004c }
                java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch:{ all -> 0x004c }
                if (r3 != 0) goto L_0x002b
                io.grpc.internal.SerializingExecutor r3 = p009io.grpc.internal.SerializingExecutor.this     // Catch:{ all -> 0x004c }
                boolean unused = r3.isThreadScheduled = r0     // Catch:{ all -> 0x004c }
                monitor-exit(r2)     // Catch:{ all -> 0x0027 }
                return
            L_0x0027:
                r1 = move-exception
                r3 = r1
                r1 = r0
                goto L_0x004d
            L_0x002b:
                monitor-exit(r2)     // Catch:{ all -> 0x004c }
                r3.run()     // Catch:{ RuntimeException -> 0x0030 }
                goto L_0x0000
            L_0x0030:
                r2 = move-exception
                java.util.logging.Logger r4 = p009io.grpc.internal.SerializingExecutor.log     // Catch:{ all -> 0x004f }
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch:{ all -> 0x004f }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x004f }
                r6.<init>()     // Catch:{ all -> 0x004f }
                java.lang.String r7 = "Exception while executing runnable "
                r6.append(r7)     // Catch:{ all -> 0x004f }
                r6.append(r3)     // Catch:{ all -> 0x004f }
                java.lang.String r3 = r6.toString()     // Catch:{ all -> 0x004f }
                r4.log(r5, r3, r2)     // Catch:{ all -> 0x004f }
                goto L_0x0000
            L_0x004c:
                r3 = move-exception
            L_0x004d:
                monitor-exit(r2)     // Catch:{ all -> 0x004c }
                throw r3     // Catch:{ all -> 0x004f }
            L_0x004f:
                r2 = move-exception
                if (r1 == 0) goto L_0x0063
                io.grpc.internal.SerializingExecutor r1 = p009io.grpc.internal.SerializingExecutor.this
                java.lang.Object r1 = r1.internalLock
                monitor-enter(r1)
                io.grpc.internal.SerializingExecutor r8 = p009io.grpc.internal.SerializingExecutor.this     // Catch:{ all -> 0x0060 }
                boolean unused = r8.isThreadScheduled = r0     // Catch:{ all -> 0x0060 }
                monitor-exit(r1)     // Catch:{ all -> 0x0060 }
                goto L_0x0063
            L_0x0060:
                r8 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0060 }
                throw r8
            L_0x0063:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: p009io.grpc.internal.SerializingExecutor.TaskRunner.run():void");
        }
    }

    public SerializingExecutor(Executor executor2) {
        MoreObjects.checkNotNull(executor2, "'executor' must not be null.");
        this.executor = executor2;
    }

    public void execute(Runnable runnable) {
        boolean z;
        MoreObjects.checkNotNull(runnable, "'r' must not be null.");
        synchronized (this.internalLock) {
            this.waitQueue.add(runnable);
            z = true;
            if (!this.isThreadScheduled) {
                this.isThreadScheduled = true;
            } else {
                z = false;
            }
        }
        if (z) {
            try {
                this.executor.execute(this.taskRunner);
            } catch (Throwable th) {
                synchronized (this.internalLock) {
                    this.isThreadScheduled = false;
                    throw th;
                }
            }
        }
    }
}
