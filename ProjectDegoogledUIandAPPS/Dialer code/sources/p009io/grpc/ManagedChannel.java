package p009io.grpc;

/* renamed from: io.grpc.ManagedChannel */
public abstract class ManagedChannel extends Channel {
    public abstract boolean isShutdown();

    public abstract ManagedChannel shutdown();
}
