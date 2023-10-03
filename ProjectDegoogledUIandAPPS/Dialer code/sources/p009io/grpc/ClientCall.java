package p009io.grpc;

/* renamed from: io.grpc.ClientCall */
public abstract class ClientCall<ReqT, RespT> {

    /* renamed from: io.grpc.ClientCall$Listener */
    public static abstract class Listener<T> {
    }

    public abstract void cancel(String str, Throwable th);

    public abstract void halfClose();

    public abstract void request(int i);

    public abstract void sendMessage(ReqT reqt);

    public abstract void start(Listener<RespT> listener, Metadata metadata);
}
