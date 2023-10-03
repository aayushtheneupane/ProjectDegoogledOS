package p009io.grpc.internal;

import p009io.grpc.ServerCall;

/* renamed from: io.grpc.internal.ServerCallImpl */
final class ServerCallImpl<ReqT, RespT> extends ServerCall<ReqT, RespT> {
    /* access modifiers changed from: private */
    public volatile boolean cancelled;

    /* renamed from: io.grpc.internal.ServerCallImpl$ServerStreamListenerImpl */
    static final class ServerStreamListenerImpl<ReqT> implements ServerStreamListener {
        public void onReady() {
            if (!null.cancelled) {
                throw null;
            }
        }
    }
}
