package p009io.grpc.internal;

import p009io.grpc.Status;

/* renamed from: io.grpc.internal.ManagedClientTransport */
public interface ManagedClientTransport extends ClientTransport, WithLogId {

    /* renamed from: io.grpc.internal.ManagedClientTransport$Listener */
    public interface Listener {
        void transportInUse(boolean z);

        void transportShutdown(Status status);

        void transportTerminated();
    }

    void shutdownNow(Status status);
}
