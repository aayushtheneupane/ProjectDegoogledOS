package p009io.grpc.stub;

import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import p009io.grpc.ClientCall;
import p009io.grpc.Metadata;
import p009io.grpc.Status;
import p009io.grpc.StatusException;
import p009io.grpc.StatusRuntimeException;

/* renamed from: io.grpc.stub.ClientCalls */
public class ClientCalls {
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(ClientCalls.class.getName());

    /* renamed from: io.grpc.stub.ClientCalls$GrpcFuture */
    private static class GrpcFuture<RespT> extends AbstractFuture<RespT> {
        private final ClientCall<?, RespT> call;

        GrpcFuture(ClientCall<?, RespT> clientCall) {
            this.call = clientCall;
        }

        /* access modifiers changed from: protected */
        public void interruptTask() {
            this.call.cancel("GrpcFuture was cancelled", (Throwable) null);
        }

        /* access modifiers changed from: protected */
        public boolean set(RespT respt) {
            return super.set(respt);
        }

        /* access modifiers changed from: protected */
        public boolean setException(Throwable th) {
            return super.setException(th);
        }
    }

    /* renamed from: io.grpc.stub.ClientCalls$ThreadlessExecutor */
    private static class ThreadlessExecutor implements Executor {
        private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue();

        /* synthetic */ ThreadlessExecutor(C09551 r1) {
        }

        public void execute(Runnable runnable) {
            this.queue.add(runnable);
        }

        public void waitAndDrain() throws InterruptedException {
            Runnable take = this.queue.take();
            while (take != null) {
                try {
                    take.run();
                } catch (Throwable th) {
                    ClientCalls.log.log(Level.WARNING, "Runnable threw exception", th);
                }
                take = (Runnable) this.queue.poll();
            }
        }
    }

    /* renamed from: io.grpc.stub.ClientCalls$UnaryStreamToFuture */
    private static class UnaryStreamToFuture<RespT> extends ClientCall.Listener<RespT> {
        private final GrpcFuture<RespT> responseFuture;

        public UnaryStreamToFuture(GrpcFuture<RespT> grpcFuture) {
            this.responseFuture = grpcFuture;
        }
    }

    private ClientCalls() {
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [io.grpc.MethodDescriptor, io.grpc.MethodDescriptor<ReqT, RespT>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <ReqT, RespT> RespT blockingUnaryCall(p009io.grpc.Channel r2, p009io.grpc.MethodDescriptor<ReqT, RespT> r3, p009io.grpc.CallOptions r4, ReqT r5) {
        /*
            io.grpc.stub.ClientCalls$ThreadlessExecutor r0 = new io.grpc.stub.ClientCalls$ThreadlessExecutor
            r1 = 0
            r0.<init>(r1)
            io.grpc.CallOptions r4 = r4.withExecutor(r0)
            io.grpc.ClientCall r2 = r2.newCall(r3, r4)
            com.google.common.util.concurrent.ListenableFuture r3 = futureUnaryCall(r2, r5)     // Catch:{ all -> 0x0034 }
        L_0x0012:
            boolean r4 = r3.isDone()     // Catch:{ all -> 0x0034 }
            if (r4 != 0) goto L_0x002f
            r0.waitAndDrain()     // Catch:{ InterruptedException -> 0x001c }
            goto L_0x0012
        L_0x001c:
            r3 = move-exception
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0034 }
            r4.interrupt()     // Catch:{ all -> 0x0034 }
            io.grpc.Status r4 = p009io.grpc.Status.CANCELLED     // Catch:{ all -> 0x0034 }
            io.grpc.Status r3 = r4.withCause(r3)     // Catch:{ all -> 0x0034 }
            io.grpc.StatusRuntimeException r3 = r3.asRuntimeException()     // Catch:{ all -> 0x0034 }
            throw r3     // Catch:{ all -> 0x0034 }
        L_0x002f:
            java.lang.Object r2 = getUnchecked(r3)     // Catch:{ all -> 0x0034 }
            return r2
        L_0x0034:
            r3 = move-exception
            r2.cancel(r1, r3)
            boolean r2 = r3 instanceof java.lang.RuntimeException
            if (r2 == 0) goto L_0x0040
            java.lang.RuntimeException r3 = (java.lang.RuntimeException) r3
            r2 = r3
            goto L_0x0045
        L_0x0040:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            r2.<init>(r3)
        L_0x0045:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p009io.grpc.stub.ClientCalls.blockingUnaryCall(io.grpc.Channel, io.grpc.MethodDescriptor, io.grpc.CallOptions, java.lang.Object):java.lang.Object");
    }

    public static <ReqT, RespT> ListenableFuture<RespT> futureUnaryCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt) {
        GrpcFuture grpcFuture = new GrpcFuture(clientCall);
        clientCall.start(new UnaryStreamToFuture(grpcFuture), new Metadata());
        clientCall.request(2);
        try {
            clientCall.sendMessage(reqt);
            clientCall.halfClose();
            return grpcFuture;
        } catch (Throwable th) {
            clientCall.cancel((String) null, th);
            throw (th instanceof RuntimeException ? th : new RuntimeException(th));
        }
    }

    private static <V> V getUnchecked(Future<V> future) {
        StatusRuntimeException asRuntimeException;
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw Status.CANCELLED.withCause(e).asRuntimeException();
        } catch (ExecutionException e2) {
            Throwable th = e2;
            while (true) {
                if (th != null) {
                    if (!(th instanceof StatusException)) {
                        if (th instanceof StatusRuntimeException) {
                            StatusRuntimeException statusRuntimeException = (StatusRuntimeException) th;
                            asRuntimeException = new StatusRuntimeException(statusRuntimeException.getStatus(), statusRuntimeException.getTrailers());
                            break;
                        }
                        th = th.getCause();
                    } else {
                        StatusException statusException = (StatusException) th;
                        asRuntimeException = new StatusRuntimeException(statusException.getStatus(), statusException.getTrailers());
                        break;
                    }
                } else {
                    asRuntimeException = Status.UNKNOWN.withCause(e2).asRuntimeException();
                    break;
                }
            }
            throw asRuntimeException;
        }
    }
}
