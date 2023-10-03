package p009io.grpc;

import p009io.grpc.ClientCall;

/* renamed from: io.grpc.ForwardingClientCall */
public abstract class ForwardingClientCall<ReqT, RespT> extends ClientCall<ReqT, RespT> {

    /* renamed from: io.grpc.ForwardingClientCall$SimpleForwardingClientCall */
    public static abstract class SimpleForwardingClientCall<ReqT, RespT> extends ForwardingClientCall<ReqT, RespT> {
        private final ClientCall<ReqT, RespT> delegate;

        protected SimpleForwardingClientCall(ClientCall<ReqT, RespT> clientCall) {
            this.delegate = clientCall;
        }

        /* access modifiers changed from: protected */
        public ClientCall<ReqT, RespT> delegate() {
            return this.delegate;
        }
    }

    public void cancel(String str, Throwable th) {
        delegate().cancel(str, th);
    }

    /* access modifiers changed from: protected */
    public abstract ClientCall<ReqT, RespT> delegate();

    public void halfClose() {
        delegate().halfClose();
    }

    public void request(int i) {
        delegate().request(i);
    }

    public void sendMessage(ReqT reqt) {
        delegate().sendMessage(reqt);
    }

    public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
        delegate().start(listener, metadata);
    }
}
