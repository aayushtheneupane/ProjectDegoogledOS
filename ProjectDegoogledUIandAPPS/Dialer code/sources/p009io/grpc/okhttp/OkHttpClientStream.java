package p009io.grpc.okhttp;

import okio.Buffer;
import p009io.grpc.internal.Http2ClientStream;

/* renamed from: io.grpc.okhttp.OkHttpClientStream */
class OkHttpClientStream extends Http2ClientStream {
    private static final Buffer EMPTY_BUFFER = new Buffer();

    /* renamed from: id */
    private volatile Integer f82id;

    /* renamed from: id */
    public Integer m96id() {
        return this.f82id;
    }
}
