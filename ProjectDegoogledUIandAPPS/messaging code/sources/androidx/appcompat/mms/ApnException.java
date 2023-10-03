package androidx.appcompat.mms;

public class ApnException extends Exception {
    public ApnException() {
    }

    public ApnException(String str) {
        super(str);
    }

    public ApnException(Throwable th) {
        super(th);
    }

    public ApnException(String str, Throwable th) {
        super(str, th);
    }
}
