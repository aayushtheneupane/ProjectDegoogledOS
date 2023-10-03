package androidx.appcompat.mms;

public class MmsHttpException extends Exception {
    private final int mStatusCode;

    public MmsHttpException(int i) {
        this.mStatusCode = i;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public MmsHttpException(int i, String str) {
        super(str);
        this.mStatusCode = i;
    }

    public MmsHttpException(int i, Throwable th) {
        super(th);
        this.mStatusCode = i;
    }

    public MmsHttpException(int i, String str, Throwable th) {
        super(str, th);
        this.mStatusCode = i;
    }
}
