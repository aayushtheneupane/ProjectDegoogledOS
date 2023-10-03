package p009io.grpc.okhttp;

import java.util.logging.Logger;
import p009io.grpc.okhttp.internal.framed.ErrorCode;
import p009io.grpc.okhttp.internal.framed.FrameWriter;

/* renamed from: io.grpc.okhttp.AsyncFrameWriter */
class AsyncFrameWriter implements FrameWriter {
    private static final Logger log = Logger.getLogger(OkHttpClientTransport.class.getName());

    public void close() {
        throw null;
    }

    public void goAway(int i, ErrorCode errorCode, byte[] bArr) {
        throw null;
    }

    public void ping(boolean z, int i, int i2) {
        throw null;
    }
}
