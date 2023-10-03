package p009io.grpc;

/* renamed from: io.grpc.StatusRuntimeException */
public class StatusRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1950934672280720624L;
    private final Status status;
    private final Metadata trailers;

    public StatusRuntimeException(Status status2, Metadata metadata) {
        super(Status.formatThrowableMessage(status2), status2.getCause());
        this.status = status2;
        this.trailers = metadata;
    }

    public final Status getStatus() {
        return this.status;
    }

    public final Metadata getTrailers() {
        return this.trailers;
    }
}
