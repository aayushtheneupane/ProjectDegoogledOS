package p009io.grpc.internal;

import java.util.concurrent.Executor;

/* renamed from: io.grpc.internal.ClientTransport */
public interface ClientTransport {

    /* renamed from: io.grpc.internal.ClientTransport$PingCallback */
    public interface PingCallback {
    }

    void ping(PingCallback pingCallback, Executor executor);
}
