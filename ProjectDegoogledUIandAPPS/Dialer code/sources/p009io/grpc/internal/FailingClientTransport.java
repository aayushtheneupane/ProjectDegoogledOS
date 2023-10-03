package p009io.grpc.internal;

import com.google.common.base.MoreObjects;
import java.util.concurrent.Executor;
import p009io.grpc.Status;
import p009io.grpc.internal.ClientTransport;
import p009io.grpc.internal.KeepAliveManager;

/* renamed from: io.grpc.internal.FailingClientTransport */
class FailingClientTransport implements ClientTransport {
    final Status error;

    FailingClientTransport(Status status) {
        MoreObjects.checkArgument(!status.isOk(), "error must not be OK");
        this.error = status;
    }

    public void ping(final ClientTransport.PingCallback pingCallback, Executor executor) {
        executor.execute(new Runnable() {
            public void run() {
                ((KeepAliveManager.KeepAlivePingCallback) pingCallback).onFailure(FailingClientTransport.this.error.asException());
            }
        });
    }
}
