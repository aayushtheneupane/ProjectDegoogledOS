package p009io.grpc;

/* renamed from: io.grpc.StatusException */
public class StatusException extends Exception {
    private static final long serialVersionUID = -660954903976144640L;
    private final Status status;
    private final Metadata trailers = null;

    public StatusException(Status status2) {
        super(Status.formatThrowableMessage(status2), status2.getCause());
        this.status = status2;
    }

    public final Status getStatus() {
        return this.status;
    }

    public final Metadata getTrailers() {
        return this.trailers;
    }
}
