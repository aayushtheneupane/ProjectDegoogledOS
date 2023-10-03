package p009io.grpc.internal;

import p009io.grpc.Metadata;

/* renamed from: io.grpc.internal.Http2ClientStream */
public abstract class Http2ClientStream extends AbstractClientStream<Integer> {
    private static final Metadata.Key<Integer> HTTP2_STATUS = Metadata.Key.m93of(":status", HTTP_STATUS_LINE_MARSHALLER);
    private static final Metadata.AsciiMarshaller<Integer> HTTP_STATUS_LINE_MARSHALLER = new Metadata.AsciiMarshaller<Integer>() {
        public String toAsciiString(Object obj) {
            return ((Integer) obj).toString();
        }
    };
}
