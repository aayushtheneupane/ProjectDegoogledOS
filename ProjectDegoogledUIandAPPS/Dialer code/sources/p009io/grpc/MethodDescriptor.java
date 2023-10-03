package p009io.grpc;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.MoreObjects;

/* renamed from: io.grpc.MethodDescriptor */
public class MethodDescriptor<ReqT, RespT> {
    private final String fullMethodName;
    private final Marshaller<ReqT> requestMarshaller;
    private final Marshaller<RespT> responseMarshaller;
    private final MethodType type;

    /* renamed from: io.grpc.MethodDescriptor$Marshaller */
    public interface Marshaller<T> {
    }

    /* renamed from: io.grpc.MethodDescriptor$MethodType */
    public enum MethodType {
        UNARY,
        CLIENT_STREAMING,
        SERVER_STREAMING,
        BIDI_STREAMING,
        UNKNOWN
    }

    private MethodDescriptor(MethodType methodType, String str, Marshaller<ReqT> marshaller, Marshaller<RespT> marshaller2, boolean z) {
        MoreObjects.checkNotNull(methodType, "type");
        this.type = methodType;
        MoreObjects.checkNotNull(str, "fullMethodName");
        this.fullMethodName = str;
        MoreObjects.checkNotNull(marshaller, "requestMarshaller");
        this.requestMarshaller = marshaller;
        MoreObjects.checkNotNull(marshaller2, "responseMarshaller");
        this.responseMarshaller = marshaller2;
    }

    public static <RequestT, ResponseT> MethodDescriptor<RequestT, ResponseT> create(MethodType methodType, String str, Marshaller<RequestT> marshaller, Marshaller<ResponseT> marshaller2) {
        return new MethodDescriptor(methodType, str, marshaller, marshaller2, false);
    }

    public static String generateFullMethodName(String str, String str2) {
        return GeneratedOutlineSupport.outline9(str, "/", str2);
    }

    public String getFullMethodName() {
        return this.fullMethodName;
    }
}
