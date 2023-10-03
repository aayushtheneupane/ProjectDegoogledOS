package p009io.grpc;

import android.support.p002v7.appcompat.R$style;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.MoreObjects;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import p009io.grpc.Metadata;

/* renamed from: io.grpc.Status */
public final class Status {
    public static final Status CANCELLED = Code.CANCELLED.toStatus();
    public static final Metadata.Key<Status> CODE_KEY = Metadata.Key.m93of("grpc-status", new StatusCodeMarshaller((C09351) null));
    public static final Status DEADLINE_EXCEEDED = Code.DEADLINE_EXCEEDED.toStatus();
    public static final Status INTERNAL = Code.INTERNAL.toStatus();
    public static final Metadata.Key<String> MESSAGE_KEY = Metadata.Key.m93of("grpc-message", STATUS_MESSAGE_MARSHALLER);
    public static final Status PERMISSION_DENIED = Code.PERMISSION_DENIED.toStatus();
    public static final Status RESOURCE_EXHAUSTED = Code.RESOURCE_EXHAUSTED.toStatus();
    /* access modifiers changed from: private */
    public static final List<Status> STATUS_LIST;
    private static final Metadata.AsciiMarshaller<String> STATUS_MESSAGE_MARSHALLER = new Metadata.AsciiMarshaller<String>() {
        public String toAsciiString(Object obj) {
            String str = (String) obj;
            StringBuilder sb = new StringBuilder(str.length());
            for (byte b : str.getBytes(Charset.forName("UTF-8"))) {
                if ((b < 32 || b >= 37) && (b <= 37 || b >= 126)) {
                    sb.append(String.format("%%%02X", new Object[]{Byte.valueOf(b)}));
                } else {
                    sb.append((char) b);
                }
            }
            return sb.toString();
        }
    };
    public static final Status UNAUTHENTICATED = Code.UNAUTHENTICATED.toStatus();
    public static final Status UNAVAILABLE = Code.UNAVAILABLE.toStatus();
    public static final Status UNKNOWN = Code.UNKNOWN.toStatus();
    private final Throwable cause;
    private final Code code;
    private final String description;

    /* renamed from: io.grpc.Status$Code */
    public enum Code {
        OK(0),
        CANCELLED(1),
        UNKNOWN(2),
        INVALID_ARGUMENT(3),
        DEADLINE_EXCEEDED(4),
        NOT_FOUND(5),
        ALREADY_EXISTS(6),
        PERMISSION_DENIED(7),
        RESOURCE_EXHAUSTED(8),
        FAILED_PRECONDITION(9),
        ABORTED(10),
        OUT_OF_RANGE(11),
        UNIMPLEMENTED(12),
        INTERNAL(13),
        UNAVAILABLE(14),
        DATA_LOSS(15),
        UNAUTHENTICATED(16);
        
        private final int value;
        /* access modifiers changed from: private */
        public final String valueAscii;

        private Code(int i) {
            this.value = i;
            this.valueAscii = Integer.toString(i);
        }

        public Status toStatus() {
            return (Status) Status.STATUS_LIST.get(this.value);
        }

        public int value() {
            return this.value;
        }
    }

    /* renamed from: io.grpc.Status$StatusCodeMarshaller */
    private static class StatusCodeMarshaller implements Metadata.AsciiMarshaller<Status> {
        /* synthetic */ StatusCodeMarshaller(C09351 r1) {
        }

        public String toAsciiString(Object obj) {
            return ((Status) obj).getCode().valueAscii;
        }
    }

    static {
        TreeMap treeMap = new TreeMap();
        Code[] values = Code.values();
        int length = values.length;
        int i = 0;
        while (i < length) {
            Code code2 = values[i];
            Status status = (Status) treeMap.put(Integer.valueOf(code2.value()), new Status(code2, (String) null, (Throwable) null));
            if (status == null) {
                i++;
            } else {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Code value duplication between ");
                outline13.append(status.code.name());
                outline13.append(" & ");
                outline13.append(code2.name());
                throw new IllegalStateException(outline13.toString());
            }
        }
        STATUS_LIST = Collections.unmodifiableList(new ArrayList(treeMap.values()));
        Code.OK.toStatus();
        Code.INVALID_ARGUMENT.toStatus();
        Code.NOT_FOUND.toStatus();
        Code.ALREADY_EXISTS.toStatus();
        Code.FAILED_PRECONDITION.toStatus();
        Code.ABORTED.toStatus();
        Code.OUT_OF_RANGE.toStatus();
        Code.UNIMPLEMENTED.toStatus();
        Code.DATA_LOSS.toStatus();
    }

    private Status(Code code2, String str, Throwable th) {
        if (code2 != null) {
            this.code = code2;
            this.description = str;
            this.cause = th;
            return;
        }
        throw new NullPointerException();
    }

    static String formatThrowableMessage(Status status) {
        if (status.description == null) {
            return status.code.toString();
        }
        return status.code + ": " + status.description;
    }

    public StatusException asException() {
        return new StatusException(this);
    }

    public StatusRuntimeException asRuntimeException() {
        return new StatusRuntimeException(this, (Metadata) null);
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Throwable getCause() {
        return this.cause;
    }

    public Code getCode() {
        return this.code;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean isOk() {
        return Code.OK == this.code;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        stringHelper.add("code", (Object) this.code.name());
        stringHelper.add("description", (Object) this.description);
        stringHelper.add("cause", (Object) this.cause);
        return stringHelper.toString();
    }

    public Status withCause(Throwable th) {
        if (R$style.equal(this.cause, th)) {
            return this;
        }
        return new Status(this.code, this.description, th);
    }

    public Status withDescription(String str) {
        if (R$style.equal(this.description, str)) {
            return this;
        }
        return new Status(this.code, str, this.cause);
    }
}
