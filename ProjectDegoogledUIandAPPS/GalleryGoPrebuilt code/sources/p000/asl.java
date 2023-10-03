package p000;

/* renamed from: asl */
/* compiled from: PG */
final class asl extends RuntimeException {
    public static final long serialVersionUID = -7530898992688511851L;

    public asl(Throwable th) {
        super("Unexpected exception thrown by non-Glide code", th);
    }
}
